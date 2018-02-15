package com.suchaz.app.service;

import com.suchaz.app.service.dto.TraitDTO;
import java.util.List;

/**
 * Service Interface for managing Trait.
 */
public interface TraitService {

    /**
     * Save a trait.
     *
     * @param traitDTO the entity to save
     * @return the persisted entity
     */
    TraitDTO save(TraitDTO traitDTO);

    /**
     * Get all the traits.
     *
     * @return the list of entities
     */
    List<TraitDTO> findAll();

    /**
     * Get the "id" trait.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TraitDTO findOne(Long id);

    /**
     * Delete the "id" trait.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
