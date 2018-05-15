package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.SuchAzMenuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SuchAzMenu and its DTO SuchAzMenuDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface SuchAzMenuMapper extends EntityMapper<SuchAzMenuDTO, SuchAzMenu> {



    default SuchAzMenu fromId(Long id) {
        if (id == null) {
            return null;
        }
        SuchAzMenu suchAzMenu = new SuchAzMenu();
        suchAzMenu.setId(id);
        return suchAzMenu;
    }
}
