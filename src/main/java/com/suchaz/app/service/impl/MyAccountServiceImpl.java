package com.suchaz.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.ActivityList;
import com.suchaz.app.domain.BuyLaterList;
import com.suchaz.app.domain.SuchAzUser;
import com.suchaz.app.domain.UserProfile;
import com.suchaz.app.domain.WishList;
import com.suchaz.app.repository.ActivityListRepository;
import com.suchaz.app.repository.BuyLaterListRepository;
import com.suchaz.app.repository.SuchAzUserRepository;
import com.suchaz.app.repository.UserProfileRepository;
import com.suchaz.app.repository.WishListRepository;
import com.suchaz.app.service.MyAccountService;
import com.suchaz.app.service.dto.ActivityListDTO;
import com.suchaz.app.service.dto.BuyLaterListDTO;
import com.suchaz.app.service.dto.MyAccountDTO;
import com.suchaz.app.service.dto.SuchAzUserDTO;
import com.suchaz.app.service.dto.UserProfileDTO;
import com.suchaz.app.service.dto.WishListDTO;
import com.suchaz.app.service.mapper.ActivityListMapper;
import com.suchaz.app.service.mapper.BuyLaterListMapper;
import com.suchaz.app.service.mapper.SuchAzUserMapper;
import com.suchaz.app.service.mapper.UserProfileMapper;
import com.suchaz.app.service.mapper.WishListMapper;

/**
 * Service Implementation for managing UserProfile.
 */
@Service
@Transactional
public class MyAccountServiceImpl implements MyAccountService {

	private final Logger log = LoggerFactory.getLogger(MyAccountServiceImpl.class);

	private final SuchAzUserRepository suchAzUserRepository;
	private final SuchAzUserMapper suchAzUserMapper;

	private final UserProfileRepository userProfileRepository;
	private final UserProfileMapper userProfileMapper;

	private final BuyLaterListRepository buyLaterListRepository;
	private final BuyLaterListMapper buyLaterListMapper;

	private final WishListRepository wishListRepository;
	private final WishListMapper wishListMapper;

	private final ActivityListRepository activityListRepository;
	private final ActivityListMapper activityListMapper;

	public MyAccountServiceImpl(UserProfileRepository userProfileRepository, UserProfileMapper userProfileMapper,
			BuyLaterListMapper buyLaterListMapper, BuyLaterListRepository buyLaterListRepository,
			WishListMapper wishListMapper, WishListRepository wishListRepository, ActivityListMapper activityListMapper,
			ActivityListRepository activityListRepository, SuchAzUserMapper suchAzUserMapper,
			SuchAzUserRepository suchAzUserRepository) {
		this.userProfileRepository = userProfileRepository;
		this.userProfileMapper = userProfileMapper;
		this.buyLaterListMapper = buyLaterListMapper;
		this.buyLaterListRepository = buyLaterListRepository;
		this.wishListMapper = wishListMapper;
		this.wishListRepository = wishListRepository;
		this.activityListMapper = activityListMapper;
		this.activityListRepository = activityListRepository;
		this.suchAzUserMapper = suchAzUserMapper;
		this.suchAzUserRepository = suchAzUserRepository;
	}

	/**
	 * Get one userProfile by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public MyAccountDTO findOne(Long id) {
		log.debug("Request to get UserProfile : {}", id);
		SuchAzUser suchAzUser = suchAzUserRepository.findOne(id);
		UserProfile userProfile = userProfileRepository.findBySuchAzUserId(id);
		BuyLaterList buyLaterList = buyLaterListRepository.findBySuchAzUserId(id);
		WishList wishList = wishListRepository.findBySuchAzUserId(id);
		ActivityList activityList = activityListRepository.findBySuchAzUserId(id);
		MyAccountDTO myAccountDTO = null;
		if (suchAzUser != null) {
			myAccountDTO = new MyAccountDTO();
			SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);
			UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);
			BuyLaterListDTO buyLaterListDTO = buyLaterListMapper.toDto(buyLaterList);
			WishListDTO wishListDTO = wishListMapper.toDto(wishList);
			ActivityListDTO activityListDTO = activityListMapper.toDto(activityList);
			myAccountDTO.setActivityListDTOs(activityListDTO);
			myAccountDTO.setBuyLaterListDTOs(buyLaterListDTO);
			myAccountDTO.setWishListDTOs(wishListDTO);
			myAccountDTO.setUserProfileDTO(userProfileDTO);
			myAccountDTO.setSuchAzUserDTO(suchAzUserDTO);
			myAccountDTO.setSuchAzUserId(id);

		}

		return myAccountDTO;
	}

}
