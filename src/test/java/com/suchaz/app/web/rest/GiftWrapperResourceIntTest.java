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
import org.springframework.util.Base64Utils;

import com.suchaz.app.SuchazapisApp;
import com.suchaz.app.domain.GiftWrapper;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.GiftWrapperRepository;
import com.suchaz.app.service.GiftWrapperService;
import com.suchaz.app.service.dto.GiftWrapperDTO;
import com.suchaz.app.service.mapper.GiftWrapperMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the GiftWrapperResource REST controller.
 *
 * @see GiftWrapperResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class GiftWrapperResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

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
    private GiftWrapperRepository giftWrapperRepository;

    @Autowired
    private GiftWrapperMapper giftWrapperMapper;

    @Autowired
    private GiftWrapperService giftWrapperService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGiftWrapperMockMvc;

    private GiftWrapper giftWrapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GiftWrapperResource giftWrapperResource = new GiftWrapperResource(giftWrapperService);
        this.restGiftWrapperMockMvc = MockMvcBuilders.standaloneSetup(giftWrapperResource)
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
    public static GiftWrapper createEntity(EntityManager em) {
        GiftWrapper giftWrapper = new GiftWrapper()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .size(DEFAULT_SIZE)
            .type(DEFAULT_TYPE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return giftWrapper;
    }

    @Before
    public void initTest() {
        giftWrapper = createEntity(em);
    }

    @Test
    @Transactional
    public void createGiftWrapper() throws Exception {
        int databaseSizeBeforeCreate = giftWrapperRepository.findAll().size();

        // Create the GiftWrapper
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(giftWrapper);
        restGiftWrapperMockMvc.perform(post("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isCreated());

        // Validate the GiftWrapper in the database
        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeCreate + 1);
        GiftWrapper testGiftWrapper = giftWrapperList.get(giftWrapperList.size() - 1);
        assertThat(testGiftWrapper.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGiftWrapper.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGiftWrapper.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testGiftWrapper.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testGiftWrapper.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testGiftWrapper.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testGiftWrapper.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGiftWrapper.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testGiftWrapper.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testGiftWrapper.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGiftWrapper.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createGiftWrapperWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = giftWrapperRepository.findAll().size();

        // Create the GiftWrapper with an existing ID
        giftWrapper.setId(1L);
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(giftWrapper);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiftWrapperMockMvc.perform(post("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GiftWrapper in the database
        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftWrapperRepository.findAll().size();
        // set the field null
        giftWrapper.setName(null);

        // Create the GiftWrapper, which fails.
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(giftWrapper);

        restGiftWrapperMockMvc.perform(post("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isBadRequest());

        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkImageIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftWrapperRepository.findAll().size();
        // set the field null
        giftWrapper.setImage(null);

        // Create the GiftWrapper, which fails.
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(giftWrapper);

        restGiftWrapperMockMvc.perform(post("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isBadRequest());

        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftWrapperRepository.findAll().size();
        // set the field null
        giftWrapper.setSize(null);

        // Create the GiftWrapper, which fails.
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(giftWrapper);

        restGiftWrapperMockMvc.perform(post("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isBadRequest());

        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftWrapperRepository.findAll().size();
        // set the field null
        giftWrapper.setStatus(null);

        // Create the GiftWrapper, which fails.
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(giftWrapper);

        restGiftWrapperMockMvc.perform(post("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isBadRequest());

        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftWrapperRepository.findAll().size();
        // set the field null
        giftWrapper.setCreatedDate(null);

        // Create the GiftWrapper, which fails.
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(giftWrapper);

        restGiftWrapperMockMvc.perform(post("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isBadRequest());

        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftWrapperRepository.findAll().size();
        // set the field null
        giftWrapper.setCreatedBy(null);

        // Create the GiftWrapper, which fails.
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(giftWrapper);

        restGiftWrapperMockMvc.perform(post("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isBadRequest());

        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGiftWrappers() throws Exception {
        // Initialize the database
        giftWrapperRepository.saveAndFlush(giftWrapper);

        // Get all the giftWrapperList
        restGiftWrapperMockMvc.perform(get("/api/gift-wrappers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(giftWrapper.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getGiftWrapper() throws Exception {
        // Initialize the database
        giftWrapperRepository.saveAndFlush(giftWrapper);

        // Get the giftWrapper
        restGiftWrapperMockMvc.perform(get("/api/gift-wrappers/{id}", giftWrapper.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(giftWrapper.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGiftWrapper() throws Exception {
        // Get the giftWrapper
        restGiftWrapperMockMvc.perform(get("/api/gift-wrappers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGiftWrapper() throws Exception {
        // Initialize the database
        giftWrapperRepository.saveAndFlush(giftWrapper);
        int databaseSizeBeforeUpdate = giftWrapperRepository.findAll().size();

        // Update the giftWrapper
        GiftWrapper updatedGiftWrapper = giftWrapperRepository.findOne(giftWrapper.getId());
        // Disconnect from session so that the updates on updatedGiftWrapper are not directly saved in db
        em.detach(updatedGiftWrapper);
        updatedGiftWrapper
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .size(UPDATED_SIZE)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(updatedGiftWrapper);

        restGiftWrapperMockMvc.perform(put("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isOk());

        // Validate the GiftWrapper in the database
        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeUpdate);
        GiftWrapper testGiftWrapper = giftWrapperList.get(giftWrapperList.size() - 1);
        assertThat(testGiftWrapper.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGiftWrapper.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGiftWrapper.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testGiftWrapper.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testGiftWrapper.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testGiftWrapper.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testGiftWrapper.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGiftWrapper.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testGiftWrapper.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testGiftWrapper.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGiftWrapper.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingGiftWrapper() throws Exception {
        int databaseSizeBeforeUpdate = giftWrapperRepository.findAll().size();

        // Create the GiftWrapper
        GiftWrapperDTO giftWrapperDTO = giftWrapperMapper.toDto(giftWrapper);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGiftWrapperMockMvc.perform(put("/api/gift-wrappers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giftWrapperDTO)))
            .andExpect(status().isCreated());

        // Validate the GiftWrapper in the database
        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGiftWrapper() throws Exception {
        // Initialize the database
        giftWrapperRepository.saveAndFlush(giftWrapper);
        int databaseSizeBeforeDelete = giftWrapperRepository.findAll().size();

        // Get the giftWrapper
        restGiftWrapperMockMvc.perform(delete("/api/gift-wrappers/{id}", giftWrapper.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GiftWrapper> giftWrapperList = giftWrapperRepository.findAll();
        assertThat(giftWrapperList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftWrapper.class);
        GiftWrapper giftWrapper1 = new GiftWrapper();
        giftWrapper1.setId(1L);
        GiftWrapper giftWrapper2 = new GiftWrapper();
        giftWrapper2.setId(giftWrapper1.getId());
        assertThat(giftWrapper1).isEqualTo(giftWrapper2);
        giftWrapper2.setId(2L);
        assertThat(giftWrapper1).isNotEqualTo(giftWrapper2);
        giftWrapper1.setId(null);
        assertThat(giftWrapper1).isNotEqualTo(giftWrapper2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiftWrapperDTO.class);
        GiftWrapperDTO giftWrapperDTO1 = new GiftWrapperDTO();
        giftWrapperDTO1.setId(1L);
        GiftWrapperDTO giftWrapperDTO2 = new GiftWrapperDTO();
        assertThat(giftWrapperDTO1).isNotEqualTo(giftWrapperDTO2);
        giftWrapperDTO2.setId(giftWrapperDTO1.getId());
        assertThat(giftWrapperDTO1).isEqualTo(giftWrapperDTO2);
        giftWrapperDTO2.setId(2L);
        assertThat(giftWrapperDTO1).isNotEqualTo(giftWrapperDTO2);
        giftWrapperDTO1.setId(null);
        assertThat(giftWrapperDTO1).isNotEqualTo(giftWrapperDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(giftWrapperMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(giftWrapperMapper.fromId(null)).isNull();
    }
}
