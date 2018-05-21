package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.ItemCommonAttribute;
import com.suchaz.app.service.dto.ItemCommonAttributeDTO;

/**
 * Mapper for the entity ItemCommonAttribute and its DTO ItemCommonAttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class, ItemAttributeTypeMapper.class, CategoryMapper.class})
public interface ItemCommonAttributeMapper extends EntityMapper<ItemCommonAttributeDTO, ItemCommonAttribute> {

    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.title", target = "itemTitle")
    @Mapping(source = "itemAttributeType.id", target = "itemAttributeTypeId")
    @Mapping(source = "itemAttributeType.attributeTypeName", target = "itemAttributeTypeAttributeTypeName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ItemCommonAttributeDTO toDto(ItemCommonAttribute itemCommonAttribute);

    @Mapping(source = "itemId", target = "item")
    @Mapping(source = "itemAttributeTypeId", target = "itemAttributeType")
    @Mapping(source = "categoryId", target = "category")
    ItemCommonAttribute toEntity(ItemCommonAttributeDTO itemCommonAttributeDTO);

    default ItemCommonAttribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemCommonAttribute itemCommonAttribute = new ItemCommonAttribute();
        itemCommonAttribute.setId(id);
        return itemCommonAttribute;
    }
}
