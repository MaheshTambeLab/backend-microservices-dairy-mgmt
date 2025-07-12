package com.maheshtambelabs.dairymgmt.userservice.repository;

import com.maheshtambelabs.dairymgmt.userservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    List<Subscription> findByCustomer_UserId(String customerId);
    List<Subscription> findByNextDeliveryDate(LocalDate date);
    List<Subscription> findByCustomer_UserIdAndIsActive(String customerId, boolean isActive);
}
