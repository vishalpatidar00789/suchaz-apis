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
import com.suchaz.app.service.ItemCommonAttributeService;
import com.suchaz.app.service.dto.ItemCommonAttributeDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ItemCommonAttribute.
 */
@RestController
@RequestMapping("/api")
public class ItemCommonAttributeResource {

    private final Logger log = LoggerFactory.getLogger(ItemCommonAttributeResource.class);

    private static final String ENTITY_NAME = "itemCommonAttribute";

    private final ItemCommonAttributeService itemCommonAttributeService;

    public ItemCommonAttributeResource(ItemCommonAttributeService itemCommonAttributeService) {
        this.itemCommonAttributeService = itemCommonAttributeService;
    }

    /**
     * POST  /item-common-attributes : Create a new itemCommonAttribute.
     *
     * @param itemCommonAttributeDTO the itemCommonAttributeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemCommonAttributeDTO, or with status 400 (Bad Request) if the itemCommonAttribute has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-common-attributes")
    @Timed
    public ResponseEntity<ItemCommonAttributeDTO> createItemCommonAttribute(@Valid @RequestBody ItemCommonAttributeDTO itemCommonAttributeDTO) throws URISyntaxException {
        log.debug("REST request to save ItemCommonAttribute : {}", itemCommonAttributeDTO);
        if (itemCommonAttributeDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemCommonAttribute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemCommonAttributeDTO result = itemCommonAttributeService.save(itemCommonAttributeDTO);
        return ResponseEntity.created(new URI("/api/item-common-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-common-attributes : Updates an existing itemCommonAttribute.
     *
     * @param itemCommonAttributeDTO the itemCommonAttributeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemCommonAttributeDTO,
     * or with status 400 (Bad Request) if the itemCommonAttributeDTO is not valid,
     * or with status 500 (Internal Server Error) if the itemCommonAttributeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-common-attributes")
    @Timed
    public ResponseEntity<ItemCommonAttributeDTO> updateItemCommonAttribute(@Valid @RequestBody ItemCommonAttributeDTO itemCommonAttributeDTO) throws URISyntaxException {
        log.debug("REST request to update ItemCommonAttribute : {}", itemCommonAttributeDTO);
        if (itemCommonAttributeDTO.getId() == null) {
            return createItemCommonAttribute(itemCommonAttributeDTO);
        }
        ItemCommonAttributeDTO result = itemCommonAttributeService.save(itemCommonAttributeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemCommonAttributeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-common-attributes : get all the itemCommonAttributes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemCommonAttributes in body
     */
    @GetMapping("/item-common-attributes")
    @Timed
    public List<ItemCommonAttributeDTO> getAllItemCommonAttributes() {
        log.debug("REST request to get all ItemCommonAttributes");
        return itemCommonAttributeService.findAll();
        }

    /**
     * GET  /item-common-attributes/:id : get the "id" itemCommonAttribute.
     *
     * @param id the id of the itemCommonAttributeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemCommonAttributeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/item-common-attributes/{id}")
    @Timed
    public ResponseEntity<ItemCommonAttributeDTO> getItemCommonAttribute(@PathVariable Long id) {
        log.debug("REST request to get ItemCommonAttribute : {}", id);
        ItemCommonAttributeDTO itemCommonAttributeDTO = itemCommonAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(itemCommonAttributeDTO));
    }

    /**
     * DELETE  /item-common-attributes/:id : delete the "id" itemCommonAttribute.
     *
     * @param id the id of the itemCommonAttributeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-common-attributes/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemCommonAttribute(@PathVariable Long id) {
        log.debug("REST request to delete ItemCommonAttribute : {}", id);
        itemCommonAttributeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
