package com.maheshtambelabs.dairymgmt.userservice.service;

import com.maheshtambelabs.dairymgmt.userservice.dto.ProductDTO;
import com.maheshtambelabs.dairymgmt.userservice.dto.VendorDTO;
import com.maheshtambelabs.dairymgmt.userservice.model.Vendor;
import com.maheshtambelabs.dairymgmt.userservice.repository.VendorRepository;
import com.maheshtambelabs.dairymgmt.userservice.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final UserService userService;
    private final ProductService productService;


    @Override
    public Vendor saveVendor(Vendor vendor) {
        if (userService.existsByEmail(vendor.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        return vendorRepository.save(vendor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAllByOrderByFarmName();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vendor> getVendorById(String id) {
        return vendorRepository.findById(id);
    }

    @Override
    public Vendor updateVendor(String id, Vendor vendorDetails) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    vendor.update(vendorDetails);
                    return vendorRepository.save(vendor);
                })
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));
    }

    @Override
    public void deleteVendor(String id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public Vendor updateFarmDetails(String vendorId, String farmName, String farmAddress) {
        return vendorRepository.findById(vendorId)
                .map(vendor -> {
                    vendor.updateFarmDetails(farmName, farmAddress);
                    return vendorRepository.save(vendor);
                })
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + vendorId));
    }

    @Override
    public Vendor updateBankDetails(String vendorId, String bankAccountNumber, String ifscCode) {
        return vendorRepository.findById(vendorId)
                .map(vendor -> {
                    vendor.updateBankDetails(bankAccountNumber, ifscCode);
                    return vendorRepository.save(vendor);
                })
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + vendorId));
    }

    @Override
    public Vendor addProductReference(String vendorId, String productId) {
        // First verify the product exists
        productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        return vendorRepository.findById(vendorId)
                .map(vendor -> {
                    vendor.addProductReference(productId);
                    return vendorRepository.save(vendor);
                })
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + vendorId));
    }

    @Override
    public void removeProductReference(String vendorId, String productId) {
        vendorRepository.findById(vendorId).ifPresent(vendor -> {
            vendor.removeProductReference(productId);
            vendorRepository.save(vendor);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VendorDTO> getVendorWithProducts(String vendorId) {
        return vendorRepository.findById(vendorId)
                .map(vendor -> {
                    VendorDTO vendorDTO = DTOConverter.convertToVendorDTO(vendor);

                    // Fetch product details for all product references
                    List<ProductDTO> products = vendor.getProductIds().stream()
                            .map(productId -> productService.getProductById(productId))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toList());

                    vendorDTO.setInventory(products);
                    return vendorDTO;
                });
    }
}
