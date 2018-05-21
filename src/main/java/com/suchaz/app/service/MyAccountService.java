package com.suchaz.app.service;

import com.suchaz.app.service.dto.MyAccountDTO;

/**
 * Service Interface for managing UserProfile.
 */
public interface MyAccountService {

    /**
     * Get the "id" userProfile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    MyAccountDTO findOne(Long id);

}
