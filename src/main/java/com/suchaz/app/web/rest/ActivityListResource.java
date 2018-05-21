package com.suchaz.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.ActivityListService;
import com.suchaz.app.service.dto.ActivityListDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ActivityList.
 */
@RestController
@RequestMapping("/api")
public class ActivityListResource {

    private final Logger log = LoggerFactory.getLogger(ActivityListResource.class);

    private static final String ENTITY_NAME = "activityList";

    private final ActivityListService activityListService;

    public ActivityListResource(ActivityListService activityListService) {
        this.activityListService = activityListService;
    }

    /**
     * POST  /activity-lists : Create a new activityList.
     *
     * @param activityListDTO the activityListDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new activityListDTO, or with status 400 (Bad Request) if the activityList has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/activity-lists")
    @Timed
    public ResponseEntity<ActivityListDTO> createActivityList(@Valid @RequestBody ActivityListDTO activityListDTO) throws URISyntaxException {
        log.debug("REST request to save ActivityList : {}", activityListDTO);
        if (activityListDTO.getId() != null) {
            throw new BadRequestAlertException("A new activityList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActivityListDTO result = activityListService.save(activityListDTO);
        return ResponseEntity.created(new URI("/api/activity-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /activity-lists : Updates an existing activityList.
     *
     * @param activityListDTO the activityListDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated activityListDTO,
     * or with status 400 (Bad Request) if the activityListDTO is not valid,
     * or with status 500 (Internal Server Error) if the activityListDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/activity-lists")
    @Timed
    public ResponseEntity<ActivityListDTO> updateActivityList(@Valid @RequestBody ActivityListDTO activityListDTO) throws URISyntaxException {
        log.debug("REST request to update ActivityList : {}", activityListDTO);
        if (activityListDTO.getId() == null) {
            return createActivityList(activityListDTO);
        }
        ActivityListDTO result = activityListService.save(activityListDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, activityListDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /activity-lists : get all the activityLists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of activityLists in body
     */
    @GetMapping("/activity-lists")
    @Timed
    public List<ActivityListDTO> getAllActivityLists() {
        log.debug("REST request to get all ActivityLists");
        return activityListService.findAll();
        }

    /**
     * GET  /activity-lists/:id : get the "id" activityList.
     *
     * @param id the id of the activityListDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the activityListDTO, or with status 404 (Not Found)
     */
    @GetMapping("/activity-lists/{id}")
    @Timed
    public ResponseEntity<ActivityListDTO> getActivityList(@PathVariable Long id) {
        log.debug("REST request to get ActivityList : {}", id);
        ActivityListDTO activityListDTO = activityListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(activityListDTO));
    }

    /**
     * DELETE  /activity-lists/:id : delete the "id" activityList.
     *
     * @param id the id of the activityListDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/activity-lists/{id}")
    @Timed
    public ResponseEntity<Void> deleteActivityList(@PathVariable Long id) {
        log.debug("REST request to delete ActivityList : {}", id);
        activityListService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
