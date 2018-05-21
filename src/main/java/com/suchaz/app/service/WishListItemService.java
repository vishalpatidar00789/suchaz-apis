package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.WishListItemDTO;

/**
 * Service Interface for managing WishListItem.
 */
public interface WishListItemService {

    /**
     * Save a wishListItem.
     *
     * @param wishListItemDTO the entity to save
     * @return the persisted entity
     */
    WishListItemDTO save(WishListItemDTO wishListItemDTO);

    /**
     * Get all the wishListItems.
     *
     * @return the list of entities
     */
    List<WishListItemDTO> findAll();

    /**
     * Get the "id" wishListItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    WishListItemDTO findOne(Long id);

    /**
     * Delete the "id" wishListItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
