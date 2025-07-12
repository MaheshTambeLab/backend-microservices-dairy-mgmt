package com.maheshtambelabs.dairymgmt.userservice.util;

import com.maheshtambelabs.dairymgmt.userservice.dto.*;
import com.maheshtambelabs.dairymgmt.userservice.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DTOConverter {

    public static UserDTO convertToUserDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setUserType(user.getUserType());

        return dto;
    }

    public static CustomerDTO convertToCustomerDTO(Customer customer) {
        if (customer == null) return null;

        CustomerDTO dto = new CustomerDTO();
        // Copy all fields from UserDTO
        UserDTO userDTO = convertToUserDTO(customer);
        dto.setUserId(userDTO.getUserId());
        dto.setName(userDTO.getName());
        dto.setEmail(userDTO.getEmail());
        dto.setPhone(userDTO.getPhone());
        dto.setAddress(userDTO.getAddress());
        dto.setUserType(userDTO.getUserType());

        // Convert subscriptions if needed
        if (customer.getSubscriptions() != null) {
            dto.setSubscriptions(customer.getSubscriptions().stream()
                    .map(DTOConverter::convertToSubscriptionDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static VendorDTO convertToVendorDTO(Vendor vendor) {
        if (vendor == null) return null;

        VendorDTO dto = new VendorDTO();
        // Copy all fields from UserDTO
        UserDTO userDTO = convertToUserDTO(vendor);
        dto.setUserId(userDTO.getUserId());
        dto.setName(userDTO.getName());
        dto.setEmail(userDTO.getEmail());
        dto.setPhone(userDTO.getPhone());
        dto.setAddress(userDTO.getAddress());
        dto.setUserType(userDTO.getUserType());

        // Set vendor-specific fields
        dto.setFarmName(vendor.getFarmName());
        dto.setFarmAddress(vendor.getFarmAddress());
        dto.setBankAccountNumber(vendor.getBankAccountNumber());
        dto.setIfscCode(vendor.getIfscCode());
        dto.setGstNumber(vendor.getGstNumber());

        // Note: Inventory should be set from the ProductService
        // The inventory field will be populated by the service layer

        return dto;
    }

    public static SubscriptionDTO convertToSubscriptionDTO(Subscription subscription) {
        if (subscription == null) return null;

        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setSubscriptionId(subscription.getSubscriptionId());
        dto.setProductId(subscription.getProductId());
        dto.setQuantity(subscription.getQuantity());
        dto.setFrequency(subscription.getFrequency());
        dto.setStartDate(subscription.getStartDate());
        dto.setNextDeliveryDate(subscription.getNextDeliveryDate());
        dto.setActive(subscription.isActive());
        dto.setDaysUntilNextDelivery(subscription.getDaysUntilNextDelivery());

        return dto;
    }
}