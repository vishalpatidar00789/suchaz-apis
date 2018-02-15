package com.suchaz.app.service;

import com.suchaz.app.service.dto.BuyLaterListItemDTO;
import java.util.List;

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
