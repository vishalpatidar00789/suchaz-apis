package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.WishListDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WishList and its DTO WishListDTO.
 */
@Mapper(componentModel = "spring", uses = {SuchAzUserMapper.class})
public interface WishListMapper extends EntityMapper<WishListDTO, WishList> {

    @Mapping(source = "suchAzUser.id", target = "suchAzUserId")
    @Mapping(source = "suchAzUser.email", target = "suchAzUserEmail")
    WishListDTO toDto(WishList wishList);

    @Mapping(source = "suchAzUserId", target = "suchAzUser")
    @Mapping(target = "wishListItems", ignore = true)
    WishList toEntity(WishListDTO wishListDTO);

    default WishList fromId(Long id) {
        if (id == null) {
            return null;
        }
        WishList wishList = new WishList();
        wishList.setId(id);
        return wishList;
    }
}
