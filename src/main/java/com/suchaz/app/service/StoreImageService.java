package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.StoreImageDTO;

/**
 * Service Interface for managing StoreImage.
 */
public interface StoreImageService {

    /**
     * Save a storeImage.
     *
     * @param storeImageDTO the entity to save
     * @return the persisted entity
     */
    StoreImageDTO save(StoreImageDTO storeImageDTO);

    /**
     * Get all the storeImages.
     *
     * @return the list of entities
     */
    List<StoreImageDTO> findAll();

    /**
     * Get the "id" storeImage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    StoreImageDTO findOne(Long id);

    /**
     * Delete the "id" storeImage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
