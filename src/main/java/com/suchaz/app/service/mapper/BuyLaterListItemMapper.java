package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.BuyLaterListItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BuyLaterListItem and its DTO BuyLaterListItemDTO.
 */
@Mapper(componentModel = "spring", uses = {BuyLaterListMapper.class, ItemMapper.class})
public interface BuyLaterListItemMapper extends EntityMapper<BuyLaterListItemDTO, BuyLaterListItem> {

    @Mapping(source = "buyLaterList.id", target = "buyLaterListId")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.title", target = "itemTitle")
    BuyLaterListItemDTO toDto(BuyLaterListItem buyLaterListItem);

    @Mapping(source = "buyLaterListId", target = "buyLaterList")
    @Mapping(source = "itemId", target = "item")
    BuyLaterListItem toEntity(BuyLaterListItemDTO buyLaterListItemDTO);

    default BuyLaterListItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        BuyLaterListItem buyLaterListItem = new BuyLaterListItem();
        buyLaterListItem.setId(id);
        return buyLaterListItem;
    }
}
