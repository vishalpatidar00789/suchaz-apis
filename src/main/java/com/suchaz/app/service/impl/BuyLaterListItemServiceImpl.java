package com.suchaz.app.service.impl;

import com.suchaz.app.service.BuyLaterListItemService;
import com.suchaz.app.domain.BuyLaterListItem;
import com.suchaz.app.repository.BuyLaterListItemRepository;
import com.suchaz.app.service.dto.BuyLaterListItemDTO;
import com.suchaz.app.service.mapper.BuyLaterListItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing BuyLaterListItem.
 */
@Service
@Transactional
public class BuyLaterListItemServiceImpl implements BuyLaterListItemService {

    private final Logger log = LoggerFactory.getLogger(BuyLaterListItemServiceImpl.class);

    private final BuyLaterListItemRepository buyLaterListItemRepository;

    private final BuyLaterListItemMapper buyLaterListItemMapper;

    public BuyLaterListItemServiceImpl(BuyLaterListItemRepository buyLaterListItemRepository, BuyLaterListItemMapper buyLaterListItemMapper) {
        this.buyLaterListItemRepository = buyLaterListItemRepository;
        this.buyLaterListItemMapper = buyLaterListItemMapper;
    }

    /**
     * Save a buyLaterListItem.
     *
     * @param buyLaterListItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BuyLaterListItemDTO save(BuyLaterListItemDTO buyLaterListItemDTO) {
        log.debug("Request to save BuyLaterListItem : {}", buyLaterListItemDTO);
        BuyLaterListItem buyLaterListItem = buyLaterListItemMapper.toEntity(buyLaterListItemDTO);
        buyLaterListItem = buyLaterListItemRepository.save(buyLaterListItem);
        return buyLaterListItemMapper.toDto(buyLaterListItem);
    }

    /**
     * Get all the buyLaterListItems.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BuyLaterListItemDTO> findAll() {
        log.debug("Request to get all BuyLaterListItems");
        return buyLaterListItemRepository.findAll().stream()
            .map(buyLaterListItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one buyLaterListItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BuyLaterListItemDTO findOne(Long id) {
        log.debug("Request to get BuyLaterListItem : {}", id);
        BuyLaterListItem buyLaterListItem = buyLaterListItemRepository.findOne(id);
        return buyLaterListItemMapper.toDto(buyLaterListItem);
    }

    /**
     * Delete the buyLaterListItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BuyLaterListItem : {}", id);
        buyLaterListItemRepository.delete(id);
    }
}
