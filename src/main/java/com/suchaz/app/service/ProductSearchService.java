package com.suchaz.app.service;

import com.suchaz.app.service.dto.ProductSearchResultDTO;

/**
 * Service Interface for searching Prodcuts.
 */
public interface ProductSearchService {

	ProductSearchResultDTO searchItemsWithKeyWord(String keyWord);


}
