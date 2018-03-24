package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.QuickViewDTO;

/**
 * Service Interface for managing Item.
 */
public interface QuickViewService {

    /**
     * Get all the Quick View of items.
     *
     * @return the list of entities
     */
    List<QuickViewDTO> findRangeOfItem(Long id []);

    /**
     * Get the "id" item.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuickViewDTO findOne(Long id);

}
