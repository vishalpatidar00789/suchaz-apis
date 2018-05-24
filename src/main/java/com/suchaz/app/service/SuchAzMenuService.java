package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.SuchAzMenuDTO;

/**
 * Service Interface for managing SuchAzMenu.
 */
public interface SuchAzMenuService {

    /**
     * Save a suchAzMenu.
     *
     * @param suchAzMenuDTO the entity to save
     * @return the persisted entity
     */
    SuchAzMenuDTO save(SuchAzMenuDTO suchAzMenuDTO);

    /**
     * Get all the suchAzMenus.
     *
     * @return the list of entities
     */
    List<SuchAzMenuDTO> findAll();

    /**
     * Get the "id" suchAzMenu.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SuchAzMenuDTO findOne(Long id);

    /**
     * Delete the "id" suchAzMenu.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    
    SuchAzMenuDTO findOneWithMenuCodeAndItems(String menuCode);
}
