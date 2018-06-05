package com.suchaz.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.suchaz.app.service.QuickViewService;
import com.suchaz.app.service.SuchAzMenuService;
import com.suchaz.app.service.dto.ItemDTO;
import com.suchaz.app.service.dto.QuickViewDTO;
import com.suchaz.app.service.dto.SuchAzMenuDTO;
import com.suchaz.app.service.impl.QuickViewServiceImpl;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing SuchAzMenu.
 */
@RestController
@RequestMapping("/api")
public class SuchAzMenuResource {

    private final Logger log = LoggerFactory.getLogger(SuchAzMenuResource.class);

    private static final String ENTITY_NAME = "suchAzMenu";

    private final SuchAzMenuService suchAzMenuService;
    
    private final QuickViewService quickViewService;
    
    
    public SuchAzMenuResource(SuchAzMenuService suchAzMenuService, QuickViewService quickViewService) {
        this.suchAzMenuService = suchAzMenuService;
        this.quickViewService = quickViewService;
    }
    

    /**
     * POST  /such-az-menus : Create a new suchAzMenu.
     *
     * @param suchAzMenuDTO the suchAzMenuDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new suchAzMenuDTO, or with status 400 (Bad Request) if the suchAzMenu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/such-az-menus")
    @Timed
    public ResponseEntity<SuchAzMenuDTO> createSuchAzMenu(@Valid @RequestBody SuchAzMenuDTO suchAzMenuDTO) throws URISyntaxException {
        log.debug("REST request to save SuchAzMenu : {}", suchAzMenuDTO);
        if (suchAzMenuDTO.getId() != null) {
            throw new BadRequestAlertException("A new suchAzMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuchAzMenuDTO result = suchAzMenuService.save(suchAzMenuDTO);
        return ResponseEntity.created(new URI("/api/such-az-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /such-az-menus : Updates an existing suchAzMenu.
     *
     * @param suchAzMenuDTO the suchAzMenuDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated suchAzMenuDTO,
     * or with status 400 (Bad Request) if the suchAzMenuDTO is not valid,
     * or with status 500 (Internal Server Error) if the suchAzMenuDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/such-az-menus")
    @Timed
    public ResponseEntity<SuchAzMenuDTO> updateSuchAzMenu(@Valid @RequestBody SuchAzMenuDTO suchAzMenuDTO) throws URISyntaxException {
        log.debug("REST request to update SuchAzMenu : {}", suchAzMenuDTO);
        if (suchAzMenuDTO.getId() == null) {
            return createSuchAzMenu(suchAzMenuDTO);
        }
        SuchAzMenuDTO result = suchAzMenuService.save(suchAzMenuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, suchAzMenuDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /such-az-menus : get all the suchAzMenus.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of suchAzMenus in body
     */
    @GetMapping("/such-az-menus")
    @Timed
    public List<SuchAzMenuDTO> getAllSuchAzMenus() {
        log.debug("REST request to get all SuchAzMenus");
        return suchAzMenuService.findAll();
        }

    /**
     * GET  /such-az-menus/:id : get the "id" suchAzMenu.
     *
     * @param id the id of the suchAzMenuDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the suchAzMenuDTO, or with status 404 (Not Found)
     */
    @GetMapping("/such-az-menus/{id}")
    @Timed
    public ResponseEntity<SuchAzMenuDTO> getSuchAzMenu(@PathVariable Long id) {
        log.debug("REST request to get SuchAzMenu : {}", id);
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(suchAzMenuDTO));
    }
    
    /**
     * GET  /such-az-menus/:id : get the "id" suchAzMenu.
     *
     * @param id the id of the suchAzMenuDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the suchAzMenuDTO, or with status 404 (Not Found)
     */
    @GetMapping("/such-az-menusWithQuickView/{id}")
    @Timed
    public ResponseEntity<SuchAzMenuDTO> getSuchAzMenuWithQuickView(@PathVariable Long id) {
        log.debug("REST request to get SuchAzMenu : {}", id);
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuService.findOne(id);
        if(null != suchAzMenuDTO.getItems() && suchAzMenuDTO.getItems().size() > 0) {
	        Long[] itemIds = new Long[suchAzMenuDTO.getItems().size()];
	        Iterator<ItemDTO> itemIterator = suchAzMenuDTO.getItems().iterator();
	        for(int i=0;i<suchAzMenuDTO.getItems().size() && itemIterator.hasNext();i++) {
	        	itemIds[i] = itemIterator.next().getId();
	        }
	        suchAzMenuDTO.setQuickViewItems(new HashSet(quickViewService.findRangeOfItem(itemIds)));
        }
        suchAzMenuDTO.setItems(null);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(suchAzMenuDTO));
    }

    /**
     * DELETE  /such-az-menus/:id : delete the "id" suchAzMenu.
     *
     * @param id the id of the suchAzMenuDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/such-az-menus/{id}")
    @Timed
    public ResponseEntity<Void> deleteSuchAzMenu(@PathVariable Long id) {
        log.debug("REST request to delete SuchAzMenu : {}", id);
        suchAzMenuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
