package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.GiftWrapper;
import com.suchaz.app.repository.GiftWrapperRepository;
import com.suchaz.app.service.GiftWrapperService;
import com.suchaz.app.service.dto.GiftWrapperDTO;
import com.suchaz.app.service.mapper.GiftWrapperMapper;

/**
 * Service Implementation for managing GiftWrapper.
 */
@Service
@Transactional
public class GiftWrapperServiceImpl implements GiftWrapperService {

    private final Logger log = LoggerFactory.getLogger(GiftWrapperServiceImpl.class);

    private final GiftWrapperRepository giftWrapperRepository;

    private final GiftWrapperMapper giftWrapperMapper;

    public GiftWrapperServiceImpl(GiftWrapperRepository giftWrapperRepository, GiftWrapperMapper giftWrapperMapper) {
        this.giftWrapperRepository = giftWrapperRepository;
        this.giftWrapperMapper = giftWrapperMapper;
    }

    /**
     * Save a giftWrapper.
     *
     * @param giftWrapperDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GiftWrapperDTO save(GiftWrapperDTO giftWrapperDTO) {
        log.debug("Request to save GiftWrapper : {}", giftWrapperDTO);
        GiftWrapper giftWrapper = giftWrapperMapper.toEntity(giftWrapperDTO);
        giftWrapper = giftWrapperRepository.save(giftWrapper);
        return giftWrapperMapper.toDto(giftWrapper);
    }

    /**
     * Get all the giftWrappers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<GiftWrapperDTO> findAll() {
        log.debug("Request to get all GiftWrappers");
        return giftWrapperRepository.findAll().stream()
            .map(giftWrapperMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one giftWrapper by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GiftWrapperDTO findOne(Long id) {
        log.debug("Request to get GiftWrapper : {}", id);
        GiftWrapper giftWrapper = giftWrapperRepository.findOne(id);
        return giftWrapperMapper.toDto(giftWrapper);
    }

    /**
     * Delete the giftWrapper by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GiftWrapper : {}", id);
        giftWrapperRepository.delete(id);
    }
}
