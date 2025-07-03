package com.maheshtambelabs.dairymgmt.productservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private List<CategoryDTO> subcategories = new ArrayList<>();
}
