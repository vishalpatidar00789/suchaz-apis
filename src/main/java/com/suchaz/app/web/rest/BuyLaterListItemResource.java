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
import com.suchaz.app.service.BuyLaterListItemService;
import com.suchaz.app.service.dto.BuyLaterListItemDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing BuyLaterListItem.
 */
@RestController
@RequestMapping("/api")
public class BuyLaterListItemResource {

    private final Logger log = LoggerFactory.getLogger(BuyLaterListItemResource.class);

    private static final String ENTITY_NAME = "buyLaterListItem";

    private final BuyLaterListItemService buyLaterListItemService;

    public BuyLaterListItemResource(BuyLaterListItemService buyLaterListItemService) {
        this.buyLaterListItemService = buyLaterListItemService;
    }

    /**
     * POST  /buy-later-list-items : Create a new buyLaterListItem.
     *
     * @param buyLaterListItemDTO the buyLaterListItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new buyLaterListItemDTO, or with status 400 (Bad Request) if the buyLaterListItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/buy-later-list-items")
    @Timed
    public ResponseEntity<BuyLaterListItemDTO> createBuyLaterListItem(@Valid @RequestBody BuyLaterListItemDTO buyLaterListItemDTO) throws URISyntaxException {
        log.debug("REST request to save BuyLaterListItem : {}", buyLaterListItemDTO);
        if (buyLaterListItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new buyLaterListItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BuyLaterListItemDTO result = buyLaterListItemService.save(buyLaterListItemDTO);
        return ResponseEntity.created(new URI("/api/buy-later-list-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /buy-later-list-items : Updates an existing buyLaterListItem.
     *
     * @param buyLaterListItemDTO the buyLaterListItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated buyLaterListItemDTO,
     * or with status 400 (Bad Request) if the buyLaterListItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the buyLaterListItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/buy-later-list-items")
    @Timed
    public ResponseEntity<BuyLaterListItemDTO> updateBuyLaterListItem(@Valid @RequestBody BuyLaterListItemDTO buyLaterListItemDTO) throws URISyntaxException {
        log.debug("REST request to update BuyLaterListItem : {}", buyLaterListItemDTO);
        if (buyLaterListItemDTO.getId() == null) {
            return createBuyLaterListItem(buyLaterListItemDTO);
        }
        BuyLaterListItemDTO result = buyLaterListItemService.save(buyLaterListItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, buyLaterListItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /buy-later-list-items : get all the buyLaterListItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of buyLaterListItems in body
     */
    @GetMapping("/buy-later-list-items")
    @Timed
    public List<BuyLaterListItemDTO> getAllBuyLaterListItems() {
        log.debug("REST request to get all BuyLaterListItems");
        return buyLaterListItemService.findAll();
        }

    /**
     * GET  /buy-later-list-items/:id : get the "id" buyLaterListItem.
     *
     * @param id the id of the buyLaterListItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the buyLaterListItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/buy-later-list-items/{id}")
    @Timed
    public ResponseEntity<BuyLaterListItemDTO> getBuyLaterListItem(@PathVariable Long id) {
        log.debug("REST request to get BuyLaterListItem : {}", id);
        BuyLaterListItemDTO buyLaterListItemDTO = buyLaterListItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(buyLaterListItemDTO));
    }

    /**
     * DELETE  /buy-later-list-items/:id : delete the "id" buyLaterListItem.
     *
     * @param id the id of the buyLaterListItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/buy-later-list-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteBuyLaterListItem(@PathVariable Long id) {
        log.debug("REST request to delete BuyLaterListItem : {}", id);
        buyLaterListItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
