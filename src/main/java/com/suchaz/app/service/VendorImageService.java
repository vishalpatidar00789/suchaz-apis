package com.suchaz.app.service;

import com.suchaz.app.service.dto.VendorImageDTO;
import java.util.List;

/**
 * Service Interface for managing VendorImage.
 */
public interface VendorImageService {

    /**
     * Save a vendorImage.
     *
     * @param vendorImageDTO the entity to save
     * @return the persisted entity
     */
    VendorImageDTO save(VendorImageDTO vendorImageDTO);

    /**
     * Get all the vendorImages.
     *
     * @return the list of entities
     */
    List<VendorImageDTO> findAll();

    /**
     * Get the "id" vendorImage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    VendorImageDTO findOne(Long id);

    /**
     * Delete the "id" vendorImage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
