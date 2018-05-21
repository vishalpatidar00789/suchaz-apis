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
import com.suchaz.app.service.VendorImageService;
import com.suchaz.app.service.dto.VendorImageDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing VendorImage.
 */
@RestController
@RequestMapping("/api")
public class VendorImageResource {

    private final Logger log = LoggerFactory.getLogger(VendorImageResource.class);

    private static final String ENTITY_NAME = "vendorImage";

    private final VendorImageService vendorImageService;

    public VendorImageResource(VendorImageService vendorImageService) {
        this.vendorImageService = vendorImageService;
    }

    /**
     * POST  /vendor-images : Create a new vendorImage.
     *
     * @param vendorImageDTO the vendorImageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendorImageDTO, or with status 400 (Bad Request) if the vendorImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vendor-images")
    @Timed
    public ResponseEntity<VendorImageDTO> createVendorImage(@Valid @RequestBody VendorImageDTO vendorImageDTO) throws URISyntaxException {
        log.debug("REST request to save VendorImage : {}", vendorImageDTO);
        if (vendorImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new vendorImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendorImageDTO result = vendorImageService.save(vendorImageDTO);
        return ResponseEntity.created(new URI("/api/vendor-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vendor-images : Updates an existing vendorImage.
     *
     * @param vendorImageDTO the vendorImageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendorImageDTO,
     * or with status 400 (Bad Request) if the vendorImageDTO is not valid,
     * or with status 500 (Internal Server Error) if the vendorImageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vendor-images")
    @Timed
    public ResponseEntity<VendorImageDTO> updateVendorImage(@Valid @RequestBody VendorImageDTO vendorImageDTO) throws URISyntaxException {
        log.debug("REST request to update VendorImage : {}", vendorImageDTO);
        if (vendorImageDTO.getId() == null) {
            return createVendorImage(vendorImageDTO);
        }
        VendorImageDTO result = vendorImageService.save(vendorImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vendorImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vendor-images : get all the vendorImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vendorImages in body
     */
    @GetMapping("/vendor-images")
    @Timed
    public List<VendorImageDTO> getAllVendorImages() {
        log.debug("REST request to get all VendorImages");
        return vendorImageService.findAll();
        }

    /**
     * GET  /vendor-images/:id : get the "id" vendorImage.
     *
     * @param id the id of the vendorImageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendorImageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-images/{id}")
    @Timed
    public ResponseEntity<VendorImageDTO> getVendorImage(@PathVariable Long id) {
        log.debug("REST request to get VendorImage : {}", id);
        VendorImageDTO vendorImageDTO = vendorImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(vendorImageDTO));
    }

    /**
     * DELETE  /vendor-images/:id : delete the "id" vendorImage.
     *
     * @param id the id of the vendorImageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vendor-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteVendorImage(@PathVariable Long id) {
        log.debug("REST request to delete VendorImage : {}", id);
        vendorImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
