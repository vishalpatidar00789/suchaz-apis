package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.ItemReviewService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.ItemReviewDTO;
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
 * REST controller for managing ItemReview.
 */
@RestController
@RequestMapping("/api")
public class ItemReviewResource {

    private final Logger log = LoggerFactory.getLogger(ItemReviewResource.class);

    private static final String ENTITY_NAME = "itemReview";

    private final ItemReviewService itemReviewService;

    public ItemReviewResource(ItemReviewService itemReviewService) {
        this.itemReviewService = itemReviewService;
    }

    /**
     * POST  /item-reviews : Create a new itemReview.
     *
     * @param itemReviewDTO the itemReviewDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemReviewDTO, or with status 400 (Bad Request) if the itemReview has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-reviews")
    @Timed
    public ResponseEntity<ItemReviewDTO> createItemReview(@Valid @RequestBody ItemReviewDTO itemReviewDTO) throws URISyntaxException {
        log.debug("REST request to save ItemReview : {}", itemReviewDTO);
        if (itemReviewDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemReview cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemReviewDTO result = itemReviewService.save(itemReviewDTO);
        return ResponseEntity.created(new URI("/api/item-reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-reviews : Updates an existing itemReview.
     *
     * @param itemReviewDTO the itemReviewDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemReviewDTO,
     * or with status 400 (Bad Request) if the itemReviewDTO is not valid,
     * or with status 500 (Internal Server Error) if the itemReviewDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-reviews")
    @Timed
    public ResponseEntity<ItemReviewDTO> updateItemReview(@Valid @RequestBody ItemReviewDTO itemReviewDTO) throws URISyntaxException {
        log.debug("REST request to update ItemReview : {}", itemReviewDTO);
        if (itemReviewDTO.getId() == null) {
            return createItemReview(itemReviewDTO);
        }
        ItemReviewDTO result = itemReviewService.save(itemReviewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemReviewDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-reviews : get all the itemReviews.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemReviews in body
     */
    @GetMapping("/item-reviews")
    @Timed
    public List<ItemReviewDTO> getAllItemReviews() {
        log.debug("REST request to get all ItemReviews");
        return itemReviewService.findAll();
        }

    /**
     * GET  /item-reviews/:id : get the "id" itemReview.
     *
     * @param id the id of the itemReviewDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemReviewDTO, or with status 404 (Not Found)
     */
    @GetMapping("/item-reviews/{id}")
    @Timed
    public ResponseEntity<ItemReviewDTO> getItemReview(@PathVariable Long id) {
        log.debug("REST request to get ItemReview : {}", id);
        ItemReviewDTO itemReviewDTO = itemReviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(itemReviewDTO));
    }

    /**
     * DELETE  /item-reviews/:id : delete the "id" itemReview.
     *
     * @param id the id of the itemReviewDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-reviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemReview(@PathVariable Long id) {
        log.debug("REST request to delete ItemReview : {}", id);
        itemReviewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
