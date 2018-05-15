package com.suchaz.app.service;

import com.suchaz.app.service.dto.ItemDetailsDTO;

/**
 * Service Interface for managing UserProfile.
 */
public interface ItemDetailService {

    /**
     * Get the "id" itemDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ItemDetailsDTO findOne(Long ItemId);

}
