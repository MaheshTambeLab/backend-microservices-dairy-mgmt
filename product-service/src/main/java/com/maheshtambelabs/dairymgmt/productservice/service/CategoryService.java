package com.maheshtambelabs.dairymgmt.productservice.service;

import com.maheshtambelabs.dairymgmt.productservice.dto.CategoryDTO;

import java.util.List;

/**
 * Service interface for managing categories.
 */
public interface CategoryService {
    /**
     * Create a new category
     *
     * @param categoryDTO the category to create
     * @return the created category
     */
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    /**
     * Get all top-level categories
     *
     * @return list of top-level categories
     */
    List<CategoryDTO> getAllCategories();

    /**
     * Get a category by ID
     *
     * @param id the category ID
     * @return the category
     */
    CategoryDTO getCategoryById(Long id);

    /**
     * Update a category
     *
     * @param id          the category ID
     * @param categoryDTO the category data to update
     * @return the updated category
     */
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    /**
     * Delete a category
     *
     * @param id the category ID
     */
    void deleteCategory(Long id);

    /**
     * Get all subcategories of a parent category
     *
     * @param parentId the ID of the parent category
     * @return list of subcategories belonging to the parent category
     */
    List<CategoryDTO> getSubcategories(Long parentId);
}