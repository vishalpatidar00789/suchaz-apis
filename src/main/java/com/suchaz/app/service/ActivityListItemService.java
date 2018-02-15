package com.suchaz.app.service;

import com.suchaz.app.service.dto.ActivityListItemDTO;
import java.util.List;

/**
 * Service Interface for managing ActivityListItem.
 */
public interface ActivityListItemService {

    /**
     * Save a activityListItem.
     *
     * @param activityListItemDTO the entity to save
     * @return the persisted entity
     */
    ActivityListItemDTO save(ActivityListItemDTO activityListItemDTO);

    /**
     * Get all the activityListItems.
     *
     * @return the list of entities
     */
    List<ActivityListItemDTO> findAll();

    /**
     * Get the "id" activityListItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ActivityListItemDTO findOne(Long id);

    /**
     * Delete the "id" activityListItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
