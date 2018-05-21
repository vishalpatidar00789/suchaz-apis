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
import com.suchaz.app.service.TraitImageService;
import com.suchaz.app.service.dto.TraitImageDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing TraitImage.
 */
@RestController
@RequestMapping("/api")
public class TraitImageResource {

    private final Logger log = LoggerFactory.getLogger(TraitImageResource.class);

    private static final String ENTITY_NAME = "traitImage";

    private final TraitImageService traitImageService;

    public TraitImageResource(TraitImageService traitImageService) {
        this.traitImageService = traitImageService;
    }

    /**
     * POST  /trait-images : Create a new traitImage.
     *
     * @param traitImageDTO the traitImageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new traitImageDTO, or with status 400 (Bad Request) if the traitImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trait-images")
    @Timed
    public ResponseEntity<TraitImageDTO> createTraitImage(@Valid @RequestBody TraitImageDTO traitImageDTO) throws URISyntaxException {
        log.debug("REST request to save TraitImage : {}", traitImageDTO);
        if (traitImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new traitImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TraitImageDTO result = traitImageService.save(traitImageDTO);
        return ResponseEntity.created(new URI("/api/trait-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trait-images : Updates an existing traitImage.
     *
     * @param traitImageDTO the traitImageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated traitImageDTO,
     * or with status 400 (Bad Request) if the traitImageDTO is not valid,
     * or with status 500 (Internal Server Error) if the traitImageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trait-images")
    @Timed
    public ResponseEntity<TraitImageDTO> updateTraitImage(@Valid @RequestBody TraitImageDTO traitImageDTO) throws URISyntaxException {
        log.debug("REST request to update TraitImage : {}", traitImageDTO);
        if (traitImageDTO.getId() == null) {
            return createTraitImage(traitImageDTO);
        }
        TraitImageDTO result = traitImageService.save(traitImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, traitImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trait-images : get all the traitImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of traitImages in body
     */
    @GetMapping("/trait-images")
    @Timed
    public List<TraitImageDTO> getAllTraitImages() {
        log.debug("REST request to get all TraitImages");
        return traitImageService.findAll();
        }

    /**
     * GET  /trait-images/:id : get the "id" traitImage.
     *
     * @param id the id of the traitImageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the traitImageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/trait-images/{id}")
    @Timed
    public ResponseEntity<TraitImageDTO> getTraitImage(@PathVariable Long id) {
        log.debug("REST request to get TraitImage : {}", id);
        TraitImageDTO traitImageDTO = traitImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(traitImageDTO));
    }

    /**
     * DELETE  /trait-images/:id : delete the "id" traitImage.
     *
     * @param id the id of the traitImageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trait-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteTraitImage(@PathVariable Long id) {
        log.debug("REST request to delete TraitImage : {}", id);
        traitImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
