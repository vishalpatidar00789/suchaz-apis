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
import com.suchaz.app.service.ConsumerProfileHistoryService;
import com.suchaz.app.service.dto.ConsumerProfileHistoryDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ConsumerProfileHistory.
 */
@RestController
@RequestMapping("/api")
public class ConsumerProfileHistoryResource {

    private final Logger log = LoggerFactory.getLogger(ConsumerProfileHistoryResource.class);

    private static final String ENTITY_NAME = "consumerProfileHistory";

    private final ConsumerProfileHistoryService consumerProfileHistoryService;

    public ConsumerProfileHistoryResource(ConsumerProfileHistoryService consumerProfileHistoryService) {
        this.consumerProfileHistoryService = consumerProfileHistoryService;
    }

    /**
     * POST  /consumer-profile-histories : Create a new consumerProfileHistory.
     *
     * @param consumerProfileHistoryDTO the consumerProfileHistoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new consumerProfileHistoryDTO, or with status 400 (Bad Request) if the consumerProfileHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/consumer-profile-histories")
    @Timed
    public ResponseEntity<ConsumerProfileHistoryDTO> createConsumerProfileHistory(@Valid @RequestBody ConsumerProfileHistoryDTO consumerProfileHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save ConsumerProfileHistory : {}", consumerProfileHistoryDTO);
        if (consumerProfileHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new consumerProfileHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsumerProfileHistoryDTO result = consumerProfileHistoryService.save(consumerProfileHistoryDTO);
        return ResponseEntity.created(new URI("/api/consumer-profile-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /consumer-profile-histories : Updates an existing consumerProfileHistory.
     *
     * @param consumerProfileHistoryDTO the consumerProfileHistoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated consumerProfileHistoryDTO,
     * or with status 400 (Bad Request) if the consumerProfileHistoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the consumerProfileHistoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/consumer-profile-histories")
    @Timed
    public ResponseEntity<ConsumerProfileHistoryDTO> updateConsumerProfileHistory(@Valid @RequestBody ConsumerProfileHistoryDTO consumerProfileHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update ConsumerProfileHistory : {}", consumerProfileHistoryDTO);
        if (consumerProfileHistoryDTO.getId() == null) {
            return createConsumerProfileHistory(consumerProfileHistoryDTO);
        }
        ConsumerProfileHistoryDTO result = consumerProfileHistoryService.save(consumerProfileHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, consumerProfileHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /consumer-profile-histories : get all the consumerProfileHistories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of consumerProfileHistories in body
     */
    @GetMapping("/consumer-profile-histories")
    @Timed
    public List<ConsumerProfileHistoryDTO> getAllConsumerProfileHistories() {
        log.debug("REST request to get all ConsumerProfileHistories");
        return consumerProfileHistoryService.findAll();
        }

    /**
     * GET  /consumer-profile-histories/:id : get the "id" consumerProfileHistory.
     *
     * @param id the id of the consumerProfileHistoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the consumerProfileHistoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/consumer-profile-histories/{id}")
    @Timed
    public ResponseEntity<ConsumerProfileHistoryDTO> getConsumerProfileHistory(@PathVariable Long id) {
        log.debug("REST request to get ConsumerProfileHistory : {}", id);
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = consumerProfileHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(consumerProfileHistoryDTO));
    }

    /**
     * DELETE  /consumer-profile-histories/:id : delete the "id" consumerProfileHistory.
     *
     * @param id the id of the consumerProfileHistoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/consumer-profile-histories/{id}")
    @Timed
    public ResponseEntity<Void> deleteConsumerProfileHistory(@PathVariable Long id) {
        log.debug("REST request to delete ConsumerProfileHistory : {}", id);
        consumerProfileHistoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
