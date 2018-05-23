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
import com.suchaz.app.service.MyAccountService;
import com.suchaz.app.service.ProductSearchService;
import com.suchaz.app.service.dto.MyAccountDTO;
import com.suchaz.app.service.dto.ProductSearchResultDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for searching products.
 */
@RestController
@RequestMapping("/api")
public class ProductSearchResource {

    private final Logger log = LoggerFactory.getLogger(ProductSearchResource.class);

    private static final String ENTITY_NAME = "ProductSearch";

    private final ProductSearchService productSearchService;

    public ProductSearchResource(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }
    /**
     * GET  
     *
     * @param keyword to search for Item Title
     * @return the ResponseEntity with status 200 (OK) and with body the ProductSearchResultDTO, or with status 404 (Not Found) along with ErrorMessage if No Results found
     */
    @GetMapping("/search-products/{keyWord}")
    @Timed
    public ResponseEntity<ProductSearchResultDTO> searchProductsWithKeyWords(@PathVariable String keyWord) {
        log.debug("REST request to search products with keyword : {}", keyWord);
        ProductSearchResultDTO productSearchResultDTO = productSearchService.searchItemsWithKeyWord(keyWord);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productSearchResultDTO));
    }

}
