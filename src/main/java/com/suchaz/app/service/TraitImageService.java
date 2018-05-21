package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.TraitImageDTO;

/**
 * Service Interface for managing TraitImage.
 */
public interface TraitImageService {

    /**
     * Save a traitImage.
     *
     * @param traitImageDTO the entity to save
     * @return the persisted entity
     */
    TraitImageDTO save(TraitImageDTO traitImageDTO);

    /**
     * Get all the traitImages.
     *
     * @return the list of entities
     */
    List<TraitImageDTO> findAll();

    /**
     * Get the "id" traitImage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TraitImageDTO findOne(Long id);

    /**
     * Delete the "id" traitImage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
