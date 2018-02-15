package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.GiftWrapperDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GiftWrapper and its DTO GiftWrapperDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GiftWrapperMapper extends EntityMapper<GiftWrapperDTO, GiftWrapper> {


    @Mapping(target = "userGiftWrappers", ignore = true)
    GiftWrapper toEntity(GiftWrapperDTO giftWrapperDTO);

    default GiftWrapper fromId(Long id) {
        if (id == null) {
            return null;
        }
        GiftWrapper giftWrapper = new GiftWrapper();
        giftWrapper.setId(id);
        return giftWrapper;
    }
}
