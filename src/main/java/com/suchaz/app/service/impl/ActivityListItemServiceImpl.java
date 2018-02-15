package com.suchaz.app.service.impl;

import com.suchaz.app.service.ActivityListItemService;
import com.suchaz.app.domain.ActivityListItem;
import com.suchaz.app.repository.ActivityListItemRepository;
import com.suchaz.app.service.dto.ActivityListItemDTO;
import com.suchaz.app.service.mapper.ActivityListItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ActivityListItem.
 */
@Service
@Transactional
public class ActivityListItemServiceImpl implements ActivityListItemService {

    private final Logger log = LoggerFactory.getLogger(ActivityListItemServiceImpl.class);

    private final ActivityListItemRepository activityListItemRepository;

    private final ActivityListItemMapper activityListItemMapper;

    public ActivityListItemServiceImpl(ActivityListItemRepository activityListItemRepository, ActivityListItemMapper activityListItemMapper) {
        this.activityListItemRepository = activityListItemRepository;
        this.activityListItemMapper = activityListItemMapper;
    }

    /**
     * Save a activityListItem.
     *
     * @param activityListItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ActivityListItemDTO save(ActivityListItemDTO activityListItemDTO) {
        log.debug("Request to save ActivityListItem : {}", activityListItemDTO);
        ActivityListItem activityListItem = activityListItemMapper.toEntity(activityListItemDTO);
        activityListItem = activityListItemRepository.save(activityListItem);
        return activityListItemMapper.toDto(activityListItem);
    }

    /**
     * Get all the activityListItems.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ActivityListItemDTO> findAll() {
        log.debug("Request to get all ActivityListItems");
        return activityListItemRepository.findAll().stream()
            .map(activityListItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one activityListItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ActivityListItemDTO findOne(Long id) {
        log.debug("Request to get ActivityListItem : {}", id);
        ActivityListItem activityListItem = activityListItemRepository.findOne(id);
        return activityListItemMapper.toDto(activityListItem);
    }

    /**
     * Delete the activityListItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ActivityListItem : {}", id);
        activityListItemRepository.delete(id);
    }
}
