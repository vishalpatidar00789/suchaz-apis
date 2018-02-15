package com.suchaz.app.service;

import com.suchaz.app.service.dto.VendorDTO;
import java.util.List;

/**
 * Service Interface for managing Vendor.
 */
public interface VendorService {

    /**
     * Save a vendor.
     *
     * @param vendorDTO the entity to save
     * @return the persisted entity
     */
    VendorDTO save(VendorDTO vendorDTO);

    /**
     * Get all the vendors.
     *
     * @return the list of entities
     */
    List<VendorDTO> findAll();

    /**
     * Get the "id" vendor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    VendorDTO findOne(Long id);

    /**
     * Delete the "id" vendor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
