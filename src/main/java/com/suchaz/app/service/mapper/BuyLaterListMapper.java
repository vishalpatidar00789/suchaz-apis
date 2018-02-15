package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.BuyLaterListDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BuyLaterList and its DTO BuyLaterListDTO.
 */
@Mapper(componentModel = "spring", uses = {SuchAzUserMapper.class})
public interface BuyLaterListMapper extends EntityMapper<BuyLaterListDTO, BuyLaterList> {

    @Mapping(source = "suchAzUser.id", target = "suchAzUserId")
    @Mapping(source = "suchAzUser.email", target = "suchAzUserEmail")
    BuyLaterListDTO toDto(BuyLaterList buyLaterList);

    @Mapping(source = "suchAzUserId", target = "suchAzUser")
    @Mapping(target = "buyLaterListItems", ignore = true)
    BuyLaterList toEntity(BuyLaterListDTO buyLaterListDTO);

    default BuyLaterList fromId(Long id) {
        if (id == null) {
            return null;
        }
        BuyLaterList buyLaterList = new BuyLaterList();
        buyLaterList.setId(id);
        return buyLaterList;
    }
}
