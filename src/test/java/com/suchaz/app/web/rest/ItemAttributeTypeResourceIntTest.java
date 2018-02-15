package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.ItemAttributeType;
import com.suchaz.app.repository.ItemAttributeTypeRepository;
import com.suchaz.app.service.ItemAttributeTypeService;
import com.suchaz.app.service.dto.ItemAttributeTypeDTO;
import com.suchaz.app.service.mapper.ItemAttributeTypeMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.suchaz.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ItemAttributeTypeResource REST controller.
 *
 * @see ItemAttributeTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class ItemAttributeTypeResourceIntTest {

    private static final String DEFAULT_ATTRIBUTE_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private ItemAttributeTypeRepository itemAttributeTypeRepository;

    @Autowired
    private ItemAttributeTypeMapper itemAttributeTypeMapper;

    @Autowired
    private ItemAttributeTypeService itemAttributeTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemAttributeTypeMockMvc;

    private ItemAttributeType itemAttributeType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemAttributeTypeResource itemAttributeTypeResource = new ItemAttributeTypeResource(itemAttributeTypeService);
        this.restItemAttributeTypeMockMvc = MockMvcBuilders.standaloneSetup(itemAttributeTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemAttributeType createEntity(EntityManager em) {
        ItemAttributeType itemAttributeType = new ItemAttributeType()
            .attributeTypeName(DEFAULT_ATTRIBUTE_TYPE_NAME);
        return itemAttributeType;
    }

    @Before
    public void initTest() {
        itemAttributeType = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemAttributeType() throws Exception {
        int databaseSizeBeforeCreate = itemAttributeTypeRepository.findAll().size();

        // Create the ItemAttributeType
        ItemAttributeTypeDTO itemAttributeTypeDTO = itemAttributeTypeMapper.toDto(itemAttributeType);
        restItemAttributeTypeMockMvc.perform(post("/api/item-attribute-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemAttributeType in the database
        List<ItemAttributeType> itemAttributeTypeList = itemAttributeTypeRepository.findAll();
        assertThat(itemAttributeTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ItemAttributeType testItemAttributeType = itemAttributeTypeList.get(itemAttributeTypeList.size() - 1);
        assertThat(testItemAttributeType.getAttributeTypeName()).isEqualTo(DEFAULT_ATTRIBUTE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void createItemAttributeTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemAttributeTypeRepository.findAll().size();

        // Create the ItemAttributeType with an existing ID
        itemAttributeType.setId(1L);
        ItemAttributeTypeDTO itemAttributeTypeDTO = itemAttributeTypeMapper.toDto(itemAttributeType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemAttributeTypeMockMvc.perform(post("/api/item-attribute-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemAttributeType in the database
        List<ItemAttributeType> itemAttributeTypeList = itemAttributeTypeRepository.findAll();
        assertThat(itemAttributeTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAttributeTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemAttributeTypeRepository.findAll().size();
        // set the field null
        itemAttributeType.setAttributeTypeName(null);

        // Create the ItemAttributeType, which fails.
        ItemAttributeTypeDTO itemAttributeTypeDTO = itemAttributeTypeMapper.toDto(itemAttributeType);

        restItemAttributeTypeMockMvc.perform(post("/api/item-attribute-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ItemAttributeType> itemAttributeTypeList = itemAttributeTypeRepository.findAll();
        assertThat(itemAttributeTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemAttributeTypes() throws Exception {
        // Initialize the database
        itemAttributeTypeRepository.saveAndFlush(itemAttributeType);

        // Get all the itemAttributeTypeList
        restItemAttributeTypeMockMvc.perform(get("/api/item-attribute-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemAttributeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].attributeTypeName").value(hasItem(DEFAULT_ATTRIBUTE_TYPE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getItemAttributeType() throws Exception {
        // Initialize the database
        itemAttributeTypeRepository.saveAndFlush(itemAttributeType);

        // Get the itemAttributeType
        restItemAttributeTypeMockMvc.perform(get("/api/item-attribute-types/{id}", itemAttributeType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemAttributeType.getId().intValue()))
            .andExpect(jsonPath("$.attributeTypeName").value(DEFAULT_ATTRIBUTE_TYPE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemAttributeType() throws Exception {
        // Get the itemAttributeType
        restItemAttributeTypeMockMvc.perform(get("/api/item-attribute-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemAttributeType() throws Exception {
        // Initialize the database
        itemAttributeTypeRepository.saveAndFlush(itemAttributeType);
        int databaseSizeBeforeUpdate = itemAttributeTypeRepository.findAll().size();

        // Update the itemAttributeType
        ItemAttributeType updatedItemAttributeType = itemAttributeTypeRepository.findOne(itemAttributeType.getId());
        // Disconnect from session so that the updates on updatedItemAttributeType are not directly saved in db
        em.detach(updatedItemAttributeType);
        updatedItemAttributeType
            .attributeTypeName(UPDATED_ATTRIBUTE_TYPE_NAME);
        ItemAttributeTypeDTO itemAttributeTypeDTO = itemAttributeTypeMapper.toDto(updatedItemAttributeType);

        restItemAttributeTypeMockMvc.perform(put("/api/item-attribute-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ItemAttributeType in the database
        List<ItemAttributeType> itemAttributeTypeList = itemAttributeTypeRepository.findAll();
        assertThat(itemAttributeTypeList).hasSize(databaseSizeBeforeUpdate);
        ItemAttributeType testItemAttributeType = itemAttributeTypeList.get(itemAttributeTypeList.size() - 1);
        assertThat(testItemAttributeType.getAttributeTypeName()).isEqualTo(UPDATED_ATTRIBUTE_TYPE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingItemAttributeType() throws Exception {
        int databaseSizeBeforeUpdate = itemAttributeTypeRepository.findAll().size();

        // Create the ItemAttributeType
        ItemAttributeTypeDTO itemAttributeTypeDTO = itemAttributeTypeMapper.toDto(itemAttributeType);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restItemAttributeTypeMockMvc.perform(put("/api/item-attribute-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemAttributeTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemAttributeType in the database
        List<ItemAttributeType> itemAttributeTypeList = itemAttributeTypeRepository.findAll();
        assertThat(itemAttributeTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteItemAttributeType() throws Exception {
        // Initialize the database
        itemAttributeTypeRepository.saveAndFlush(itemAttributeType);
        int databaseSizeBeforeDelete = itemAttributeTypeRepository.findAll().size();

        // Get the itemAttributeType
        restItemAttributeTypeMockMvc.perform(delete("/api/item-attribute-types/{id}", itemAttributeType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemAttributeType> itemAttributeTypeList = itemAttributeTypeRepository.findAll();
        assertThat(itemAttributeTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAttributeType.class);
        ItemAttributeType itemAttributeType1 = new ItemAttributeType();
        itemAttributeType1.setId(1L);
        ItemAttributeType itemAttributeType2 = new ItemAttributeType();
        itemAttributeType2.setId(itemAttributeType1.getId());
        assertThat(itemAttributeType1).isEqualTo(itemAttributeType2);
        itemAttributeType2.setId(2L);
        assertThat(itemAttributeType1).isNotEqualTo(itemAttributeType2);
        itemAttributeType1.setId(null);
        assertThat(itemAttributeType1).isNotEqualTo(itemAttributeType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemAttributeTypeDTO.class);
        ItemAttributeTypeDTO itemAttributeTypeDTO1 = new ItemAttributeTypeDTO();
        itemAttributeTypeDTO1.setId(1L);
        ItemAttributeTypeDTO itemAttributeTypeDTO2 = new ItemAttributeTypeDTO();
        assertThat(itemAttributeTypeDTO1).isNotEqualTo(itemAttributeTypeDTO2);
        itemAttributeTypeDTO2.setId(itemAttributeTypeDTO1.getId());
        assertThat(itemAttributeTypeDTO1).isEqualTo(itemAttributeTypeDTO2);
        itemAttributeTypeDTO2.setId(2L);
        assertThat(itemAttributeTypeDTO1).isNotEqualTo(itemAttributeTypeDTO2);
        itemAttributeTypeDTO1.setId(null);
        assertThat(itemAttributeTypeDTO1).isNotEqualTo(itemAttributeTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(itemAttributeTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(itemAttributeTypeMapper.fromId(null)).isNull();
    }
}
