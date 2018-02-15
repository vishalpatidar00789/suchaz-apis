package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.HobbyImageService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.HobbyImageDTO;
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
 * REST controller for managing HobbyImage.
 */
@RestController
@RequestMapping("/api")
public class HobbyImageResource {

    private final Logger log = LoggerFactory.getLogger(HobbyImageResource.class);

    private static final String ENTITY_NAME = "hobbyImage";

    private final HobbyImageService hobbyImageService;

    public HobbyImageResource(HobbyImageService hobbyImageService) {
        this.hobbyImageService = hobbyImageService;
    }

    /**
     * POST  /hobby-images : Create a new hobbyImage.
     *
     * @param hobbyImageDTO the hobbyImageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hobbyImageDTO, or with status 400 (Bad Request) if the hobbyImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hobby-images")
    @Timed
    public ResponseEntity<HobbyImageDTO> createHobbyImage(@Valid @RequestBody HobbyImageDTO hobbyImageDTO) throws URISyntaxException {
        log.debug("REST request to save HobbyImage : {}", hobbyImageDTO);
        if (hobbyImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new hobbyImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HobbyImageDTO result = hobbyImageService.save(hobbyImageDTO);
        return ResponseEntity.created(new URI("/api/hobby-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hobby-images : Updates an existing hobbyImage.
     *
     * @param hobbyImageDTO the hobbyImageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hobbyImageDTO,
     * or with status 400 (Bad Request) if the hobbyImageDTO is not valid,
     * or with status 500 (Internal Server Error) if the hobbyImageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hobby-images")
    @Timed
    public ResponseEntity<HobbyImageDTO> updateHobbyImage(@Valid @RequestBody HobbyImageDTO hobbyImageDTO) throws URISyntaxException {
        log.debug("REST request to update HobbyImage : {}", hobbyImageDTO);
        if (hobbyImageDTO.getId() == null) {
            return createHobbyImage(hobbyImageDTO);
        }
        HobbyImageDTO result = hobbyImageService.save(hobbyImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hobbyImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hobby-images : get all the hobbyImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hobbyImages in body
     */
    @GetMapping("/hobby-images")
    @Timed
    public List<HobbyImageDTO> getAllHobbyImages() {
        log.debug("REST request to get all HobbyImages");
        return hobbyImageService.findAll();
        }

    /**
     * GET  /hobby-images/:id : get the "id" hobbyImage.
     *
     * @param id the id of the hobbyImageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hobbyImageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/hobby-images/{id}")
    @Timed
    public ResponseEntity<HobbyImageDTO> getHobbyImage(@PathVariable Long id) {
        log.debug("REST request to get HobbyImage : {}", id);
        HobbyImageDTO hobbyImageDTO = hobbyImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(hobbyImageDTO));
    }

    /**
     * DELETE  /hobby-images/:id : delete the "id" hobbyImage.
     *
     * @param id the id of the hobbyImageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hobby-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteHobbyImage(@PathVariable Long id) {
        log.debug("REST request to delete HobbyImage : {}", id);
        hobbyImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
