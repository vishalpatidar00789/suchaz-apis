package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.TraitImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TraitImage and its DTO TraitImageDTO.
 */
@Mapper(componentModel = "spring", uses = {TraitMapper.class})
public interface TraitImageMapper extends EntityMapper<TraitImageDTO, TraitImage> {

    @Mapping(source = "trait.id", target = "traitId")
    @Mapping(source = "trait.name", target = "traitName")
    TraitImageDTO toDto(TraitImage traitImage);

    @Mapping(source = "traitId", target = "trait")
    TraitImage toEntity(TraitImageDTO traitImageDTO);

    default TraitImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        TraitImage traitImage = new TraitImage();
        traitImage.setId(id);
        return traitImage;
    }
}
