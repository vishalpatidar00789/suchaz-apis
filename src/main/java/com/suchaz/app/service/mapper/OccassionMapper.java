package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.Occassion;
import com.suchaz.app.service.dto.OccassionDTO;

/**
 * Mapper for the entity Occassion and its DTO OccassionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OccassionMapper extends EntityMapper<OccassionDTO, Occassion> {


    @Mapping(target = "occasionImages", ignore = false)
    Occassion toEntity(OccassionDTO occassionDTO);

    default Occassion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Occassion occassion = new Occassion();
        occassion.setId(id);
        return occassion;
    }
}
