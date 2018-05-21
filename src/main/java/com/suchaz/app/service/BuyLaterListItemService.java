package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.BuyLaterListItemDTO;

/**
 * Service Interface for managing BuyLaterListItem.
 */
public interface BuyLaterListItemService {

    /**
     * Save a buyLaterListItem.
     *
     * @param buyLaterListItemDTO the entity to save
     * @return the persisted entity
     */
    BuyLaterListItemDTO save(BuyLaterListItemDTO buyLaterListItemDTO);

    /**
     * Get all the buyLaterListItems.
     *
     * @return the list of entities
     */
    List<BuyLaterListItemDTO> findAll();

    /**
     * Get the "id" buyLaterListItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BuyLaterListItemDTO findOne(Long id);

    /**
     * Delete the "id" buyLaterListItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
