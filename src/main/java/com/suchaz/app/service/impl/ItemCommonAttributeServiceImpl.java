package com.suchaz.app.service.impl;

import com.suchaz.app.service.ItemCommonAttributeService;
import com.suchaz.app.domain.ItemCommonAttribute;
import com.suchaz.app.repository.ItemCommonAttributeRepository;
import com.suchaz.app.service.dto.ItemCommonAttributeDTO;
import com.suchaz.app.service.mapper.ItemCommonAttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ItemCommonAttribute.
 */
@Service
@Transactional
public class ItemCommonAttributeServiceImpl implements ItemCommonAttributeService {

    private final Logger log = LoggerFactory.getLogger(ItemCommonAttributeServiceImpl.class);

    private final ItemCommonAttributeRepository itemCommonAttributeRepository;

    private final ItemCommonAttributeMapper itemCommonAttributeMapper;

    public ItemCommonAttributeServiceImpl(ItemCommonAttributeRepository itemCommonAttributeRepository, ItemCommonAttributeMapper itemCommonAttributeMapper) {
        this.itemCommonAttributeRepository = itemCommonAttributeRepository;
        this.itemCommonAttributeMapper = itemCommonAttributeMapper;
    }

    /**
     * Save a itemCommonAttribute.
     *
     * @param itemCommonAttributeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemCommonAttributeDTO save(ItemCommonAttributeDTO itemCommonAttributeDTO) {
        log.debug("Request to save ItemCommonAttribute : {}", itemCommonAttributeDTO);
        ItemCommonAttribute itemCommonAttribute = itemCommonAttributeMapper.toEntity(itemCommonAttributeDTO);
        itemCommonAttribute = itemCommonAttributeRepository.save(itemCommonAttribute);
        return itemCommonAttributeMapper.toDto(itemCommonAttribute);
    }

    /**
     * Get all the itemCommonAttributes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ItemCommonAttributeDTO> findAll() {
        log.debug("Request to get all ItemCommonAttributes");
        return itemCommonAttributeRepository.findAll().stream()
            .map(itemCommonAttributeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one itemCommonAttribute by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ItemCommonAttributeDTO findOne(Long id) {
        log.debug("Request to get ItemCommonAttribute : {}", id);
        ItemCommonAttribute itemCommonAttribute = itemCommonAttributeRepository.findOne(id);
        return itemCommonAttributeMapper.toDto(itemCommonAttribute);
    }

    /**
     * Delete the itemCommonAttribute by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemCommonAttribute : {}", id);
        itemCommonAttributeRepository.delete(id);
    }
}
