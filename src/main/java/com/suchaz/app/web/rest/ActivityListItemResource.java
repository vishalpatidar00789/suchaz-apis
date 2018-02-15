package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.ActivityListItemService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.ActivityListItemDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ActivityListItem.
 */
@RestController
@RequestMapping("/api")
public class ActivityListItemResource {

    private final Logger log = LoggerFactory.getLogger(ActivityListItemResource.class);

    private static final String ENTITY_NAME = "activityListItem";

    private final ActivityListItemService activityListItemService;

    public ActivityListItemResource(ActivityListItemService activityListItemService) {
        this.activityListItemService = activityListItemService;
    }

    /**
     * POST  /activity-list-items : Create a new activityListItem.
     *
     * @param activityListItemDTO the activityListItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new activityListItemDTO, or with status 400 (Bad Request) if the activityListItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/activity-list-items")
    @Timed
    public ResponseEntity<ActivityListItemDTO> createActivityListItem(@Valid @RequestBody ActivityListItemDTO activityListItemDTO) throws URISyntaxException {
        log.debug("REST request to save ActivityListItem : {}", activityListItemDTO);
        if (activityListItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new activityListItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActivityListItemDTO result = activityListItemService.save(activityListItemDTO);
        return ResponseEntity.created(new URI("/api/activity-list-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /activity-list-items : Updates an existing activityListItem.
     *
     * @param activityListItemDTO the activityListItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated activityListItemDTO,
     * or with status 400 (Bad Request) if the activityListItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the activityListItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/activity-list-items")
    @Timed
    public ResponseEntity<ActivityListItemDTO> updateActivityListItem(@Valid @RequestBody ActivityListItemDTO activityListItemDTO) throws URISyntaxException {
        log.debug("REST request to update ActivityListItem : {}", activityListItemDTO);
        if (activityListItemDTO.getId() == null) {
            return createActivityListItem(activityListItemDTO);
        }
        ActivityListItemDTO result = activityListItemService.save(activityListItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, activityListItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /activity-list-items : get all the activityListItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of activityListItems in body
     */
    @GetMapping("/activity-list-items")
    @Timed
    public List<ActivityListItemDTO> getAllActivityListItems() {
        log.debug("REST request to get all ActivityListItems");
        return activityListItemService.findAll();
        }

    /**
     * GET  /activity-list-items/:id : get the "id" activityListItem.
     *
     * @param id the id of the activityListItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the activityListItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/activity-list-items/{id}")
    @Timed
    public ResponseEntity<ActivityListItemDTO> getActivityListItem(@PathVariable Long id) {
        log.debug("REST request to get ActivityListItem : {}", id);
        ActivityListItemDTO activityListItemDTO = activityListItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(activityListItemDTO));
    }

    /**
     * DELETE  /activity-list-items/:id : delete the "id" activityListItem.
     *
     * @param id the id of the activityListItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/activity-list-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteActivityListItem(@PathVariable Long id) {
        log.debug("REST request to delete ActivityListItem : {}", id);
        activityListItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
