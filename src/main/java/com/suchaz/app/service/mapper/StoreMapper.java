package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.Store;
import com.suchaz.app.service.dto.StoreDTO;

/**
 * Mapper for the entity Store and its DTO StoreDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface StoreMapper extends EntityMapper<StoreDTO, Store> {


    @Mapping(target = "storeImages", ignore = false)
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
