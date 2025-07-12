package com.maheshtambelabs.dairymgmt.userservice.controller;

import com.maheshtambelabs.dairymgmt.userservice.client.ProductServiceClient;
import com.maheshtambelabs.dairymgmt.userservice.dto.ProductDTO;
import com.maheshtambelabs.dairymgmt.userservice.dto.VendorDTO;
import com.maheshtambelabs.dairymgmt.userservice.model.Vendor;
import com.maheshtambelabs.dairymgmt.userservice.service.VendorService;
import com.maheshtambelabs.dairymgmt.userservice.util.DTOConverter;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;
    private final ProductServiceClient productServiceClient;

    @Autowired
    public VendorController(VendorService vendorService, ProductServiceClient productServiceClient) {
        this.vendorService = vendorService;
        this.productServiceClient = productServiceClient;
    }

    @PostMapping
    public VendorDTO createVendor(@RequestBody Vendor vendor) {
        return DTOConverter.convertToVendorDTO(vendorService.saveVendor(vendor));
    }

    @GetMapping
    public List<VendorDTO> getAllVendors() {
        return vendorService.getAllVendors().stream()
                .map(DTOConverter::convertToVendorDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable String id) {
        return vendorService.getVendorById(id)
                .map(DTOConverter::convertToVendorDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/with-products")
    public ResponseEntity<VendorDTO> getVendorWithProducts(@PathVariable String id) {
        return vendorService.getVendorWithProducts(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable String id, @RequestBody Vendor vendorDetails) {
        try {
            Vendor updatedVendor = vendorService.updateVendor(id, vendorDetails);
            return ResponseEntity.ok(DTOConverter.convertToVendorDTO(updatedVendor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable String id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{vendorId}/products")
    public ResponseEntity<ProductDTO> addProduct(
            @PathVariable String vendorId,
            @RequestBody ProductDTO productDTO) {
        try {
            // Call product service via Feign client to create the product
            ProductDTO createdProduct = productServiceClient.createProduct(productDTO);
            
            // Update vendor's product references if needed
            Vendor vendor = vendorService.addProductReference(vendorId, createdProduct.getProductId());
            
            return ResponseEntity.ok(createdProduct);
        } catch (FeignException.NotFound e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{vendorId}/products/{productId}")
    public ResponseEntity<Void> removeProduct(
            @PathVariable String vendorId,
            @PathVariable String productId) {
        vendorService.removeProductReference(vendorId, productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{vendorId}/farm-details")
    public ResponseEntity<VendorDTO> updateFarmDetails(
            @PathVariable String vendorId,
            @RequestParam(required = false) String farmName,
            @RequestParam(required = false) String farmAddress) {
        try {
            Vendor updatedVendor = vendorService.updateFarmDetails(vendorId, farmName, farmAddress);
            return ResponseEntity.ok(DTOConverter.convertToVendorDTO(updatedVendor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{vendorId}/bank-details")
    public ResponseEntity<VendorDTO> updateBankDetails(
            @PathVariable String vendorId,
            @RequestParam String bankAccountNumber,
            @RequestParam String ifscCode) {
        try {
            Vendor updatedVendor = vendorService.updateBankDetails(
                    vendorId, bankAccountNumber, ifscCode);
            return ResponseEntity.ok(DTOConverter.convertToVendorDTO(updatedVendor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
