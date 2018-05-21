package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.ActivityList;
import com.suchaz.app.service.dto.ActivityListDTO;

/**
 * Mapper for the entity ActivityList and its DTO ActivityListDTO.
 */
@Mapper(componentModel = "spring", uses = {SuchAzUserMapper.class})
public interface ActivityListMapper extends EntityMapper<ActivityListDTO, ActivityList> {

    @Mapping(source = "suchAzUser.id", target = "suchAzUserId")
    @Mapping(source = "suchAzUser.email", target = "suchAzUserEmail")
    ActivityListDTO toDto(ActivityList activityList);

    @Mapping(source = "suchAzUserId", target = "suchAzUser")
    @Mapping(target = "activityListItems", ignore = true)
    ActivityList toEntity(ActivityListDTO activityListDTO);

    default ActivityList fromId(Long id) {
        if (id == null) {
            return null;
        }
        ActivityList activityList = new ActivityList();
        activityList.setId(id);
        return activityList;
    }
}
