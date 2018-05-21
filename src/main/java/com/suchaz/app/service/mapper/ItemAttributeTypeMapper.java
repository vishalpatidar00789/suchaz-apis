package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.ItemAttributeType;
import com.suchaz.app.service.dto.ItemAttributeTypeDTO;

/**
 * Mapper for the entity ItemAttributeType and its DTO ItemAttributeTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ItemAttributeTypeMapper extends EntityMapper<ItemAttributeTypeDTO, ItemAttributeType> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ItemAttributeTypeDTO toDto(ItemAttributeType itemAttributeType);

    @Mapping(target = "itemCommonAttributes", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    ItemAttributeType toEntity(ItemAttributeTypeDTO itemAttributeTypeDTO);

    default ItemAttributeType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemAttributeType itemAttributeType = new ItemAttributeType();
        itemAttributeType.setId(id);
        return itemAttributeType;
    }
}
