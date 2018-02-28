package com.suchaz.app.service;

import com.suchaz.app.service.dto.MyAccountDTO;
import com.suchaz.app.service.dto.UserProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
