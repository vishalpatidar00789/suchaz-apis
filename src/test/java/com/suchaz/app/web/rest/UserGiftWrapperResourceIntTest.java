package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.UserGiftWrapper;
import com.suchaz.app.repository.UserGiftWrapperRepository;
import com.suchaz.app.service.UserGiftWrapperService;
import com.suchaz.app.service.dto.UserGiftWrapperDTO;
import com.suchaz.app.service.mapper.UserGiftWrapperMapper;
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

import com.suchaz.app.domain.enumeration.Status;
/**
 * Test class for the UserGiftWrapperResource REST controller.
 *
 * @see UserGiftWrapperResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class UserGiftWrapperResourceIntTest {

    private static final byte[] DEFAULT_GIFT_WRAPPER_IMG = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_GIFT_WRAPPER_IMG = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_GIFT_WRAPPER_IMG_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_GIFT_WRAPPER_IMG_CONTENT_TYPE = "image/png";

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
    private UserGiftWrapperRepository userGiftWrapperRepository;

    @Autowired
    private UserGiftWrapperMapper userGiftWrapperMapper;

    @Autowired
    private UserGiftWrapperService userGiftWrapperService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserGiftWrapperMockMvc;

    private UserGiftWrapper userGiftWrapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserGiftWrapperResource userGiftWrapperResource = new UserGiftWrapperResource(userGiftWrapperService);
        this.restUserGiftWrapperMockMvc = MockMvcBuilders.standaloneSetup(userGiftWrapperResource)
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
    public static UserGiftWrapper createEntity(EntityManager em) {
        UserGiftWrapper userGiftWrapper = new UserGiftWrapper()
            .giftWrapperImg(DEFAULT_GIFT_WRAPPER_IMG)
            .giftWrapperImgContentType(DEFAULT_GIFT_WRAPPER_IMG_CONTENT_TYPE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return userGiftWrapper;
    }

    @Before
    public void initTest() {
        userGiftWrapper = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserGiftWrapper() throws Exception {
        int databaseSizeBeforeCreate = userGiftWrapperRepository.findAll().size();

        // Create the UserGiftWrapper
        UserGiftWrapperDTO userGiftWrapperDTO = userGiftWrapperMapper.toDto(userGiftWrapper);
        restUserGiftWrapperMockMvc.perform(post("/api/user-gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGiftWrapperDTO)))
            .andExpect(status().isCreated());

        // Validate the UserGiftWrapper in the database
        List<UserGiftWrapper> userGiftWrapperList = userGiftWrapperRepository.findAll();
        assertThat(userGiftWrapperList).hasSize(databaseSizeBeforeCreate + 1);
        UserGiftWrapper testUserGiftWrapper = userGiftWrapperList.get(userGiftWrapperList.size() - 1);
        assertThat(testUserGiftWrapper.getGiftWrapperImg()).isEqualTo(DEFAULT_GIFT_WRAPPER_IMG);
        assertThat(testUserGiftWrapper.getGiftWrapperImgContentType()).isEqualTo(DEFAULT_GIFT_WRAPPER_IMG_CONTENT_TYPE);
        assertThat(testUserGiftWrapper.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUserGiftWrapper.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserGiftWrapper.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testUserGiftWrapper.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUserGiftWrapper.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createUserGiftWrapperWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userGiftWrapperRepository.findAll().size();

        // Create the UserGiftWrapper with an existing ID
        userGiftWrapper.setId(1L);
        UserGiftWrapperDTO userGiftWrapperDTO = userGiftWrapperMapper.toDto(userGiftWrapper);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserGiftWrapperMockMvc.perform(post("/api/user-gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGiftWrapperDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserGiftWrapper in the database
        List<UserGiftWrapper> userGiftWrapperList = userGiftWrapperRepository.findAll();
        assertThat(userGiftWrapperList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = userGiftWrapperRepository.findAll().size();
        // set the field null
        userGiftWrapper.setStatus(null);

        // Create the UserGiftWrapper, which fails.
        UserGiftWrapperDTO userGiftWrapperDTO = userGiftWrapperMapper.toDto(userGiftWrapper);

        restUserGiftWrapperMockMvc.perform(post("/api/user-gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGiftWrapperDTO)))
            .andExpect(status().isBadRequest());

        List<UserGiftWrapper> userGiftWrapperList = userGiftWrapperRepository.findAll();
        assertThat(userGiftWrapperList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userGiftWrapperRepository.findAll().size();
        // set the field null
        userGiftWrapper.setCreatedDate(null);

        // Create the UserGiftWrapper, which fails.
        UserGiftWrapperDTO userGiftWrapperDTO = userGiftWrapperMapper.toDto(userGiftWrapper);

        restUserGiftWrapperMockMvc.perform(post("/api/user-gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGiftWrapperDTO)))
            .andExpect(status().isBadRequest());

        List<UserGiftWrapper> userGiftWrapperList = userGiftWrapperRepository.findAll();
        assertThat(userGiftWrapperList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = userGiftWrapperRepository.findAll().size();
        // set the field null
        userGiftWrapper.setCreatedBy(null);

        // Create the UserGiftWrapper, which fails.
        UserGiftWrapperDTO userGiftWrapperDTO = userGiftWrapperMapper.toDto(userGiftWrapper);

        restUserGiftWrapperMockMvc.perform(post("/api/user-gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGiftWrapperDTO)))
            .andExpect(status().isBadRequest());

        List<UserGiftWrapper> userGiftWrapperList = userGiftWrapperRepository.findAll();
        assertThat(userGiftWrapperList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserGiftWrappers() throws Exception {
        // Initialize the database
        userGiftWrapperRepository.saveAndFlush(userGiftWrapper);

        // Get all the userGiftWrapperList
        restUserGiftWrapperMockMvc.perform(get("/api/user-gift-wrappers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userGiftWrapper.getId().intValue())))
            .andExpect(jsonPath("$.[*].giftWrapperImgContentType").value(hasItem(DEFAULT_GIFT_WRAPPER_IMG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].giftWrapperImg").value(hasItem(Base64Utils.encodeToString(DEFAULT_GIFT_WRAPPER_IMG))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getUserGiftWrapper() throws Exception {
        // Initialize the database
        userGiftWrapperRepository.saveAndFlush(userGiftWrapper);

        // Get the userGiftWrapper
        restUserGiftWrapperMockMvc.perform(get("/api/user-gift-wrappers/{id}", userGiftWrapper.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userGiftWrapper.getId().intValue()))
            .andExpect(jsonPath("$.giftWrapperImgContentType").value(DEFAULT_GIFT_WRAPPER_IMG_CONTENT_TYPE))
            .andExpect(jsonPath("$.giftWrapperImg").value(Base64Utils.encodeToString(DEFAULT_GIFT_WRAPPER_IMG)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserGiftWrapper() throws Exception {
        // Get the userGiftWrapper
        restUserGiftWrapperMockMvc.perform(get("/api/user-gift-wrappers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserGiftWrapper() throws Exception {
        // Initialize the database
        userGiftWrapperRepository.saveAndFlush(userGiftWrapper);
        int databaseSizeBeforeUpdate = userGiftWrapperRepository.findAll().size();

        // Update the userGiftWrapper
        UserGiftWrapper updatedUserGiftWrapper = userGiftWrapperRepository.findOne(userGiftWrapper.getId());
        // Disconnect from session so that the updates on updatedUserGiftWrapper are not directly saved in db
        em.detach(updatedUserGiftWrapper);
        updatedUserGiftWrapper
            .giftWrapperImg(UPDATED_GIFT_WRAPPER_IMG)
            .giftWrapperImgContentType(UPDATED_GIFT_WRAPPER_IMG_CONTENT_TYPE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        UserGiftWrapperDTO userGiftWrapperDTO = userGiftWrapperMapper.toDto(updatedUserGiftWrapper);

        restUserGiftWrapperMockMvc.perform(put("/api/user-gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGiftWrapperDTO)))
            .andExpect(status().isOk());

        // Validate the UserGiftWrapper in the database
        List<UserGiftWrapper> userGiftWrapperList = userGiftWrapperRepository.findAll();
        assertThat(userGiftWrapperList).hasSize(databaseSizeBeforeUpdate);
        UserGiftWrapper testUserGiftWrapper = userGiftWrapperList.get(userGiftWrapperList.size() - 1);
        assertThat(testUserGiftWrapper.getGiftWrapperImg()).isEqualTo(UPDATED_GIFT_WRAPPER_IMG);
        assertThat(testUserGiftWrapper.getGiftWrapperImgContentType()).isEqualTo(UPDATED_GIFT_WRAPPER_IMG_CONTENT_TYPE);
        assertThat(testUserGiftWrapper.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUserGiftWrapper.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserGiftWrapper.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testUserGiftWrapper.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUserGiftWrapper.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingUserGiftWrapper() throws Exception {
        int databaseSizeBeforeUpdate = userGiftWrapperRepository.findAll().size();

        // Create the UserGiftWrapper
        UserGiftWrapperDTO userGiftWrapperDTO = userGiftWrapperMapper.toDto(userGiftWrapper);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserGiftWrapperMockMvc.perform(put("/api/user-gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userGiftWrapperDTO)))
            .andExpect(status().isCreated());

        // Validate the UserGiftWrapper in the database
        List<UserGiftWrapper> userGiftWrapperList = userGiftWrapperRepository.findAll();
        assertThat(userGiftWrapperList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserGiftWrapper() throws Exception {
        // Initialize the database
        userGiftWrapperRepository.saveAndFlush(userGiftWrapper);
        int databaseSizeBeforeDelete = userGiftWrapperRepository.findAll().size();

        // Get the userGiftWrapper
        restUserGiftWrapperMockMvc.perform(delete("/api/user-gift-wrappers/{id}", userGiftWrapper.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserGiftWrapper> userGiftWrapperList = userGiftWrapperRepository.findAll();
        assertThat(userGiftWrapperList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserGiftWrapper.class);
        UserGiftWrapper userGiftWrapper1 = new UserGiftWrapper();
        userGiftWrapper1.setId(1L);
        UserGiftWrapper userGiftWrapper2 = new UserGiftWrapper();
        userGiftWrapper2.setId(userGiftWrapper1.getId());
        assertThat(userGiftWrapper1).isEqualTo(userGiftWrapper2);
        userGiftWrapper2.setId(2L);
        assertThat(userGiftWrapper1).isNotEqualTo(userGiftWrapper2);
        userGiftWrapper1.setId(null);
        assertThat(userGiftWrapper1).isNotEqualTo(userGiftWrapper2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserGiftWrapperDTO.class);
        UserGiftWrapperDTO userGiftWrapperDTO1 = new UserGiftWrapperDTO();
        userGiftWrapperDTO1.setId(1L);
        UserGiftWrapperDTO userGiftWrapperDTO2 = new UserGiftWrapperDTO();
        assertThat(userGiftWrapperDTO1).isNotEqualTo(userGiftWrapperDTO2);
        userGiftWrapperDTO2.setId(userGiftWrapperDTO1.getId());
        assertThat(userGiftWrapperDTO1).isEqualTo(userGiftWrapperDTO2);
        userGiftWrapperDTO2.setId(2L);
        assertThat(userGiftWrapperDTO1).isNotEqualTo(userGiftWrapperDTO2);
        userGiftWrapperDTO1.setId(null);
        assertThat(userGiftWrapperDTO1).isNotEqualTo(userGiftWrapperDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userGiftWrapperMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userGiftWrapperMapper.fromId(null)).isNull();
    }
}
