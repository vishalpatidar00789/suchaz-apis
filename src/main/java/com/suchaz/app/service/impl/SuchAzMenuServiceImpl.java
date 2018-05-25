package com.suchaz.app.service.impl;

import com.suchaz.app.service.SuchAzMenuService;
import com.suchaz.app.domain.SuchAzMenu;
import com.suchaz.app.repository.SuchAzMenuRepository;
import com.suchaz.app.service.dto.SuchAzMenuDTO;
import com.suchaz.app.service.mapper.SuchAzMenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing SuchAzMenu.
 */
@Service
@Transactional
public class SuchAzMenuServiceImpl implements SuchAzMenuService {

    private final Logger log = LoggerFactory.getLogger(SuchAzMenuServiceImpl.class);

    private final SuchAzMenuRepository suchAzMenuRepository;

    private final SuchAzMenuMapper suchAzMenuMapper;

    public SuchAzMenuServiceImpl(SuchAzMenuRepository suchAzMenuRepository, SuchAzMenuMapper suchAzMenuMapper) {
        this.suchAzMenuRepository = suchAzMenuRepository;
        this.suchAzMenuMapper = suchAzMenuMapper;
    }

    /**
     * Save a suchAzMenu.
     *
     * @param suchAzMenuDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SuchAzMenuDTO save(SuchAzMenuDTO suchAzMenuDTO) {
        log.debug("Request to save SuchAzMenu : {}", suchAzMenuDTO);
        SuchAzMenu suchAzMenu = suchAzMenuMapper.toEntity(suchAzMenuDTO);
        suchAzMenu = suchAzMenuRepository.save(suchAzMenu);
        return suchAzMenuMapper.toDto(suchAzMenu);
    }

    /**
     * Get all the suchAzMenus.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SuchAzMenuDTO> findAll() {
        log.debug("Request to get all SuchAzMenus");
        return suchAzMenuRepository.findAllWithEagerRelationships().stream()
            .map(suchAzMenuMapper::toDtoWithoutItems)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get one suchAzMenu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SuchAzMenuDTO findOne(Long id) {
        log.debug("Request to get SuchAzMenu : {}", id);
        SuchAzMenu suchAzMenu = suchAzMenuRepository.findOneWithEagerRelationships(id);
        return suchAzMenuMapper.toDto(suchAzMenu);
    }

    /**
     * Delete the suchAzMenu by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SuchAzMenu : {}", id);
        suchAzMenuRepository.delete(id);
    }

	@Override
	public SuchAzMenuDTO findOneWithMenuCodeAndItems(String menuCode) {
		log.debug("Request to get SuchAzMenu with MenuCode: {}", menuCode);
        SuchAzMenu suchAzMenu = suchAzMenuRepository.findOneWithMenuCodeAndItems(menuCode);
        return suchAzMenuMapper.toDto(suchAzMenu);
	}
}
