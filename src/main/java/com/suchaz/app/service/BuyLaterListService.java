package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.BuyLaterListDTO;

/**
 * Service Interface for managing BuyLaterList.
 */
public interface BuyLaterListService {

    /**
     * Save a buyLaterList.
     *
     * @param buyLaterListDTO the entity to save
     * @return the persisted entity
     */
    BuyLaterListDTO save(BuyLaterListDTO buyLaterListDTO);

    /**
     * Get all the buyLaterLists.
     *
     * @return the list of entities
     */
    List<BuyLaterListDTO> findAll();

    /**
     * Get the "id" buyLaterList.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BuyLaterListDTO findOne(Long id);

    /**
     * Delete the "id" buyLaterList.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
