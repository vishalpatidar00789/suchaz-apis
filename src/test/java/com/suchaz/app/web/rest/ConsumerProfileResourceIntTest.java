package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.ConsumerProfile;
import com.suchaz.app.repository.ConsumerProfileRepository;
import com.suchaz.app.service.ConsumerProfileService;
import com.suchaz.app.service.dto.ConsumerProfileDTO;
import com.suchaz.app.service.mapper.ConsumerProfileMapper;
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
 * Test class for the ConsumerProfileResource REST controller.
 *
 * @see ConsumerProfileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class ConsumerProfileResourceIntTest {

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


    private static final Boolean DEFAULT_IS_LOGGED_IN_USER = false;
    private static final Boolean UPDATED_IS_LOGGED_IN_USER = true;

    @Autowired
    private ConsumerProfileRepository consumerProfileRepository;

    @Autowired
    private ConsumerProfileMapper consumerProfileMapper;

    @Autowired
    private ConsumerProfileService consumerProfileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConsumerProfileMockMvc;

    private ConsumerProfile consumerProfile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConsumerProfileResource consumerProfileResource = new ConsumerProfileResource(consumerProfileService);
        this.restConsumerProfileMockMvc = MockMvcBuilders.standaloneSetup(consumerProfileResource)
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
    public static ConsumerProfile createEntity(EntityManager em) {
        ConsumerProfile consumerProfile = new ConsumerProfile()
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

            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .isLoggedInUser(DEFAULT_IS_LOGGED_IN_USER);
        return consumerProfile;
    }

    @Before
    public void initTest() {
        consumerProfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsumerProfile() throws Exception {
        int databaseSizeBeforeCreate = consumerProfileRepository.findAll().size();

        // Create the ConsumerProfile
        ConsumerProfileDTO consumerProfileDTO = consumerProfileMapper.toDto(consumerProfile);
        restConsumerProfileMockMvc.perform(post("/api/consumer-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsumerProfile in the database
        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeCreate + 1);
        ConsumerProfile testConsumerProfile = consumerProfileList.get(consumerProfileList.size() - 1);
        assertThat(testConsumerProfile.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testConsumerProfile.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testConsumerProfile.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testConsumerProfile.getInputTraits()).isEqualTo(DEFAULT_INPUT_TRAITS);
        assertThat(testConsumerProfile.getTraitStructure()).isEqualTo(DEFAULT_TRAIT_STRUCTURE);
        assertThat(testConsumerProfile.getActivityStructure()).isEqualTo(DEFAULT_ACTIVITY_STRUCTURE);
        assertThat(testConsumerProfile.getHobbyStructure()).isEqualTo(DEFAULT_HOBBY_STRUCTURE);
        assertThat(testConsumerProfile.getInputReletionship()).isEqualTo(DEFAULT_INPUT_RELETIONSHIP);
        assertThat(testConsumerProfile.getInputHobbies()).isEqualTo(DEFAULT_INPUT_HOBBIES);
        assertThat(testConsumerProfile.getReccomendedProductTypes()).isEqualTo(DEFAULT_RECCOMENDED_PRODUCT_TYPES);
        assertThat(testConsumerProfile.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testConsumerProfile.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testConsumerProfile.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testConsumerProfile.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);

        assertThat(testConsumerProfile.isIsLoggedInUser()).isEqualTo(DEFAULT_IS_LOGGED_IN_USER);

    }

    @Test
    @Transactional
    public void createConsumerProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consumerProfileRepository.findAll().size();

        // Create the ConsumerProfile with an existing ID
        consumerProfile.setId(1L);
        ConsumerProfileDTO consumerProfileDTO = consumerProfileMapper.toDto(consumerProfile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsumerProfileMockMvc.perform(post("/api/consumer-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsumerProfile in the database
        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = consumerProfileRepository.findAll().size();
        // set the field null
        consumerProfile.setGender(null);

        // Create the ConsumerProfile, which fails.
        ConsumerProfileDTO consumerProfileDTO = consumerProfileMapper.toDto(consumerProfile);

        restConsumerProfileMockMvc.perform(post("/api/consumer-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileDTO)))
            .andExpect(status().isBadRequest());

        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgeIsRequired() throws Exception {
        int databaseSizeBeforeTest = consumerProfileRepository.findAll().size();
        // set the field null
        consumerProfile.setAge(null);

        // Create the ConsumerProfile, which fails.
        ConsumerProfileDTO consumerProfileDTO = consumerProfileMapper.toDto(consumerProfile);

        restConsumerProfileMockMvc.perform(post("/api/consumer-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileDTO)))
            .andExpect(status().isBadRequest());

        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = consumerProfileRepository.findAll().size();
        // set the field null
        consumerProfile.setCreatedDate(null);

        // Create the ConsumerProfile, which fails.
        ConsumerProfileDTO consumerProfileDTO = consumerProfileMapper.toDto(consumerProfile);

        restConsumerProfileMockMvc.perform(post("/api/consumer-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileDTO)))
            .andExpect(status().isBadRequest());

        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = consumerProfileRepository.findAll().size();
        // set the field null
        consumerProfile.setCreatedBy(null);

        // Create the ConsumerProfile, which fails.
        ConsumerProfileDTO consumerProfileDTO = consumerProfileMapper.toDto(consumerProfile);

        restConsumerProfileMockMvc.perform(post("/api/consumer-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileDTO)))
            .andExpect(status().isBadRequest());

        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional

    public void checkIsLoggedInUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = consumerProfileRepository.findAll().size();
        // set the field null
        consumerProfile.setIsLoggedInUser(null);

        // Create the ConsumerProfile, which fails.
        ConsumerProfileDTO consumerProfileDTO = consumerProfileMapper.toDto(consumerProfile);

        restConsumerProfileMockMvc.perform(post("/api/consumer-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileDTO)))
            .andExpect(status().isBadRequest());

        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional

    public void getAllConsumerProfiles() throws Exception {
        // Initialize the database
        consumerProfileRepository.saveAndFlush(consumerProfile);

        // Get all the consumerProfileList
        restConsumerProfileMockMvc.perform(get("/api/consumer-profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consumerProfile.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].isLoggedInUser").value(hasItem(DEFAULT_IS_LOGGED_IN_USER.booleanValue())));
    }

    @Test
    @Transactional
    public void getConsumerProfile() throws Exception {
        // Initialize the database
        consumerProfileRepository.saveAndFlush(consumerProfile);

        // Get the consumerProfile
        restConsumerProfileMockMvc.perform(get("/api/consumer-profiles/{id}", consumerProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consumerProfile.getId().intValue()))
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
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.isLoggedInUser").value(DEFAULT_IS_LOGGED_IN_USER.booleanValue()));

    }

    @Test
    @Transactional
    public void getNonExistingConsumerProfile() throws Exception {
        // Get the consumerProfile
        restConsumerProfileMockMvc.perform(get("/api/consumer-profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsumerProfile() throws Exception {
        // Initialize the database
        consumerProfileRepository.saveAndFlush(consumerProfile);
        int databaseSizeBeforeUpdate = consumerProfileRepository.findAll().size();

        // Update the consumerProfile
        ConsumerProfile updatedConsumerProfile = consumerProfileRepository.findOne(consumerProfile.getId());
        // Disconnect from session so that the updates on updatedConsumerProfile are not directly saved in db
        em.detach(updatedConsumerProfile);
        updatedConsumerProfile
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
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .isLoggedInUser(UPDATED_IS_LOGGED_IN_USER);
        ConsumerProfileDTO consumerProfileDTO = consumerProfileMapper.toDto(updatedConsumerProfile);

        restConsumerProfileMockMvc.perform(put("/api/consumer-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileDTO)))
            .andExpect(status().isOk());

        // Validate the ConsumerProfile in the database
        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeUpdate);
        ConsumerProfile testConsumerProfile = consumerProfileList.get(consumerProfileList.size() - 1);
        assertThat(testConsumerProfile.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testConsumerProfile.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testConsumerProfile.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testConsumerProfile.getInputTraits()).isEqualTo(UPDATED_INPUT_TRAITS);
        assertThat(testConsumerProfile.getTraitStructure()).isEqualTo(UPDATED_TRAIT_STRUCTURE);
        assertThat(testConsumerProfile.getActivityStructure()).isEqualTo(UPDATED_ACTIVITY_STRUCTURE);
        assertThat(testConsumerProfile.getHobbyStructure()).isEqualTo(UPDATED_HOBBY_STRUCTURE);
        assertThat(testConsumerProfile.getInputReletionship()).isEqualTo(UPDATED_INPUT_RELETIONSHIP);
        assertThat(testConsumerProfile.getInputHobbies()).isEqualTo(UPDATED_INPUT_HOBBIES);
        assertThat(testConsumerProfile.getReccomendedProductTypes()).isEqualTo(UPDATED_RECCOMENDED_PRODUCT_TYPES);
        assertThat(testConsumerProfile.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testConsumerProfile.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testConsumerProfile.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testConsumerProfile.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testConsumerProfile.isIsLoggedInUser()).isEqualTo(UPDATED_IS_LOGGED_IN_USER);

    }

    @Test
    @Transactional
    public void updateNonExistingConsumerProfile() throws Exception {
        int databaseSizeBeforeUpdate = consumerProfileRepository.findAll().size();

        // Create the ConsumerProfile
        ConsumerProfileDTO consumerProfileDTO = consumerProfileMapper.toDto(consumerProfile);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConsumerProfileMockMvc.perform(put("/api/consumer-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consumerProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsumerProfile in the database
        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConsumerProfile() throws Exception {
        // Initialize the database
        consumerProfileRepository.saveAndFlush(consumerProfile);
        int databaseSizeBeforeDelete = consumerProfileRepository.findAll().size();

        // Get the consumerProfile
        restConsumerProfileMockMvc.perform(delete("/api/consumer-profiles/{id}", consumerProfile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConsumerProfile> consumerProfileList = consumerProfileRepository.findAll();
        assertThat(consumerProfileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsumerProfile.class);
        ConsumerProfile consumerProfile1 = new ConsumerProfile();
        consumerProfile1.setId(1L);
        ConsumerProfile consumerProfile2 = new ConsumerProfile();
        consumerProfile2.setId(consumerProfile1.getId());
        assertThat(consumerProfile1).isEqualTo(consumerProfile2);
        consumerProfile2.setId(2L);
        assertThat(consumerProfile1).isNotEqualTo(consumerProfile2);
        consumerProfile1.setId(null);
        assertThat(consumerProfile1).isNotEqualTo(consumerProfile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsumerProfileDTO.class);
        ConsumerProfileDTO consumerProfileDTO1 = new ConsumerProfileDTO();
        consumerProfileDTO1.setId(1L);
        ConsumerProfileDTO consumerProfileDTO2 = new ConsumerProfileDTO();
        assertThat(consumerProfileDTO1).isNotEqualTo(consumerProfileDTO2);
        consumerProfileDTO2.setId(consumerProfileDTO1.getId());
        assertThat(consumerProfileDTO1).isEqualTo(consumerProfileDTO2);
        consumerProfileDTO2.setId(2L);
        assertThat(consumerProfileDTO1).isNotEqualTo(consumerProfileDTO2);
        consumerProfileDTO1.setId(null);
        assertThat(consumerProfileDTO1).isNotEqualTo(consumerProfileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(consumerProfileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(consumerProfileMapper.fromId(null)).isNull();
    }
}
