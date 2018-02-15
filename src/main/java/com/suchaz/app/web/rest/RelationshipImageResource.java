package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.RelationshipImageService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.RelationshipImageDTO;
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
 * REST controller for managing RelationshipImage.
 */
@RestController
@RequestMapping("/api")
public class RelationshipImageResource {

    private final Logger log = LoggerFactory.getLogger(RelationshipImageResource.class);

    private static final String ENTITY_NAME = "relationshipImage";

    private final RelationshipImageService relationshipImageService;

    public RelationshipImageResource(RelationshipImageService relationshipImageService) {
        this.relationshipImageService = relationshipImageService;
    }

    /**
     * POST  /relationship-images : Create a new relationshipImage.
     *
     * @param relationshipImageDTO the relationshipImageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new relationshipImageDTO, or with status 400 (Bad Request) if the relationshipImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/relationship-images")
    @Timed
    public ResponseEntity<RelationshipImageDTO> createRelationshipImage(@Valid @RequestBody RelationshipImageDTO relationshipImageDTO) throws URISyntaxException {
        log.debug("REST request to save RelationshipImage : {}", relationshipImageDTO);
        if (relationshipImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new relationshipImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelationshipImageDTO result = relationshipImageService.save(relationshipImageDTO);
        return ResponseEntity.created(new URI("/api/relationship-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /relationship-images : Updates an existing relationshipImage.
     *
     * @param relationshipImageDTO the relationshipImageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated relationshipImageDTO,
     * or with status 400 (Bad Request) if the relationshipImageDTO is not valid,
     * or with status 500 (Internal Server Error) if the relationshipImageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/relationship-images")
    @Timed
    public ResponseEntity<RelationshipImageDTO> updateRelationshipImage(@Valid @RequestBody RelationshipImageDTO relationshipImageDTO) throws URISyntaxException {
        log.debug("REST request to update RelationshipImage : {}", relationshipImageDTO);
        if (relationshipImageDTO.getId() == null) {
            return createRelationshipImage(relationshipImageDTO);
        }
        RelationshipImageDTO result = relationshipImageService.save(relationshipImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, relationshipImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /relationship-images : get all the relationshipImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of relationshipImages in body
     */
    @GetMapping("/relationship-images")
    @Timed
    public List<RelationshipImageDTO> getAllRelationshipImages() {
        log.debug("REST request to get all RelationshipImages");
        return relationshipImageService.findAll();
        }

    /**
     * GET  /relationship-images/:id : get the "id" relationshipImage.
     *
     * @param id the id of the relationshipImageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the relationshipImageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/relationship-images/{id}")
    @Timed
    public ResponseEntity<RelationshipImageDTO> getRelationshipImage(@PathVariable Long id) {
        log.debug("REST request to get RelationshipImage : {}", id);
        RelationshipImageDTO relationshipImageDTO = relationshipImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(relationshipImageDTO));
    }

    /**
     * DELETE  /relationship-images/:id : delete the "id" relationshipImage.
     *
     * @param id the id of the relationshipImageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/relationship-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteRelationshipImage(@PathVariable Long id) {
        log.debug("REST request to delete RelationshipImage : {}", id);
        relationshipImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
