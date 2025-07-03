package com.maheshtambelabs.dairymgmt.productservice.controller;

import com.maheshtambelabs.dairymgmt.productservice.dto.ProductDTO;
import com.maheshtambelabs.dairymgmt.productservice.dto.ProductResponseDTO;
import com.maheshtambelabs.dairymgmt.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
@Tag(name = "Product Management", description = "APIs for managing products")
public class ProductController {


    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product with the given details")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieves a paginated list of all products")
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(
            @Parameter(description = "Pagination and sorting parameters")
            @ParameterObject
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID")
    public ResponseEntity<ProductResponseDTO> getProductById(
            @Parameter(description = "ID of the product to be retrieved")
            @PathVariable @Positive(message = "ID must be a positive number") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Updates an existing product with the given ID")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @Parameter(description = "ID of the product to be updated")
            @PathVariable @Positive(message = "ID must be a positive number") Long id,
            @Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product with the given ID")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID of the product to be deleted")
            @PathVariable @Positive(message = "ID must be a positive number") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get products by category", description = "Retrieves all products belonging to a specific category")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(
            @Parameter(description = "ID of the category")
            @PathVariable @Positive(message = "Category ID must be a positive number") Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/subcategory/{subcategoryId}")
    @Operation(summary = "Get products by subcategory", description = "Retrieves all products belonging to a specific subcategory")
    public ResponseEntity<List<ProductResponseDTO>> getProductsBySubcategory(
            @Parameter(description = "ID of the subcategory")
            @PathVariable @Positive(message = "Subcategory ID must be a positive number") Long subcategoryId) {
        return ResponseEntity.ok(productService.getProductsBySubcategory(subcategoryId));
    }
}
