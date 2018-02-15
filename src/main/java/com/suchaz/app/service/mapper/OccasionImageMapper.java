package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.OccasionImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OccasionImage and its DTO OccasionImageDTO.
 */
@Mapper(componentModel = "spring", uses = {OccassionMapper.class})
public interface OccasionImageMapper extends EntityMapper<OccasionImageDTO, OccasionImage> {

    @Mapping(source = "occasion.id", target = "occasionId")
    @Mapping(source = "occasion.name", target = "occasionName")
    OccasionImageDTO toDto(OccasionImage occasionImage);

    @Mapping(source = "occasionId", target = "occasion")
    OccasionImage toEntity(OccasionImageDTO occasionImageDTO);

    default OccasionImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        OccasionImage occasionImage = new OccasionImage();
        occasionImage.setId(id);
        return occasionImage;
    }
}
