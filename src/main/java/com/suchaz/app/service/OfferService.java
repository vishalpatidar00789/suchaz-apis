package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.OfferDTO;

/**
 * Service Interface for managing Offer.
 */
public interface OfferService {

    /**
     * Save a offer.
     *
     * @param offerDTO the entity to save
     * @return the persisted entity
     */
    OfferDTO save(OfferDTO offerDTO);

    /**
     * Get all the offers.
     *
     * @return the list of entities
     */
    List<OfferDTO> findAll();

    /**
     * Get the "id" offer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OfferDTO findOne(Long id);

    /**
     * Delete the "id" offer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
