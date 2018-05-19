package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.SuchAzMenu;
import com.suchaz.app.service.dto.SuchAzMenuDTO;

/**
 * Mapper for the entity SuchAzMenu and its DTO SuchAzMenuDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SuchAzMenuMapper extends EntityMapper<SuchAzMenuDTO, SuchAzMenu> {


	@Mapping(target = "items", ignore = true)
	SuchAzMenuDTO toDto(SuchAzMenu suchAzMenu);
	
    default SuchAzMenu fromId(Long id) {
        if (id == null) {
            return null;
        }
        SuchAzMenu suchAzMenu = new SuchAzMenu();
        suchAzMenu.setId(id);
        return suchAzMenu;
    }
}
