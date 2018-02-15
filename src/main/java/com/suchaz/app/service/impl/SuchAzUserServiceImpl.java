package com.suchaz.app.service.impl;

import com.suchaz.app.service.SuchAzUserService;
import com.suchaz.app.domain.SuchAzUser;
import com.suchaz.app.repository.SuchAzUserRepository;
import com.suchaz.app.service.dto.SuchAzUserDTO;
import com.suchaz.app.service.mapper.SuchAzUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing SuchAzUser.
 */
@Service
@Transactional
public class SuchAzUserServiceImpl implements SuchAzUserService {

    private final Logger log = LoggerFactory.getLogger(SuchAzUserServiceImpl.class);

    private final SuchAzUserRepository suchAzUserRepository;

    private final SuchAzUserMapper suchAzUserMapper;

    public SuchAzUserServiceImpl(SuchAzUserRepository suchAzUserRepository, SuchAzUserMapper suchAzUserMapper) {
        this.suchAzUserRepository = suchAzUserRepository;
        this.suchAzUserMapper = suchAzUserMapper;
    }

    /**
     * Save a suchAzUser.
     *
     * @param suchAzUserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SuchAzUserDTO save(SuchAzUserDTO suchAzUserDTO) {
        log.debug("Request to save SuchAzUser : {}", suchAzUserDTO);
        SuchAzUser suchAzUser = suchAzUserMapper.toEntity(suchAzUserDTO);
        suchAzUser = suchAzUserRepository.save(suchAzUser);
        return suchAzUserMapper.toDto(suchAzUser);
    }

    /**
     * Get all the suchAzUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SuchAzUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SuchAzUsers");
        return suchAzUserRepository.findAll(pageable)
            .map(suchAzUserMapper::toDto);
    }


    /**
     *  get all the suchAzUsers where UserProfile is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<SuchAzUserDTO> findAllWhereUserProfileIsNull() {
        log.debug("Request to get all suchAzUsers where UserProfile is null");
        return StreamSupport
            .stream(suchAzUserRepository.findAll().spliterator(), false)
            .filter(suchAzUser -> suchAzUser.getUserProfile() == null)
            .map(suchAzUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the suchAzUsers where WishList is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<SuchAzUserDTO> findAllWhereWishListIsNull() {
        log.debug("Request to get all suchAzUsers where WishList is null");
        return StreamSupport
            .stream(suchAzUserRepository.findAll().spliterator(), false)
            .filter(suchAzUser -> suchAzUser.getWishList() == null)
            .map(suchAzUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the suchAzUsers where BuyLaterList is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<SuchAzUserDTO> findAllWhereBuyLaterListIsNull() {
        log.debug("Request to get all suchAzUsers where BuyLaterList is null");
        return StreamSupport
            .stream(suchAzUserRepository.findAll().spliterator(), false)
            .filter(suchAzUser -> suchAzUser.getBuyLaterList() == null)
            .map(suchAzUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the suchAzUsers where ActivityList is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<SuchAzUserDTO> findAllWhereActivityListIsNull() {
        log.debug("Request to get all suchAzUsers where ActivityList is null");
        return StreamSupport
            .stream(suchAzUserRepository.findAll().spliterator(), false)
            .filter(suchAzUser -> suchAzUser.getActivityList() == null)
            .map(suchAzUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one suchAzUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SuchAzUserDTO findOne(Long id) {
        log.debug("Request to get SuchAzUser : {}", id);
        SuchAzUser suchAzUser = suchAzUserRepository.findOneWithEagerRelationships(id);
        return suchAzUserMapper.toDto(suchAzUser);
    }

    /**
     * Delete the suchAzUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SuchAzUser : {}", id);
        suchAzUserRepository.delete(id);
    }
}
