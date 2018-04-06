package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.ConsumerProfileHistory;
import com.suchaz.app.repository.ConsumerProfileHistoryRepository;
import com.suchaz.app.service.ConsumerProfileHistoryService;
import com.suchaz.app.service.dto.ConsumerProfileHistoryDTO;
import com.suchaz.app.service.mapper.ConsumerProfileHistoryMapper;
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

import com.suchaz.app.domain.enumeration.Gender;
import com.suchaz.app.domain.enumeration.AgeGroup;
/**
 * Test class for the ConsumerProfileHistoryResource REST controller.
 *
 * @see ConsumerProfileHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class ConsumerProfileHistoryResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final AgeGroup DEFAULT_AGE = AgeGroup.KIDS;
    private static final AgeGroup UPDATED_AGE = AgeGroup.TEEN;

    private static final String DEFAULT_INPUT_TRAITS = "AAAAAAAAAA";
    private static final String UPDATED_INPUT_TRAITS = "BBBBBBBBBB";

    private static final String DEFAULT_TRAIT_STRUCTURE = "AAAAAAAAAA";
    private static final String UPDATED_TRAIT_STRUCTURE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITY_STRUCTURE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_STRUCTURE = "BBBBBBBBBB";

    private static final String DEFAULT_HOBBY_STRUCTURE = "AAAAAAAAAA";
    private static final String UPDATED_HOBBY_STRUCTURE = "BBBBBBBBBB";

    private static final String DEFAULT_INPUT_RELETIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_INPUT_RELETIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_INPUT_HOBBIES = "AAAAAAAAAA";
    private static final String UPDATED_INPUT_HOBBIES = "BBBBBBBBBB";

    private static final String DEFAULT_RECCOMENDED_PRODUCT_TYPES = "AAAAAAAAAA";
    private static final String UPDATED_RECCOMENDED_PRODUCT_TYPES = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_DATE = 1L;
    private static final Long UPDATED_CREATED_DATE = 2L;

    private static final Long DEFAULT_LAST_UPDATED_DATE = 1L;
    private static final Long UPDATED_LAST_UPDATED_DATE = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private ConsumerProfileHistoryRepository consumerProfileHistoryRepository;

    @Autowired
    private ConsumerProfileHistoryMapper consumerProfileHistoryMapper;

    @Autowired
    private ConsumerProfileHistoryService consumerProfileHistoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConsumerProfileHistoryMockMvc;

    private ConsumerProfileHistory consumerProfileHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConsumerProfileHistoryResource consumerProfileHistoryResource = new ConsumerProfileHistoryResource(consumerProfileHistoryService);
        this.restConsumerProfileHistoryMockMvc = MockMvcBuilders.standaloneSetup(consumerProfileHistoryResource)
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
    public static ConsumerProfileHistory createEntity(EntityManager em) {
        ConsumerProfileHistory consumerProfileHistory = new ConsumerProfileHistory()
            .name(DEFAULT_NAME)
            .gender(DEFAULT_GENDER)
            .age(DEFAULT_AGE)
            .inputTraits(DEFAULT_INPUT_TRAITS)
            .traitStructure(DEFAULT_TRAIT_STRUCTURE)
            .activityStructure(DEFAULT_ACTIVITY_STRUCTURE)
            .hobbyStructure(DEFAULT_HOBBY_STRUCTURE)
            .inputReletionship(DEFAULT_INPUT_RELETIONSHIP)
            .inputHobbies(DEFAULT_INPUT_HOBBIES)
            .reccomendedProductTypes(DEFAULT_RECCOMENDED_PRODUCT_TYPES)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return consumerProfileHistory;
    }

    @Before
    public void initTest() {
        consumerProfileHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsumerProfileHistory() throws Exception {
        int databaseSizeBeforeCreate = consumerProfileHistoryRepository.findAll().size();

        // Create the ConsumerProfileHistory
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = consumerProfileHistoryMapper.toDto(consumerProfileHistory);
        restConsumerProfileHistoryMockMvc.perform(post("/api/consumer-profile-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsumerProfileHistory in the database
        List<ConsumerProfileHistory> consumerProfileHistoryList = consumerProfileHistoryRepository.findAll();
        assertThat(consumerProfileHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        ConsumerProfileHistory testConsumerProfileHistory = consumerProfileHistoryList.get(consumerProfileHistoryList.size() - 1);
        assertThat(testConsumerProfileHistory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testConsumerProfileHistory.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testConsumerProfileHistory.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testConsumerProfileHistory.getInputTraits()).isEqualTo(DEFAULT_INPUT_TRAITS);
        assertThat(testConsumerProfileHistory.getTraitStructure()).isEqualTo(DEFAULT_TRAIT_STRUCTURE);
        assertThat(testConsumerProfileHistory.getActivityStructure()).isEqualTo(DEFAULT_ACTIVITY_STRUCTURE);
        assertThat(testConsumerProfileHistory.getHobbyStructure()).isEqualTo(DEFAULT_HOBBY_STRUCTURE);
        assertThat(testConsumerProfileHistory.getInputReletionship()).isEqualTo(DEFAULT_INPUT_RELETIONSHIP);
        assertThat(testConsumerProfileHistory.getInputHobbies()).isEqualTo(DEFAULT_INPUT_HOBBIES);
        assertThat(testConsumerProfileHistory.getReccomendedProductTypes()).isEqualTo(DEFAULT_RECCOMENDED_PRODUCT_TYPES);
        assertThat(testConsumerProfileHistory.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testConsumerProfileHistory.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testConsumerProfileHistory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testConsumerProfileHistory.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createConsumerProfileHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consumerProfileHistoryRepository.findAll().size();

        // Create the ConsumerProfileHistory with an existing ID
        consumerProfileHistory.setId(1L);
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = consumerProfileHistoryMapper.toDto(consumerProfileHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsumerProfileHistoryMockMvc.perform(post("/api/consumer-profile-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsumerProfileHistory in the database
        List<ConsumerProfileHistory> consumerProfileHistoryList = consumerProfileHistoryRepository.findAll();
        assertThat(consumerProfileHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = consumerProfileHistoryRepository.findAll().size();
        // set the field null
        consumerProfileHistory.setGender(null);

        // Create the ConsumerProfileHistory, which fails.
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = consumerProfileHistoryMapper.toDto(consumerProfileHistory);

        restConsumerProfileHistoryMockMvc.perform(post("/api/consumer-profile-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<ConsumerProfileHistory> consumerProfileHistoryList = consumerProfileHistoryRepository.findAll();
        assertThat(consumerProfileHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgeIsRequired() throws Exception {
        int databaseSizeBeforeTest = consumerProfileHistoryRepository.findAll().size();
        // set the field null
        consumerProfileHistory.setAge(null);

        // Create the ConsumerProfileHistory, which fails.
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = consumerProfileHistoryMapper.toDto(consumerProfileHistory);

        restConsumerProfileHistoryMockMvc.perform(post("/api/consumer-profile-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<ConsumerProfileHistory> consumerProfileHistoryList = consumerProfileHistoryRepository.findAll();
        assertThat(consumerProfileHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = consumerProfileHistoryRepository.findAll().size();
        // set the field null
        consumerProfileHistory.setCreatedDate(null);

        // Create the ConsumerProfileHistory, which fails.
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = consumerProfileHistoryMapper.toDto(consumerProfileHistory);

        restConsumerProfileHistoryMockMvc.perform(post("/api/consumer-profile-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<ConsumerProfileHistory> consumerProfileHistoryList = consumerProfileHistoryRepository.findAll();
        assertThat(consumerProfileHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = consumerProfileHistoryRepository.findAll().size();
        // set the field null
        consumerProfileHistory.setCreatedBy(null);

        // Create the ConsumerProfileHistory, which fails.
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = consumerProfileHistoryMapper.toDto(consumerProfileHistory);

        restConsumerProfileHistoryMockMvc.perform(post("/api/consumer-profile-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<ConsumerProfileHistory> consumerProfileHistoryList = consumerProfileHistoryRepository.findAll();
        assertThat(consumerProfileHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConsumerProfileHistories() throws Exception {
        // Initialize the database
        consumerProfileHistoryRepository.saveAndFlush(consumerProfileHistory);

        // Get all the consumerProfileHistoryList
        restConsumerProfileHistoryMockMvc.perform(get("/api/consumer-profile-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consumerProfileHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE.toString())))
            .andExpect(jsonPath("$.[*].inputTraits").value(hasItem(DEFAULT_INPUT_TRAITS.toString())))
            .andExpect(jsonPath("$.[*].traitStructure").value(hasItem(DEFAULT_TRAIT_STRUCTURE.toString())))
            .andExpect(jsonPath("$.[*].activityStructure").value(hasItem(DEFAULT_ACTIVITY_STRUCTURE.toString())))
            .andExpect(jsonPath("$.[*].hobbyStructure").value(hasItem(DEFAULT_HOBBY_STRUCTURE.toString())))
            .andExpect(jsonPath("$.[*].inputReletionship").value(hasItem(DEFAULT_INPUT_RELETIONSHIP.toString())))
            .andExpect(jsonPath("$.[*].inputHobbies").value(hasItem(DEFAULT_INPUT_HOBBIES.toString())))
            .andExpect(jsonPath("$.[*].reccomendedProductTypes").value(hasItem(DEFAULT_RECCOMENDED_PRODUCT_TYPES.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getConsumerProfileHistory() throws Exception {
        // Initialize the database
        consumerProfileHistoryRepository.saveAndFlush(consumerProfileHistory);

        // Get the consumerProfileHistory
        restConsumerProfileHistoryMockMvc.perform(get("/api/consumer-profile-histories/{id}", consumerProfileHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consumerProfileHistory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE.toString()))
            .andExpect(jsonPath("$.inputTraits").value(DEFAULT_INPUT_TRAITS.toString()))
            .andExpect(jsonPath("$.traitStructure").value(DEFAULT_TRAIT_STRUCTURE.toString()))
            .andExpect(jsonPath("$.activityStructure").value(DEFAULT_ACTIVITY_STRUCTURE.toString()))
            .andExpect(jsonPath("$.hobbyStructure").value(DEFAULT_HOBBY_STRUCTURE.toString()))
            .andExpect(jsonPath("$.inputReletionship").value(DEFAULT_INPUT_RELETIONSHIP.toString()))
            .andExpect(jsonPath("$.inputHobbies").value(DEFAULT_INPUT_HOBBIES.toString()))
            .andExpect(jsonPath("$.reccomendedProductTypes").value(DEFAULT_RECCOMENDED_PRODUCT_TYPES.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConsumerProfileHistory() throws Exception {
        // Get the consumerProfileHistory
        restConsumerProfileHistoryMockMvc.perform(get("/api/consumer-profile-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsumerProfileHistory() throws Exception {
        // Initialize the database
        consumerProfileHistoryRepository.saveAndFlush(consumerProfileHistory);
        int databaseSizeBeforeUpdate = consumerProfileHistoryRepository.findAll().size();

        // Update the consumerProfileHistory
        ConsumerProfileHistory updatedConsumerProfileHistory = consumerProfileHistoryRepository.findOne(consumerProfileHistory.getId());
        // Disconnect from session so that the updates on updatedConsumerProfileHistory are not directly saved in db
        em.detach(updatedConsumerProfileHistory);
        updatedConsumerProfileHistory
            .name(UPDATED_NAME)
            .gender(UPDATED_GENDER)
            .age(UPDATED_AGE)
            .inputTraits(UPDATED_INPUT_TRAITS)
            .traitStructure(UPDATED_TRAIT_STRUCTURE)
            .activityStructure(UPDATED_ACTIVITY_STRUCTURE)
            .hobbyStructure(UPDATED_HOBBY_STRUCTURE)
            .inputReletionship(UPDATED_INPUT_RELETIONSHIP)
            .inputHobbies(UPDATED_INPUT_HOBBIES)
            .reccomendedProductTypes(UPDATED_RECCOMENDED_PRODUCT_TYPES)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = consumerProfileHistoryMapper.toDto(updatedConsumerProfileHistory);

        restConsumerProfileHistoryMockMvc.perform(put("/api/consumer-profile-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the ConsumerProfileHistory in the database
        List<ConsumerProfileHistory> consumerProfileHistoryList = consumerProfileHistoryRepository.findAll();
        assertThat(consumerProfileHistoryList).hasSize(databaseSizeBeforeUpdate);
        ConsumerProfileHistory testConsumerProfileHistory = consumerProfileHistoryList.get(consumerProfileHistoryList.size() - 1);
        assertThat(testConsumerProfileHistory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testConsumerProfileHistory.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testConsumerProfileHistory.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testConsumerProfileHistory.getInputTraits()).isEqualTo(UPDATED_INPUT_TRAITS);
        assertThat(testConsumerProfileHistory.getTraitStructure()).isEqualTo(UPDATED_TRAIT_STRUCTURE);
        assertThat(testConsumerProfileHistory.getActivityStructure()).isEqualTo(UPDATED_ACTIVITY_STRUCTURE);
        assertThat(testConsumerProfileHistory.getHobbyStructure()).isEqualTo(UPDATED_HOBBY_STRUCTURE);
        assertThat(testConsumerProfileHistory.getInputReletionship()).isEqualTo(UPDATED_INPUT_RELETIONSHIP);
        assertThat(testConsumerProfileHistory.getInputHobbies()).isEqualTo(UPDATED_INPUT_HOBBIES);
        assertThat(testConsumerProfileHistory.getReccomendedProductTypes()).isEqualTo(UPDATED_RECCOMENDED_PRODUCT_TYPES);
        assertThat(testConsumerProfileHistory.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testConsumerProfileHistory.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testConsumerProfileHistory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testConsumerProfileHistory.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingConsumerProfileHistory() throws Exception {
        int databaseSizeBeforeUpdate = consumerProfileHistoryRepository.findAll().size();

        // Create the ConsumerProfileHistory
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = consumerProfileHistoryMapper.toDto(consumerProfileHistory);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConsumerProfileHistoryMockMvc.perform(put("/api/consumer-profile-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsumerProfileHistory in the database
        List<ConsumerProfileHistory> consumerProfileHistoryList = consumerProfileHistoryRepository.findAll();
        assertThat(consumerProfileHistoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConsumerProfileHistory() throws Exception {
        // Initialize the database
        consumerProfileHistoryRepository.saveAndFlush(consumerProfileHistory);
        int databaseSizeBeforeDelete = consumerProfileHistoryRepository.findAll().size();

        // Get the consumerProfileHistory
        restConsumerProfileHistoryMockMvc.perform(delete("/api/consumer-profile-histories/{id}", consumerProfileHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConsumerProfileHistory> consumerProfileHistoryList = consumerProfileHistoryRepository.findAll();
        assertThat(consumerProfileHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsumerProfileHistory.class);
        ConsumerProfileHistory consumerProfileHistory1 = new ConsumerProfileHistory();
        consumerProfileHistory1.setId(1L);
        ConsumerProfileHistory consumerProfileHistory2 = new ConsumerProfileHistory();
        consumerProfileHistory2.setId(consumerProfileHistory1.getId());
        assertThat(consumerProfileHistory1).isEqualTo(consumerProfileHistory2);
        consumerProfileHistory2.setId(2L);
        assertThat(consumerProfileHistory1).isNotEqualTo(consumerProfileHistory2);
        consumerProfileHistory1.setId(null);
        assertThat(consumerProfileHistory1).isNotEqualTo(consumerProfileHistory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsumerProfileHistoryDTO.class);
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO1 = new ConsumerProfileHistoryDTO();
        consumerProfileHistoryDTO1.setId(1L);
        ConsumerProfileHistoryDTO consumerProfileHistoryDTO2 = new ConsumerProfileHistoryDTO();
        assertThat(consumerProfileHistoryDTO1).isNotEqualTo(consumerProfileHistoryDTO2);
        consumerProfileHistoryDTO2.setId(consumerProfileHistoryDTO1.getId());
        assertThat(consumerProfileHistoryDTO1).isEqualTo(consumerProfileHistoryDTO2);
        consumerProfileHistoryDTO2.setId(2L);
        assertThat(consumerProfileHistoryDTO1).isNotEqualTo(consumerProfileHistoryDTO2);
        consumerProfileHistoryDTO1.setId(null);
        assertThat(consumerProfileHistoryDTO1).isNotEqualTo(consumerProfileHistoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(consumerProfileHistoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(consumerProfileHistoryMapper.fromId(null)).isNull();
    }
}
