package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.ItemDTO;

/**
 * Service Interface for managing Item.
 */
public interface ItemService {

    /**
     * Save a item.
     *
     * @param itemDTO the entity to save
     * @return the persisted entity
     */
    ItemDTO save(ItemDTO itemDTO);

    /**
     * Get all the items.
     *
     * @return the list of entities
     */
    List<ItemDTO> findAll();

    /**
     * Get the "id" item.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ItemDTO findOne(Long id);

    /**
     * Delete the "id" item.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Get all the weekly Featured Items
     *
     * @return the list of entities
     */
    List<ItemDTO> findAllWeeklyFeaturedItem();
    
    /**
     * Get all the Items with Title Search
     *
     * @return the list of entities
     */
    Long[] findAllItemsIdsWithKeyWord(String keyWord);
}
