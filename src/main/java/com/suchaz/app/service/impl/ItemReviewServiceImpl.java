package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.ItemReview;
import com.suchaz.app.repository.ItemReviewRepository;
import com.suchaz.app.service.ItemReviewService;
import com.suchaz.app.service.dto.ItemReviewDTO;
import com.suchaz.app.service.mapper.ItemReviewMapper;

/**
 * Service Implementation for managing ItemReview.
 */
@Service
@Transactional
public class ItemReviewServiceImpl implements ItemReviewService {

    private final Logger log = LoggerFactory.getLogger(ItemReviewServiceImpl.class);

    private final ItemReviewRepository itemReviewRepository;

    private final ItemReviewMapper itemReviewMapper;

    public ItemReviewServiceImpl(ItemReviewRepository itemReviewRepository, ItemReviewMapper itemReviewMapper) {
        this.itemReviewRepository = itemReviewRepository;
        this.itemReviewMapper = itemReviewMapper;
    }

    /**
     * Save a itemReview.
     *
     * @param itemReviewDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemReviewDTO save(ItemReviewDTO itemReviewDTO) {
        log.debug("Request to save ItemReview : {}", itemReviewDTO);
        ItemReview itemReview = itemReviewMapper.toEntity(itemReviewDTO);
        itemReview = itemReviewRepository.save(itemReview);
        return itemReviewMapper.toDto(itemReview);
    }

    /**
     * Get all the itemReviews.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ItemReviewDTO> findAll() {
        log.debug("Request to get all ItemReviews");
        return itemReviewRepository.findAll().stream()
            .map(itemReviewMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one itemReview by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ItemReviewDTO findOne(Long id) {
        log.debug("Request to get ItemReview : {}", id);
        ItemReview itemReview = itemReviewRepository.findOne(id);
        return itemReviewMapper.toDto(itemReview);
    }

    /**
     * Delete the itemReview by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemReview : {}", id);
        itemReviewRepository.delete(id);
    }
}
