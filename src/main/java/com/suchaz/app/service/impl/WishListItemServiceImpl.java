package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.WishListItem;
import com.suchaz.app.repository.WishListItemRepository;
import com.suchaz.app.service.WishListItemService;
import com.suchaz.app.service.dto.WishListItemDTO;
import com.suchaz.app.service.mapper.WishListItemMapper;

/**
 * Service Implementation for managing WishListItem.
 */
@Service
@Transactional
public class WishListItemServiceImpl implements WishListItemService {

    private final Logger log = LoggerFactory.getLogger(WishListItemServiceImpl.class);

    private final WishListItemRepository wishListItemRepository;

    private final WishListItemMapper wishListItemMapper;

    public WishListItemServiceImpl(WishListItemRepository wishListItemRepository, WishListItemMapper wishListItemMapper) {
        this.wishListItemRepository = wishListItemRepository;
        this.wishListItemMapper = wishListItemMapper;
    }

    /**
     * Save a wishListItem.
     *
     * @param wishListItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WishListItemDTO save(WishListItemDTO wishListItemDTO) {
        log.debug("Request to save WishListItem : {}", wishListItemDTO);
        WishListItem wishListItem = wishListItemMapper.toEntity(wishListItemDTO);
        wishListItem = wishListItemRepository.save(wishListItem);
        return wishListItemMapper.toDto(wishListItem);
    }

    /**
     * Get all the wishListItems.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WishListItemDTO> findAll() {
        log.debug("Request to get all WishListItems");
        return wishListItemRepository.findAll().stream()
            .map(wishListItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one wishListItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WishListItemDTO findOne(Long id) {
        log.debug("Request to get WishListItem : {}", id);
        WishListItem wishListItem = wishListItemRepository.findOne(id);
        return wishListItemMapper.toDto(wishListItem);
    }

    /**
     * Delete the wishListItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WishListItem : {}", id);
        wishListItemRepository.delete(id);
    }
}
