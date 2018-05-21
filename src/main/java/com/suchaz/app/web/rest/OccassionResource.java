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
import com.suchaz.app.service.OccassionService;
import com.suchaz.app.service.dto.OccassionDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Occassion.
 */
@RestController
@RequestMapping("/api")
public class OccassionResource {

    private final Logger log = LoggerFactory.getLogger(OccassionResource.class);

    private static final String ENTITY_NAME = "occassion";

    private final OccassionService occassionService;

    public OccassionResource(OccassionService occassionService) {
        this.occassionService = occassionService;
    }

    /**
     * POST  /occassions : Create a new occassion.
     *
     * @param occassionDTO the occassionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new occassionDTO, or with status 400 (Bad Request) if the occassion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/occassions")
    @Timed
    public ResponseEntity<OccassionDTO> createOccassion(@Valid @RequestBody OccassionDTO occassionDTO) throws URISyntaxException {
        log.debug("REST request to save Occassion : {}", occassionDTO);
        if (occassionDTO.getId() != null) {
            throw new BadRequestAlertException("A new occassion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OccassionDTO result = occassionService.save(occassionDTO);
        return ResponseEntity.created(new URI("/api/occassions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /occassions : Updates an existing occassion.
     *
     * @param occassionDTO the occassionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated occassionDTO,
     * or with status 400 (Bad Request) if the occassionDTO is not valid,
     * or with status 500 (Internal Server Error) if the occassionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/occassions")
    @Timed
    public ResponseEntity<OccassionDTO> updateOccassion(@Valid @RequestBody OccassionDTO occassionDTO) throws URISyntaxException {
        log.debug("REST request to update Occassion : {}", occassionDTO);
        if (occassionDTO.getId() == null) {
            return createOccassion(occassionDTO);
        }
        OccassionDTO result = occassionService.save(occassionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, occassionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /occassions : get all the occassions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of occassions in body
     */
    @GetMapping("/occassions")
    @Timed
    public List<OccassionDTO> getAllOccassions() {
        log.debug("REST request to get all Occassions");
        return occassionService.findAll();
        }

    /**
     * GET  /occassions/:id : get the "id" occassion.
     *
     * @param id the id of the occassionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the occassionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/occassions/{id}")
    @Timed
    public ResponseEntity<OccassionDTO> getOccassion(@PathVariable Long id) {
        log.debug("REST request to get Occassion : {}", id);
        OccassionDTO occassionDTO = occassionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(occassionDTO));
    }

    /**
     * DELETE  /occassions/:id : delete the "id" occassion.
     *
     * @param id the id of the occassionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/occassions/{id}")
    @Timed
    public ResponseEntity<Void> deleteOccassion(@PathVariable Long id) {
        log.debug("REST request to delete Occassion : {}", id);
        occassionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
