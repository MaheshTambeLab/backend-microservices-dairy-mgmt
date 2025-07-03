package com.maheshtambelabs.dairymgmt.productservice.mapper;

import com.maheshtambelabs.dairymgmt.productservice.dto.CategoryDTO;
import com.maheshtambelabs.dairymgmt.productservice.dto.ProductDTO;
import com.maheshtambelabs.dairymgmt.productservice.dto.ProductResponseDTO;
import com.maheshtambelabs.dairymgmt.productservice.model.Category;
import com.maheshtambelabs.dairymgmt.productservice.model.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {CategoryMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "category", source = "category", qualifiedByName = "mapCategory")
    @Mapping(target = "subcategory", source = "subcategory", qualifiedByName = "mapCategory")
    Product toEntity(ProductDTO productDTO);

    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "category", source = "category", qualifiedByName = "mapCategoryToDTO")
    @Mapping(target = "subcategory", source = "subcategory", qualifiedByName = "mapCategoryToDTO")
    ProductResponseDTO toResponseDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "category", source = "category", qualifiedByName = "mapCategory")
    @Mapping(target = "subcategory", source = "subcategory", qualifiedByName = "mapCategory")
    void updateFromDTO(ProductDTO productDTO, @MappingTarget Product product);

    @Named("mapCategory")
    default Category mapCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        return CategoryMapper.INSTANCE.toEntity(categoryDTO);
    }

    @Named("mapCategoryToDTO")
    default CategoryDTO mapCategoryToDTO(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryMapper.INSTANCE.toDTO(category);
    }
}