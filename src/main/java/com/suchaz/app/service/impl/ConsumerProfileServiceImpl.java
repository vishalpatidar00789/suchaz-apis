package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.ConsumerProfile;
import com.suchaz.app.repository.ConsumerProfileRepository;
import com.suchaz.app.service.ConsumerProfileService;
import com.suchaz.app.service.dto.ConsumerProfileDTO;
import com.suchaz.app.service.mapper.ConsumerProfileMapper;

/**
 * Service Implementation for managing ConsumerProfile.
 */
@Service
@Transactional
public class ConsumerProfileServiceImpl implements ConsumerProfileService {

    private final Logger log = LoggerFactory.getLogger(ConsumerProfileServiceImpl.class);

    private final ConsumerProfileRepository consumerProfileRepository;

    private final ConsumerProfileMapper consumerProfileMapper;

    public ConsumerProfileServiceImpl(ConsumerProfileRepository consumerProfileRepository, ConsumerProfileMapper consumerProfileMapper) {
        this.consumerProfileRepository = consumerProfileRepository;
        this.consumerProfileMapper = consumerProfileMapper;
    }

    /**
     * Save a consumerProfile.
     *
     * @param consumerProfileDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConsumerProfileDTO save(ConsumerProfileDTO consumerProfileDTO) {
        log.debug("Request to save ConsumerProfile : {}", consumerProfileDTO);
        ConsumerProfile consumerProfile = consumerProfileMapper.toEntity(consumerProfileDTO);
        consumerProfile = consumerProfileRepository.save(consumerProfile);
        return consumerProfileMapper.toDto(consumerProfile);
    }

    /**
     * Get all the consumerProfiles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConsumerProfileDTO> findAll() {
        log.debug("Request to get all ConsumerProfiles");
        return consumerProfileRepository.findAll().stream()
            .map(consumerProfileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one consumerProfile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ConsumerProfileDTO findOne(Long id) {
        log.debug("Request to get ConsumerProfile : {}", id);
        ConsumerProfile consumerProfile = consumerProfileRepository.findOne(id);
        return consumerProfileMapper.toDto(consumerProfile);
    }

    /**
     * Delete the consumerProfile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConsumerProfile : {}", id);
        consumerProfileRepository.delete(id);
    }
}
