package com.suchaz.app.service.impl;

import com.suchaz.app.service.VendorService;
import com.suchaz.app.domain.Vendor;
import com.suchaz.app.repository.VendorRepository;
import com.suchaz.app.service.dto.VendorDTO;
import com.suchaz.app.service.mapper.VendorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Vendor.
 */
@Service
@Transactional
public class VendorServiceImpl implements VendorService {

    private final Logger log = LoggerFactory.getLogger(VendorServiceImpl.class);

    private final VendorRepository vendorRepository;

    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    /**
     * Save a vendor.
     *
     * @param vendorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VendorDTO save(VendorDTO vendorDTO) {
        log.debug("Request to save Vendor : {}", vendorDTO);
        Vendor vendor = vendorMapper.toEntity(vendorDTO);
        vendor = vendorRepository.save(vendor);
        return vendorMapper.toDto(vendor);
    }

    /**
     * Get all the vendors.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VendorDTO> findAll() {
        log.debug("Request to get all Vendors");
        return vendorRepository.findAll().stream()
            .map(vendorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one vendor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public VendorDTO findOne(Long id) {
        log.debug("Request to get Vendor : {}", id);
        Vendor vendor = vendorRepository.findOne(id);
        return vendorMapper.toDto(vendor);
    }

    /**
     * Delete the vendor by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vendor : {}", id);
        vendorRepository.delete(id);
    }
}
