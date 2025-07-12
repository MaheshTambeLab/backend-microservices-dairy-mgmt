package com.maheshtambelabs.dairymgmt.userservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends UserDTO {
    private List<SubscriptionDTO> subscriptions;
}
