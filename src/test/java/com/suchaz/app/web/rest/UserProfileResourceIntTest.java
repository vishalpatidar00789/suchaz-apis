package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.UserProfile;
import com.suchaz.app.repository.UserProfileRepository;
import com.suchaz.app.service.UserProfileService;
import com.suchaz.app.service.dto.UserProfileDTO;
import com.suchaz.app.service.mapper.UserProfileMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.suchaz.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.suchaz.app.domain.enumeration.RelationshipStatus;
/**
 * Test class for the UserProfileResource REST controller.
 *
 * @see UserProfileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class UserProfileResourceIntTest {

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DOB = 1L;
    private static final Long UPDATED_DOB = 2L;

    private static final String DEFAULT_ABOUT_ME = "AAAAAAAAAA";
    private static final String UPDATED_ABOUT_ME = "BBBBBBBBBB";

    private static final RelationshipStatus DEFAULT_RELATIONSHIP_STATUS = RelationshipStatus.SINGLE;
    private static final RelationshipStatus UPDATED_RELATIONSHIP_STATUS = RelationshipStatus.COMMITTED;

    private static final String DEFAULT_GEO_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_GEO_LOCATION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PRIFILE_PIC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PRIFILE_PIC = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PRIFILE_PIC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PRIFILE_PIC_CONTENT_TYPE = "image/png";

    private static final Long DEFAULT_CREATED_DATE = 1L;
    private static final Long UPDATED_CREATED_DATE = 2L;

    private static final Long DEFAULT_LAST_UPDATED_DATE = 1L;
    private static final Long UPDATED_LAST_UPDATED_DATE = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserProfileMockMvc;

    private UserProfile userProfile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserProfileResource userProfileResource = new UserProfileResource(userProfileService);
        this.restUserProfileMockMvc = MockMvcBuilders.standaloneSetup(userProfileResource)
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
    public static UserProfile createEntity(EntityManager em) {
        UserProfile userProfile = new UserProfile()
            .fullName(DEFAULT_FULL_NAME)
            .dob(DEFAULT_DOB)
            .aboutMe(DEFAULT_ABOUT_ME)
            .relationshipStatus(DEFAULT_RELATIONSHIP_STATUS)
            .geoLocation(DEFAULT_GEO_LOCATION)
            .prifilePic(DEFAULT_PRIFILE_PIC)
            .prifilePicContentType(DEFAULT_PRIFILE_PIC_CONTENT_TYPE)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return userProfile;
    }

    @Before
    public void initTest() {
        userProfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserProfile() throws Exception {
        int databaseSizeBeforeCreate = userProfileRepository.findAll().size();

        // Create the UserProfile
        UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);
        restUserProfileMockMvc.perform(post("/api/user-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the UserProfile in the database
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        assertThat(userProfileList).hasSize(databaseSizeBeforeCreate + 1);
        UserProfile testUserProfile = userProfileList.get(userProfileList.size() - 1);
        assertThat(testUserProfile.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testUserProfile.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testUserProfile.getAboutMe()).isEqualTo(DEFAULT_ABOUT_ME);
        assertThat(testUserProfile.getRelationshipStatus()).isEqualTo(DEFAULT_RELATIONSHIP_STATUS);
        assertThat(testUserProfile.getGeoLocation()).isEqualTo(DEFAULT_GEO_LOCATION);
        assertThat(testUserProfile.getPrifilePic()).isEqualTo(DEFAULT_PRIFILE_PIC);
        assertThat(testUserProfile.getPrifilePicContentType()).isEqualTo(DEFAULT_PRIFILE_PIC_CONTENT_TYPE);
        assertThat(testUserProfile.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserProfile.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testUserProfile.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUserProfile.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createUserProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userProfileRepository.findAll().size();

        // Create the UserProfile with an existing ID
        userProfile.setId(1L);
        UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserProfileMockMvc.perform(post("/api/user-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserProfile in the database
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        assertThat(userProfileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userProfileRepository.findAll().size();
        // set the field null
        userProfile.setFullName(null);

        // Create the UserProfile, which fails.
        UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

        restUserProfileMockMvc.perform(post("/api/user-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
            .andExpect(status().isBadRequest());

        List<UserProfile> userProfileList = userProfileRepository.findAll();
        assertThat(userProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userProfileRepository.findAll().size();
        // set the field null
        userProfile.setCreatedDate(null);

        // Create the UserProfile, which fails.
        UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

        restUserProfileMockMvc.perform(post("/api/user-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
            .andExpect(status().isBadRequest());

        List<UserProfile> userProfileList = userProfileRepository.findAll();
        assertThat(userProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = userProfileRepository.findAll().size();
        // set the field null
        userProfile.setCreatedBy(null);

        // Create the UserProfile, which fails.
        UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

        restUserProfileMockMvc.perform(post("/api/user-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
            .andExpect(status().isBadRequest());

        List<UserProfile> userProfileList = userProfileRepository.findAll();
        assertThat(userProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserProfiles() throws Exception {
        // Initialize the database
        userProfileRepository.saveAndFlush(userProfile);

        // Get all the userProfileList
        restUserProfileMockMvc.perform(get("/api/user-profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.intValue())))
            .andExpect(jsonPath("$.[*].aboutMe").value(hasItem(DEFAULT_ABOUT_ME.toString())))
            .andExpect(jsonPath("$.[*].relationshipStatus").value(hasItem(DEFAULT_RELATIONSHIP_STATUS.toString())))
            .andExpect(jsonPath("$.[*].geoLocation").value(hasItem(DEFAULT_GEO_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].prifilePicContentType").value(hasItem(DEFAULT_PRIFILE_PIC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].prifilePic").value(hasItem(Base64Utils.encodeToString(DEFAULT_PRIFILE_PIC))))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getUserProfile() throws Exception {
        // Initialize the database
        userProfileRepository.saveAndFlush(userProfile);

        // Get the userProfile
        restUserProfileMockMvc.perform(get("/api/user-profiles/{id}", userProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userProfile.getId().intValue()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.intValue()))
            .andExpect(jsonPath("$.aboutMe").value(DEFAULT_ABOUT_ME.toString()))
            .andExpect(jsonPath("$.relationshipStatus").value(DEFAULT_RELATIONSHIP_STATUS.toString()))
            .andExpect(jsonPath("$.geoLocation").value(DEFAULT_GEO_LOCATION.toString()))
            .andExpect(jsonPath("$.prifilePicContentType").value(DEFAULT_PRIFILE_PIC_CONTENT_TYPE))
            .andExpect(jsonPath("$.prifilePic").value(Base64Utils.encodeToString(DEFAULT_PRIFILE_PIC)))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserProfile() throws Exception {
        // Get the userProfile
        restUserProfileMockMvc.perform(get("/api/user-profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserProfile() throws Exception {
        // Initialize the database
        userProfileRepository.saveAndFlush(userProfile);
        int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();

        // Update the userProfile
        UserProfile updatedUserProfile = userProfileRepository.findOne(userProfile.getId());
        // Disconnect from session so that the updates on updatedUserProfile are not directly saved in db
        em.detach(updatedUserProfile);
        updatedUserProfile
            .fullName(UPDATED_FULL_NAME)
            .dob(UPDATED_DOB)
            .aboutMe(UPDATED_ABOUT_ME)
            .relationshipStatus(UPDATED_RELATIONSHIP_STATUS)
            .geoLocation(UPDATED_GEO_LOCATION)
            .prifilePic(UPDATED_PRIFILE_PIC)
            .prifilePicContentType(UPDATED_PRIFILE_PIC_CONTENT_TYPE)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        UserProfileDTO userProfileDTO = userProfileMapper.toDto(updatedUserProfile);

        restUserProfileMockMvc.perform(put("/api/user-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
            .andExpect(status().isOk());

        // Validate the UserProfile in the database
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate);
        UserProfile testUserProfile = userProfileList.get(userProfileList.size() - 1);
        assertThat(testUserProfile.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testUserProfile.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testUserProfile.getAboutMe()).isEqualTo(UPDATED_ABOUT_ME);
        assertThat(testUserProfile.getRelationshipStatus()).isEqualTo(UPDATED_RELATIONSHIP_STATUS);
        assertThat(testUserProfile.getGeoLocation()).isEqualTo(UPDATED_GEO_LOCATION);
        assertThat(testUserProfile.getPrifilePic()).isEqualTo(UPDATED_PRIFILE_PIC);
        assertThat(testUserProfile.getPrifilePicContentType()).isEqualTo(UPDATED_PRIFILE_PIC_CONTENT_TYPE);
        assertThat(testUserProfile.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserProfile.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testUserProfile.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUserProfile.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingUserProfile() throws Exception {
        int databaseSizeBeforeUpdate = userProfileRepository.findAll().size();

        // Create the UserProfile
        UserProfileDTO userProfileDTO = userProfileMapper.toDto(userProfile);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserProfileMockMvc.perform(put("/api/user-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the UserProfile in the database
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        assertThat(userProfileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserProfile() throws Exception {
        // Initialize the database
        userProfileRepository.saveAndFlush(userProfile);
        int databaseSizeBeforeDelete = userProfileRepository.findAll().size();

        // Get the userProfile
        restUserProfileMockMvc.perform(delete("/api/user-profiles/{id}", userProfile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        assertThat(userProfileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserProfile.class);
        UserProfile userProfile1 = new UserProfile();
        userProfile1.setId(1L);
        UserProfile userProfile2 = new UserProfile();
        userProfile2.setId(userProfile1.getId());
        assertThat(userProfile1).isEqualTo(userProfile2);
        userProfile2.setId(2L);
        assertThat(userProfile1).isNotEqualTo(userProfile2);
        userProfile1.setId(null);
        assertThat(userProfile1).isNotEqualTo(userProfile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserProfileDTO.class);
        UserProfileDTO userProfileDTO1 = new UserProfileDTO();
        userProfileDTO1.setId(1L);
        UserProfileDTO userProfileDTO2 = new UserProfileDTO();
        assertThat(userProfileDTO1).isNotEqualTo(userProfileDTO2);
        userProfileDTO2.setId(userProfileDTO1.getId());
        assertThat(userProfileDTO1).isEqualTo(userProfileDTO2);
        userProfileDTO2.setId(2L);
        assertThat(userProfileDTO1).isNotEqualTo(userProfileDTO2);
        userProfileDTO1.setId(null);
        assertThat(userProfileDTO1).isNotEqualTo(userProfileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userProfileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userProfileMapper.fromId(null)).isNull();
    }
}
