package com.suchaz.app.web.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.ProductRecommendationService;
import com.suchaz.app.service.dto.ProductSearchResultDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserProfile.
 */
@RestController
@RequestMapping("/api")
public class ProductRecommendationResource {

    private final Logger log = LoggerFactory.getLogger(ProductRecommendationResource.class);

    //private static final String ENTITY_NAME = "productRecommendation";

    private final ProductRecommendationService productRecommendationService;

    public ProductRecommendationResource(ProductRecommendationService productRecommendationService) {
        this.productRecommendationService = productRecommendationService;
    }
    /**
     * GET  /Recommended Products/.
     *
     * @param ageGroup : Age group selected by user
     * @param gender : Gender selected by user 
     * @param triatsCode: Search code
     * @param isLoggedInUser: boolean if user is logged in
     * @param userIdentifier : user identifier: emailId
     * @param menuIdentifier : Char code corresponding to each main menu
     * @return the ResponseEntity with status 200 (OK) and with body the ProductSearchResultDTO, or with status 404 (Not Found)
     */
    @GetMapping("/search-product/{ageGroup,gender,triatCode,isLoggedInUser,userIdentifier}")
    @Timed
    public ResponseEntity<ProductSearchResultDTO> searchProducts(@RequestParam String ageGroup,@RequestParam String gender,@RequestParam String triatsCode,@RequestParam boolean isLoggedInUser,@RequestParam String userIdentifier, @RequestParam String menuIdentifier) {
        log.debug("REST request to get Search Product : ageGroup: {}, gender : {}, traitsCode : {}, isLoggedInUser : {}, userIdentifier: {}", ageGroup,gender,triatsCode,isLoggedInUser,userIdentifier);
        ProductSearchResultDTO productSearchResultDTO = null;
      
        if(isLoggedInUser) {
        	// Call Service for details where we have suchAzuser ID.
        	productSearchResultDTO = productRecommendationService.searchProductsForLoggedInUser(ageGroup, gender, triatsCode, userIdentifier, menuIdentifier);
        	
        }else
        {
        	// Call service where we don't have user created in suchaz or not logged in user.
        	productSearchResultDTO = productRecommendationService.searchProductsForNonLoggedInUser(ageGroup, gender, triatsCode, userIdentifier, menuIdentifier);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productSearchResultDTO));
    }

}
