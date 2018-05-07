package com.suchaz.app.service;

import com.suchaz.app.service.dto.ProductSearchResultDTO;

/**
 * Service Interface for managing Item.
 */
public interface ProductRecommendationService {

    /**
     * Get all Recommended products for Logged In user
     *
     * @return the ProductSearchResultDTO
     */
	ProductSearchResultDTO searchProductsForLoggedInUser(String ageGroup, String gender, String userInputCodes, String userIdentifier, String menuIdentifier);

	/**
     * Get all Recommended products for Non Logged In User
     *s
     * @return the ProductSearchResultDTO
     */
	ProductSearchResultDTO searchProductsForNonLoggedInUser(String ageGroup, String gender, String userInputCodes, String userIdentifier, String menuIdentifier);

}
