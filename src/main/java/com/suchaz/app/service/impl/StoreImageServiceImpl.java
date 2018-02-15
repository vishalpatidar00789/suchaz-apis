package com.suchaz.app.service.impl;

import com.suchaz.app.service.StoreImageService;
import com.suchaz.app.domain.StoreImage;
import com.suchaz.app.repository.StoreImageRepository;
import com.suchaz.app.service.dto.StoreImageDTO;
import com.suchaz.app.service.mapper.StoreImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing StoreImage.
 */
@Service
@Transactional
public class StoreImageServiceImpl implements StoreImageService {

    private final Logger log = LoggerFactory.getLogger(StoreImageServiceImpl.class);

    private final StoreImageRepository storeImageRepository;

    private final StoreImageMapper storeImageMapper;

    public StoreImageServiceImpl(StoreImageRepository storeImageRepository, StoreImageMapper storeImageMapper) {
        this.storeImageRepository = storeImageRepository;
        this.storeImageMapper = storeImageMapper;
    }

    /**
     * Save a storeImage.
     *
     * @param storeImageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StoreImageDTO save(StoreImageDTO storeImageDTO) {
        log.debug("Request to save StoreImage : {}", storeImageDTO);
        StoreImage storeImage = storeImageMapper.toEntity(storeImageDTO);
        storeImage = storeImageRepository.save(storeImage);
        return storeImageMapper.toDto(storeImage);
    }

    /**
     * Get all the storeImages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StoreImageDTO> findAll() {
        log.debug("Request to get all StoreImages");
        return storeImageRepository.findAll().stream()
            .map(storeImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one storeImage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StoreImageDTO findOne(Long id) {
        log.debug("Request to get StoreImage : {}", id);
        StoreImage storeImage = storeImageRepository.findOne(id);
        return storeImageMapper.toDto(storeImage);
    }

    /**
     * Delete the storeImage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StoreImage : {}", id);
        storeImageRepository.delete(id);
    }
}
