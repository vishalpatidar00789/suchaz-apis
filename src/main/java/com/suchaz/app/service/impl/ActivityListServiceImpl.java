package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.ActivityList;
import com.suchaz.app.repository.ActivityListRepository;
import com.suchaz.app.service.ActivityListService;
import com.suchaz.app.service.dto.ActivityListDTO;
import com.suchaz.app.service.mapper.ActivityListMapper;

/**
 * Service Implementation for managing ActivityList.
 */
@Service
@Transactional
public class ActivityListServiceImpl implements ActivityListService {

    private final Logger log = LoggerFactory.getLogger(ActivityListServiceImpl.class);

    private final ActivityListRepository activityListRepository;

    private final ActivityListMapper activityListMapper;

    public ActivityListServiceImpl(ActivityListRepository activityListRepository, ActivityListMapper activityListMapper) {
        this.activityListRepository = activityListRepository;
        this.activityListMapper = activityListMapper;
    }

    /**
     * Save a activityList.
     *
     * @param activityListDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ActivityListDTO save(ActivityListDTO activityListDTO) {
        log.debug("Request to save ActivityList : {}", activityListDTO);
        ActivityList activityList = activityListMapper.toEntity(activityListDTO);
        activityList = activityListRepository.save(activityList);
        return activityListMapper.toDto(activityList);
    }

    /**
     * Get all the activityLists.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ActivityListDTO> findAll() {
        log.debug("Request to get all ActivityLists");
        return activityListRepository.findAll().stream()
            .map(activityListMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one activityList by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ActivityListDTO findOne(Long id) {
        log.debug("Request to get ActivityList : {}", id);
        ActivityList activityList = activityListRepository.findOne(id);
        return activityListMapper.toDto(activityList);
    }

    /**
     * Delete the activityList by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ActivityList : {}", id);
        activityListRepository.delete(id);
    }
}
