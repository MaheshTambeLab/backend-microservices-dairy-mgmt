package com.maheshtambelabs.dairymgmt.productservice.service;

import com.maheshtambelabs.dairymgmt.productservice.dto.ProductDTO;
import com.maheshtambelabs.dairymgmt.productservice.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing products.
 */
public interface ProductService {
    /**
     * Create a new product
     *
     * @param productDTO the product to create
     * @return the created product
     */
    ProductResponseDTO createProduct(ProductDTO productDTO);

    /**
     * Get all products with pagination
     *
     * @param pageable pagination information
     * @return page of products
     */
    Page<ProductResponseDTO> getAllProducts(Pageable pageable);

    /**
     * Get a product by ID
     *
     * @param id the product ID
     * @return the product
     */
    ProductResponseDTO getProductById(Long id);

    /**
     * Update a product
     *
     * @param id         the product ID
     * @param productDTO the product data to update
     * @return the updated product
     */
    ProductResponseDTO updateProduct(Long id, ProductDTO productDTO);

    /**
     * Delete a product
     *
     * @param id the product ID
     */
    void deleteProduct(Long id);

    /**
     * Get products by category ID
     *
     * @param categoryId the category ID
     * @return list of products in the category
     */
    List<ProductResponseDTO> getProductsByCategory(Long categoryId);

    /**
     * Get products by subcategory ID
     *
     * @param subcategoryId the subcategory ID
     * @return list of products in the subcategory
     */
    List<ProductResponseDTO> getProductsBySubcategory(Long subcategoryId);
}

