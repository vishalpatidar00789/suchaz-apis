package com.suchaz.app.service;

import com.suchaz.app.service.dto.SuchAzUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing SuchAzUser.
 */
public interface SuchAzUserService {

    /**
     * Save a suchAzUser.
     *
     * @param suchAzUserDTO the entity to save
     * @return the persisted entity
     */
    SuchAzUserDTO save(SuchAzUserDTO suchAzUserDTO);

    /**
     * Get all the suchAzUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SuchAzUserDTO> findAll(Pageable pageable);
    /**
     * Get all the SuchAzUserDTO where UserProfile is null.
     *
     * @return the list of entities
     */
    List<SuchAzUserDTO> findAllWhereUserProfileIsNull();
    /**
     * Get all the SuchAzUserDTO where WishList is null.
     *
     * @return the list of entities
     */
    List<SuchAzUserDTO> findAllWhereWishListIsNull();
    /**
     * Get all the SuchAzUserDTO where BuyLaterList is null.
     *
     * @return the list of entities
     */
    List<SuchAzUserDTO> findAllWhereBuyLaterListIsNull();
    /**
     * Get all the SuchAzUserDTO where ActivityList is null.
     *
     * @return the list of entities
     */
    List<SuchAzUserDTO> findAllWhereActivityListIsNull();

    /**
     * Get the "id" suchAzUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SuchAzUserDTO findOne(Long id);

    /**
     * Delete the "id" suchAzUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
