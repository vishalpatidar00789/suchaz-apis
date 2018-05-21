package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.OccassionDTO;

/**
 * Service Interface for managing Occassion.
 */
public interface OccassionService {

    /**
     * Save a occassion.
     *
     * @param occassionDTO the entity to save
     * @return the persisted entity
     */
    OccassionDTO save(OccassionDTO occassionDTO);

    /**
     * Get all the occassions.
     *
     * @return the list of entities
     */
    List<OccassionDTO> findAll();

    /**
     * Get the "id" occassion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OccassionDTO findOne(Long id);

    /**
     * Delete the "id" occassion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
