package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.ItemCommonAttributeDTO;

/**
 * Service Interface for managing ItemCommonAttribute.
 */
public interface ItemCommonAttributeService {

    /**
     * Save a itemCommonAttribute.
     *
     * @param itemCommonAttributeDTO the entity to save
     * @return the persisted entity
     */
    ItemCommonAttributeDTO save(ItemCommonAttributeDTO itemCommonAttributeDTO);

    /**
     * Get all the itemCommonAttributes.
     *
     * @return the list of entities
     */
    List<ItemCommonAttributeDTO> findAll();

    /**
     * Get the "id" itemCommonAttribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ItemCommonAttributeDTO findOne(Long id);

    /**
     * Delete the "id" itemCommonAttribute.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
