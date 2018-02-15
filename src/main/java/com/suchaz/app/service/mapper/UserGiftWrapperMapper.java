package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.UserGiftWrapperDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserGiftWrapper and its DTO UserGiftWrapperDTO.
 */
@Mapper(componentModel = "spring", uses = {SuchAzUserMapper.class, ItemMapper.class, GiftWrapperMapper.class})
public interface UserGiftWrapperMapper extends EntityMapper<UserGiftWrapperDTO, UserGiftWrapper> {

    @Mapping(source = "suchAzUser.id", target = "suchAzUserId")
    @Mapping(source = "suchAzUser.email", target = "suchAzUserEmail")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.title", target = "itemTitle")
    @Mapping(source = "giftWrapper.id", target = "giftWrapperId")
    @Mapping(source = "giftWrapper.name", target = "giftWrapperName")
    UserGiftWrapperDTO toDto(UserGiftWrapper userGiftWrapper);

    @Mapping(source = "suchAzUserId", target = "suchAzUser")
    @Mapping(source = "itemId", target = "item")
    @Mapping(source = "giftWrapperId", target = "giftWrapper")
    UserGiftWrapper toEntity(UserGiftWrapperDTO userGiftWrapperDTO);

    default UserGiftWrapper fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserGiftWrapper userGiftWrapper = new UserGiftWrapper();
        userGiftWrapper.setId(id);
        return userGiftWrapper;
    }
}
