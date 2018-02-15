package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.HobbyImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity HobbyImage and its DTO HobbyImageDTO.
 */
@Mapper(componentModel = "spring", uses = {HobbyMapper.class})
public interface HobbyImageMapper extends EntityMapper<HobbyImageDTO, HobbyImage> {

    @Mapping(source = "hobby.id", target = "hobbyId")
    @Mapping(source = "hobby.name", target = "hobbyName")
    HobbyImageDTO toDto(HobbyImage hobbyImage);

    @Mapping(source = "hobbyId", target = "hobby")
    HobbyImage toEntity(HobbyImageDTO hobbyImageDTO);

    default HobbyImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        HobbyImage hobbyImage = new HobbyImage();
        hobbyImage.setId(id);
        return hobbyImage;
    }
}
