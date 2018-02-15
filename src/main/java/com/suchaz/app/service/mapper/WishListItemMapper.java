package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.WishListItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WishListItem and its DTO WishListItemDTO.
 */
@Mapper(componentModel = "spring", uses = {WishListMapper.class, ItemMapper.class})
public interface WishListItemMapper extends EntityMapper<WishListItemDTO, WishListItem> {

    @Mapping(source = "wishList.id", target = "wishListId")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.title", target = "itemTitle")
    WishListItemDTO toDto(WishListItem wishListItem);

    @Mapping(source = "wishListId", target = "wishList")
    @Mapping(source = "itemId", target = "item")
    WishListItem toEntity(WishListItemDTO wishListItemDTO);

    default WishListItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        WishListItem wishListItem = new WishListItem();
        wishListItem.setId(id);
        return wishListItem;
    }
}
