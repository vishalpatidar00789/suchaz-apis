package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.Trait;
import com.suchaz.app.service.dto.TraitDTO;

/**
 * Mapper for the entity Trait and its DTO TraitDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TraitMapper extends EntityMapper<TraitDTO, Trait> {


    @Mapping(target = "traitImages", ignore = false)
    @Mapping(target = "suchAzUsers", ignore = true)
    Trait toEntity(TraitDTO traitDTO);

    default Trait fromId(Long id) {
        if (id == null) {
            return null;
        }
        Trait trait = new Trait();
        trait.setId(id);
        return trait;
    }
}
