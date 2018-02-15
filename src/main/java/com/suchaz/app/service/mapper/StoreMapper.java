package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.StoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Store and its DTO StoreDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface StoreMapper extends EntityMapper<StoreDTO, Store> {


    @Mapping(target = "storeImages", ignore = true)
    Store toEntity(StoreDTO storeDTO);

    default Store fromId(Long id) {
        if (id == null) {
            return null;
        }
        Store store = new Store();
        store.setId(id);
        return store;
    }
}
