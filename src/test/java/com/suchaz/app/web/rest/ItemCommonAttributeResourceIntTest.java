package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.ItemCommonAttribute;
import com.suchaz.app.repository.ItemCommonAttributeRepository;
import com.suchaz.app.service.ItemCommonAttributeService;
import com.suchaz.app.service.dto.ItemCommonAttributeDTO;
import com.suchaz.app.service.mapper.ItemCommonAttributeMapper;
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
 * Test class for the ItemCommonAttributeResource REST controller.
 *
 * @see ItemCommonAttributeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class ItemCommonAttributeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_QUICK_VIEW_ENABLED = false;
    private static final Boolean UPDATED_IS_QUICK_VIEW_ENABLED = true;

    @Autowired
    private ItemCommonAttributeRepository itemCommonAttributeRepository;

    @Autowired
    private ItemCommonAttributeMapper itemCommonAttributeMapper;

    @Autowired
    private ItemCommonAttributeService itemCommonAttributeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemCommonAttributeMockMvc;

    private ItemCommonAttribute itemCommonAttribute;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemCommonAttributeResource itemCommonAttributeResource = new ItemCommonAttributeResource(itemCommonAttributeService);
        this.restItemCommonAttributeMockMvc = MockMvcBuilders.standaloneSetup(itemCommonAttributeResource)
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
    public static ItemCommonAttribute createEntity(EntityManager em) {
        ItemCommonAttribute itemCommonAttribute = new ItemCommonAttribute()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .isQuickViewEnabled(DEFAULT_IS_QUICK_VIEW_ENABLED);
        return itemCommonAttribute;
    }

    @Before
    public void initTest() {
        itemCommonAttribute = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemCommonAttribute() throws Exception {
        int databaseSizeBeforeCreate = itemCommonAttributeRepository.findAll().size();

        // Create the ItemCommonAttribute
        ItemCommonAttributeDTO itemCommonAttributeDTO = itemCommonAttributeMapper.toDto(itemCommonAttribute);
        restItemCommonAttributeMockMvc.perform(post("/api/item-common-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemCommonAttributeDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemCommonAttribute in the database
        List<ItemCommonAttribute> itemCommonAttributeList = itemCommonAttributeRepository.findAll();
        assertThat(itemCommonAttributeList).hasSize(databaseSizeBeforeCreate + 1);
        ItemCommonAttribute testItemCommonAttribute = itemCommonAttributeList.get(itemCommonAttributeList.size() - 1);
        assertThat(testItemCommonAttribute.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testItemCommonAttribute.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testItemCommonAttribute.isIsQuickViewEnabled()).isEqualTo(DEFAULT_IS_QUICK_VIEW_ENABLED);
    }

    @Test
    @Transactional
    public void createItemCommonAttributeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemCommonAttributeRepository.findAll().size();

        // Create the ItemCommonAttribute with an existing ID
        itemCommonAttribute.setId(1L);
        ItemCommonAttributeDTO itemCommonAttributeDTO = itemCommonAttributeMapper.toDto(itemCommonAttribute);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemCommonAttributeMockMvc.perform(post("/api/item-common-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemCommonAttributeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemCommonAttribute in the database
        List<ItemCommonAttribute> itemCommonAttributeList = itemCommonAttributeRepository.findAll();
        assertThat(itemCommonAttributeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemCommonAttributeRepository.findAll().size();
        // set the field null
        itemCommonAttribute.setName(null);

        // Create the ItemCommonAttribute, which fails.
        ItemCommonAttributeDTO itemCommonAttributeDTO = itemCommonAttributeMapper.toDto(itemCommonAttribute);

        restItemCommonAttributeMockMvc.perform(post("/api/item-common-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemCommonAttributeDTO)))
            .andExpect(status().isBadRequest());

        List<ItemCommonAttribute> itemCommonAttributeList = itemCommonAttributeRepository.findAll();
        assertThat(itemCommonAttributeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemCommonAttributeRepository.findAll().size();
        // set the field null
        itemCommonAttribute.setValue(null);

        // Create the ItemCommonAttribute, which fails.
        ItemCommonAttributeDTO itemCommonAttributeDTO = itemCommonAttributeMapper.toDto(itemCommonAttribute);

        restItemCommonAttributeMockMvc.perform(post("/api/item-common-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemCommonAttributeDTO)))
            .andExpect(status().isBadRequest());

        List<ItemCommonAttribute> itemCommonAttributeList = itemCommonAttributeRepository.findAll();
        assertThat(itemCommonAttributeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemCommonAttributes() throws Exception {
        // Initialize the database
        itemCommonAttributeRepository.saveAndFlush(itemCommonAttribute);

        // Get all the itemCommonAttributeList
        restItemCommonAttributeMockMvc.perform(get("/api/item-common-attributes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemCommonAttribute.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].isQuickViewEnabled").value(hasItem(DEFAULT_IS_QUICK_VIEW_ENABLED.booleanValue())));
    }

    @Test
    @Transactional
    public void getItemCommonAttribute() throws Exception {
        // Initialize the database
        itemCommonAttributeRepository.saveAndFlush(itemCommonAttribute);

        // Get the itemCommonAttribute
        restItemCommonAttributeMockMvc.perform(get("/api/item-common-attributes/{id}", itemCommonAttribute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemCommonAttribute.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.isQuickViewEnabled").value(DEFAULT_IS_QUICK_VIEW_ENABLED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingItemCommonAttribute() throws Exception {
        // Get the itemCommonAttribute
        restItemCommonAttributeMockMvc.perform(get("/api/item-common-attributes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemCommonAttribute() throws Exception {
        // Initialize the database
        itemCommonAttributeRepository.saveAndFlush(itemCommonAttribute);
        int databaseSizeBeforeUpdate = itemCommonAttributeRepository.findAll().size();

        // Update the itemCommonAttribute
        ItemCommonAttribute updatedItemCommonAttribute = itemCommonAttributeRepository.findOne(itemCommonAttribute.getId());
        // Disconnect from session so that the updates on updatedItemCommonAttribute are not directly saved in db
        em.detach(updatedItemCommonAttribute);
        updatedItemCommonAttribute
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .isQuickViewEnabled(UPDATED_IS_QUICK_VIEW_ENABLED);
        ItemCommonAttributeDTO itemCommonAttributeDTO = itemCommonAttributeMapper.toDto(updatedItemCommonAttribute);

        restItemCommonAttributeMockMvc.perform(put("/api/item-common-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemCommonAttributeDTO)))
            .andExpect(status().isOk());

        // Validate the ItemCommonAttribute in the database
        List<ItemCommonAttribute> itemCommonAttributeList = itemCommonAttributeRepository.findAll();
        assertThat(itemCommonAttributeList).hasSize(databaseSizeBeforeUpdate);
        ItemCommonAttribute testItemCommonAttribute = itemCommonAttributeList.get(itemCommonAttributeList.size() - 1);
        assertThat(testItemCommonAttribute.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testItemCommonAttribute.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testItemCommonAttribute.isIsQuickViewEnabled()).isEqualTo(UPDATED_IS_QUICK_VIEW_ENABLED);
    }

    @Test
    @Transactional
    public void updateNonExistingItemCommonAttribute() throws Exception {
        int databaseSizeBeforeUpdate = itemCommonAttributeRepository.findAll().size();

        // Create the ItemCommonAttribute
        ItemCommonAttributeDTO itemCommonAttributeDTO = itemCommonAttributeMapper.toDto(itemCommonAttribute);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restItemCommonAttributeMockMvc.perform(put("/api/item-common-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemCommonAttributeDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemCommonAttribute in the database
        List<ItemCommonAttribute> itemCommonAttributeList = itemCommonAttributeRepository.findAll();
        assertThat(itemCommonAttributeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteItemCommonAttribute() throws Exception {
        // Initialize the database
        itemCommonAttributeRepository.saveAndFlush(itemCommonAttribute);
        int databaseSizeBeforeDelete = itemCommonAttributeRepository.findAll().size();

        // Get the itemCommonAttribute
        restItemCommonAttributeMockMvc.perform(delete("/api/item-common-attributes/{id}", itemCommonAttribute.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemCommonAttribute> itemCommonAttributeList = itemCommonAttributeRepository.findAll();
        assertThat(itemCommonAttributeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemCommonAttribute.class);
        ItemCommonAttribute itemCommonAttribute1 = new ItemCommonAttribute();
        itemCommonAttribute1.setId(1L);
        ItemCommonAttribute itemCommonAttribute2 = new ItemCommonAttribute();
        itemCommonAttribute2.setId(itemCommonAttribute1.getId());
        assertThat(itemCommonAttribute1).isEqualTo(itemCommonAttribute2);
        itemCommonAttribute2.setId(2L);
        assertThat(itemCommonAttribute1).isNotEqualTo(itemCommonAttribute2);
        itemCommonAttribute1.setId(null);
        assertThat(itemCommonAttribute1).isNotEqualTo(itemCommonAttribute2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemCommonAttributeDTO.class);
        ItemCommonAttributeDTO itemCommonAttributeDTO1 = new ItemCommonAttributeDTO();
        itemCommonAttributeDTO1.setId(1L);
        ItemCommonAttributeDTO itemCommonAttributeDTO2 = new ItemCommonAttributeDTO();
        assertThat(itemCommonAttributeDTO1).isNotEqualTo(itemCommonAttributeDTO2);
        itemCommonAttributeDTO2.setId(itemCommonAttributeDTO1.getId());
        assertThat(itemCommonAttributeDTO1).isEqualTo(itemCommonAttributeDTO2);
        itemCommonAttributeDTO2.setId(2L);
        assertThat(itemCommonAttributeDTO1).isNotEqualTo(itemCommonAttributeDTO2);
        itemCommonAttributeDTO1.setId(null);
        assertThat(itemCommonAttributeDTO1).isNotEqualTo(itemCommonAttributeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(itemCommonAttributeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(itemCommonAttributeMapper.fromId(null)).isNull();
    }
}
