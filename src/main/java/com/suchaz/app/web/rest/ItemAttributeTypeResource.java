package com.suchaz.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.ItemAttributeTypeService;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;
import com.suchaz.app.service.dto.ItemAttributeTypeDTO;
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
 * REST controller for managing ItemAttributeType.
 */
@RestController
@RequestMapping("/api")
public class ItemAttributeTypeResource {

    private final Logger log = LoggerFactory.getLogger(ItemAttributeTypeResource.class);

    private static final String ENTITY_NAME = "itemAttributeType";

    private final ItemAttributeTypeService itemAttributeTypeService;

    public ItemAttributeTypeResource(ItemAttributeTypeService itemAttributeTypeService) {
        this.itemAttributeTypeService = itemAttributeTypeService;
    }

    /**
     * POST  /item-attribute-types : Create a new itemAttributeType.
     *
     * @param itemAttributeTypeDTO the itemAttributeTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAttributeTypeDTO, or with status 400 (Bad Request) if the itemAttributeType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-attribute-types")
    @Timed
    public ResponseEntity<ItemAttributeTypeDTO> createItemAttributeType(@Valid @RequestBody ItemAttributeTypeDTO itemAttributeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ItemAttributeType : {}", itemAttributeTypeDTO);
        if (itemAttributeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemAttributeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAttributeTypeDTO result = itemAttributeTypeService.save(itemAttributeTypeDTO);
        return ResponseEntity.created(new URI("/api/item-attribute-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-attribute-types : Updates an existing itemAttributeType.
     *
     * @param itemAttributeTypeDTO the itemAttributeTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAttributeTypeDTO,
     * or with status 400 (Bad Request) if the itemAttributeTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the itemAttributeTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-attribute-types")
    @Timed
    public ResponseEntity<ItemAttributeTypeDTO> updateItemAttributeType(@Valid @RequestBody ItemAttributeTypeDTO itemAttributeTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ItemAttributeType : {}", itemAttributeTypeDTO);
        if (itemAttributeTypeDTO.getId() == null) {
            return createItemAttributeType(itemAttributeTypeDTO);
        }
        ItemAttributeTypeDTO result = itemAttributeTypeService.save(itemAttributeTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAttributeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-attribute-types : get all the itemAttributeTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemAttributeTypes in body
     */
    @GetMapping("/item-attribute-types")
    @Timed
    public List<ItemAttributeTypeDTO> getAllItemAttributeTypes() {
        log.debug("REST request to get all ItemAttributeTypes");
        return itemAttributeTypeService.findAll();
        }

    /**
     * GET  /item-attribute-types/:id : get the "id" itemAttributeType.
     *
     * @param id the id of the itemAttributeTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAttributeTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/item-attribute-types/{id}")
    @Timed
    public ResponseEntity<ItemAttributeTypeDTO> getItemAttributeType(@PathVariable Long id) {
        log.debug("REST request to get ItemAttributeType : {}", id);
        ItemAttributeTypeDTO itemAttributeTypeDTO = itemAttributeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(itemAttributeTypeDTO));
    }

    /**
     * DELETE  /item-attribute-types/:id : delete the "id" itemAttributeType.
     *
     * @param id the id of the itemAttributeTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-attribute-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAttributeType(@PathVariable Long id) {
        log.debug("REST request to delete ItemAttributeType : {}", id);
        itemAttributeTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
