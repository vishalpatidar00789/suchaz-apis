package com.suchaz.app.service.impl;

import com.suchaz.app.service.HobbyImageService;
import com.suchaz.app.domain.HobbyImage;
import com.suchaz.app.repository.HobbyImageRepository;
import com.suchaz.app.service.dto.HobbyImageDTO;
import com.suchaz.app.service.mapper.HobbyImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing HobbyImage.
 */
@Service
@Transactional
public class HobbyImageServiceImpl implements HobbyImageService {

    private final Logger log = LoggerFactory.getLogger(HobbyImageServiceImpl.class);

    private final HobbyImageRepository hobbyImageRepository;

    private final HobbyImageMapper hobbyImageMapper;

    public HobbyImageServiceImpl(HobbyImageRepository hobbyImageRepository, HobbyImageMapper hobbyImageMapper) {
        this.hobbyImageRepository = hobbyImageRepository;
        this.hobbyImageMapper = hobbyImageMapper;
    }

    /**
     * Save a hobbyImage.
     *
     * @param hobbyImageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HobbyImageDTO save(HobbyImageDTO hobbyImageDTO) {
        log.debug("Request to save HobbyImage : {}", hobbyImageDTO);
        HobbyImage hobbyImage = hobbyImageMapper.toEntity(hobbyImageDTO);
        hobbyImage = hobbyImageRepository.save(hobbyImage);
        return hobbyImageMapper.toDto(hobbyImage);
    }

    /**
     * Get all the hobbyImages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<HobbyImageDTO> findAll() {
        log.debug("Request to get all HobbyImages");
        return hobbyImageRepository.findAll().stream()
            .map(hobbyImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one hobbyImage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public HobbyImageDTO findOne(Long id) {
        log.debug("Request to get HobbyImage : {}", id);
        HobbyImage hobbyImage = hobbyImageRepository.findOne(id);
        return hobbyImageMapper.toDto(hobbyImage);
    }

    /**
     * Delete the hobbyImage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HobbyImage : {}", id);
        hobbyImageRepository.delete(id);
    }
}
