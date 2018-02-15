package com.suchaz.app.service;

import com.suchaz.app.service.dto.GiftWrapperDTO;
import java.util.List;

/**
 * Service Interface for managing GiftWrapper.
 */
public interface GiftWrapperService {

    /**
     * Save a giftWrapper.
     *
     * @param giftWrapperDTO the entity to save
     * @return the persisted entity
     */
    GiftWrapperDTO save(GiftWrapperDTO giftWrapperDTO);

    /**
     * Get all the giftWrappers.
     *
     * @return the list of entities
     */
    List<GiftWrapperDTO> findAll();

    /**
     * Get the "id" giftWrapper.
     *
     * @param id the id of the entity
     * @return the entity
     */
    GiftWrapperDTO findOne(Long id);

    /**
     * Delete the "id" giftWrapper.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
