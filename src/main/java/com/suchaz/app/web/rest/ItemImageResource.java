package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.ItemImageService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.ItemImageDTO;
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
 * REST controller for managing ItemImage.
 */
@RestController
@RequestMapping("/api")
public class ItemImageResource {

    private final Logger log = LoggerFactory.getLogger(ItemImageResource.class);

    private static final String ENTITY_NAME = "itemImage";

    private final ItemImageService itemImageService;

    public ItemImageResource(ItemImageService itemImageService) {
        this.itemImageService = itemImageService;
    }

    /**
     * POST  /item-images : Create a new itemImage.
     *
     * @param itemImageDTO the itemImageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemImageDTO, or with status 400 (Bad Request) if the itemImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-images")
    @Timed
    public ResponseEntity<ItemImageDTO> createItemImage(@Valid @RequestBody ItemImageDTO itemImageDTO) throws URISyntaxException {
        log.debug("REST request to save ItemImage : {}", itemImageDTO);
        if (itemImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemImageDTO result = itemImageService.save(itemImageDTO);
        return ResponseEntity.created(new URI("/api/item-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-images : Updates an existing itemImage.
     *
     * @param itemImageDTO the itemImageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemImageDTO,
     * or with status 400 (Bad Request) if the itemImageDTO is not valid,
     * or with status 500 (Internal Server Error) if the itemImageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-images")
    @Timed
    public ResponseEntity<ItemImageDTO> updateItemImage(@Valid @RequestBody ItemImageDTO itemImageDTO) throws URISyntaxException {
        log.debug("REST request to update ItemImage : {}", itemImageDTO);
        if (itemImageDTO.getId() == null) {
            return createItemImage(itemImageDTO);
        }
        ItemImageDTO result = itemImageService.save(itemImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-images : get all the itemImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemImages in body
     */
    @GetMapping("/item-images")
    @Timed
    public List<ItemImageDTO> getAllItemImages() {
        log.debug("REST request to get all ItemImages");
        return itemImageService.findAll();
        }

    /**
     * GET  /item-images/:id : get the "id" itemImage.
     *
     * @param id the id of the itemImageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemImageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/item-images/{id}")
    @Timed
    public ResponseEntity<ItemImageDTO> getItemImage(@PathVariable Long id) {
        log.debug("REST request to get ItemImage : {}", id);
        ItemImageDTO itemImageDTO = itemImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(itemImageDTO));
    }

    /**
     * DELETE  /item-images/:id : delete the "id" itemImage.
     *
     * @param id the id of the itemImageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemImage(@PathVariable Long id) {
        log.debug("REST request to delete ItemImage : {}", id);
        itemImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
