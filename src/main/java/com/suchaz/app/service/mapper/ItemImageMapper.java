package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.ItemImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ItemImage and its DTO ItemImageDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface ItemImageMapper extends EntityMapper<ItemImageDTO, ItemImage> {

    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.title", target = "itemTitle")
    ItemImageDTO toDto(ItemImage itemImage);

    @Mapping(source = "itemId", target = "item")
    ItemImage toEntity(ItemImageDTO itemImageDTO);

    default ItemImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemImage itemImage = new ItemImage();
        itemImage.setId(id);
        return itemImage;
    }
}
