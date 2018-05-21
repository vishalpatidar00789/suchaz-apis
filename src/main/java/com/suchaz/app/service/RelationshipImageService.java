package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.RelationshipImageDTO;

/**
 * Service Interface for managing RelationshipImage.
 */
public interface RelationshipImageService {

    /**
     * Save a relationshipImage.
     *
     * @param relationshipImageDTO the entity to save
     * @return the persisted entity
     */
    RelationshipImageDTO save(RelationshipImageDTO relationshipImageDTO);

    /**
     * Get all the relationshipImages.
     *
     * @return the list of entities
     */
    List<RelationshipImageDTO> findAll();

    /**
     * Get the "id" relationshipImage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RelationshipImageDTO findOne(Long id);

    /**
     * Delete the "id" relationshipImage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
