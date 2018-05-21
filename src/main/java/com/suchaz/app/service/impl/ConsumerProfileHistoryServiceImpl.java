package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.ConsumerProfileHistory;
import com.suchaz.app.repository.ConsumerProfileHistoryRepository;
import com.suchaz.app.service.ConsumerProfileHistoryService;
import com.suchaz.app.service.dto.ConsumerProfileHistoryDTO;
import com.suchaz.app.service.mapper.ConsumerProfileHistoryMapper;

/**
 * Service Implementation for managing ConsumerProfileHistory.
 */
@Service
@Transactional
public class ConsumerProfileHistoryServiceImpl implements ConsumerProfileHistoryService {

    private final Logger log = LoggerFactory.getLogger(ConsumerProfileHistoryServiceImpl.class);

    private final ConsumerProfileHistoryRepository consumerProfileHistoryRepository;

    private final ConsumerProfileHistoryMapper consumerProfileHistoryMapper;

    public ConsumerProfileHistoryServiceImpl(ConsumerProfileHistoryRepository consumerProfileHistoryRepository, ConsumerProfileHistoryMapper consumerProfileHistoryMapper) {
        this.consumerProfileHistoryRepository = consumerProfileHistoryRepository;
        this.consumerProfileHistoryMapper = consumerProfileHistoryMapper;
    }

    /**
     * Save a consumerProfileHistory.
     *
     * @param consumerProfileHistoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConsumerProfileHistoryDTO save(ConsumerProfileHistoryDTO consumerProfileHistoryDTO) {
        log.debug("Request to save ConsumerProfileHistory : {}", consumerProfileHistoryDTO);
        ConsumerProfileHistory consumerProfileHistory = consumerProfileHistoryMapper.toEntity(consumerProfileHistoryDTO);
        consumerProfileHistory = consumerProfileHistoryRepository.save(consumerProfileHistory);
        return consumerProfileHistoryMapper.toDto(consumerProfileHistory);
    }

    /**
     * Get all the consumerProfileHistories.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConsumerProfileHistoryDTO> findAll() {
        log.debug("Request to get all ConsumerProfileHistories");
        return consumerProfileHistoryRepository.findAll().stream()
            .map(consumerProfileHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one consumerProfileHistory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ConsumerProfileHistoryDTO findOne(Long id) {
        log.debug("Request to get ConsumerProfileHistory : {}", id);
        ConsumerProfileHistory consumerProfileHistory = consumerProfileHistoryRepository.findOne(id);
        return consumerProfileHistoryMapper.toDto(consumerProfileHistory);
    }

    /**
     * Delete the consumerProfileHistory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConsumerProfileHistory : {}", id);
        consumerProfileHistoryRepository.delete(id);
    }
}
