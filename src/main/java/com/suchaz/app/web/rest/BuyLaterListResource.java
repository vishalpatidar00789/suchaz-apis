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
import com.suchaz.app.service.BuyLaterListService;
import com.suchaz.app.service.dto.BuyLaterListDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing BuyLaterList.
 */
@RestController
@RequestMapping("/api")
public class BuyLaterListResource {

    private final Logger log = LoggerFactory.getLogger(BuyLaterListResource.class);

    private static final String ENTITY_NAME = "buyLaterList";

    private final BuyLaterListService buyLaterListService;

    public BuyLaterListResource(BuyLaterListService buyLaterListService) {
        this.buyLaterListService = buyLaterListService;
    }

    /**
     * POST  /buy-later-lists : Create a new buyLaterList.
     *
     * @param buyLaterListDTO the buyLaterListDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new buyLaterListDTO, or with status 400 (Bad Request) if the buyLaterList has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/buy-later-lists")
    @Timed
    public ResponseEntity<BuyLaterListDTO> createBuyLaterList(@Valid @RequestBody BuyLaterListDTO buyLaterListDTO) throws URISyntaxException {
        log.debug("REST request to save BuyLaterList : {}", buyLaterListDTO);
        if (buyLaterListDTO.getId() != null) {
            throw new BadRequestAlertException("A new buyLaterList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BuyLaterListDTO result = buyLaterListService.save(buyLaterListDTO);
        return ResponseEntity.created(new URI("/api/buy-later-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /buy-later-lists : Updates an existing buyLaterList.
     *
     * @param buyLaterListDTO the buyLaterListDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated buyLaterListDTO,
     * or with status 400 (Bad Request) if the buyLaterListDTO is not valid,
     * or with status 500 (Internal Server Error) if the buyLaterListDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/buy-later-lists")
    @Timed
    public ResponseEntity<BuyLaterListDTO> updateBuyLaterList(@Valid @RequestBody BuyLaterListDTO buyLaterListDTO) throws URISyntaxException {
        log.debug("REST request to update BuyLaterList : {}", buyLaterListDTO);
        if (buyLaterListDTO.getId() == null) {
            return createBuyLaterList(buyLaterListDTO);
        }
        BuyLaterListDTO result = buyLaterListService.save(buyLaterListDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, buyLaterListDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /buy-later-lists : get all the buyLaterLists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of buyLaterLists in body
     */
    @GetMapping("/buy-later-lists")
    @Timed
    public List<BuyLaterListDTO> getAllBuyLaterLists() {
        log.debug("REST request to get all BuyLaterLists");
        return buyLaterListService.findAll();
        }

    /**
     * GET  /buy-later-lists/:id : get the "id" buyLaterList.
     *
     * @param id the id of the buyLaterListDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the buyLaterListDTO, or with status 404 (Not Found)
     */
    @GetMapping("/buy-later-lists/{id}")
    @Timed
    public ResponseEntity<BuyLaterListDTO> getBuyLaterList(@PathVariable Long id) {
        log.debug("REST request to get BuyLaterList : {}", id);
        BuyLaterListDTO buyLaterListDTO = buyLaterListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(buyLaterListDTO));
    }

    /**
     * DELETE  /buy-later-lists/:id : delete the "id" buyLaterList.
     *
     * @param id the id of the buyLaterListDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/buy-later-lists/{id}")
    @Timed
    public ResponseEntity<Void> deleteBuyLaterList(@PathVariable Long id) {
        log.debug("REST request to delete BuyLaterList : {}", id);
        buyLaterListService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
