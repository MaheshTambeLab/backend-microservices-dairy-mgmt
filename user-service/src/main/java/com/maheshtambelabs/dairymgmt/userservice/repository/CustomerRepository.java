package com.maheshtambelabs.dairymgmt.userservice.repository;

import com.maheshtambelabs.dairymgmt.userservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    List<Customer> findAllByOrderByName();
    List<Customer> findByEmailContainingIgnoreCase(String email);
}
