package com.maheshtambelabs.dairymgmt.userservice.controller;

import com.maheshtambelabs.dairymgmt.userservice.dto.CustomerDTO;
import com.maheshtambelabs.dairymgmt.userservice.dto.SubscriptionDTO;
import com.maheshtambelabs.dairymgmt.userservice.model.Customer;
import com.maheshtambelabs.dairymgmt.userservice.model.Subscription;
import com.maheshtambelabs.dairymgmt.userservice.service.CustomerService;
import com.maheshtambelabs.dairymgmt.userservice.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody Customer customer) {
        return DTOConverter.convertToCustomerDTO(customerService.saveCustomer(customer));
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(DTOConverter::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id)
                .map(DTOConverter::convertToCustomerDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
            return ResponseEntity.ok(DTOConverter.convertToCustomerDTO(updatedCustomer));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{customerId}/subscribe")
    public ResponseEntity<SubscriptionDTO> subscribe(
            @PathVariable String customerId,
            @RequestBody Subscription subscription) {
        try {
            Subscription createdSubscription = customerService.subscribe(customerId, subscription);
            return ResponseEntity.ok(DTOConverter.convertToSubscriptionDTO(createdSubscription));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{customerId}/unsubscribe/{subscriptionId}")
    public ResponseEntity<Void> unsubscribe(
            @PathVariable String customerId,
            @PathVariable String subscriptionId) {
        customerService.unsubscribe(customerId, subscriptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{customerId}/subscriptions")
    public List<SubscriptionDTO> getCustomerSubscriptions(@PathVariable String customerId) {
        return customerService.getCustomerSubscriptions(customerId).stream()
                .map(DTOConverter::convertToSubscriptionDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{customerId}/subscriptions/{subscriptionId}")
    public ResponseEntity<SubscriptionDTO> updateSubscription(
            @PathVariable String customerId,
            @PathVariable String subscriptionId,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) String frequency) {
        try {
            if (quantity == null && frequency == null) {
                return ResponseEntity.badRequest().build();
            }

            if (quantity == null) quantity = -1; // Indicates no change
            if (frequency == null) frequency = ""; // Indicates no change

            Subscription updatedSubscription = customerService.updateSubscription(
                    customerId, subscriptionId, quantity, frequency);
            return ResponseEntity.ok(DTOConverter.convertToSubscriptionDTO(updatedSubscription));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
