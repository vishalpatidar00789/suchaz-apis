package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.OfferDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Offer and its DTO OfferDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OfferMapper extends EntityMapper<OfferDTO, Offer> {


    @Mapping(target = "items", ignore = true)
    Offer toEntity(OfferDTO offerDTO);

    default Offer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Offer offer = new Offer();
        offer.setId(id);
        return offer;
    }
}
