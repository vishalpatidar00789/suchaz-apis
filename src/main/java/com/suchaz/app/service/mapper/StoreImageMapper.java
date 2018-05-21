package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.StoreImage;
import com.suchaz.app.service.dto.StoreImageDTO;

/**
 * Mapper for the entity StoreImage and its DTO StoreImageDTO.
 */
@Mapper(componentModel = "spring", uses = {StoreMapper.class})
public interface StoreImageMapper extends EntityMapper<StoreImageDTO, StoreImage> {

    @Mapping(source = "store.id", target = "storeId")
    @Mapping(source = "store.name", target = "storeName")
    StoreImageDTO toDto(StoreImage storeImage);

    @Mapping(source = "storeId", target = "store")
    StoreImage toEntity(StoreImageDTO storeImageDTO);

    default StoreImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        StoreImage storeImage = new StoreImage();
        storeImage.setId(id);
        return storeImage;
    }
}
