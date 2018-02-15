package com.suchaz.app.service;

import com.suchaz.app.service.dto.ConsumerProfileDTO;
import java.util.List;

/**
 * Service Interface for managing ConsumerProfile.
 */
public interface ConsumerProfileService {

    /**
     * Save a consumerProfile.
     *
     * @param consumerProfileDTO the entity to save
     * @return the persisted entity
     */
    ConsumerProfileDTO save(ConsumerProfileDTO consumerProfileDTO);

    /**
     * Get all the consumerProfiles.
     *
     * @return the list of entities
     */
    List<ConsumerProfileDTO> findAll();

    /**
     * Get the "id" consumerProfile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ConsumerProfileDTO findOne(Long id);

    /**
     * Delete the "id" consumerProfile.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
