package com.suchaz.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.Category;
import com.suchaz.app.domain.ConsumerProfile;
import com.suchaz.app.domain.ConsumerProfileHistory;
import com.suchaz.app.domain.SuchAzUser;
import com.suchaz.app.domain.enumeration.AgeGroup;
import com.suchaz.app.domain.enumeration.Gender;
import com.suchaz.app.repository.CategoryRepository;
import com.suchaz.app.repository.ConsumerProfileHistoryRepository;
import com.suchaz.app.repository.ConsumerProfileRepository;
import com.suchaz.app.repository.ItemRepository;
import com.suchaz.app.repository.SuchAzUserRepository;
import com.suchaz.app.service.ProductRecommendationService;
import com.suchaz.app.service.QuickViewService;
import com.suchaz.app.service.dto.ProductSearchResultDTO;
import com.suchaz.app.service.dto.QuickViewDTO;
import com.suchaz.app.service.util.CustomUtil;

/**
 * Service Implementation for searching recommended products.
 */
@Service
@Transactional
public class ProductRecommendationServiceImpl implements ProductRecommendationService {
	
	private final SuchAzUserRepository suchAzUserRepository;
	private final ConsumerProfileHistoryRepository consumerProfileHistoryRepository;
	private final ConsumerProfileRepository consumerProfileRepository;
	private final ItemRepository itemRepository;
	private final CategoryRepository categoryRepository;
	
	@Autowired
	private QuickViewService quickViewService;
	


	
	public ProductRecommendationServiceImpl(SuchAzUserRepository suchAzUserRepository,
			ConsumerProfileHistoryRepository consumerProfileHistoryRepository,
			ConsumerProfileRepository consumerProfileRepository, ItemRepository itemRepository, CategoryRepository categoryRepository) {
		super();
		this.suchAzUserRepository = suchAzUserRepository;
		this.consumerProfileHistoryRepository = consumerProfileHistoryRepository;
		this.consumerProfileRepository = consumerProfileRepository;
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
	}

	private final Logger log = LoggerFactory.getLogger(ProductRecommendationServiceImpl.class);

