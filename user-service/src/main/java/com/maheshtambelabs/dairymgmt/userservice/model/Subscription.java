package com.maheshtambelabs.dairymgmt.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String subscriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String productId;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private DeliveryFrequency frequency;

    private LocalDate startDate;
    private LocalDate nextDeliveryDate;
    private boolean isActive = true;

    public void update(int quantity, DeliveryFrequency frequency) {
        if (quantity > 0) this.quantity = quantity;
        if (frequency != null) this.frequency = frequency;
        calculateNextDeliveryDate();
    }

    public void calculateNextDeliveryDate() {
        if (startDate == null) {
            startDate = LocalDate.now();
        }

        LocalDate today = LocalDate.now();
        LocalDate nextDate = startDate;

        while (!nextDate.isAfter(today)) {
            switch (frequency) {
                case DAILY:
                    nextDate = nextDate.plusDays(1);
                    break;
                case WEEKLY:
                    nextDate = nextDate.plusWeeks(1);
                    break;
                case ALTERNATE_DAY:
                    nextDate = nextDate.plusDays(2);
                    break;
            }
        }

        this.nextDeliveryDate = nextDate;
    }

    public long getDaysUntilNextDelivery() {
        return LocalDate.now().until(nextDeliveryDate, ChronoUnit.DAYS);
    }
}
