package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.Occassion;
import com.suchaz.app.repository.OccassionRepository;
import com.suchaz.app.service.OccassionService;
import com.suchaz.app.service.dto.OccassionDTO;
import com.suchaz.app.service.mapper.OccassionMapper;

/**
 * Service Implementation for managing Occassion.
 */
@Service
@Transactional
public class OccassionServiceImpl implements OccassionService {

    private final Logger log = LoggerFactory.getLogger(OccassionServiceImpl.class);

    private final OccassionRepository occassionRepository;

    private final OccassionMapper occassionMapper;

    public OccassionServiceImpl(OccassionRepository occassionRepository, OccassionMapper occassionMapper) {
        this.occassionRepository = occassionRepository;
        this.occassionMapper = occassionMapper;
    }

    /**
     * Save a occassion.
     *
     * @param occassionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OccassionDTO save(OccassionDTO occassionDTO) {
        log.debug("Request to save Occassion : {}", occassionDTO);
        Occassion occassion = occassionMapper.toEntity(occassionDTO);
        occassion = occassionRepository.save(occassion);
        return occassionMapper.toDto(occassion);
    }

    /**
     * Get all the occassions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OccassionDTO> findAll() {
        log.debug("Request to get all Occassions");
        return occassionRepository.findAll().stream()
            .map(occassionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one occassion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OccassionDTO findOne(Long id) {
        log.debug("Request to get Occassion : {}", id);
        Occassion occassion = occassionRepository.findOne(id);
        return occassionMapper.toDto(occassion);
    }

    /**
     * Delete the occassion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Occassion : {}", id);
        occassionRepository.delete(id);
    }
}
