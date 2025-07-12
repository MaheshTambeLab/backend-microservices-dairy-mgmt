package com.maheshtambelabs.dairymgmt.userservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class VendorDTO extends UserDTO {
    private String farmName;
    private String farmAddress;
    private String bankAccountNumber;
    private String ifscCode;
    private String gstNumber;
    private List<ProductDTO> inventory;
}
