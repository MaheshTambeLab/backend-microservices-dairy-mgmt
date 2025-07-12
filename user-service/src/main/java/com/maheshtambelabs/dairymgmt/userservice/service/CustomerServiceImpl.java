package com.maheshtambelabs.dairymgmt.userservice.service;

import com.maheshtambelabs.dairymgmt.userservice.model.Customer;
import com.maheshtambelabs.dairymgmt.userservice.model.Subscription;
import com.maheshtambelabs.dairymgmt.userservice.model.DeliveryFrequency;
import com.maheshtambelabs.dairymgmt.userservice.repository.CustomerRepository;
import com.maheshtambelabs.dairymgmt.userservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                             SubscriptionRepository subscriptionRepository,
                             UserService userService) {
        this.customerRepository = customerRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        if (userService.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAllByOrderByName();
    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer updateCustomer(String id, Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.update(customerDetails);
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Subscription subscribe(String customerId, Subscription subscription) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    customer.subscribe(subscription);
                    subscription.calculateNextDeliveryDate();
                    return subscriptionRepository.save(subscription);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
    }

    @Override
    public void unsubscribe(String customerId, String subscriptionId) {
        customerRepository.findById(customerId).ifPresent(customer -> {
            customer.unsubscribe(subscriptionId);
            subscriptionRepository.deleteById(subscriptionId);
        });
    }

    @Override
    public List<Subscription> getCustomerSubscriptions(String customerId) {
        return subscriptionRepository.findByCustomer_UserId(customerId);
    }

    @Override
    public Subscription updateSubscription(String customerId, String subscriptionId, int quantity, String frequency) {
        return subscriptionRepository.findById(subscriptionId)
                .filter(sub -> sub.getCustomer().getUserId().equals(customerId))
                .map(subscription -> {
                    DeliveryFrequency freq = DeliveryFrequency.valueOf(frequency);
                    subscription.update(quantity, freq);
                    return subscriptionRepository.save(subscription);
                })
                .orElseThrow(() -> new RuntimeException("Subscription not found or does not belong to customer"));
    }
}
