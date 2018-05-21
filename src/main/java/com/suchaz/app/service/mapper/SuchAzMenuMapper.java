package com.suchaz.app.service.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.suchaz.app.domain.SuchAzMenu;
import com.suchaz.app.service.dto.SuchAzMenuDTO;

/**
 * Mapper for the entity SuchAzMenu and its DTO SuchAzMenuDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface SuchAzMenuMapper extends EntityMapper<SuchAzMenuDTO, SuchAzMenu> {

	@IterableMapping(qualifiedByName="mapWithoutItems")
	List<SuchAzMenuDTO> toDto(List<SuchAzMenu> suchAzMenus);

	@Named("mapWithoutItems")
	@Mapping(target = "items", ignore = true)
	SuchAzMenuDTO toDtoWithoutItems(SuchAzMenu suchAzMenu);

    default SuchAzMenu fromId(Long id) {
        if (id == null) {
            return null;
        }
        SuchAzMenu suchAzMenu = new SuchAzMenu();
        suchAzMenu.setId(id);
        return suchAzMenu;
    }
}
