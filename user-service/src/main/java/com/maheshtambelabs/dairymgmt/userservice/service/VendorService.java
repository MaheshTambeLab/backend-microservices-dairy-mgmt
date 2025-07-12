package com.maheshtambelabs.dairymgmt.userservice.service;

import com.maheshtambelabs.dairymgmt.userservice.dto.VendorDTO;
import com.maheshtambelabs.dairymgmt.userservice.model.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
    Vendor saveVendor(Vendor vendor);

    List<Vendor> getAllVendors();

    Optional<Vendor> getVendorById(String id);

    Vendor updateVendor(String id, Vendor vendorDetails);

    void deleteVendor(String id);

    Vendor addProductReference(String vendorId, String productId);

    void removeProductReference(String vendorId, String productId);

    Vendor updateFarmDetails(String vendorId, String farmName, String farmAddress);

    Vendor updateBankDetails(String vendorId, String bankAccountNumber, String ifscCode);

    /**
     * Get vendor with their product details
     * @param vendorId the ID of the vendor
     * @return VendorDTO with product details if found, empty otherwise
     */
    Optional<VendorDTO> getVendorWithProducts(String vendorId);
}
