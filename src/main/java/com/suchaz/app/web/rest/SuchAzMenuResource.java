package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.SuchAzMenuService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.SuchAzMenuDTO;
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
 * REST controller for managing SuchAzMenu.
 */
@RestController
@RequestMapping("/api")
public class SuchAzMenuResource {

    private final Logger log = LoggerFactory.getLogger(SuchAzMenuResource.class);

    private static final String ENTITY_NAME = "suchAzMenu";

    private final SuchAzMenuService suchAzMenuService;

    public SuchAzMenuResource(SuchAzMenuService suchAzMenuService) {
        this.suchAzMenuService = suchAzMenuService;
    }

    /**
     * POST  /such-az-menus : Create a new suchAzMenu.
     *
     * @param suchAzMenuDTO the suchAzMenuDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new suchAzMenuDTO, or with status 400 (Bad Request) if the suchAzMenu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/such-az-menus")
    @Timed
    public ResponseEntity<SuchAzMenuDTO> createSuchAzMenu(@Valid @RequestBody SuchAzMenuDTO suchAzMenuDTO) throws URISyntaxException {
        log.debug("REST request to save SuchAzMenu : {}", suchAzMenuDTO);
        if (suchAzMenuDTO.getId() != null) {
            throw new BadRequestAlertException("A new suchAzMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuchAzMenuDTO result = suchAzMenuService.save(suchAzMenuDTO);
        return ResponseEntity.created(new URI("/api/such-az-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /such-az-menus : Updates an existing suchAzMenu.
     *
     * @param suchAzMenuDTO the suchAzMenuDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated suchAzMenuDTO,
     * or with status 400 (Bad Request) if the suchAzMenuDTO is not valid,
     * or with status 500 (Internal Server Error) if the suchAzMenuDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/such-az-menus")
    @Timed
    public ResponseEntity<SuchAzMenuDTO> updateSuchAzMenu(@Valid @RequestBody SuchAzMenuDTO suchAzMenuDTO) throws URISyntaxException {
        log.debug("REST request to update SuchAzMenu : {}", suchAzMenuDTO);
        if (suchAzMenuDTO.getId() == null) {
            return createSuchAzMenu(suchAzMenuDTO);
        }
        SuchAzMenuDTO result = suchAzMenuService.save(suchAzMenuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, suchAzMenuDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /such-az-menus : get all the suchAzMenus.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of suchAzMenus in body
     */
    @GetMapping("/such-az-menus")
    @Timed
    public List<SuchAzMenuDTO> getAllSuchAzMenus() {
        log.debug("REST request to get all SuchAzMenus");
        return suchAzMenuService.findAll();
        }

    /**
     * GET  /such-az-menus/:id : get the "id" suchAzMenu.
     *
     * @param id the id of the suchAzMenuDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the suchAzMenuDTO, or with status 404 (Not Found)
     */
    @GetMapping("/such-az-menus/{id}")
    @Timed
    public ResponseEntity<SuchAzMenuDTO> getSuchAzMenu(@PathVariable Long id) {
        log.debug("REST request to get SuchAzMenu : {}", id);
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(suchAzMenuDTO));
    }

    /**
     * DELETE  /such-az-menus/:id : delete the "id" suchAzMenu.
     *
     * @param id the id of the suchAzMenuDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/such-az-menus/{id}")
    @Timed
    public ResponseEntity<Void> deleteSuchAzMenu(@PathVariable Long id) {
        log.debug("REST request to delete SuchAzMenu : {}", id);
        suchAzMenuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
