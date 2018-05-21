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
import com.suchaz.app.service.TraitService;
import com.suchaz.app.service.dto.TraitDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Trait.
 */
@RestController
@RequestMapping("/api")
public class TraitResource {

    private final Logger log = LoggerFactory.getLogger(TraitResource.class);

    private static final String ENTITY_NAME = "trait";

    private final TraitService traitService;

    public TraitResource(TraitService traitService) {
        this.traitService = traitService;
    }

    /**
     * POST  /traits : Create a new trait.
     *
     * @param traitDTO the traitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new traitDTO, or with status 400 (Bad Request) if the trait has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/traits")
    @Timed
    public ResponseEntity<TraitDTO> createTrait(@Valid @RequestBody TraitDTO traitDTO) throws URISyntaxException {
        log.debug("REST request to save Trait : {}", traitDTO);
        if (traitDTO.getId() != null) {
            throw new BadRequestAlertException("A new trait cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TraitDTO result = traitService.save(traitDTO);
        return ResponseEntity.created(new URI("/api/traits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /traits : Updates an existing trait.
     *
     * @param traitDTO the traitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated traitDTO,
     * or with status 400 (Bad Request) if the traitDTO is not valid,
     * or with status 500 (Internal Server Error) if the traitDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/traits")
    @Timed
    public ResponseEntity<TraitDTO> updateTrait(@Valid @RequestBody TraitDTO traitDTO) throws URISyntaxException {
        log.debug("REST request to update Trait : {}", traitDTO);
        if (traitDTO.getId() == null) {
            return createTrait(traitDTO);
        }
        TraitDTO result = traitService.save(traitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, traitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /traits : get all the traits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of traits in body
     */
    @GetMapping("/traits")
    @Timed
    public List<TraitDTO> getAllTraits() {
        log.debug("REST request to get all Traits");
        return traitService.findAll();
        }

    /**
     * GET  /traits/:id : get the "id" trait.
     *
     * @param id the id of the traitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the traitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/traits/{id}")
    @Timed
    public ResponseEntity<TraitDTO> getTrait(@PathVariable Long id) {
        log.debug("REST request to get Trait : {}", id);
        TraitDTO traitDTO = traitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(traitDTO));
    }

    /**
     * DELETE  /traits/:id : delete the "id" trait.
     *
     * @param id the id of the traitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/traits/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrait(@PathVariable Long id) {
        log.debug("REST request to delete Trait : {}", id);
        traitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
