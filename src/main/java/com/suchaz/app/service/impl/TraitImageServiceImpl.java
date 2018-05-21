package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.TraitImage;
import com.suchaz.app.repository.TraitImageRepository;
import com.suchaz.app.service.TraitImageService;
import com.suchaz.app.service.dto.TraitImageDTO;
import com.suchaz.app.service.mapper.TraitImageMapper;

/**
 * Service Implementation for managing TraitImage.
 */
@Service
@Transactional
public class TraitImageServiceImpl implements TraitImageService {

    private final Logger log = LoggerFactory.getLogger(TraitImageServiceImpl.class);

    private final TraitImageRepository traitImageRepository;

    private final TraitImageMapper traitImageMapper;

    public TraitImageServiceImpl(TraitImageRepository traitImageRepository, TraitImageMapper traitImageMapper) {
        this.traitImageRepository = traitImageRepository;
        this.traitImageMapper = traitImageMapper;
    }

    /**
     * Save a traitImage.
     *
     * @param traitImageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TraitImageDTO save(TraitImageDTO traitImageDTO) {
        log.debug("Request to save TraitImage : {}", traitImageDTO);
        TraitImage traitImage = traitImageMapper.toEntity(traitImageDTO);
        traitImage = traitImageRepository.save(traitImage);
        return traitImageMapper.toDto(traitImage);
    }

    /**
     * Get all the traitImages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TraitImageDTO> findAll() {
        log.debug("Request to get all TraitImages");
        return traitImageRepository.findAll().stream()
            .map(traitImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one traitImage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TraitImageDTO findOne(Long id) {
        log.debug("Request to get TraitImage : {}", id);
        TraitImage traitImage = traitImageRepository.findOne(id);
        return traitImageMapper.toDto(traitImage);
    }

    /**
     * Delete the traitImage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TraitImage : {}", id);
        traitImageRepository.delete(id);
    }
}
