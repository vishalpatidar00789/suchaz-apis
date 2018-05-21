package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.ItemAttributeType;
import com.suchaz.app.repository.ItemAttributeTypeRepository;
import com.suchaz.app.service.ItemAttributeTypeService;
import com.suchaz.app.service.dto.ItemAttributeTypeDTO;
import com.suchaz.app.service.mapper.ItemAttributeTypeMapper;

/**
 * Service Implementation for managing ItemAttributeType.
 */
@Service
@Transactional
public class ItemAttributeTypeServiceImpl implements ItemAttributeTypeService {

    private final Logger log = LoggerFactory.getLogger(ItemAttributeTypeServiceImpl.class);

    private final ItemAttributeTypeRepository itemAttributeTypeRepository;

    private final ItemAttributeTypeMapper itemAttributeTypeMapper;

    public ItemAttributeTypeServiceImpl(ItemAttributeTypeRepository itemAttributeTypeRepository, ItemAttributeTypeMapper itemAttributeTypeMapper) {
        this.itemAttributeTypeRepository = itemAttributeTypeRepository;
        this.itemAttributeTypeMapper = itemAttributeTypeMapper;
    }

    /**
     * Save a itemAttributeType.
     *
     * @param itemAttributeTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemAttributeTypeDTO save(ItemAttributeTypeDTO itemAttributeTypeDTO) {
        log.debug("Request to save ItemAttributeType : {}", itemAttributeTypeDTO);
        ItemAttributeType itemAttributeType = itemAttributeTypeMapper.toEntity(itemAttributeTypeDTO);
        itemAttributeType = itemAttributeTypeRepository.save(itemAttributeType);
        return itemAttributeTypeMapper.toDto(itemAttributeType);
    }

    /**
     * Get all the itemAttributeTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ItemAttributeTypeDTO> findAll() {
        log.debug("Request to get all ItemAttributeTypes");
        return itemAttributeTypeRepository.findAll().stream()
            .map(itemAttributeTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one itemAttributeType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ItemAttributeTypeDTO findOne(Long id) {
        log.debug("Request to get ItemAttributeType : {}", id);
        ItemAttributeType itemAttributeType = itemAttributeTypeRepository.findOne(id);
        return itemAttributeTypeMapper.toDto(itemAttributeType);
    }

    /**
     * Delete the itemAttributeType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemAttributeType : {}", id);
        itemAttributeTypeRepository.delete(id);
    }
}
