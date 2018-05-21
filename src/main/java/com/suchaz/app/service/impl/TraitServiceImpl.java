package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.Trait;
import com.suchaz.app.repository.TraitRepository;
import com.suchaz.app.service.TraitService;
import com.suchaz.app.service.dto.TraitDTO;
import com.suchaz.app.service.mapper.TraitMapper;

/**
 * Service Implementation for managing Trait.
 */
@Service
@Transactional
public class TraitServiceImpl implements TraitService {

    private final Logger log = LoggerFactory.getLogger(TraitServiceImpl.class);

    private final TraitRepository traitRepository;

    private final TraitMapper traitMapper;

    public TraitServiceImpl(TraitRepository traitRepository, TraitMapper traitMapper) {
        this.traitRepository = traitRepository;
        this.traitMapper = traitMapper;
    }

    /**
     * Save a trait.
     *
     * @param traitDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TraitDTO save(TraitDTO traitDTO) {
        log.debug("Request to save Trait : {}", traitDTO);
        Trait trait = traitMapper.toEntity(traitDTO);
        trait = traitRepository.save(trait);
        return traitMapper.toDto(trait);
    }

    /**
     * Get all the traits.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TraitDTO> findAll() {
        log.debug("Request to get all Traits");
        return traitRepository.findAll().stream()
            .map(traitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one trait by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TraitDTO findOne(Long id) {
        log.debug("Request to get Trait : {}", id);
        Trait trait = traitRepository.findOne(id);
        return traitMapper.toDto(trait);
    }

    /**
     * Delete the trait by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Trait : {}", id);
        traitRepository.delete(id);
    }
}
