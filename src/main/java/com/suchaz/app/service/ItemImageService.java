package com.suchaz.app.service;

import com.suchaz.app.service.dto.ItemImageDTO;
import java.util.List;

/**
 * Service Interface for managing ItemImage.
 */
public interface ItemImageService {

    /**
     * Save a itemImage.
     *
     * @param itemImageDTO the entity to save
     * @return the persisted entity
     */
    ItemImageDTO save(ItemImageDTO itemImageDTO);

    /**
     * Get all the itemImages.
     *
     * @return the list of entities
     */
    List<ItemImageDTO> findAll();

    /**
     * Get the "id" itemImage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ItemImageDTO findOne(Long id);

    /**
     * Delete the "id" itemImage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
