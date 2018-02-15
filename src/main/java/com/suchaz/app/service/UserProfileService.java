package com.suchaz.app.service;

import com.suchaz.app.service.dto.UserProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing UserProfile.
 */
public interface UserProfileService {

    /**
     * Save a userProfile.
     *
     * @param userProfileDTO the entity to save
     * @return the persisted entity
     */
    UserProfileDTO save(UserProfileDTO userProfileDTO);

    /**
     * Get all the userProfiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserProfileDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userProfile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserProfileDTO findOne(Long id);

    /**
     * Delete the "id" userProfile.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
