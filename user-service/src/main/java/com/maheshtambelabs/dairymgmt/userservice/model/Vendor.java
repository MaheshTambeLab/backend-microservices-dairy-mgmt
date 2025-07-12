package com.maheshtambelabs.dairymgmt.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vendors")
@PrimaryKeyJoinColumn(name = "user_id")
public class Vendor extends User {
    private String farmName;
    private String farmAddress;
    private String bankAccountNumber;
    private String ifscCode;
    private String gstNumber;

    @ElementCollection
    private List<String> productIds = new ArrayList<>();

    @Override
    public UserType getUserType() {
        return UserType.VENDOR;
    }

    public void addProductReference(String productId) {
        if (!this.productIds.contains(productId)) {
            this.productIds.add(productId);
        }
    }

    public void removeProductReference(String productId) {
        this.productIds.remove(productId);
    }

    public void updateFarmDetails(String farmName, String farmAddress) {
        if (farmName != null) this.farmName = farmName;
        if (farmAddress != null) this.farmAddress = farmAddress;
    }

    public void updateBankDetails(String bankAccountNumber, String ifscCode) {
        if (bankAccountNumber != null) this.bankAccountNumber = bankAccountNumber;
        if (ifscCode != null) this.ifscCode = ifscCode;
    }
}
