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
import com.suchaz.app.service.CategoryImageService;
import com.suchaz.app.service.dto.CategoryImageDTO;
import com.suchaz.app.web.rest.errors.BadRequestAlertException;
import com.suchaz.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing CategoryImage.
 */
@RestController
@RequestMapping("/api")
public class CategoryImageResource {

    private final Logger log = LoggerFactory.getLogger(CategoryImageResource.class);

    private static final String ENTITY_NAME = "categoryImage";

    private final CategoryImageService categoryImageService;

    public CategoryImageResource(CategoryImageService categoryImageService) {
        this.categoryImageService = categoryImageService;
    }

    /**
     * POST  /category-images : Create a new categoryImage.
     *
     * @param categoryImageDTO the categoryImageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categoryImageDTO, or with status 400 (Bad Request) if the categoryImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/category-images")
    @Timed
    public ResponseEntity<CategoryImageDTO> createCategoryImage(@Valid @RequestBody CategoryImageDTO categoryImageDTO) throws URISyntaxException {
        log.debug("REST request to save CategoryImage : {}", categoryImageDTO);
        if (categoryImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new categoryImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryImageDTO result = categoryImageService.save(categoryImageDTO);
        return ResponseEntity.created(new URI("/api/category-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /category-images : Updates an existing categoryImage.
     *
     * @param categoryImageDTO the categoryImageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoryImageDTO,
     * or with status 400 (Bad Request) if the categoryImageDTO is not valid,
     * or with status 500 (Internal Server Error) if the categoryImageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/category-images")
    @Timed
    public ResponseEntity<CategoryImageDTO> updateCategoryImage(@Valid @RequestBody CategoryImageDTO categoryImageDTO) throws URISyntaxException {
        log.debug("REST request to update CategoryImage : {}", categoryImageDTO);
        if (categoryImageDTO.getId() == null) {
            return createCategoryImage(categoryImageDTO);
        }
        CategoryImageDTO result = categoryImageService.save(categoryImageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categoryImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /category-images : get all the categoryImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categoryImages in body
     */
    @GetMapping("/category-images")
    @Timed
    public List<CategoryImageDTO> getAllCategoryImages() {
        log.debug("REST request to get all CategoryImages");
        return categoryImageService.findAll();
        }

    /**
     * GET  /category-images/:id : get the "id" categoryImage.
     *
     * @param id the id of the categoryImageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categoryImageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/category-images/{id}")
    @Timed
    public ResponseEntity<CategoryImageDTO> getCategoryImage(@PathVariable Long id) {
        log.debug("REST request to get CategoryImage : {}", id);
        CategoryImageDTO categoryImageDTO = categoryImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(categoryImageDTO));
    }

    /**
     * DELETE  /category-images/:id : delete the "id" categoryImage.
     *
     * @param id the id of the categoryImageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/category-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteCategoryImage(@PathVariable Long id) {
        log.debug("REST request to delete CategoryImage : {}", id);
        categoryImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
