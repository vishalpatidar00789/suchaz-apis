package com.suchaz.app.service.impl;

import com.suchaz.app.service.BuyLaterListService;
import com.suchaz.app.domain.BuyLaterList;
import com.suchaz.app.repository.BuyLaterListRepository;
import com.suchaz.app.service.dto.BuyLaterListDTO;
import com.suchaz.app.service.mapper.BuyLaterListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing BuyLaterList.
 */
@Service
@Transactional
public class BuyLaterListServiceImpl implements BuyLaterListService {

    private final Logger log = LoggerFactory.getLogger(BuyLaterListServiceImpl.class);

    private final BuyLaterListRepository buyLaterListRepository;

    private final BuyLaterListMapper buyLaterListMapper;

    public BuyLaterListServiceImpl(BuyLaterListRepository buyLaterListRepository, BuyLaterListMapper buyLaterListMapper) {
        this.buyLaterListRepository = buyLaterListRepository;
        this.buyLaterListMapper = buyLaterListMapper;
    }

    /**
     * Save a buyLaterList.
     *
     * @param buyLaterListDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BuyLaterListDTO save(BuyLaterListDTO buyLaterListDTO) {
        log.debug("Request to save BuyLaterList : {}", buyLaterListDTO);
        BuyLaterList buyLaterList = buyLaterListMapper.toEntity(buyLaterListDTO);
        buyLaterList = buyLaterListRepository.save(buyLaterList);
        return buyLaterListMapper.toDto(buyLaterList);
    }

    /**
     * Get all the buyLaterLists.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BuyLaterListDTO> findAll() {
        log.debug("Request to get all BuyLaterLists");
        return buyLaterListRepository.findAll().stream()
            .map(buyLaterListMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one buyLaterList by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BuyLaterListDTO findOne(Long id) {
        log.debug("Request to get BuyLaterList : {}", id);
        BuyLaterList buyLaterList = buyLaterListRepository.findOne(id);
        return buyLaterListMapper.toDto(buyLaterList);
    }

    /**
     * Delete the buyLaterList by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BuyLaterList : {}", id);
        buyLaterListRepository.delete(id);
    }
}
