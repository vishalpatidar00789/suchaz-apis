package com.suchaz.app.service.impl;

import com.suchaz.app.service.WishListService;
import com.suchaz.app.domain.WishList;
import com.suchaz.app.repository.WishListRepository;
import com.suchaz.app.service.dto.WishListDTO;
import com.suchaz.app.service.mapper.WishListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing WishList.
 */
@Service
@Transactional
public class WishListServiceImpl implements WishListService {

    private final Logger log = LoggerFactory.getLogger(WishListServiceImpl.class);

    private final WishListRepository wishListRepository;

    private final WishListMapper wishListMapper;

    public WishListServiceImpl(WishListRepository wishListRepository, WishListMapper wishListMapper) {
        this.wishListRepository = wishListRepository;
        this.wishListMapper = wishListMapper;
    }

    /**
     * Save a wishList.
     *
     * @param wishListDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WishListDTO save(WishListDTO wishListDTO) {
        log.debug("Request to save WishList : {}", wishListDTO);
        WishList wishList = wishListMapper.toEntity(wishListDTO);
        wishList = wishListRepository.save(wishList);
        return wishListMapper.toDto(wishList);
    }

    /**
     * Get all the wishLists.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WishListDTO> findAll() {
        log.debug("Request to get all WishLists");
        return wishListRepository.findAll().stream()
            .map(wishListMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one wishList by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WishListDTO findOne(Long id) {
        log.debug("Request to get WishList : {}", id);
        WishList wishList = wishListRepository.findOne(id);
        return wishListMapper.toDto(wishList);
    }

    /**
     * Delete the wishList by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WishList : {}", id);
        wishListRepository.delete(id);
    }
}
