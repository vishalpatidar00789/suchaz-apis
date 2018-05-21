package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.Hobby;
import com.suchaz.app.repository.HobbyRepository;
import com.suchaz.app.service.HobbyService;
import com.suchaz.app.service.dto.HobbyDTO;
import com.suchaz.app.service.mapper.HobbyMapper;

/**
 * Service Implementation for managing Hobby.
 */
@Service
@Transactional
public class HobbyServiceImpl implements HobbyService {

    private final Logger log = LoggerFactory.getLogger(HobbyServiceImpl.class);

    private final HobbyRepository hobbyRepository;

    private final HobbyMapper hobbyMapper;

    public HobbyServiceImpl(HobbyRepository hobbyRepository, HobbyMapper hobbyMapper) {
        this.hobbyRepository = hobbyRepository;
        this.hobbyMapper = hobbyMapper;
    }

    /**
     * Save a hobby.
     *
     * @param hobbyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HobbyDTO save(HobbyDTO hobbyDTO) {
        log.debug("Request to save Hobby : {}", hobbyDTO);
        Hobby hobby = hobbyMapper.toEntity(hobbyDTO);
        hobby = hobbyRepository.save(hobby);
        return hobbyMapper.toDto(hobby);
    }

    /**
     * Get all the hobbies.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<HobbyDTO> findAll() {
        log.debug("Request to get all Hobbies");
        return hobbyRepository.findAll().stream()
            .map(hobbyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one hobby by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public HobbyDTO findOne(Long id) {
        log.debug("Request to get Hobby : {}", id);
        Hobby hobby = hobbyRepository.findOne(id);
        return hobbyMapper.toDto(hobby);
    }

    /**
     * Delete the hobby by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hobby : {}", id);
        hobbyRepository.delete(id);
    }
}
