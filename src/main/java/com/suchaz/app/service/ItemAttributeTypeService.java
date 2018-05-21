package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.ItemAttributeTypeDTO;

/**
 * Service Interface for managing ItemAttributeType.
 */
public interface ItemAttributeTypeService {

    /**
     * Save a itemAttributeType.
     *
     * @param itemAttributeTypeDTO the entity to save
     * @return the persisted entity
     */
    ItemAttributeTypeDTO save(ItemAttributeTypeDTO itemAttributeTypeDTO);

    /**
     * Get all the itemAttributeTypes.
     *
     * @return the list of entities
     */
    List<ItemAttributeTypeDTO> findAll();

    /**
     * Get the "id" itemAttributeType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ItemAttributeTypeDTO findOne(Long id);

    /**
     * Delete the "id" itemAttributeType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
