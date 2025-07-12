package com.maheshtambelabs.dairymgmt.userservice.dto;

import com.maheshtambelabs.dairymgmt.userservice.model.UserType;
import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private UserType userType;
}
