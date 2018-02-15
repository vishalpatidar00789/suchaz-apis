package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.RelationshipService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.RelationshipDTO;
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
 * REST controller for managing Relationship.
 */
@RestController
@RequestMapping("/api")
public class RelationshipResource {

    private final Logger log = LoggerFactory.getLogger(RelationshipResource.class);

    private static final String ENTITY_NAME = "relationship";

    private final RelationshipService relationshipService;

    public RelationshipResource(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    /**
     * POST  /relationships : Create a new relationship.
     *
     * @param relationshipDTO the relationshipDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new relationshipDTO, or with status 400 (Bad Request) if the relationship has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/relationships")
    @Timed
    public ResponseEntity<RelationshipDTO> createRelationship(@Valid @RequestBody RelationshipDTO relationshipDTO) throws URISyntaxException {
        log.debug("REST request to save Relationship : {}", relationshipDTO);
        if (relationshipDTO.getId() != null) {
            throw new BadRequestAlertException("A new relationship cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelationshipDTO result = relationshipService.save(relationshipDTO);
        return ResponseEntity.created(new URI("/api/relationships/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /relationships : Updates an existing relationship.
     *
     * @param relationshipDTO the relationshipDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated relationshipDTO,
     * or with status 400 (Bad Request) if the relationshipDTO is not valid,
     * or with status 500 (Internal Server Error) if the relationshipDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/relationships")
    @Timed
    public ResponseEntity<RelationshipDTO> updateRelationship(@Valid @RequestBody RelationshipDTO relationshipDTO) throws URISyntaxException {
        log.debug("REST request to update Relationship : {}", relationshipDTO);
        if (relationshipDTO.getId() == null) {
            return createRelationship(relationshipDTO);
        }
        RelationshipDTO result = relationshipService.save(relationshipDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, relationshipDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /relationships : get all the relationships.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of relationships in body
     */
    @GetMapping("/relationships")
    @Timed
    public List<RelationshipDTO> getAllRelationships() {
        log.debug("REST request to get all Relationships");
        return relationshipService.findAll();
        }

    /**
     * GET  /relationships/:id : get the "id" relationship.
     *
     * @param id the id of the relationshipDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the relationshipDTO, or with status 404 (Not Found)
     */
    @GetMapping("/relationships/{id}")
    @Timed
    public ResponseEntity<RelationshipDTO> getRelationship(@PathVariable Long id) {
        log.debug("REST request to get Relationship : {}", id);
        RelationshipDTO relationshipDTO = relationshipService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(relationshipDTO));
    }

    /**
     * DELETE  /relationships/:id : delete the "id" relationship.
     *
     * @param id the id of the relationshipDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/relationships/{id}")
    @Timed
    public ResponseEntity<Void> deleteRelationship(@PathVariable Long id) {
        log.debug("REST request to delete Relationship : {}", id);
        relationshipService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
