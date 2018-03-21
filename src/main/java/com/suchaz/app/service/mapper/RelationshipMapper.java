package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.RelationshipDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Relationship and its DTO RelationshipDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RelationshipMapper extends EntityMapper<RelationshipDTO, Relationship> {


    @Mapping(target = "relationshipImages", ignore = false)
    Relationship toEntity(RelationshipDTO relationshipDTO);

    default Relationship fromId(Long id) {
        if (id == null) {
            return null;
        }
        Relationship relationship = new Relationship();
        relationship.setId(id);
        return relationship;
    }
}
