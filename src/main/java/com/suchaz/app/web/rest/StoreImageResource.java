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
import com.suchaz.app.service.StoreImageService;
import com.suchaz.app.service.dto.StoreImageDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing StoreImage.
 */
@RestController
@RequestMapping("/api")
public class StoreImageResource {

    private final Logger log = LoggerFactory.getLogger(StoreImageResource.class);

    private static final String ENTITY_NAME = "storeImage";

    private final StoreImageService storeImageService;

    public StoreImageResource(StoreImageService storeImageService) {
        this.storeImageService = storeImageService;
    }

    /**
     * POST  /store-images : Create a new storeImage.
     *
     * @param storeImageDTO the storeImageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new storeImageDTO, or with status 400 (Bad Request) if the storeImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/store-images")
    @Timed
    public ResponseEntity<StoreImageDTO> createStoreImage(@Valid @RequestBody StoreImageDTO storeImageDTO) throws URISyntaxException {
        log.debug("REST request to save StoreImage : {}", storeImageDTO);
        if (storeImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new storeImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StoreImageDTO result = storeImageService.save(storeImageDTO);
        return ResponseEntity.created(new URI("/api/store-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /store-images : Updates an existing storeImage.
     *
     * @param storeImageDTO the storeImageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated storeImageDTO,
     * or with status 400 (Bad Request) if the storeImageDTO is not valid,
     * or with status 500 (Internal Server Error) if the storeImageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/store-images")
    @Timed
    public ResponseEntity<StoreImageDTO> updateStoreImage(@Valid @RequestBody StoreImageDTO storeImageDTO) throws URISyntaxException {
        log.debug("REST request to update StoreImage : {}", storeImageDTO);
        if (storeImageDTO.getId() == null) {
            return createStoreImage(storeImageDTO);
        }
        StoreImageDTO result = storeImageService.save(storeImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, storeImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /store-images : get all the storeImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of storeImages in body
     */
    @GetMapping("/store-images")
    @Timed
    public List<StoreImageDTO> getAllStoreImages() {
        log.debug("REST request to get all StoreImages");
        return storeImageService.findAll();
        }

    /**
     * GET  /store-images/:id : get the "id" storeImage.
     *
     * @param id the id of the storeImageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the storeImageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/store-images/{id}")
    @Timed
    public ResponseEntity<StoreImageDTO> getStoreImage(@PathVariable Long id) {
        log.debug("REST request to get StoreImage : {}", id);
        StoreImageDTO storeImageDTO = storeImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(storeImageDTO));
    }

    /**
     * DELETE  /store-images/:id : delete the "id" storeImage.
     *
     * @param id the id of the storeImageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/store-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteStoreImage(@PathVariable Long id) {
        log.debug("REST request to delete StoreImage : {}", id);
        storeImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
