package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.ActivityListItem;
import com.suchaz.app.service.dto.ActivityListItemDTO;

/**
 * Mapper for the entity ActivityListItem and its DTO ActivityListItemDTO.
 */
@Mapper(componentModel = "spring", uses = {ActivityListMapper.class, ItemMapper.class})
public interface ActivityListItemMapper extends EntityMapper<ActivityListItemDTO, ActivityListItem> {

    @Mapping(source = "activityList.id", target = "activityListId")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.title", target = "itemTitle")
    ActivityListItemDTO toDto(ActivityListItem activityListItem);

    @Mapping(source = "activityListId", target = "activityList")
    @Mapping(source = "itemId", target = "item")
    ActivityListItem toEntity(ActivityListItemDTO activityListItemDTO);

    default ActivityListItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        ActivityListItem activityListItem = new ActivityListItem();
        activityListItem.setId(id);
        return activityListItem;
    }
}
