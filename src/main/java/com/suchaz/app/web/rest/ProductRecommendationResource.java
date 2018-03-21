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
import com.suchaz.app.service.dto.MyAccountDTO;
import com.suchaz.app.service.dto.ProductSearchResultDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserProfile.
 */
@RestController
@RequestMapping("/api")
public class ProductRecommendationResource {

    private final Logger log = LoggerFactory.getLogger(ProductRecommendationResource.class);

    private static final String ENTITY_NAME = "productRecommendation";

    private final MyAccountService myAccountService;

    public ProductRecommendationResource(MyAccountService myAccountService) {
        this.myAccountService = myAccountService;
    }
    /**
     * GET  /user-profiles/:id : get the "id" userProfile.
     *
     * @param id the id of the userProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/search-product/{ageGroup,gender,triatCode}")
    @Timed
    public ResponseEntity<ProductSearchResultDTO> searchProducts(@PathVariable String ageGroup,@PathVariable String gender,@PathVariable String triatsCode) {
        log.debug("REST request to get Search Product : ageGroup: {}, gender : {}, traitsCode : {}", ageGroup,gender,triatsCode);
       // ProductSearchResultDTO ProductSearchResultDTO = myAccountService.findOne(id);
      //  return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ProductSearchResultDTO));
        return null;
    }

}
