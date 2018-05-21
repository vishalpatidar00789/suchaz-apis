package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.WishListDTO;

/**
 * Service Interface for managing WishList.
 */
public interface WishListService {

    /**
     * Save a wishList.
     *
     * @param wishListDTO the entity to save
     * @return the persisted entity
     */
    WishListDTO save(WishListDTO wishListDTO);

    /**
     * Get all the wishLists.
     *
     * @return the list of entities
     */
    List<WishListDTO> findAll();

    /**
     * Get the "id" wishList.
     *
     * @param id the id of the entity
     * @return the entity
     */
    WishListDTO findOne(Long id);

    /**
     * Delete the "id" wishList.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
