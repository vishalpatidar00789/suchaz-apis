package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.ItemReviewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ItemReview and its DTO ItemReviewDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface ItemReviewMapper extends EntityMapper<ItemReviewDTO, ItemReview> {

    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.title", target = "itemTitle")
    ItemReviewDTO toDto(ItemReview itemReview);

    @Mapping(source = "itemId", target = "item")
    ItemReview toEntity(ItemReviewDTO itemReviewDTO);

    default ItemReview fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemReview itemReview = new ItemReview();
        itemReview.setId(id);
        return itemReview;
    }
}
