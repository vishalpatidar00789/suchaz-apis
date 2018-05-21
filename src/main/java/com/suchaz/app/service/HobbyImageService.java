package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.HobbyImageDTO;

/**
 * Service Interface for managing HobbyImage.
 */
public interface HobbyImageService {

    /**
     * Save a hobbyImage.
     *
     * @param hobbyImageDTO the entity to save
     * @return the persisted entity
     */
    HobbyImageDTO save(HobbyImageDTO hobbyImageDTO);

    /**
     * Get all the hobbyImages.
     *
     * @return the list of entities
     */
    List<HobbyImageDTO> findAll();

    /**
     * Get the "id" hobbyImage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    HobbyImageDTO findOne(Long id);

    /**
     * Delete the "id" hobbyImage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
