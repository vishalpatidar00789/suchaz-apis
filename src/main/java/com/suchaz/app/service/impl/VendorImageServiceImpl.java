package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.VendorImage;
import com.suchaz.app.repository.VendorImageRepository;
import com.suchaz.app.service.VendorImageService;
import com.suchaz.app.service.dto.VendorImageDTO;
import com.suchaz.app.service.mapper.VendorImageMapper;

/**
 * Service Implementation for managing VendorImage.
 */
@Service
@Transactional
public class VendorImageServiceImpl implements VendorImageService {

    private final Logger log = LoggerFactory.getLogger(VendorImageServiceImpl.class);

    private final VendorImageRepository vendorImageRepository;

    private final VendorImageMapper vendorImageMapper;

    public VendorImageServiceImpl(VendorImageRepository vendorImageRepository, VendorImageMapper vendorImageMapper) {
        this.vendorImageRepository = vendorImageRepository;
        this.vendorImageMapper = vendorImageMapper;
    }

    /**
     * Save a vendorImage.
     *
     * @param vendorImageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VendorImageDTO save(VendorImageDTO vendorImageDTO) {
        log.debug("Request to save VendorImage : {}", vendorImageDTO);
        VendorImage vendorImage = vendorImageMapper.toEntity(vendorImageDTO);
        vendorImage = vendorImageRepository.save(vendorImage);
        return vendorImageMapper.toDto(vendorImage);
    }

    /**
     * Get all the vendorImages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VendorImageDTO> findAll() {
        log.debug("Request to get all VendorImages");
        return vendorImageRepository.findAll().stream()
            .map(vendorImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one vendorImage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public VendorImageDTO findOne(Long id) {
        log.debug("Request to get VendorImage : {}", id);
        VendorImage vendorImage = vendorImageRepository.findOne(id);
        return vendorImageMapper.toDto(vendorImage);
    }

    /**
     * Delete the vendorImage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VendorImage : {}", id);
        vendorImageRepository.delete(id);
    }
}
