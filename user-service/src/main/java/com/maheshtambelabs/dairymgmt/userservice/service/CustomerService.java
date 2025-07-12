package com.maheshtambelabs.dairymgmt.userservice.service;

import com.maheshtambelabs.dairymgmt.userservice.model.Customer;
import com.maheshtambelabs.dairymgmt.userservice.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(String id);
    Customer updateCustomer(String id, Customer customerDetails);
    void deleteCustomer(String id);
    Subscription subscribe(String customerId, Subscription subscription);
    void unsubscribe(String customerId, String subscriptionId);
    List<Subscription> getCustomerSubscriptions(String customerId);
    Subscription updateSubscription(String customerId, String subscriptionId, int quantity, String frequency);
}
