package com.suchaz.app.service;

import java.util.List;

import com.suchaz.app.service.dto.CategoryImageDTO;

/**
 * Service Interface for managing CategoryImage.
 */
public interface CategoryImageService {

    /**
     * Save a categoryImage.
     *
     * @param categoryImageDTO the entity to save
     * @return the persisted entity
     */
    CategoryImageDTO save(CategoryImageDTO categoryImageDTO);

    /**
     * Get all the categoryImages.
     *
     * @return the list of entities
     */
    List<CategoryImageDTO> findAll();

    /**
     * Get the "id" categoryImage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CategoryImageDTO findOne(Long id);

    /**
     * Delete the "id" categoryImage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
