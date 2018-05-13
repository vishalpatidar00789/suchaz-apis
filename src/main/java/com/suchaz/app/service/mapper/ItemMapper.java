package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.ItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Item and its DTO ItemDTO.
 */
@Mapper(componentModel = "spring", uses = {OfferMapper.class, CategoryMapper.class, VendorMapper.class})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "vendor.id", target = "vendorId")
    @Mapping(source = "vendor.vendorName", target = "vendorVendorName")
    ItemDTO toDto(Item item);

    @Mapping(target = "wishListItems", ignore = true)
    @Mapping(target = "buyLaterListItems", ignore = true)
    @Mapping(target = "activityListItems", ignore = true)
    @Mapping(target = "itemCommonAttributes", ignore = true)
    @Mapping(target = "itemReviews", ignore = true)
    @Mapping(target = "userGiftWrappers", ignore = true)
    @Mapping(target = "itemImages", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "vendorId", target = "vendor")
    @Mapping(target = "stores", ignore = true)
    @Mapping(target = "menus", ignore = true)
    Item toEntity(ItemDTO itemDTO);

    default Item fromId(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
