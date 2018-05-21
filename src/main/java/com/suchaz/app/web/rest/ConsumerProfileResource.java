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
import com.suchaz.app.service.ConsumerProfileService;
import com.suchaz.app.service.dto.ConsumerProfileDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ConsumerProfile.
 */
@RestController
@RequestMapping("/api")
public class ConsumerProfileResource {

    private final Logger log = LoggerFactory.getLogger(ConsumerProfileResource.class);

    private static final String ENTITY_NAME = "consumerProfile";

    private final ConsumerProfileService consumerProfileService;

    public ConsumerProfileResource(ConsumerProfileService consumerProfileService) {
        this.consumerProfileService = consumerProfileService;
    }

    /**
     * POST  /consumer-profiles : Create a new consumerProfile.
     *
     * @param consumerProfileDTO the consumerProfileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new consumerProfileDTO, or with status 400 (Bad Request) if the consumerProfile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/consumer-profiles")
    @Timed
    public ResponseEntity<ConsumerProfileDTO> createConsumerProfile(@Valid @RequestBody ConsumerProfileDTO consumerProfileDTO) throws URISyntaxException {
        log.debug("REST request to save ConsumerProfile : {}", consumerProfileDTO);
        if (consumerProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new consumerProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsumerProfileDTO result = consumerProfileService.save(consumerProfileDTO);
        return ResponseEntity.created(new URI("/api/consumer-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /consumer-profiles : Updates an existing consumerProfile.
     *
     * @param consumerProfileDTO the consumerProfileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated consumerProfileDTO,
     * or with status 400 (Bad Request) if the consumerProfileDTO is not valid,
     * or with status 500 (Internal Server Error) if the consumerProfileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/consumer-profiles")
    @Timed
    public ResponseEntity<ConsumerProfileDTO> updateConsumerProfile(@Valid @RequestBody ConsumerProfileDTO consumerProfileDTO) throws URISyntaxException {
        log.debug("REST request to update ConsumerProfile : {}", consumerProfileDTO);
        if (consumerProfileDTO.getId() == null) {
            return createConsumerProfile(consumerProfileDTO);
        }
        ConsumerProfileDTO result = consumerProfileService.save(consumerProfileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, consumerProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /consumer-profiles : get all the consumerProfiles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of consumerProfiles in body
     */
    @GetMapping("/consumer-profiles")
    @Timed
    public List<ConsumerProfileDTO> getAllConsumerProfiles() {
        log.debug("REST request to get all ConsumerProfiles");
        return consumerProfileService.findAll();
        }

    /**
     * GET  /consumer-profiles/:id : get the "id" consumerProfile.
     *
     * @param id the id of the consumerProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the consumerProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/consumer-profiles/{id}")
    @Timed
    public ResponseEntity<ConsumerProfileDTO> getConsumerProfile(@PathVariable Long id) {
        log.debug("REST request to get ConsumerProfile : {}", id);
        ConsumerProfileDTO consumerProfileDTO = consumerProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(consumerProfileDTO));
    }

    /**
     * DELETE  /consumer-profiles/:id : delete the "id" consumerProfile.
     *
     * @param id the id of the consumerProfileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/consumer-profiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteConsumerProfile(@PathVariable Long id) {
        log.debug("REST request to delete ConsumerProfile : {}", id);
        consumerProfileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
