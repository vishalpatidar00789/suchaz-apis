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
import com.suchaz.app.service.WishListItemService;
import com.suchaz.app.service.dto.WishListItemDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing WishListItem.
 */
@RestController
@RequestMapping("/api")
public class WishListItemResource {

    private final Logger log = LoggerFactory.getLogger(WishListItemResource.class);

    private static final String ENTITY_NAME = "wishListItem";

    private final WishListItemService wishListItemService;

    public WishListItemResource(WishListItemService wishListItemService) {
        this.wishListItemService = wishListItemService;
    }

    /**
     * POST  /wish-list-items : Create a new wishListItem.
     *
     * @param wishListItemDTO the wishListItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wishListItemDTO, or with status 400 (Bad Request) if the wishListItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wish-list-items")
    @Timed
    public ResponseEntity<WishListItemDTO> createWishListItem(@Valid @RequestBody WishListItemDTO wishListItemDTO) throws URISyntaxException {
        log.debug("REST request to save WishListItem : {}", wishListItemDTO);
        if (wishListItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new wishListItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WishListItemDTO result = wishListItemService.save(wishListItemDTO);
        return ResponseEntity.created(new URI("/api/wish-list-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wish-list-items : Updates an existing wishListItem.
     *
     * @param wishListItemDTO the wishListItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wishListItemDTO,
     * or with status 400 (Bad Request) if the wishListItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the wishListItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wish-list-items")
    @Timed
    public ResponseEntity<WishListItemDTO> updateWishListItem(@Valid @RequestBody WishListItemDTO wishListItemDTO) throws URISyntaxException {
        log.debug("REST request to update WishListItem : {}", wishListItemDTO);
        if (wishListItemDTO.getId() == null) {
            return createWishListItem(wishListItemDTO);
        }
        WishListItemDTO result = wishListItemService.save(wishListItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wishListItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wish-list-items : get all the wishListItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of wishListItems in body
     */
    @GetMapping("/wish-list-items")
    @Timed
    public List<WishListItemDTO> getAllWishListItems() {
        log.debug("REST request to get all WishListItems");
        return wishListItemService.findAll();
        }

    /**
     * GET  /wish-list-items/:id : get the "id" wishListItem.
     *
     * @param id the id of the wishListItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wishListItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wish-list-items/{id}")
    @Timed
    public ResponseEntity<WishListItemDTO> getWishListItem(@PathVariable Long id) {
        log.debug("REST request to get WishListItem : {}", id);
        WishListItemDTO wishListItemDTO = wishListItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wishListItemDTO));
    }

    /**
     * DELETE  /wish-list-items/:id : delete the "id" wishListItem.
     *
     * @param id the id of the wishListItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wish-list-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteWishListItem(@PathVariable Long id) {
        log.debug("REST request to delete WishListItem : {}", id);
        wishListItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
