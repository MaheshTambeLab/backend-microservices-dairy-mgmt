package com.maheshtambelabs.dairymgmt.productservice.mapper;

import com.maheshtambelabs.dairymgmt.productservice.dto.CategoryDTO;
import com.maheshtambelabs.dairymgmt.productservice.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {CategoryMapper.class})
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "subcategories", ignore = true)
    @Mapping(target = "parent", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);

    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "subcategories", source = "subcategories")
    CategoryDTO toDTO(Category category);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
