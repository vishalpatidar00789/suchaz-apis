package com.suchaz.app.service.impl;

import com.suchaz.app.service.ItemImageService;
import com.suchaz.app.domain.ItemImage;
import com.suchaz.app.repository.ItemImageRepository;
import com.suchaz.app.service.dto.ItemImageDTO;
import com.suchaz.app.service.mapper.ItemImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ItemImage.
 */
@Service
@Transactional
public class ItemImageServiceImpl implements ItemImageService {

    private final Logger log = LoggerFactory.getLogger(ItemImageServiceImpl.class);

    private final ItemImageRepository itemImageRepository;

    private final ItemImageMapper itemImageMapper;

    public ItemImageServiceImpl(ItemImageRepository itemImageRepository, ItemImageMapper itemImageMapper) {
        this.itemImageRepository = itemImageRepository;
        this.itemImageMapper = itemImageMapper;
    }

    /**
     * Save a itemImage.
     *
     * @param itemImageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemImageDTO save(ItemImageDTO itemImageDTO) {
        log.debug("Request to save ItemImage : {}", itemImageDTO);
        ItemImage itemImage = itemImageMapper.toEntity(itemImageDTO);
        itemImage = itemImageRepository.save(itemImage);
        return itemImageMapper.toDto(itemImage);
    }

    /**
     * Get all the itemImages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ItemImageDTO> findAll() {
        log.debug("Request to get all ItemImages");
        return itemImageRepository.findAll().stream()
            .map(itemImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one itemImage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ItemImageDTO findOne(Long id) {
        log.debug("Request to get ItemImage : {}", id);
        ItemImage itemImage = itemImageRepository.findOne(id);
        return itemImageMapper.toDto(itemImage);
    }

    /**
     * Delete the itemImage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemImage : {}", id);
        itemImageRepository.delete(id);
    }
}
