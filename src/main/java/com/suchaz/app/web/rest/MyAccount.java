package com.suchaz.app.web.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.MyAccountService;
import com.suchaz.app.service.dto.MyAccountDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserProfile.
 */
@RestController
@RequestMapping("/api")
public class MyAccount {

    private final Logger log = LoggerFactory.getLogger(MyAccount.class);

    private static final String ENTITY_NAME = "myAccount";

    private final MyAccountService myAccountService;

    public MyAccount(MyAccountService myAccountService) {
        this.myAccountService = myAccountService;
    }
    /**
     * GET  /user-profiles/:id : get the "id" userProfile.
     *
     * @param id the id of the userProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-account/{id}")
    @Timed
    public ResponseEntity<MyAccountDTO> getMyAccount(@PathVariable Long id) {
        log.debug("REST request to get MyAccount : {}", id);
        MyAccountDTO myAccountDTO = myAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myAccountDTO));
    }

}
