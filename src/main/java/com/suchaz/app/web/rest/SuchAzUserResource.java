package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.SuchAzUserService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.web.rest.util.PaginationUtil;
import com.suchaz.app.service.dto.SuchAzUserDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing SuchAzUser.
 */
@RestController
@RequestMapping("/api")
public class SuchAzUserResource {

    private final Logger log = LoggerFactory.getLogger(SuchAzUserResource.class);

    private static final String ENTITY_NAME = "suchAzUser";

    private final SuchAzUserService suchAzUserService;

    public SuchAzUserResource(SuchAzUserService suchAzUserService) {
        this.suchAzUserService = suchAzUserService;
    }

    /**
     * POST  /such-az-users : Create a new suchAzUser.
     *
     * @param suchAzUserDTO the suchAzUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new suchAzUserDTO, or with status 400 (Bad Request) if the suchAzUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/such-az-users")
    @Timed
    public ResponseEntity<SuchAzUserDTO> createSuchAzUser(@Valid @RequestBody SuchAzUserDTO suchAzUserDTO) throws URISyntaxException {
        log.debug("REST request to save SuchAzUser : {}", suchAzUserDTO);
        if (suchAzUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new suchAzUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuchAzUserDTO result = suchAzUserService.save(suchAzUserDTO);
        return ResponseEntity.created(new URI("/api/such-az-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /such-az-users : Updates an existing suchAzUser.
     *
     * @param suchAzUserDTO the suchAzUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated suchAzUserDTO,
     * or with status 400 (Bad Request) if the suchAzUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the suchAzUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/such-az-users")
    @Timed
    public ResponseEntity<SuchAzUserDTO> updateSuchAzUser(@Valid @RequestBody SuchAzUserDTO suchAzUserDTO) throws URISyntaxException {
        log.debug("REST request to update SuchAzUser : {}", suchAzUserDTO);
        if (suchAzUserDTO.getId() == null) {
            return createSuchAzUser(suchAzUserDTO);
        }
        SuchAzUserDTO result = suchAzUserService.save(suchAzUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, suchAzUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /such-az-users : get all the suchAzUsers.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of suchAzUsers in body
     */
    @GetMapping("/such-az-users")
    @Timed
    public ResponseEntity<List<SuchAzUserDTO>> getAllSuchAzUsers(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("userprofile-is-null".equals(filter)) {
            log.debug("REST request to get all SuchAzUsers where userProfile is null");
            return new ResponseEntity<>(suchAzUserService.findAllWhereUserProfileIsNull(),
                    HttpStatus.OK);
        }
        if ("wishlist-is-null".equals(filter)) {
            log.debug("REST request to get all SuchAzUsers where wishList is null");
            return new ResponseEntity<>(suchAzUserService.findAllWhereWishListIsNull(),
                    HttpStatus.OK);
        }
        if ("buylaterlist-is-null".equals(filter)) {
            log.debug("REST request to get all SuchAzUsers where buyLaterList is null");
            return new ResponseEntity<>(suchAzUserService.findAllWhereBuyLaterListIsNull(),
                    HttpStatus.OK);
        }
        if ("activitylist-is-null".equals(filter)) {
            log.debug("REST request to get all SuchAzUsers where activityList is null");
            return new ResponseEntity<>(suchAzUserService.findAllWhereActivityListIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of SuchAzUsers");
        Page<SuchAzUserDTO> page = suchAzUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/such-az-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /such-az-users/:id : get the "id" suchAzUser.
     *
     * @param id the id of the suchAzUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the suchAzUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/such-az-users/{id}")
    @Timed
    public ResponseEntity<SuchAzUserDTO> getSuchAzUser(@PathVariable Long id) {
        log.debug("REST request to get SuchAzUser : {}", id);
        SuchAzUserDTO suchAzUserDTO = suchAzUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(suchAzUserDTO));
    }

    /**
     * DELETE  /such-az-users/:id : delete the "id" suchAzUser.
     *
     * @param id the id of the suchAzUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/such-az-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteSuchAzUser(@PathVariable Long id) {
        log.debug("REST request to delete SuchAzUser : {}", id);
        suchAzUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
