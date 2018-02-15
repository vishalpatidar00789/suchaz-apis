package com.suchaz.app.service.impl;

import com.suchaz.app.service.RelationshipImageService;
import com.suchaz.app.domain.RelationshipImage;
import com.suchaz.app.repository.RelationshipImageRepository;
import com.suchaz.app.service.dto.RelationshipImageDTO;
import com.suchaz.app.service.mapper.RelationshipImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RelationshipImage.
 */
@Service
@Transactional
public class RelationshipImageServiceImpl implements RelationshipImageService {

    private final Logger log = LoggerFactory.getLogger(RelationshipImageServiceImpl.class);

    private final RelationshipImageRepository relationshipImageRepository;

    private final RelationshipImageMapper relationshipImageMapper;

    public RelationshipImageServiceImpl(RelationshipImageRepository relationshipImageRepository, RelationshipImageMapper relationshipImageMapper) {
        this.relationshipImageRepository = relationshipImageRepository;
        this.relationshipImageMapper = relationshipImageMapper;
    }

    /**
     * Save a relationshipImage.
     *
     * @param relationshipImageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RelationshipImageDTO save(RelationshipImageDTO relationshipImageDTO) {
        log.debug("Request to save RelationshipImage : {}", relationshipImageDTO);
        RelationshipImage relationshipImage = relationshipImageMapper.toEntity(relationshipImageDTO);
        relationshipImage = relationshipImageRepository.save(relationshipImage);
        return relationshipImageMapper.toDto(relationshipImage);
    }

    /**
     * Get all the relationshipImages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RelationshipImageDTO> findAll() {
        log.debug("Request to get all RelationshipImages");
        return relationshipImageRepository.findAll().stream()
            .map(relationshipImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one relationshipImage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RelationshipImageDTO findOne(Long id) {
        log.debug("Request to get RelationshipImage : {}", id);
        RelationshipImage relationshipImage = relationshipImageRepository.findOne(id);
        return relationshipImageMapper.toDto(relationshipImage);
    }

    /**
     * Delete the relationshipImage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelationshipImage : {}", id);
        relationshipImageRepository.delete(id);
    }
}
