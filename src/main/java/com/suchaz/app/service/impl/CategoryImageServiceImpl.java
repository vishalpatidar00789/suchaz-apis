package com.suchaz.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.CategoryImage;
import com.suchaz.app.repository.CategoryImageRepository;
import com.suchaz.app.service.CategoryImageService;
import com.suchaz.app.service.dto.CategoryImageDTO;
import com.suchaz.app.service.mapper.CategoryImageMapper;

/**
 * Service Implementation for managing CategoryImage.
 */
@Service
@Transactional
public class CategoryImageServiceImpl implements CategoryImageService {

    private final Logger log = LoggerFactory.getLogger(CategoryImageServiceImpl.class);

    private final CategoryImageRepository categoryImageRepository;

    private final CategoryImageMapper categoryImageMapper;

    public CategoryImageServiceImpl(CategoryImageRepository categoryImageRepository, CategoryImageMapper categoryImageMapper) {
        this.categoryImageRepository = categoryImageRepository;
        this.categoryImageMapper = categoryImageMapper;
    }

    /**
     * Save a categoryImage.
     *
     * @param categoryImageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryImageDTO save(CategoryImageDTO categoryImageDTO) {
        log.debug("Request to save CategoryImage : {}", categoryImageDTO);
        CategoryImage categoryImage = categoryImageMapper.toEntity(categoryImageDTO);
        categoryImage = categoryImageRepository.save(categoryImage);
        return categoryImageMapper.toDto(categoryImage);
    }

    /**
     * Get all the categoryImages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategoryImageDTO> findAll() {
        log.debug("Request to get all CategoryImages");
        return categoryImageRepository.findAll().stream()
            .map(categoryImageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one categoryImage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CategoryImageDTO findOne(Long id) {
        log.debug("Request to get CategoryImage : {}", id);
        CategoryImage categoryImage = categoryImageRepository.findOne(id);
        return categoryImageMapper.toDto(categoryImage);
    }

    /**
     * Delete the categoryImage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoryImage : {}", id);
        categoryImageRepository.delete(id);
    }
}
