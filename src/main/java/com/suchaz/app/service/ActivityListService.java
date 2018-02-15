package com.suchaz.app.service;

import com.suchaz.app.service.dto.ActivityListDTO;
import java.util.List;

/**
 * Service Interface for managing ActivityList.
 */
public interface ActivityListService {

    /**
     * Save a activityList.
     *
     * @param activityListDTO the entity to save
     * @return the persisted entity
     */
    ActivityListDTO save(ActivityListDTO activityListDTO);

    /**
     * Get all the activityLists.
     *
     * @return the list of entities
     */
    List<ActivityListDTO> findAll();

    /**
     * Get the "id" activityList.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ActivityListDTO findOne(Long id);

    /**
     * Delete the "id" activityList.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
