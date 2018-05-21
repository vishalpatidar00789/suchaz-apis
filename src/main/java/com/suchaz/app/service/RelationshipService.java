package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.RelationshipDTO;

/**
 * Service Interface for managing Relationship.
 */
public interface RelationshipService {

    /**
     * Save a relationship.
     *
     * @param relationshipDTO the entity to save
     * @return the persisted entity
     */
    RelationshipDTO save(RelationshipDTO relationshipDTO);

    /**
     * Get all the relationships.
     *
     * @return the list of entities
     */
    List<RelationshipDTO> findAll();

    /**
     * Get the "id" relationship.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RelationshipDTO findOne(Long id);

    /**
     * Delete the "id" relationship.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
