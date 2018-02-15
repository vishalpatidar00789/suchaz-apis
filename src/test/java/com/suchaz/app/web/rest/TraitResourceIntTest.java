package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.Trait;
import com.suchaz.app.repository.TraitRepository;
import com.suchaz.app.service.TraitService;
import com.suchaz.app.service.dto.TraitDTO;
import com.suchaz.app.service.mapper.TraitMapper;
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

import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.domain.enumeration.Status;
/**
 * Test class for the TraitResource REST controller.
 *
 * @see TraitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class TraitResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TRAIT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRAIT_CODE = "BBBBBBBBBB";

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
    private TraitRepository traitRepository;

    @Autowired
    private TraitMapper traitMapper;

    @Autowired
    private TraitService traitService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTraitMockMvc;

    private Trait trait;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TraitResource traitResource = new TraitResource(traitService);
        this.restTraitMockMvc = MockMvcBuilders.standaloneSetup(traitResource)
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
    public static Trait createEntity(EntityManager em) {
        Trait trait = new Trait()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .traitCode(DEFAULT_TRAIT_CODE)
            .isExposedToMenu(DEFAULT_IS_EXPOSED_TO_MENU)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return trait;
    }

    @Before
    public void initTest() {
        trait = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrait() throws Exception {
        int databaseSizeBeforeCreate = traitRepository.findAll().size();

        // Create the Trait
        TraitDTO traitDTO = traitMapper.toDto(trait);
        restTraitMockMvc.perform(post("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isCreated());

        // Validate the Trait in the database
        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeCreate + 1);
        Trait testTrait = traitList.get(traitList.size() - 1);
        assertThat(testTrait.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTrait.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTrait.getTraitCode()).isEqualTo(DEFAULT_TRAIT_CODE);
        assertThat(testTrait.getIsExposedToMenu()).isEqualTo(DEFAULT_IS_EXPOSED_TO_MENU);
        assertThat(testTrait.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTrait.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTrait.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testTrait.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTrait.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createTraitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = traitRepository.findAll().size();

        // Create the Trait with an existing ID
        trait.setId(1L);
        TraitDTO traitDTO = traitMapper.toDto(trait);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTraitMockMvc.perform(post("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trait in the database
        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitRepository.findAll().size();
        // set the field null
        trait.setName(null);

        // Create the Trait, which fails.
        TraitDTO traitDTO = traitMapper.toDto(trait);

        restTraitMockMvc.perform(post("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isBadRequest());

        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTraitCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitRepository.findAll().size();
        // set the field null
        trait.setTraitCode(null);

        // Create the Trait, which fails.
        TraitDTO traitDTO = traitMapper.toDto(trait);

        restTraitMockMvc.perform(post("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isBadRequest());

        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsExposedToMenuIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitRepository.findAll().size();
        // set the field null
        trait.setIsExposedToMenu(null);

        // Create the Trait, which fails.
        TraitDTO traitDTO = traitMapper.toDto(trait);

        restTraitMockMvc.perform(post("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isBadRequest());

        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitRepository.findAll().size();
        // set the field null
        trait.setStatus(null);

        // Create the Trait, which fails.
        TraitDTO traitDTO = traitMapper.toDto(trait);

        restTraitMockMvc.perform(post("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isBadRequest());

        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitRepository.findAll().size();
        // set the field null
        trait.setCreatedDate(null);

        // Create the Trait, which fails.
        TraitDTO traitDTO = traitMapper.toDto(trait);

        restTraitMockMvc.perform(post("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isBadRequest());

        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitRepository.findAll().size();
        // set the field null
        trait.setCreatedBy(null);

        // Create the Trait, which fails.
        TraitDTO traitDTO = traitMapper.toDto(trait);

        restTraitMockMvc.perform(post("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isBadRequest());

        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTraits() throws Exception {
        // Initialize the database
        traitRepository.saveAndFlush(trait);

        // Get all the traitList
        restTraitMockMvc.perform(get("/api/traits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trait.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].traitCode").value(hasItem(DEFAULT_TRAIT_CODE.toString())))
            .andExpect(jsonPath("$.[*].isExposedToMenu").value(hasItem(DEFAULT_IS_EXPOSED_TO_MENU.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getTrait() throws Exception {
        // Initialize the database
        traitRepository.saveAndFlush(trait);

        // Get the trait
        restTraitMockMvc.perform(get("/api/traits/{id}", trait.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trait.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.traitCode").value(DEFAULT_TRAIT_CODE.toString()))
            .andExpect(jsonPath("$.isExposedToMenu").value(DEFAULT_IS_EXPOSED_TO_MENU.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrait() throws Exception {
        // Get the trait
        restTraitMockMvc.perform(get("/api/traits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrait() throws Exception {
        // Initialize the database
        traitRepository.saveAndFlush(trait);
        int databaseSizeBeforeUpdate = traitRepository.findAll().size();

        // Update the trait
        Trait updatedTrait = traitRepository.findOne(trait.getId());
        // Disconnect from session so that the updates on updatedTrait are not directly saved in db
        em.detach(updatedTrait);
        updatedTrait
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .traitCode(UPDATED_TRAIT_CODE)
            .isExposedToMenu(UPDATED_IS_EXPOSED_TO_MENU)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        TraitDTO traitDTO = traitMapper.toDto(updatedTrait);

        restTraitMockMvc.perform(put("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isOk());

        // Validate the Trait in the database
        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeUpdate);
        Trait testTrait = traitList.get(traitList.size() - 1);
        assertThat(testTrait.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTrait.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTrait.getTraitCode()).isEqualTo(UPDATED_TRAIT_CODE);
        assertThat(testTrait.getIsExposedToMenu()).isEqualTo(UPDATED_IS_EXPOSED_TO_MENU);
        assertThat(testTrait.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTrait.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTrait.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testTrait.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTrait.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingTrait() throws Exception {
        int databaseSizeBeforeUpdate = traitRepository.findAll().size();

        // Create the Trait
        TraitDTO traitDTO = traitMapper.toDto(trait);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTraitMockMvc.perform(put("/api/traits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitDTO)))
            .andExpect(status().isCreated());

        // Validate the Trait in the database
        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTrait() throws Exception {
        // Initialize the database
        traitRepository.saveAndFlush(trait);
        int databaseSizeBeforeDelete = traitRepository.findAll().size();

        // Get the trait
        restTraitMockMvc.perform(delete("/api/traits/{id}", trait.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Trait> traitList = traitRepository.findAll();
        assertThat(traitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trait.class);
        Trait trait1 = new Trait();
        trait1.setId(1L);
        Trait trait2 = new Trait();
        trait2.setId(trait1.getId());
        assertThat(trait1).isEqualTo(trait2);
        trait2.setId(2L);
        assertThat(trait1).isNotEqualTo(trait2);
        trait1.setId(null);
        assertThat(trait1).isNotEqualTo(trait2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TraitDTO.class);
        TraitDTO traitDTO1 = new TraitDTO();
        traitDTO1.setId(1L);
        TraitDTO traitDTO2 = new TraitDTO();
        assertThat(traitDTO1).isNotEqualTo(traitDTO2);
        traitDTO2.setId(traitDTO1.getId());
        assertThat(traitDTO1).isEqualTo(traitDTO2);
        traitDTO2.setId(2L);
        assertThat(traitDTO1).isNotEqualTo(traitDTO2);
        traitDTO1.setId(null);
        assertThat(traitDTO1).isNotEqualTo(traitDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(traitMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(traitMapper.fromId(null)).isNull();
    }
}
