package com.suchaz.app.service;

import com.suchaz.app.service.dto.OccasionImageDTO;
import java.util.List;

/**
 * Service Interface for managing OccasionImage.
 */
public interface OccasionImageService {

    /**
     * Save a occasionImage.
     *
     * @param occasionImageDTO the entity to save
     * @return the persisted entity
     */
    OccasionImageDTO save(OccasionImageDTO occasionImageDTO);

    /**
     * Get all the occasionImages.
     *
     * @return the list of entities
     */
    List<OccasionImageDTO> findAll();

    /**
     * Get the "id" occasionImage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OccasionImageDTO findOne(Long id);

    /**
     * Delete the "id" occasionImage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
