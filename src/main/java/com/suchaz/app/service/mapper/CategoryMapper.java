package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.CategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Category and its DTO CategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    CategoryDTO toDto(Category category);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "itemAttributeTypes", ignore = true)
    @Mapping(target = "itemCommonAttributes", ignore = true)
    @Mapping(target = "categoryImages", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    Category toEntity(CategoryDTO categoryDTO);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
