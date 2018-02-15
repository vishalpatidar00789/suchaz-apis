package com.suchaz.app.service;

import com.suchaz.app.service.dto.HobbyDTO;
import java.util.List;

/**
 * Service Interface for managing Hobby.
 */
public interface HobbyService {

    /**
     * Save a hobby.
     *
     * @param hobbyDTO the entity to save
     * @return the persisted entity
     */
    HobbyDTO save(HobbyDTO hobbyDTO);

    /**
     * Get all the hobbies.
     *
     * @return the list of entities
     */
    List<HobbyDTO> findAll();

    /**
     * Get the "id" hobby.
     *
     * @param id the id of the entity
     * @return the entity
     */
    HobbyDTO findOne(Long id);

    /**
     * Delete the "id" hobby.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
