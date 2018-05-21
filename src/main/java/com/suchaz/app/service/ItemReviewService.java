package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.ItemReviewDTO;

/**
 * Service Interface for managing ItemReview.
 */
public interface ItemReviewService {

    /**
     * Save a itemReview.
     *
     * @param itemReviewDTO the entity to save
     * @return the persisted entity
     */
    ItemReviewDTO save(ItemReviewDTO itemReviewDTO);

    /**
     * Get all the itemReviews.
     *
     * @return the list of entities
     */
    List<ItemReviewDTO> findAll();

    /**
     * Get the "id" itemReview.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ItemReviewDTO findOne(Long id);

    /**
     * Delete the "id" itemReview.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
