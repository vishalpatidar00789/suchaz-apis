package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.ItemCommonAttributeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ItemCommonAttribute and its DTO ItemCommonAttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class, ItemAttributeTypeMapper.class})
public interface ItemCommonAttributeMapper extends EntityMapper<ItemCommonAttributeDTO, ItemCommonAttribute> {

    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.title", target = "itemTitle")
    @Mapping(source = "itemAttributeType.id", target = "itemAttributeTypeId")
    @Mapping(source = "itemAttributeType.attributeTypeName", target = "itemAttributeTypeAttributeTypeName")
    ItemCommonAttributeDTO toDto(ItemCommonAttribute itemCommonAttribute);

    @Mapping(source = "itemId", target = "item")
    @Mapping(source = "itemAttributeTypeId", target = "itemAttributeType")
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