	@Override
	public ProductSearchResultDTO searchProductsForLoggedInUser(String ageGroup, String gender, String userInputCodes, String userIdentifier, String menuIdentifier) {
		
		String recommendedProductCategories;
		ArrayList<QuickViewDTO> listOfQuickViewDTOs = null;
		ProductSearchResultDTO productSearchResultDTO = null;
		
		if(CustomUtil.isNotNull(ageGroup) && CustomUtil.isNotNull(gender) && CustomUtil.isNotNull(userInputCodes) && CustomUtil.isNotNull(userIdentifier) && CustomUtil.isNotNull(menuIdentifier))
		{
			// We can create seperate thread here and call AI engine
			//String [] arrOfuserInputCodes = userInputCodes.split(",");
			
			// AI call end
			ConsumerProfile consumerProfile;
			SuchAzUser suchAzUser = suchAzUserRepository.findOne(Long.parseLong(userIdentifier));
			if(null!=suchAzUser)
			{
				consumerProfile = consumerProfileRepository.findOneWithSuchAzEmailId(suchAzUser.getEmail());
				if(null!=consumerProfile)
				{
					ConsumerProfileHistory consumerProfileHistory = new ConsumerProfileHistory();
					consumerProfileHistory.setActivityStructure(consumerProfile.getActivityStructure());
					consumerProfileHistory.setAge(consumerProfile.getAge());
					consumerProfileHistory.setCreatedBy(suchAzUser.getEmail());
					consumerProfileHistory.setCreatedDate(DateTime.now().getMillis());
					consumerProfileHistory.setGender(consumerProfile.getGender());
					consumerProfileHistory.setHobbyStructure(consumerProfile.getHobbyStructure());
					consumerProfileHistory.setInputHobbies(consumerProfile.getInputHobbies());
					consumerProfileHistory.setInputReletionship(consumerProfile.getInputReletionship());
					consumerProfileHistory.setInputTraits(consumerProfile.getInputTraits());
					consumerProfileHistory.setLastUpdatedBy(null);
					consumerProfileHistory.setLastUpdatedDate(null);
					consumerProfileHistory.setName(consumerProfile.getName());
					consumerProfileHistory.setReccomendedProductTypes(consumerProfile.getReccomendedProductTypes());
					consumerProfileHistory.setTraitStructure(consumerProfile.getTraitStructure());
					consumerProfileHistory.setIsLoggedInUser(consumerProfile.isIsLoggedInUser());
					
					consumerProfileHistoryRepository.saveAndFlush(consumerProfileHistory);
					/*consumerProfileRepository.delete(consumerProfile);
					consumerProfileRepository.flush();
					consumerProfile = null;*/

				}
				// Creating Consumer Profile Domain for saving
					consumerProfile = new ConsumerProfile();
					if(menuIdentifier.equals("P"))
					{
					consumerProfile.setSuchAzUser(suchAzUser);
					//consumerProfile.setActivityStructure(activityStructure);
					switch (ageGroup) {
					case "KIDS":
						consumerProfile.setAge(AgeGroup.KIDS);
						break;
					case "YOUTH":
						consumerProfile.setAge(AgeGroup.YOUTH);
						break;
					case "ELDER":
						consumerProfile.setAge(AgeGroup.ELDER);
						break;
					case "TEEN":
						consumerProfile.setAge(AgeGroup.TEEN);
						break;
					}
					
					consumerProfile.setCreatedBy("SS_ADMIN");
					consumerProfile.setCreatedDate(DateTime.now().getMillis());
					switch(gender)
					{
					case "M":
						consumerProfile.setGender(Gender.MALE);
						break;
					case "F":
						consumerProfile.setGender(Gender.FEMALE);
						break;
					}
					//consumerProfile.setHobbyStructure(hobbyStructure);
					//consumerProfile.setInputHobbies(inputHobbies);
					//consumerProfile.setInputReletionship(inputReletionship);
					consumerProfile.setInputTraits(userInputCodes);
					//consumerProfile.setName(name);
					//consumerProfile.setTraitStructure();
					consumerProfile.setIsLoggedInUser(true);
					
					consumerProfileRepository.saveAndFlush(consumerProfile);
					}
					else if(menuIdentifier.equals("B"))
					{
						
					}
					else if(menuIdentifier.equals("A"))
					{
						
					}
					else if(menuIdentifier.equals("O"))
					{
						//Rule set
					}
					else if(menuIdentifier.equals("R"))
					{
						
					}
					else if(menuIdentifier.equals("H"))
					{
						consumerProfile.setSuchAzUser(suchAzUser);
						//consumerProfile.setActivityStructure(activityStructure);
						switch (ageGroup) {
						case "KIDS":
							consumerProfile.setAge(AgeGroup.KIDS);
							break;
						case "YOUTH":
							consumerProfile.setAge(AgeGroup.YOUTH);
							break;
						case "ELDER":
							consumerProfile.setAge(AgeGroup.ELDER);
							break;
						case "TEEN":
							consumerProfile.setAge(AgeGroup.TEEN);
							break;
						}
						
						consumerProfile.setCreatedBy("SS_ADMIN");
						consumerProfile.setCreatedDate(DateTime.now().getMillis());
						switch(gender)
						{
						case "M":
							consumerProfile.setGender(Gender.MALE);
							break;
						case "F":
							consumerProfile.setGender(Gender.FEMALE);
							break;
						}
						//consumerProfile.setHobbyStructure(hobbyStructure);
						consumerProfile.setInputHobbies(userInputCodes);
						//consumerProfile.setInputReletionship(inputReletionship);
						//consumerProfile.setInputTraits(userInputCodes);
						//consumerProfile.setName(name);
						//consumerProfile.setTraitStructure();
						consumerProfile.setIsLoggedInUser(true);
						
						consumerProfileRepository.saveAndFlush(consumerProfile);
						//consumerProfile = null;
					}
					else if(menuIdentifier.equals("X"))
					{
						//Rules
					}
					
					// Now invoke AI engine with set of Data and keep the current thread on hold untill AI doesn't gives information
					
					// AI Engine has completed its work and consumer profile is updated with recommended product type.
					// consumerProfile = consumerProfileRepository.findOneWithSuchAzEmailId((suchAzUser.getEmail()));
					 if(null!=consumerProfile)
					 {
						 recommendedProductCategories = consumerProfile.getReccomendedProductTypes(); // here either AI service will update consumer profile or will return comma separated Categories to search. Logic will be updated accordingly
						 ArrayList<Long> listOfAllItemsIdsAgainstCategory =  searchItemsWithCategoryCodes(recommendedProductCategories);
						 //Get QuickView DTO for all Items.
						 if(null!=listOfAllItemsIdsAgainstCategory && listOfAllItemsIdsAgainstCategory.size()>0)
						 {
						 listOfQuickViewDTOs = (ArrayList<QuickViewDTO>) quickViewService.findRangeOfItem(listOfAllItemsIdsAgainstCategory.toArray(new Long[listOfAllItemsIdsAgainstCategory.size()]));
						 }
						 else
						 {
							 ////something wrong
						 }
					 }
					 
					 //Fill ProductSearchResultDTO
					 productSearchResultDTO = new ProductSearchResultDTO();
					 productSearchResultDTO.setSuchAzUserId(suchAzUser.getId());
					 productSearchResultDTO.setListOfItems(listOfQuickViewDTOs);
					
			}
			else
			{
				// no user found with given id, need to think logic here
			}
		}
		return productSearchResultDTO;
	}

