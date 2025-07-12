package com.maheshtambelabs.dairymgmt.userservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private String unit;
}
