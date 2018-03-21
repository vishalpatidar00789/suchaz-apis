package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.HobbyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Hobby and its DTO HobbyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HobbyMapper extends EntityMapper<HobbyDTO, Hobby> {


    @Mapping(target = "hobbyImages", ignore = false)
    Hobby toEntity(HobbyDTO hobbyDTO);

    default Hobby fromId(Long id) {
        if (id == null) {
            return null;
        }
        Hobby hobby = new Hobby();
        hobby.setId(id);
        return hobby;
    }
}
