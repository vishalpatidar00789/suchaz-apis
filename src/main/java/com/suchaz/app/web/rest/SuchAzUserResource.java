package com.suchaz.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.domain.enumeration.UserRole;
import com.suchaz.app.service.MailService;
import com.suchaz.app.service.SuchAzUserService;
import com.suchaz.app.service.dto.SuchAzUserDTO;
import com.suchaz.app.service.dto.UserProfileDTO;
import com.suchaz.app.service.view.SignUpView;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing SuchAzUser.
 */
@RestController
@RequestMapping("/api")
public class SuchAzUserResource {

    private final Logger log = LoggerFactory.getLogger(SuchAzUserResource.class);

    private static final String ENTITY_NAME = "suchAzUser";

    private final SuchAzUserService suchAzUserService;
    
    private final MailService mailService;

    public SuchAzUserResource(SuchAzUserService suchAzUserService, MailService mailService) {
        this.suchAzUserService = suchAzUserService;
        this.mailService = mailService;
    }
    /**
     * POST  /such-az-users : Create a new suchAzUser.
     *
     * @param suchAzUserDTO the suchAzUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new suchAzUserDTO, or with status 400 (Bad Request) if the suchAzUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/signup")
    @Timed
    public ResponseEntity<String> signUP(@Valid @RequestBody SignUpView signUpView) throws URISyntaxException {
        log.debug("REST request to SignUp SuchAzUser : {}", signUpView);
        
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setFullName(signUpView.getFullName());
        userProfileDTO.setCreatedBy("SYSTEM");
        userProfileDTO.setCreatedDate(new Date().getTime());
        SuchAzUserDTO suchAzUserDTO = new SuchAzUserDTO();
        suchAzUserDTO.setEmail(signUpView.getEmail());
        suchAzUserDTO.setPassword(signUpView.getPassword());
        suchAzUserDTO.setRole(UserRole.GIFTER);
        suchAzUserDTO.setIsVarified("N");
        suchAzUserDTO.setStatus(Status.INACTIVE);
        suchAzUserDTO.setCreatedBy("SYSTEM");
        suchAzUserDTO.setCreatedDate(new Date().getTime());
        return createSuchAzUserAPI(suchAzUserDTO);
    }
    
    
    public ResponseEntity<String> createSuchAzUserAPI(@Valid SuchAzUserDTO suchAzUserDTO) throws URISyntaxException {
        log.debug("REST request to save SuchAzUser : {}", suchAzUserDTO);
        if (suchAzUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new suchAzUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuchAzUserDTO result = suchAzUserService.save(suchAzUserDTO);
        mailService.sendActivationEmailToSuchAzUser(suchAzUserDTO);
        return ResponseEntity.ok()//created(new URI("/api/such-az-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body("Success");
            //.build();
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
