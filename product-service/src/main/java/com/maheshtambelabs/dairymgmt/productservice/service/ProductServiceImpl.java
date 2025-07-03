package com.maheshtambelabs.dairymgmt.productservice.service;

import com.maheshtambelabs.dairymgmt.productservice.dto.ProductDTO;
import com.maheshtambelabs.dairymgmt.productservice.dto.ProductResponseDTO;
import com.maheshtambelabs.dairymgmt.productservice.exception.ResourceNotFoundException;
import com.maheshtambelabs.dairymgmt.productservice.mapper.ProductMapper;
import com.maheshtambelabs.dairymgmt.productservice.model.Category;
import com.maheshtambelabs.dairymgmt.productservice.model.Product;
import com.maheshtambelabs.dairymgmt.productservice.repository.CategoryRepository;
import com.maheshtambelabs.dairymgmt.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the {@link ProductService} interface.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);

        // Set category
        Category category = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productDTO.getCategory().getId()));
        product.setCategory(category);

        // Set subcategory if provided
        if (productDTO.getSubcategory() != null && productDTO.getSubcategory().getId() != null) {
            Category subcategory = categoryRepository.findById(productDTO.getSubcategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Subcategory not found with id: " + productDTO.getSubcategory().getId()));
            product.setSubcategory(subcategory);
        }

        return productMapper.toResponseDTO(productRepository.save(product));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        // Update fields from DTO
        productMapper.updateFromDTO(productDTO, existingProduct);

        // Update category if changed
        if (productDTO.getCategory() != null && !existingProduct.getCategory().getId().equals(productDTO.getCategory().getId())) {
            Category category = categoryRepository.findById(productDTO.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productDTO.getCategory().getId()));
            existingProduct.setCategory(category);
        }

        // Update subcategory if changed
        if (productDTO.getSubcategory() != null) {
            if (productDTO.getSubcategory().getId() == null) {
                existingProduct.setSubcategory(null);
            } else if (existingProduct.getSubcategory() == null ||
                    !existingProduct.getSubcategory().getId().equals(productDTO.getSubcategory().getId())) {
                Category subcategory = categoryRepository.findById(productDTO.getSubcategory().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Subcategory not found with id: " + productDTO.getSubcategory().getId()));
                existingProduct.setSubcategory(subcategory);
            }
        }

        return productMapper.toResponseDTO(productRepository.save(existingProduct));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getProductsByCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found with id: " + categoryId);
        }
        return productRepository.findByCategoryId(categoryId).stream()
                .map(productMapper::toResponseDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getProductsBySubcategory(Long subcategoryId) {
        if (!categoryRepository.existsById(subcategoryId)) {
            throw new ResourceNotFoundException("Subcategory not found with id: " + subcategoryId);
        }
        return productRepository.findBySubcategoryId(subcategoryId).stream()
                .map(productMapper::toResponseDTO)
                .collect(java.util.stream.Collectors.toList());
    }
}