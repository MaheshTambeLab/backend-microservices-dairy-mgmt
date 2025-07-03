package com.maheshtambelabs.dairymgmt.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mahesh Tambe
 */
@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private int weeklyQuantity;
    private int usedQuantity;
    private Long vendorId;
    private CategoryDTO category;
    private CategoryDTO subcategory;
    private List<Long> subscriptionIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}