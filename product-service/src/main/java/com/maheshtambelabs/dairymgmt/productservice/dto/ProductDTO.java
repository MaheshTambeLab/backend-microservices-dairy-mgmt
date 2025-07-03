package com.maheshtambelabs.dairymgmt.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mahesh Tambe
 */
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discount = BigDecimal.ZERO;
    private int weeklyQuantity;
    private int usedQuantity;
    private Long vendorId;
    private CategoryDTO category;
    private CategoryDTO subcategory;
    private List<Long> subscriptionIds;
}