	@Override
	public ProductSearchResultDTO searchProductsForNonLoggedInUser(String ageGroup, String gender, String userInputCodes, String userIdentifier, String menuIdentifier) {
		String recommendedProductCategories;
		ArrayList<QuickViewDTO> listOfQuickViewDTOs = null;
		ProductSearchResultDTO productSearchResultDTO = null;
		
		if(CustomUtil.isNotNull(ageGroup) && CustomUtil.isNotNull(gender) && CustomUtil.isNotNull(userInputCodes) && CustomUtil.isNotNull(userIdentifier) && CustomUtil.isNotNull(menuIdentifier))
		{
			// We can create seperate thread here and call AI engine
			//String [] arrOfuserInputCodes = userInputCodes.split(",");
			
			// AI call end
			    ConsumerProfile consumerProfile;
				consumerProfile = consumerProfileRepository.findOneWithUniqueIdentifierForNonLoggedInUser(userIdentifier);
				if(null!=consumerProfile)
				{
					ConsumerProfileHistory consumerProfileHistory = new ConsumerProfileHistory();
					consumerProfileHistory.setActivityStructure(consumerProfile.getActivityStructure());
					consumerProfileHistory.setAge(consumerProfile.getAge());
					consumerProfileHistory.setCreatedBy("NON_LOGGED_IN_USER");
					consumerProfileHistory.setCreatedDate(DateTime.now().getMillis());
					consumerProfileHistory.setGender(consumerProfile.getGender());
					consumerProfileHistory.setHobbyStructure(consumerProfile.getHobbyStructure());
					consumerProfileHistory.setInputHobbies(consumerProfile.getInputHobbies());
					consumerProfileHistory.setInputReletionship(consumerProfile.getInputReletionship());
					consumerProfileHistory.setInputTraits(consumerProfile.getInputTraits());
					consumerProfileHistory.setLastUpdatedBy(null);
					consumerProfileHistory.setLastUpdatedDate(null);
					consumerProfileHistory.setName(consumerProfile.getName());
					consumerProfileHistory.setReccomendedProductTypes(consumerProfile.getReccomendedProductTypes());
					consumerProfileHistory.setTraitStructure(consumerProfile.getTraitStructure());
					consumerProfileHistory.setIsLoggedInUser(consumerProfile.isIsLoggedInUser());
					
					consumerProfileHistoryRepository.saveAndFlush(consumerProfileHistory);
					/*consumerProfileRepository.delete(consumerProfile);
					consumerProfileRepository.flush();
					consumerProfile = null;*/

				}
				// Creating Consumer Profile Domain for saving
				if(null==consumerProfile)
				{
				consumerProfile = new ConsumerProfile();
				}
				
					if(menuIdentifier.equals("P"))
					{
					//consumerProfile.setSuchAzUser(suchAzUser);
					//consumerProfile.setActivityStructure(activityStructure);
					switch (ageGroup) {
					case "KIDS":
						consumerProfile.setAge(AgeGroup.KIDS);
						break;
					case "YOUTH":
						consumerProfile.setAge(AgeGroup.YOUTH);
						break;
					case "ELDER":
						consumerProfile.setAge(AgeGroup.ELDER);
						break;
					case "TEEN":
						consumerProfile.setAge(AgeGroup.TEEN);
						break;
					}
					
					consumerProfile.setCreatedBy("SS_ADMIN");
					consumerProfile.setCreatedDate(DateTime.now().getMillis());
					switch(gender)
					{
					case "M":
						consumerProfile.setGender(Gender.MALE);
						break;
					case "F":
						consumerProfile.setGender(Gender.FEMALE);
						break;
					}
					//consumerProfile.setHobbyStructure(hobbyStructure);
					//consumerProfile.setInputHobbies(inputHobbies);
					//consumerProfile.setInputReletionship(inputReletionship);
					consumerProfile.setInputTraits(userInputCodes);
					//consumerProfile.setName(name);
					//consumerProfile.setTraitStructure();
					consumerProfile.setIsLoggedInUser(false);
					
					consumerProfileRepository.saveAndFlush(consumerProfile);
					}
					else if(menuIdentifier.equals("B"))
					{
						
					}
					else if(menuIdentifier.equals("A"))
					{
						
					}
					else if(menuIdentifier.equals("O"))
					{
						//Rule set
					}
					else if(menuIdentifier.equals("R"))
					{
						
					}
					else if(menuIdentifier.equals("H"))
					{
						//consumerProfile.setSuchAzUser(suchAzUser);
						//consumerProfile.setActivityStructure(activityStructure);
						switch (ageGroup) {
						case "KIDS":
							consumerProfile.setAge(AgeGroup.KIDS);
							break;
						case "YOUTH":
							consumerProfile.setAge(AgeGroup.YOUTH);
							break;
						case "ELDER":
							consumerProfile.setAge(AgeGroup.ELDER);
							break;
						case "TEEN":
							consumerProfile.setAge(AgeGroup.TEEN);
							break;
						}
						
						consumerProfile.setCreatedBy("SS_ADMIN");
						consumerProfile.setCreatedDate(DateTime.now().getMillis());
						switch(gender)
						{
						case "M":
							consumerProfile.setGender(Gender.MALE);
							break;
						case "F":
							consumerProfile.setGender(Gender.FEMALE);
							break;
						}
						//consumerProfile.setHobbyStructure(hobbyStructure);
						consumerProfile.setInputHobbies(userInputCodes);
						//consumerProfile.setInputReletionship(inputReletionship);
						//consumerProfile.setInputTraits(userInputCodes);
						//consumerProfile.setName(name);
						//consumerProfile.setTraitStructure();
						consumerProfile.setIsLoggedInUser(false);
						
						consumerProfileRepository.saveAndFlush(consumerProfile);
						//consumerProfile = null;
					}
					else if(menuIdentifier.equals("X"))
					{
						//Rules
					}
					
					// Now invoke AI engine with set of Data and keep the current thread on hold until AI doesn't gives information
					
					// AI Engine has completed its work and consumer profile is updated with recommended product type.
					consumerProfile = consumerProfileRepository.findOneWithUniqueIdentifierForNonLoggedInUser(userIdentifier);
					 if(null!=consumerProfile)
					 {
						 recommendedProductCategories = consumerProfile.getReccomendedProductTypes(); // here either AI service will update consumer profile or will return comma separated Categories to search. Logic will be updated accordingly
						 ArrayList<Long> listOfAllItemsIdsAgainstCategory =  searchItemsWithCategoryCodes(recommendedProductCategories);
						 //Get QuickView DTO for all Items.
						 if(null!=listOfAllItemsIdsAgainstCategory && listOfAllItemsIdsAgainstCategory.size()>0)
						 {
						 listOfQuickViewDTOs = (ArrayList<QuickViewDTO>) quickViewService.findRangeOfItem(listOfAllItemsIdsAgainstCategory.toArray(new Long[listOfAllItemsIdsAgainstCategory.size()]));
						 }
						 else
						 {
							 ////something wrong
						 }
					 }
					 else
					 {
						 //something wrong
					 }
					 
					 //Fill ProductSearchResultDTO
					 productSearchResultDTO = new ProductSearchResultDTO();
					 productSearchResultDTO.setSuchAzUserId(null);
					 productSearchResultDTO.setListOfItems(listOfQuickViewDTOs);
					
			}
			else
			{
				// no user found with given id, need to think logic here
			}
		return productSearchResultDTO;
	}

	private ArrayList<Long> searchItemsWithCategoryCodes(String recommendedProductCategories) {
		ArrayList<Long> listOfAllItemsIdFound  = null;
		if(CustomUtil.isNotNull(recommendedProductCategories))
		{
			HashSet<String> categoryCodesSet = new HashSet<String>();
			String[] inputCodesArray = recommendedProductCategories.split(",");
			List<Category> listOfCategories;
			
			for (String inputCode : inputCodesArray) 
			{
				
				listOfCategories = categoryRepository.findAllByParentCategoryCode(inputCode);
				if(null!=listOfCategories)
				{
					for(Category category : listOfCategories)
					{
						categoryCodesSet.add(category.getCategoryCode());
					}
				
			
				}
					
			}
			
			// Now fetch All Items who has category codes in given hashSet.
			// This needs to be improved for better performance
			listOfAllItemsIdFound = (ArrayList<Long>) itemRepository.findAllItemsIdsforCategory(categoryCodesSet.toArray(new String[categoryCodesSet.size()]));		
		
		}
	
		return listOfAllItemsIdFound;

	}
	
}