package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.SuchAzUser;
import com.suchaz.app.repository.SuchAzUserRepository;
import com.suchaz.app.service.SuchAzUserService;
import com.suchaz.app.service.dto.SuchAzUserDTO;
import com.suchaz.app.service.mapper.SuchAzUserMapper;
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
import com.suchaz.app.domain.enumeration.UserRole;
import com.suchaz.app.domain.enumeration.FBAccessTokenType;
import com.suchaz.app.domain.enumeration.SignupMethod;
import com.suchaz.app.domain.enumeration.SignupMethod;
/**
 * Test class for the SuchAzUserResource REST controller.
 *
 * @see SuchAzUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class SuchAzUserResourceIntTest {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Long DEFAULT_CONTACT = 1L;
    private static final Long UPDATED_CONTACT = 2L;

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.INACTIVE;

    private static final UserRole DEFAULT_ROLE = UserRole.GIFTER;
    private static final UserRole UPDATED_ROLE = UserRole.GIFTER;

    private static final String DEFAULT_FB_ACCESS_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_FB_ACCESS_TOKEN = "BBBBBBBBBB";

    private static final FBAccessTokenType DEFAULT_FB_ACCESS_TOKEN_TYPE = FBAccessTokenType.USER_ACCESS_TOKEN;
    private static final FBAccessTokenType UPDATED_FB_ACCESS_TOKEN_TYPE = FBAccessTokenType.USER_ACCESS_TOKEN;

    private static final String DEFAULT_IS_VARIFIED = "AAAAAAAAAA";
    private static final String UPDATED_IS_VARIFIED = "BBBBBBBBBB";

    private static final Long DEFAULT_TOKEN_EXP_DATE = 1L;
    private static final Long UPDATED_TOKEN_EXP_DATE = 2L;

    private static final SignupMethod DEFAULT_VARIFIED_BY = SignupMethod.FACEBOOK;
    private static final SignupMethod UPDATED_VARIFIED_BY = SignupMethod.EMAIL;

    private static final SignupMethod DEFAULT_SIGNUP_METHOD = SignupMethod.FACEBOOK;
    private static final SignupMethod UPDATED_SIGNUP_METHOD = SignupMethod.EMAIL;

    private static final Long DEFAULT_CREATED_DATE = 1L;
    private static final Long UPDATED_CREATED_DATE = 2L;

    private static final Long DEFAULT_LAST_UPDATED_DATE = 1L;
    private static final Long UPDATED_LAST_UPDATED_DATE = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private SuchAzUserRepository suchAzUserRepository;

    @Autowired
    private SuchAzUserMapper suchAzUserMapper;

    @Autowired
    private SuchAzUserService suchAzUserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSuchAzUserMockMvc;

    private SuchAzUser suchAzUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SuchAzUserResource suchAzUserResource = new SuchAzUserResource(suchAzUserService);
        this.restSuchAzUserMockMvc = MockMvcBuilders.standaloneSetup(suchAzUserResource)
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
    public static SuchAzUser createEntity(EntityManager em) {
        SuchAzUser suchAzUser = new SuchAzUser()
            .email(DEFAULT_EMAIL)
            .password(DEFAULT_PASSWORD)
            .contact(DEFAULT_CONTACT)
            .status(DEFAULT_STATUS)
            .role(DEFAULT_ROLE)
            .fbAccessToken(DEFAULT_FB_ACCESS_TOKEN)
            .fbAccessTokenType(DEFAULT_FB_ACCESS_TOKEN_TYPE)
            .isVarified(DEFAULT_IS_VARIFIED)
            .tokenExpDate(DEFAULT_TOKEN_EXP_DATE)
            .varifiedBy(DEFAULT_VARIFIED_BY)
            .signupMethod(DEFAULT_SIGNUP_METHOD)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return suchAzUser;
    }

    @Before
    public void initTest() {
        suchAzUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createSuchAzUser() throws Exception {
        int databaseSizeBeforeCreate = suchAzUserRepository.findAll().size();

        // Create the SuchAzUser
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);
        restSuchAzUserMockMvc.perform(post("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isCreated());

        // Validate the SuchAzUser in the database
        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeCreate + 1);
        SuchAzUser testSuchAzUser = suchAzUserList.get(suchAzUserList.size() - 1);
        assertThat(testSuchAzUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSuchAzUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testSuchAzUser.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testSuchAzUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSuchAzUser.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testSuchAzUser.getFbAccessToken()).isEqualTo(DEFAULT_FB_ACCESS_TOKEN);
        assertThat(testSuchAzUser.getFbAccessTokenType()).isEqualTo(DEFAULT_FB_ACCESS_TOKEN_TYPE);
        assertThat(testSuchAzUser.getIsVarified()).isEqualTo(DEFAULT_IS_VARIFIED);
        assertThat(testSuchAzUser.getTokenExpDate()).isEqualTo(DEFAULT_TOKEN_EXP_DATE);
        assertThat(testSuchAzUser.getVarifiedBy()).isEqualTo(DEFAULT_VARIFIED_BY);
        assertThat(testSuchAzUser.getSignupMethod()).isEqualTo(DEFAULT_SIGNUP_METHOD);
        assertThat(testSuchAzUser.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSuchAzUser.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSuchAzUser.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSuchAzUser.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createSuchAzUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = suchAzUserRepository.findAll().size();

        // Create the SuchAzUser with an existing ID
        suchAzUser.setId(1L);
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSuchAzUserMockMvc.perform(post("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SuchAzUser in the database
        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzUserRepository.findAll().size();
        // set the field null
        suchAzUser.setEmail(null);

        // Create the SuchAzUser, which fails.
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);

        restSuchAzUserMockMvc.perform(post("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzUserRepository.findAll().size();
        // set the field null
        suchAzUser.setPassword(null);

        // Create the SuchAzUser, which fails.
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);

        restSuchAzUserMockMvc.perform(post("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzUserRepository.findAll().size();
        // set the field null
        suchAzUser.setStatus(null);

        // Create the SuchAzUser, which fails.
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);

        restSuchAzUserMockMvc.perform(post("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzUserRepository.findAll().size();
        // set the field null
        suchAzUser.setRole(null);

        // Create the SuchAzUser, which fails.
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);

        restSuchAzUserMockMvc.perform(post("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsVarifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzUserRepository.findAll().size();
        // set the field null
        suchAzUser.setIsVarified(null);

        // Create the SuchAzUser, which fails.
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);

        restSuchAzUserMockMvc.perform(post("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzUserRepository.findAll().size();
        // set the field null
        suchAzUser.setCreatedDate(null);

        // Create the SuchAzUser, which fails.
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);

        restSuchAzUserMockMvc.perform(post("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzUserRepository.findAll().size();
        // set the field null
        suchAzUser.setCreatedBy(null);

        // Create the SuchAzUser, which fails.
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);

        restSuchAzUserMockMvc.perform(post("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSuchAzUsers() throws Exception {
        // Initialize the database
        suchAzUserRepository.saveAndFlush(suchAzUser);

        // Get all the suchAzUserList
        restSuchAzUserMockMvc.perform(get("/api/such-az-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(suchAzUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())))
            .andExpect(jsonPath("$.[*].fbAccessToken").value(hasItem(DEFAULT_FB_ACCESS_TOKEN.toString())))
            .andExpect(jsonPath("$.[*].fbAccessTokenType").value(hasItem(DEFAULT_FB_ACCESS_TOKEN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].isVarified").value(hasItem(DEFAULT_IS_VARIFIED.toString())))
            .andExpect(jsonPath("$.[*].tokenExpDate").value(hasItem(DEFAULT_TOKEN_EXP_DATE.intValue())))
            .andExpect(jsonPath("$.[*].varifiedBy").value(hasItem(DEFAULT_VARIFIED_BY.toString())))
            .andExpect(jsonPath("$.[*].signupMethod").value(hasItem(DEFAULT_SIGNUP_METHOD.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getSuchAzUser() throws Exception {
        // Initialize the database
        suchAzUserRepository.saveAndFlush(suchAzUser);

        // Get the suchAzUser
        restSuchAzUserMockMvc.perform(get("/api/such-az-users/{id}", suchAzUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(suchAzUser.getId().intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()))
            .andExpect(jsonPath("$.fbAccessToken").value(DEFAULT_FB_ACCESS_TOKEN.toString()))
            .andExpect(jsonPath("$.fbAccessTokenType").value(DEFAULT_FB_ACCESS_TOKEN_TYPE.toString()))
            .andExpect(jsonPath("$.isVarified").value(DEFAULT_IS_VARIFIED.toString()))
            .andExpect(jsonPath("$.tokenExpDate").value(DEFAULT_TOKEN_EXP_DATE.intValue()))
            .andExpect(jsonPath("$.varifiedBy").value(DEFAULT_VARIFIED_BY.toString()))
            .andExpect(jsonPath("$.signupMethod").value(DEFAULT_SIGNUP_METHOD.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSuchAzUser() throws Exception {
        // Get the suchAzUser
        restSuchAzUserMockMvc.perform(get("/api/such-az-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSuchAzUser() throws Exception {
        // Initialize the database
        suchAzUserRepository.saveAndFlush(suchAzUser);
        int databaseSizeBeforeUpdate = suchAzUserRepository.findAll().size();

        // Update the suchAzUser
        SuchAzUser updatedSuchAzUser = suchAzUserRepository.findOne(suchAzUser.getId());
        // Disconnect from session so that the updates on updatedSuchAzUser are not directly saved in db
        em.detach(updatedSuchAzUser);
        updatedSuchAzUser
            .email(UPDATED_EMAIL)
            .password(UPDATED_PASSWORD)
            .contact(UPDATED_CONTACT)
            .status(UPDATED_STATUS)
            .role(UPDATED_ROLE)
            .fbAccessToken(UPDATED_FB_ACCESS_TOKEN)
            .fbAccessTokenType(UPDATED_FB_ACCESS_TOKEN_TYPE)
            .isVarified(UPDATED_IS_VARIFIED)
            .tokenExpDate(UPDATED_TOKEN_EXP_DATE)
            .varifiedBy(UPDATED_VARIFIED_BY)
            .signupMethod(UPDATED_SIGNUP_METHOD)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(updatedSuchAzUser);

        restSuchAzUserMockMvc.perform(put("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isOk());

        // Validate the SuchAzUser in the database
        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeUpdate);
        SuchAzUser testSuchAzUser = suchAzUserList.get(suchAzUserList.size() - 1);
        assertThat(testSuchAzUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSuchAzUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testSuchAzUser.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testSuchAzUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSuchAzUser.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testSuchAzUser.getFbAccessToken()).isEqualTo(UPDATED_FB_ACCESS_TOKEN);
        assertThat(testSuchAzUser.getFbAccessTokenType()).isEqualTo(UPDATED_FB_ACCESS_TOKEN_TYPE);
        assertThat(testSuchAzUser.getIsVarified()).isEqualTo(UPDATED_IS_VARIFIED);
        assertThat(testSuchAzUser.getTokenExpDate()).isEqualTo(UPDATED_TOKEN_EXP_DATE);
        assertThat(testSuchAzUser.getVarifiedBy()).isEqualTo(UPDATED_VARIFIED_BY);
        assertThat(testSuchAzUser.getSignupMethod()).isEqualTo(UPDATED_SIGNUP_METHOD);
        assertThat(testSuchAzUser.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSuchAzUser.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSuchAzUser.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSuchAzUser.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingSuchAzUser() throws Exception {
        int databaseSizeBeforeUpdate = suchAzUserRepository.findAll().size();

        // Create the SuchAzUser
        SuchAzUserDTO suchAzUserDTO = suchAzUserMapper.toDto(suchAzUser);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSuchAzUserMockMvc.perform(put("/api/such-az-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzUserDTO)))
            .andExpect(status().isCreated());

        // Validate the SuchAzUser in the database
        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSuchAzUser() throws Exception {
        // Initialize the database
        suchAzUserRepository.saveAndFlush(suchAzUser);
        int databaseSizeBeforeDelete = suchAzUserRepository.findAll().size();

        // Get the suchAzUser
        restSuchAzUserMockMvc.perform(delete("/api/such-az-users/{id}", suchAzUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SuchAzUser> suchAzUserList = suchAzUserRepository.findAll();
        assertThat(suchAzUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuchAzUser.class);
        SuchAzUser suchAzUser1 = new SuchAzUser();
        suchAzUser1.setId(1L);
        SuchAzUser suchAzUser2 = new SuchAzUser();
        suchAzUser2.setId(suchAzUser1.getId());
        assertThat(suchAzUser1).isEqualTo(suchAzUser2);
        suchAzUser2.setId(2L);
        assertThat(suchAzUser1).isNotEqualTo(suchAzUser2);
        suchAzUser1.setId(null);
        assertThat(suchAzUser1).isNotEqualTo(suchAzUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuchAzUserDTO.class);
        SuchAzUserDTO suchAzUserDTO1 = new SuchAzUserDTO();
        suchAzUserDTO1.setId(1L);
        SuchAzUserDTO suchAzUserDTO2 = new SuchAzUserDTO();
        assertThat(suchAzUserDTO1).isNotEqualTo(suchAzUserDTO2);
        suchAzUserDTO2.setId(suchAzUserDTO1.getId());
        assertThat(suchAzUserDTO1).isEqualTo(suchAzUserDTO2);
        suchAzUserDTO2.setId(2L);
        assertThat(suchAzUserDTO1).isNotEqualTo(suchAzUserDTO2);
        suchAzUserDTO1.setId(null);
        assertThat(suchAzUserDTO1).isNotEqualTo(suchAzUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(suchAzUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(suchAzUserMapper.fromId(null)).isNull();
    }
}
