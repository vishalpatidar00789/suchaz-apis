package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.GiftWrapperService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.GiftWrapperDTO;
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
 * REST controller for managing GiftWrapper.
 */
@RestController
@RequestMapping("/api")
public class GiftWrapperResource {

    private final Logger log = LoggerFactory.getLogger(GiftWrapperResource.class);

    private static final String ENTITY_NAME = "giftWrapper";

    private final GiftWrapperService giftWrapperService;

    public GiftWrapperResource(GiftWrapperService giftWrapperService) {
        this.giftWrapperService = giftWrapperService;
    }

    /**
     * POST  /gift-wrappers : Create a new giftWrapper.
     *
     * @param giftWrapperDTO the giftWrapperDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new giftWrapperDTO, or with status 400 (Bad Request) if the giftWrapper has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gift-wrappers")
    @Timed
    public ResponseEntity<GiftWrapperDTO> createGiftWrapper(@Valid @RequestBody GiftWrapperDTO giftWrapperDTO) throws URISyntaxException {
        log.debug("REST request to save GiftWrapper : {}", giftWrapperDTO);
        if (giftWrapperDTO.getId() != null) {
            throw new BadRequestAlertException("A new giftWrapper cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GiftWrapperDTO result = giftWrapperService.save(giftWrapperDTO);
        return ResponseEntity.created(new URI("/api/gift-wrappers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gift-wrappers : Updates an existing giftWrapper.
     *
     * @param giftWrapperDTO the giftWrapperDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated giftWrapperDTO,
     * or with status 400 (Bad Request) if the giftWrapperDTO is not valid,
     * or with status 500 (Internal Server Error) if the giftWrapperDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gift-wrappers")
    @Timed
    public ResponseEntity<GiftWrapperDTO> updateGiftWrapper(@Valid @RequestBody GiftWrapperDTO giftWrapperDTO) throws URISyntaxException {
        log.debug("REST request to update GiftWrapper : {}", giftWrapperDTO);
        if (giftWrapperDTO.getId() == null) {
            return createGiftWrapper(giftWrapperDTO);
        }
        GiftWrapperDTO result = giftWrapperService.save(giftWrapperDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, giftWrapperDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gift-wrappers : get all the giftWrappers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of giftWrappers in body
     */
    @GetMapping("/gift-wrappers")
    @Timed
    public List<GiftWrapperDTO> getAllGiftWrappers() {
        log.debug("REST request to get all GiftWrappers");
        return giftWrapperService.findAll();
        }

    /**
     * GET  /gift-wrappers/:id : get the "id" giftWrapper.
     *
     * @param id the id of the giftWrapperDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the giftWrapperDTO, or with status 404 (Not Found)
     */
    @GetMapping("/gift-wrappers/{id}")
    @Timed
    public ResponseEntity<GiftWrapperDTO> getGiftWrapper(@PathVariable Long id) {
        log.debug("REST request to get GiftWrapper : {}", id);
        GiftWrapperDTO giftWrapperDTO = giftWrapperService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(giftWrapperDTO));
    }

    /**
     * DELETE  /gift-wrappers/:id : delete the "id" giftWrapper.
     *
     * @param id the id of the giftWrapperDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gift-wrappers/{id}")
    @Timed
    public ResponseEntity<Void> deleteGiftWrapper(@PathVariable Long id) {
        log.debug("REST request to delete GiftWrapper : {}", id);
        giftWrapperService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
