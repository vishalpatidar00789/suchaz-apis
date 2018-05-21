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
import com.suchaz.app.service.OccasionImageService;
import com.suchaz.app.service.dto.OccasionImageDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing OccasionImage.
 */
@RestController
@RequestMapping("/api")
public class OccasionImageResource {

    private final Logger log = LoggerFactory.getLogger(OccasionImageResource.class);

    private static final String ENTITY_NAME = "occasionImage";

    private final OccasionImageService occasionImageService;

    public OccasionImageResource(OccasionImageService occasionImageService) {
        this.occasionImageService = occasionImageService;
    }

    /**
     * POST  /occasion-images : Create a new occasionImage.
     *
     * @param occasionImageDTO the occasionImageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new occasionImageDTO, or with status 400 (Bad Request) if the occasionImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/occasion-images")
    @Timed
    public ResponseEntity<OccasionImageDTO> createOccasionImage(@Valid @RequestBody OccasionImageDTO occasionImageDTO) throws URISyntaxException {
        log.debug("REST request to save OccasionImage : {}", occasionImageDTO);
        if (occasionImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new occasionImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OccasionImageDTO result = occasionImageService.save(occasionImageDTO);
        return ResponseEntity.created(new URI("/api/occasion-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /occasion-images : Updates an existing occasionImage.
     *
     * @param occasionImageDTO the occasionImageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated occasionImageDTO,
     * or with status 400 (Bad Request) if the occasionImageDTO is not valid,
     * or with status 500 (Internal Server Error) if the occasionImageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/occasion-images")
    @Timed
    public ResponseEntity<OccasionImageDTO> updateOccasionImage(@Valid @RequestBody OccasionImageDTO occasionImageDTO) throws URISyntaxException {
        log.debug("REST request to update OccasionImage : {}", occasionImageDTO);
        if (occasionImageDTO.getId() == null) {
            return createOccasionImage(occasionImageDTO);
        }
        OccasionImageDTO result = occasionImageService.save(occasionImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, occasionImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /occasion-images : get all the occasionImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of occasionImages in body
     */
    @GetMapping("/occasion-images")
    @Timed
    public List<OccasionImageDTO> getAllOccasionImages() {
        log.debug("REST request to get all OccasionImages");
        return occasionImageService.findAll();
        }

    /**
     * GET  /occasion-images/:id : get the "id" occasionImage.
     *
     * @param id the id of the occasionImageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the occasionImageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/occasion-images/{id}")
    @Timed
    public ResponseEntity<OccasionImageDTO> getOccasionImage(@PathVariable Long id) {
        log.debug("REST request to get OccasionImage : {}", id);
        OccasionImageDTO occasionImageDTO = occasionImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(occasionImageDTO));
    }

    /**
     * DELETE  /occasion-images/:id : delete the "id" occasionImage.
     *
     * @param id the id of the occasionImageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/occasion-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteOccasionImage(@PathVariable Long id) {
        log.debug("REST request to delete OccasionImage : {}", id);
        occasionImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
