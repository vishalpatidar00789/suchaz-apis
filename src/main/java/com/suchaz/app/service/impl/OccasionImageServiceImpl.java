package com.suchaz.app.service.impl;

import com.suchaz.app.service.OccasionImageService;
import com.suchaz.app.domain.OccasionImage;
import com.suchaz.app.repository.OccasionImageRepository;
import com.suchaz.app.service.dto.OccasionImageDTO;
import com.suchaz.app.service.mapper.OccasionImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing OccasionImage.
 */
@Service
@Transactional
public class OccasionImageServiceImpl implements OccasionImageService {

    private final Logger log = LoggerFactory.getLogger(OccasionImageServiceImpl.class);

    private final OccasionImageRepository occasionImageRepository;

    private final OccasionImageMapper occasionImageMapper;

    public OccasionImageServiceImpl(OccasionImageRepository occasionImageRepository, OccasionImageMapper occasionImageMapper) {
        this.occasionImageRepository = occasionImageRepository;
        this.occasionImageMapper = occasionImageMapper;
    }

    /**
     * Save a occasionImage.
     *
     * @param occasionImageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OccasionImageDTO save(OccasionImageDTO occasionImageDTO) {
        log.debug("Request to save OccasionImage : {}", occasionImageDTO);
        OccasionImage occasionImage = occasionImageMapper.toEntity(occasionImageDTO);
        occasionImage = occasionImageRepository.save(occasionImage);
        return occasionImageMapper.toDto(occasionImage);
    }

    /**
     * Get all the occasionImages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OccasionImageDTO> findAll() {
        log.debug("Request to get all OccasionImages");
        return occasionImageRepository.findAll().stream()
            .map(occasionImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one occasionImage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OccasionImageDTO findOne(Long id) {
        log.debug("Request to get OccasionImage : {}", id);
        OccasionImage occasionImage = occasionImageRepository.findOne(id);
        return occasionImageMapper.toDto(occasionImage);
    }

    /**
     * Delete the occasionImage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OccasionImage : {}", id);
        occasionImageRepository.delete(id);
    }
}
