package com.maheshtambelabs.dairymgmt.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Customer extends User {
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public UserType getUserType() {
        return UserType.CUSTOMER;
    }

    public void subscribe(Subscription subscription) {
        subscription.setCustomer(this);
        this.subscriptions.add(subscription);
    }

    public void unsubscribe(String subscriptionId) {
        this.subscriptions.removeIf(s -> s.getSubscriptionId().equals(subscriptionId));
    }
}
