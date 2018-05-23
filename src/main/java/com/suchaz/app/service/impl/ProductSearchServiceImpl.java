package com.suchaz.app.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.service.ItemService;
import com.suchaz.app.service.ProductSearchService;
import com.suchaz.app.service.QuickViewService;
import com.suchaz.app.service.dto.ProductSearchResultDTO;
import com.suchaz.app.service.dto.QuickViewDTO;
import com.suchaz.app.service.util.CustomUtil;

/**
 * Service Implementation for search products.
 */
@Service
@Transactional
public class ProductSearchServiceImpl implements ProductSearchService {

	private final Logger log = LoggerFactory.getLogger(ProductSearchServiceImpl.class);

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private QuickViewService quickViewService;
	
	@Override
	public ProductSearchResultDTO searchItemsWithKeyWord(String keyWord) {
		
		log.warn("Inside ProductSearchServiceImpl::searchItemsWithKeyWord with Param as {}",keyWord);
		ProductSearchResultDTO productSearchResultDTO = new ProductSearchResultDTO();
		if(CustomUtil.isNotNull(keyWord))
		{
			Long[] listOfItemIds = itemService.findAllItemsIdsWithKeyWord(keyWord);
			
			if(listOfItemIds.length>0)
			{
				ArrayList<QuickViewDTO> listOfQuickViewDTO = (ArrayList<QuickViewDTO>) quickViewService.findRangeOfItem(listOfItemIds);
				if(listOfQuickViewDTO!=null && listOfQuickViewDTO.size()>0)
				{
					productSearchResultDTO.setListOfItems(listOfQuickViewDTO);
				}
				else
				{
					productSearchResultDTO.setErrorMessage("No Products Found with fetched ItemIds. Please Contact System Admin");
				}
			}
			else
			{
				productSearchResultDTO.setErrorMessage("No Products Found. Please modify Search Query");
			}
			
			productSearchResultDTO.setSearchKeyWord(keyWord);
		}
		else
		{
			productSearchResultDTO.setErrorMessage("Incorrect Search Paramaters. Please modify Search Query");
		}
		return productSearchResultDTO;
	}

	

}
