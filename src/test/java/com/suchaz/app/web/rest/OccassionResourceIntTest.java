package com.suchaz.app.web.rest;

import static com.suchaz.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

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

import com.suchaz.app.SuchazapisApp;
import com.suchaz.app.domain.Occassion;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.OccassionRepository;
import com.suchaz.app.service.OccassionService;
import com.suchaz.app.service.dto.OccassionDTO;
import com.suchaz.app.service.mapper.OccassionMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the OccassionResource REST controller.
 *
 * @see OccassionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class OccassionResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_OCCASION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_OCCASION_CODE = "BBBBBBBBBB";

    private static final Status DEFAULT_IS_EXPOSED_TO_MENU = Status.ACTIVE;
    private static final Status UPDATED_IS_EXPOSED_TO_MENU = Status.INACTIVE;

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.INACTIVE;

    private static final Long DEFAULT_CREATED_DATE = 1L;
    private static final Long UPDATED_CREATED_DATE = 2L;

    private static final Long DEFAULT_LAST_UPDATED_DATE = 1L;
    private static final Long UPDATED_LAST_UPDATED_DATE = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private OccassionRepository occassionRepository;

    @Autowired
    private OccassionMapper occassionMapper;

    @Autowired
    private OccassionService occassionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOccassionMockMvc;

    private Occassion occassion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OccassionResource occassionResource = new OccassionResource(occassionService);
        this.restOccassionMockMvc = MockMvcBuilders.standaloneSetup(occassionResource)
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
    public static Occassion createEntity(EntityManager em) {
        Occassion occassion = new Occassion()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .occasionCode(DEFAULT_OCCASION_CODE)
            .isExposedToMenu(DEFAULT_IS_EXPOSED_TO_MENU)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return occassion;
    }

    @Before
    public void initTest() {
        occassion = createEntity(em);
    }

    @Test
    @Transactional
    public void createOccassion() throws Exception {
        int databaseSizeBeforeCreate = occassionRepository.findAll().size();

        // Create the Occassion
        OccassionDTO occassionDTO = occassionMapper.toDto(occassion);
        restOccassionMockMvc.perform(post("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isCreated());

        // Validate the Occassion in the database
        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeCreate + 1);
        Occassion testOccassion = occassionList.get(occassionList.size() - 1);
        assertThat(testOccassion.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOccassion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOccassion.getOccasionCode()).isEqualTo(DEFAULT_OCCASION_CODE);
        assertThat(testOccassion.getIsExposedToMenu()).isEqualTo(DEFAULT_IS_EXPOSED_TO_MENU);
        assertThat(testOccassion.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOccassion.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOccassion.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOccassion.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOccassion.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createOccassionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = occassionRepository.findAll().size();

        // Create the Occassion with an existing ID
        occassion.setId(1L);
        OccassionDTO occassionDTO = occassionMapper.toDto(occassion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOccassionMockMvc.perform(post("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Occassion in the database
        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = occassionRepository.findAll().size();
        // set the field null
        occassion.setName(null);

        // Create the Occassion, which fails.
        OccassionDTO occassionDTO = occassionMapper.toDto(occassion);

        restOccassionMockMvc.perform(post("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isBadRequest());

        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOccasionCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = occassionRepository.findAll().size();
        // set the field null
        occassion.setOccasionCode(null);

        // Create the Occassion, which fails.
        OccassionDTO occassionDTO = occassionMapper.toDto(occassion);

        restOccassionMockMvc.perform(post("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isBadRequest());

        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsExposedToMenuIsRequired() throws Exception {
        int databaseSizeBeforeTest = occassionRepository.findAll().size();
        // set the field null
        occassion.setIsExposedToMenu(null);

        // Create the Occassion, which fails.
        OccassionDTO occassionDTO = occassionMapper.toDto(occassion);

        restOccassionMockMvc.perform(post("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isBadRequest());

        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = occassionRepository.findAll().size();
        // set the field null
        occassion.setStatus(null);

        // Create the Occassion, which fails.
        OccassionDTO occassionDTO = occassionMapper.toDto(occassion);

        restOccassionMockMvc.perform(post("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isBadRequest());

        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = occassionRepository.findAll().size();
        // set the field null
        occassion.setCreatedDate(null);

        // Create the Occassion, which fails.
        OccassionDTO occassionDTO = occassionMapper.toDto(occassion);

        restOccassionMockMvc.perform(post("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isBadRequest());

        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = occassionRepository.findAll().size();
        // set the field null
        occassion.setCreatedBy(null);

        // Create the Occassion, which fails.
        OccassionDTO occassionDTO = occassionMapper.toDto(occassion);

        restOccassionMockMvc.perform(post("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isBadRequest());

        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOccassions() throws Exception {
        // Initialize the database
        occassionRepository.saveAndFlush(occassion);

        // Get all the occassionList
        restOccassionMockMvc.perform(get("/api/occassions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(occassion.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].occasionCode").value(hasItem(DEFAULT_OCCASION_CODE.toString())))
            .andExpect(jsonPath("$.[*].isExposedToMenu").value(hasItem(DEFAULT_IS_EXPOSED_TO_MENU.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getOccassion() throws Exception {
        // Initialize the database
        occassionRepository.saveAndFlush(occassion);

        // Get the occassion
        restOccassionMockMvc.perform(get("/api/occassions/{id}", occassion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(occassion.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.occasionCode").value(DEFAULT_OCCASION_CODE.toString()))
            .andExpect(jsonPath("$.isExposedToMenu").value(DEFAULT_IS_EXPOSED_TO_MENU.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOccassion() throws Exception {
        // Get the occassion
        restOccassionMockMvc.perform(get("/api/occassions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOccassion() throws Exception {
        // Initialize the database
        occassionRepository.saveAndFlush(occassion);
        int databaseSizeBeforeUpdate = occassionRepository.findAll().size();

        // Update the occassion
        Occassion updatedOccassion = occassionRepository.findOne(occassion.getId());
        // Disconnect from session so that the updates on updatedOccassion are not directly saved in db
        em.detach(updatedOccassion);
        updatedOccassion
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .occasionCode(UPDATED_OCCASION_CODE)
            .isExposedToMenu(UPDATED_IS_EXPOSED_TO_MENU)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        OccassionDTO occassionDTO = occassionMapper.toDto(updatedOccassion);

        restOccassionMockMvc.perform(put("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isOk());

        // Validate the Occassion in the database
        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeUpdate);
        Occassion testOccassion = occassionList.get(occassionList.size() - 1);
        assertThat(testOccassion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOccassion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOccassion.getOccasionCode()).isEqualTo(UPDATED_OCCASION_CODE);
        assertThat(testOccassion.getIsExposedToMenu()).isEqualTo(UPDATED_IS_EXPOSED_TO_MENU);
        assertThat(testOccassion.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOccassion.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOccassion.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOccassion.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOccassion.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingOccassion() throws Exception {
        int databaseSizeBeforeUpdate = occassionRepository.findAll().size();

        // Create the Occassion
        OccassionDTO occassionDTO = occassionMapper.toDto(occassion);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOccassionMockMvc.perform(put("/api/occassions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occassionDTO)))
            .andExpect(status().isCreated());

        // Validate the Occassion in the database
        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOccassion() throws Exception {
        // Initialize the database
        occassionRepository.saveAndFlush(occassion);
        int databaseSizeBeforeDelete = occassionRepository.findAll().size();

        // Get the occassion
        restOccassionMockMvc.perform(delete("/api/occassions/{id}", occassion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Occassion> occassionList = occassionRepository.findAll();
        assertThat(occassionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Occassion.class);
        Occassion occassion1 = new Occassion();
        occassion1.setId(1L);
        Occassion occassion2 = new Occassion();
        occassion2.setId(occassion1.getId());
        assertThat(occassion1).isEqualTo(occassion2);
        occassion2.setId(2L);
        assertThat(occassion1).isNotEqualTo(occassion2);
        occassion1.setId(null);
        assertThat(occassion1).isNotEqualTo(occassion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OccassionDTO.class);
        OccassionDTO occassionDTO1 = new OccassionDTO();
        occassionDTO1.setId(1L);
        OccassionDTO occassionDTO2 = new OccassionDTO();
        assertThat(occassionDTO1).isNotEqualTo(occassionDTO2);
        occassionDTO2.setId(occassionDTO1.getId());
        assertThat(occassionDTO1).isEqualTo(occassionDTO2);
        occassionDTO2.setId(2L);
        assertThat(occassionDTO1).isNotEqualTo(occassionDTO2);
        occassionDTO1.setId(null);
        assertThat(occassionDTO1).isNotEqualTo(occassionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(occassionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(occassionMapper.fromId(null)).isNull();
    }
}
