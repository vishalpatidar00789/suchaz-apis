package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.UserGiftWrapperDTO;

/**
 * Service Interface for managing UserGiftWrapper.
 */
public interface UserGiftWrapperService {

    /**
     * Save a userGiftWrapper.
     *
     * @param userGiftWrapperDTO the entity to save
     * @return the persisted entity
     */
    UserGiftWrapperDTO save(UserGiftWrapperDTO userGiftWrapperDTO);

    /**
     * Get all the userGiftWrappers.
     *
     * @return the list of entities
     */
    List<UserGiftWrapperDTO> findAll();

    /**
     * Get the "id" userGiftWrapper.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserGiftWrapperDTO findOne(Long id);

    /**
     * Delete the "id" userGiftWrapper.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
