package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.ConsumerProfileHistoryDTO;

/**
 * Service Interface for managing ConsumerProfileHistory.
 */
public interface ConsumerProfileHistoryService {

    /**
     * Save a consumerProfileHistory.
     *
     * @param consumerProfileHistoryDTO the entity to save
     * @return the persisted entity
     */
    ConsumerProfileHistoryDTO save(ConsumerProfileHistoryDTO consumerProfileHistoryDTO);

    /**
     * Get all the consumerProfileHistories.
     *
     * @return the list of entities
     */
    List<ConsumerProfileHistoryDTO> findAll();

    /**
     * Get the "id" consumerProfileHistory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ConsumerProfileHistoryDTO findOne(Long id);

    /**
     * Delete the "id" consumerProfileHistory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
