package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.HobbyService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.HobbyDTO;
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
 * REST controller for managing Hobby.
 */
@RestController
@RequestMapping("/api")
public class HobbyResource {

    private final Logger log = LoggerFactory.getLogger(HobbyResource.class);

    private static final String ENTITY_NAME = "hobby";

    private final HobbyService hobbyService;

    public HobbyResource(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    /**
     * POST  /hobbies : Create a new hobby.
     *
     * @param hobbyDTO the hobbyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hobbyDTO, or with status 400 (Bad Request) if the hobby has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hobbies")
    @Timed
    public ResponseEntity<HobbyDTO> createHobby(@Valid @RequestBody HobbyDTO hobbyDTO) throws URISyntaxException {
        log.debug("REST request to save Hobby : {}", hobbyDTO);
        if (hobbyDTO.getId() != null) {
            throw new BadRequestAlertException("A new hobby cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HobbyDTO result = hobbyService.save(hobbyDTO);
        return ResponseEntity.created(new URI("/api/hobbies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hobbies : Updates an existing hobby.
     *
     * @param hobbyDTO the hobbyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hobbyDTO,
     * or with status 400 (Bad Request) if the hobbyDTO is not valid,
     * or with status 500 (Internal Server Error) if the hobbyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hobbies")
    @Timed
    public ResponseEntity<HobbyDTO> updateHobby(@Valid @RequestBody HobbyDTO hobbyDTO) throws URISyntaxException {
        log.debug("REST request to update Hobby : {}", hobbyDTO);
        if (hobbyDTO.getId() == null) {
            return createHobby(hobbyDTO);
        }
        HobbyDTO result = hobbyService.save(hobbyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hobbyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hobbies : get all the hobbies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hobbies in body
     */
    @GetMapping("/hobbies")
    @Timed
    public List<HobbyDTO> getAllHobbies() {
        log.debug("REST request to get all Hobbies");
        return hobbyService.findAll();
        }

    /**
     * GET  /hobbies/:id : get the "id" hobby.
     *
     * @param id the id of the hobbyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hobbyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/hobbies/{id}")
    @Timed
    public ResponseEntity<HobbyDTO> getHobby(@PathVariable Long id) {
        log.debug("REST request to get Hobby : {}", id);
        HobbyDTO hobbyDTO = hobbyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(hobbyDTO));
    }

    /**
     * DELETE  /hobbies/:id : delete the "id" hobby.
     *
     * @param id the id of the hobbyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hobbies/{id}")
    @Timed
    public ResponseEntity<Void> deleteHobby(@PathVariable Long id) {
        log.debug("REST request to delete Hobby : {}", id);
        hobbyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
