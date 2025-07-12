package com.maheshtambelabs.dairymgmt.userservice.dto;

import com.maheshtambelabs.dairymgmt.userservice.model.DeliveryFrequency;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionDTO {
    private String subscriptionId;
    private String productId;
    private String productName;
    private int quantity;
    private DeliveryFrequency frequency;
    private LocalDate startDate;
    private LocalDate nextDeliveryDate;
    private boolean isActive;
    private long daysUntilNextDelivery;
}
