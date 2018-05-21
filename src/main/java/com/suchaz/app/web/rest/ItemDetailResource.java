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
import com.suchaz.app.service.ItemDetailService;
import com.suchaz.app.service.dto.ItemDetailsDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for Item Details.
 */
@RestController
@RequestMapping("/api")
public class ItemDetailResource {

    private final Logger log = LoggerFactory.getLogger(ItemDetailResource.class);

    private final ItemDetailService itemDetailService;

    public ItemDetailResource(ItemDetailService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }
    /**
     * GET  /Item Details: get Item Details.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the ItemDetailsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/itemDetails/{itemId}")
    @Timed
    public ResponseEntity<ItemDetailsDTO> getItemDetails(@PathVariable Long itemId) {
        log.debug("REST request to get ItemDetails : {}", itemId);
        ItemDetailsDTO itemDetailsDTO = itemDetailService.findOne(itemId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(itemDetailsDTO));
    }

}
