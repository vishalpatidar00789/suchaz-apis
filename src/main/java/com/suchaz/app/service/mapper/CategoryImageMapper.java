package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.CategoryImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CategoryImage and its DTO CategoryImageDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CategoryImageMapper extends EntityMapper<CategoryImageDTO, CategoryImage> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    CategoryImageDTO toDto(CategoryImage categoryImage);

    @Mapping(source = "categoryId", target = "category")
    CategoryImage toEntity(CategoryImageDTO categoryImageDTO);

    default CategoryImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoryImage categoryImage = new CategoryImage();
        categoryImage.setId(id);
        return categoryImage;
    }
}
