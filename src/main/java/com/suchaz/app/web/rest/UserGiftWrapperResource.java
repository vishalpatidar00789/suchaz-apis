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
import com.suchaz.app.service.UserGiftWrapperService;
import com.suchaz.app.service.dto.UserGiftWrapperDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserGiftWrapper.
 */
@RestController
@RequestMapping("/api")
public class UserGiftWrapperResource {

    private final Logger log = LoggerFactory.getLogger(UserGiftWrapperResource.class);

    private static final String ENTITY_NAME = "userGiftWrapper";

    private final UserGiftWrapperService userGiftWrapperService;

    public UserGiftWrapperResource(UserGiftWrapperService userGiftWrapperService) {
        this.userGiftWrapperService = userGiftWrapperService;
    }

    /**
     * POST  /user-gift-wrappers : Create a new userGiftWrapper.
     *
     * @param userGiftWrapperDTO the userGiftWrapperDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userGiftWrapperDTO, or with status 400 (Bad Request) if the userGiftWrapper has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-gift-wrappers")
    @Timed
    public ResponseEntity<UserGiftWrapperDTO> createUserGiftWrapper(@Valid @RequestBody UserGiftWrapperDTO userGiftWrapperDTO) throws URISyntaxException {
        log.debug("REST request to save UserGiftWrapper : {}", userGiftWrapperDTO);
        if (userGiftWrapperDTO.getId() != null) {
            throw new BadRequestAlertException("A new userGiftWrapper cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserGiftWrapperDTO result = userGiftWrapperService.save(userGiftWrapperDTO);
        return ResponseEntity.created(new URI("/api/user-gift-wrappers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-gift-wrappers : Updates an existing userGiftWrapper.
     *
     * @param userGiftWrapperDTO the userGiftWrapperDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userGiftWrapperDTO,
     * or with status 400 (Bad Request) if the userGiftWrapperDTO is not valid,
     * or with status 500 (Internal Server Error) if the userGiftWrapperDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-gift-wrappers")
    @Timed
    public ResponseEntity<UserGiftWrapperDTO> updateUserGiftWrapper(@Valid @RequestBody UserGiftWrapperDTO userGiftWrapperDTO) throws URISyntaxException {
        log.debug("REST request to update UserGiftWrapper : {}", userGiftWrapperDTO);
        if (userGiftWrapperDTO.getId() == null) {
            return createUserGiftWrapper(userGiftWrapperDTO);
        }
        UserGiftWrapperDTO result = userGiftWrapperService.save(userGiftWrapperDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userGiftWrapperDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-gift-wrappers : get all the userGiftWrappers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userGiftWrappers in body
     */
    @GetMapping("/user-gift-wrappers")
    @Timed
    public List<UserGiftWrapperDTO> getAllUserGiftWrappers() {
        log.debug("REST request to get all UserGiftWrappers");
        return userGiftWrapperService.findAll();
        }

    /**
     * GET  /user-gift-wrappers/:id : get the "id" userGiftWrapper.
     *
     * @param id the id of the userGiftWrapperDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userGiftWrapperDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-gift-wrappers/{id}")
    @Timed
    public ResponseEntity<UserGiftWrapperDTO> getUserGiftWrapper(@PathVariable Long id) {
        log.debug("REST request to get UserGiftWrapper : {}", id);
        UserGiftWrapperDTO userGiftWrapperDTO = userGiftWrapperService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userGiftWrapperDTO));
    }

    /**
     * DELETE  /user-gift-wrappers/:id : delete the "id" userGiftWrapper.
     *
     * @param id the id of the userGiftWrapperDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-gift-wrappers/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserGiftWrapper(@PathVariable Long id) {
        log.debug("REST request to delete UserGiftWrapper : {}", id);
        userGiftWrapperService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
