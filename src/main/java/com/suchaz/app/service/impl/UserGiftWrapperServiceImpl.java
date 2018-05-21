package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.UserGiftWrapper;
import com.suchaz.app.repository.UserGiftWrapperRepository;
import com.suchaz.app.service.UserGiftWrapperService;
import com.suchaz.app.service.dto.UserGiftWrapperDTO;
import com.suchaz.app.service.mapper.UserGiftWrapperMapper;

/**
 * Service Implementation for managing UserGiftWrapper.
 */
@Service
@Transactional
public class UserGiftWrapperServiceImpl implements UserGiftWrapperService {

    private final Logger log = LoggerFactory.getLogger(UserGiftWrapperServiceImpl.class);

    private final UserGiftWrapperRepository userGiftWrapperRepository;

    private final UserGiftWrapperMapper userGiftWrapperMapper;

    public UserGiftWrapperServiceImpl(UserGiftWrapperRepository userGiftWrapperRepository, UserGiftWrapperMapper userGiftWrapperMapper) {
        this.userGiftWrapperRepository = userGiftWrapperRepository;
        this.userGiftWrapperMapper = userGiftWrapperMapper;
    }

    /**
     * Save a userGiftWrapper.
     *
     * @param userGiftWrapperDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserGiftWrapperDTO save(UserGiftWrapperDTO userGiftWrapperDTO) {
        log.debug("Request to save UserGiftWrapper : {}", userGiftWrapperDTO);
        UserGiftWrapper userGiftWrapper = userGiftWrapperMapper.toEntity(userGiftWrapperDTO);
        userGiftWrapper = userGiftWrapperRepository.save(userGiftWrapper);
        return userGiftWrapperMapper.toDto(userGiftWrapper);
    }

    /**
     * Get all the userGiftWrappers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserGiftWrapperDTO> findAll() {
        log.debug("Request to get all UserGiftWrappers");
        return userGiftWrapperRepository.findAll().stream()
            .map(userGiftWrapperMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userGiftWrapper by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserGiftWrapperDTO findOne(Long id) {
        log.debug("Request to get UserGiftWrapper : {}", id);
        UserGiftWrapper userGiftWrapper = userGiftWrapperRepository.findOne(id);
        return userGiftWrapperMapper.toDto(userGiftWrapper);
    }

    /**
     * Delete the userGiftWrapper by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserGiftWrapper : {}", id);
        userGiftWrapperRepository.delete(id);
    }
}
