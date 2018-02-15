package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.RelationshipImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RelationshipImage and its DTO RelationshipImageDTO.
 */
@Mapper(componentModel = "spring", uses = {RelationshipMapper.class})
public interface RelationshipImageMapper extends EntityMapper<RelationshipImageDTO, RelationshipImage> {

    @Mapping(source = "relationship.id", target = "relationshipId")
    @Mapping(source = "relationship.name", target = "relationshipName")
    RelationshipImageDTO toDto(RelationshipImage relationshipImage);

    @Mapping(source = "relationshipId", target = "relationship")
    RelationshipImage toEntity(RelationshipImageDTO relationshipImageDTO);

    default RelationshipImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelationshipImage relationshipImage = new RelationshipImage();
        relationshipImage.setId(id);
        return relationshipImage;
    }
}
