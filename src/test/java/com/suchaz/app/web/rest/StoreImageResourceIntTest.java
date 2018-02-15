package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.StoreImage;
import com.suchaz.app.repository.StoreImageRepository;
import com.suchaz.app.service.StoreImageService;
import com.suchaz.app.service.dto.StoreImageDTO;
import com.suchaz.app.service.mapper.StoreImageMapper;
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
 * Test class for the StoreImageResource REST controller.
 *
 * @see StoreImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class StoreImageResourceIntTest {

    private static final String DEFAULT_STORE_IMAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORE_IMAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_IMAGE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_STORE_IMAGE_DESC = "BBBBBBBBBB";

    private static final Long DEFAULT_STORE_IMAGE_SIZE = 1L;
    private static final Long UPDATED_STORE_IMAGE_SIZE = 2L;

    private static final byte[] DEFAULT_STORE_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_STORE_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_STORE_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_STORE_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_STORE_IMAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_STORE_IMAGE_TYPE = "BBBBBBBBBB";

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
    private StoreImageRepository storeImageRepository;

    @Autowired
    private StoreImageMapper storeImageMapper;

    @Autowired
    private StoreImageService storeImageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStoreImageMockMvc;

    private StoreImage storeImage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StoreImageResource storeImageResource = new StoreImageResource(storeImageService);
        this.restStoreImageMockMvc = MockMvcBuilders.standaloneSetup(storeImageResource)
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
    public static StoreImage createEntity(EntityManager em) {
        StoreImage storeImage = new StoreImage()
            .storeImageName(DEFAULT_STORE_IMAGE_NAME)
            .storeImageDesc(DEFAULT_STORE_IMAGE_DESC)
            .storeImageSize(DEFAULT_STORE_IMAGE_SIZE)
            .storeImage(DEFAULT_STORE_IMAGE)
            .storeImageContentType(DEFAULT_STORE_IMAGE_CONTENT_TYPE)
            .storeImageType(DEFAULT_STORE_IMAGE_TYPE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return storeImage;
    }

    @Before
    public void initTest() {
        storeImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createStoreImage() throws Exception {
        int databaseSizeBeforeCreate = storeImageRepository.findAll().size();

        // Create the StoreImage
        StoreImageDTO storeImageDTO = storeImageMapper.toDto(storeImage);
        restStoreImageMockMvc.perform(post("/api/store-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeImageDTO)))
            .andExpect(status().isCreated());

        // Validate the StoreImage in the database
        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeCreate + 1);
        StoreImage testStoreImage = storeImageList.get(storeImageList.size() - 1);
        assertThat(testStoreImage.getStoreImageName()).isEqualTo(DEFAULT_STORE_IMAGE_NAME);
        assertThat(testStoreImage.getStoreImageDesc()).isEqualTo(DEFAULT_STORE_IMAGE_DESC);
        assertThat(testStoreImage.getStoreImageSize()).isEqualTo(DEFAULT_STORE_IMAGE_SIZE);
        assertThat(testStoreImage.getStoreImage()).isEqualTo(DEFAULT_STORE_IMAGE);
        assertThat(testStoreImage.getStoreImageContentType()).isEqualTo(DEFAULT_STORE_IMAGE_CONTENT_TYPE);
        assertThat(testStoreImage.getStoreImageType()).isEqualTo(DEFAULT_STORE_IMAGE_TYPE);
        assertThat(testStoreImage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testStoreImage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testStoreImage.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testStoreImage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testStoreImage.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createStoreImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = storeImageRepository.findAll().size();

        // Create the StoreImage with an existing ID
        storeImage.setId(1L);
        StoreImageDTO storeImageDTO = storeImageMapper.toDto(storeImage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStoreImageMockMvc.perform(post("/api/store-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StoreImage in the database
        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStoreImageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = storeImageRepository.findAll().size();
        // set the field null
        storeImage.setStoreImageName(null);

        // Create the StoreImage, which fails.
        StoreImageDTO storeImageDTO = storeImageMapper.toDto(storeImage);

        restStoreImageMockMvc.perform(post("/api/store-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeImageDTO)))
            .andExpect(status().isBadRequest());

        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStoreImageSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = storeImageRepository.findAll().size();
        // set the field null
        storeImage.setStoreImageSize(null);

        // Create the StoreImage, which fails.
        StoreImageDTO storeImageDTO = storeImageMapper.toDto(storeImage);

        restStoreImageMockMvc.perform(post("/api/store-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeImageDTO)))
            .andExpect(status().isBadRequest());

        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = storeImageRepository.findAll().size();
        // set the field null
        storeImage.setStatus(null);

        // Create the StoreImage, which fails.
        StoreImageDTO storeImageDTO = storeImageMapper.toDto(storeImage);

        restStoreImageMockMvc.perform(post("/api/store-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeImageDTO)))
            .andExpect(status().isBadRequest());

        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = storeImageRepository.findAll().size();
        // set the field null
        storeImage.setCreatedDate(null);

        // Create the StoreImage, which fails.
        StoreImageDTO storeImageDTO = storeImageMapper.toDto(storeImage);

        restStoreImageMockMvc.perform(post("/api/store-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeImageDTO)))
            .andExpect(status().isBadRequest());

        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = storeImageRepository.findAll().size();
        // set the field null
        storeImage.setCreatedBy(null);

        // Create the StoreImage, which fails.
        StoreImageDTO storeImageDTO = storeImageMapper.toDto(storeImage);

        restStoreImageMockMvc.perform(post("/api/store-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeImageDTO)))
            .andExpect(status().isBadRequest());

        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStoreImages() throws Exception {
        // Initialize the database
        storeImageRepository.saveAndFlush(storeImage);

        // Get all the storeImageList
        restStoreImageMockMvc.perform(get("/api/store-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(storeImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].storeImageName").value(hasItem(DEFAULT_STORE_IMAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].storeImageDesc").value(hasItem(DEFAULT_STORE_IMAGE_DESC.toString())))
            .andExpect(jsonPath("$.[*].storeImageSize").value(hasItem(DEFAULT_STORE_IMAGE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].storeImageContentType").value(hasItem(DEFAULT_STORE_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].storeImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_STORE_IMAGE))))
            .andExpect(jsonPath("$.[*].storeImageType").value(hasItem(DEFAULT_STORE_IMAGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getStoreImage() throws Exception {
        // Initialize the database
        storeImageRepository.saveAndFlush(storeImage);

        // Get the storeImage
        restStoreImageMockMvc.perform(get("/api/store-images/{id}", storeImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(storeImage.getId().intValue()))
            .andExpect(jsonPath("$.storeImageName").value(DEFAULT_STORE_IMAGE_NAME.toString()))
            .andExpect(jsonPath("$.storeImageDesc").value(DEFAULT_STORE_IMAGE_DESC.toString()))
            .andExpect(jsonPath("$.storeImageSize").value(DEFAULT_STORE_IMAGE_SIZE.intValue()))
            .andExpect(jsonPath("$.storeImageContentType").value(DEFAULT_STORE_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.storeImage").value(Base64Utils.encodeToString(DEFAULT_STORE_IMAGE)))
            .andExpect(jsonPath("$.storeImageType").value(DEFAULT_STORE_IMAGE_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStoreImage() throws Exception {
        // Get the storeImage
        restStoreImageMockMvc.perform(get("/api/store-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStoreImage() throws Exception {
        // Initialize the database
        storeImageRepository.saveAndFlush(storeImage);
        int databaseSizeBeforeUpdate = storeImageRepository.findAll().size();

        // Update the storeImage
        StoreImage updatedStoreImage = storeImageRepository.findOne(storeImage.getId());
        // Disconnect from session so that the updates on updatedStoreImage are not directly saved in db
        em.detach(updatedStoreImage);
        updatedStoreImage
            .storeImageName(UPDATED_STORE_IMAGE_NAME)
            .storeImageDesc(UPDATED_STORE_IMAGE_DESC)
            .storeImageSize(UPDATED_STORE_IMAGE_SIZE)
            .storeImage(UPDATED_STORE_IMAGE)
            .storeImageContentType(UPDATED_STORE_IMAGE_CONTENT_TYPE)
            .storeImageType(UPDATED_STORE_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        StoreImageDTO storeImageDTO = storeImageMapper.toDto(updatedStoreImage);

        restStoreImageMockMvc.perform(put("/api/store-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeImageDTO)))
            .andExpect(status().isOk());

        // Validate the StoreImage in the database
        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeUpdate);
        StoreImage testStoreImage = storeImageList.get(storeImageList.size() - 1);
        assertThat(testStoreImage.getStoreImageName()).isEqualTo(UPDATED_STORE_IMAGE_NAME);
        assertThat(testStoreImage.getStoreImageDesc()).isEqualTo(UPDATED_STORE_IMAGE_DESC);
        assertThat(testStoreImage.getStoreImageSize()).isEqualTo(UPDATED_STORE_IMAGE_SIZE);
        assertThat(testStoreImage.getStoreImage()).isEqualTo(UPDATED_STORE_IMAGE);
        assertThat(testStoreImage.getStoreImageContentType()).isEqualTo(UPDATED_STORE_IMAGE_CONTENT_TYPE);
        assertThat(testStoreImage.getStoreImageType()).isEqualTo(UPDATED_STORE_IMAGE_TYPE);
        assertThat(testStoreImage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStoreImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testStoreImage.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testStoreImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testStoreImage.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingStoreImage() throws Exception {
        int databaseSizeBeforeUpdate = storeImageRepository.findAll().size();

        // Create the StoreImage
        StoreImageDTO storeImageDTO = storeImageMapper.toDto(storeImage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStoreImageMockMvc.perform(put("/api/store-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeImageDTO)))
            .andExpect(status().isCreated());

        // Validate the StoreImage in the database
        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStoreImage() throws Exception {
        // Initialize the database
        storeImageRepository.saveAndFlush(storeImage);
        int databaseSizeBeforeDelete = storeImageRepository.findAll().size();

        // Get the storeImage
        restStoreImageMockMvc.perform(delete("/api/store-images/{id}", storeImage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StoreImage> storeImageList = storeImageRepository.findAll();
        assertThat(storeImageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StoreImage.class);
        StoreImage storeImage1 = new StoreImage();
        storeImage1.setId(1L);
        StoreImage storeImage2 = new StoreImage();
        storeImage2.setId(storeImage1.getId());
        assertThat(storeImage1).isEqualTo(storeImage2);
        storeImage2.setId(2L);
        assertThat(storeImage1).isNotEqualTo(storeImage2);
        storeImage1.setId(null);
        assertThat(storeImage1).isNotEqualTo(storeImage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StoreImageDTO.class);
        StoreImageDTO storeImageDTO1 = new StoreImageDTO();
        storeImageDTO1.setId(1L);
        StoreImageDTO storeImageDTO2 = new StoreImageDTO();
        assertThat(storeImageDTO1).isNotEqualTo(storeImageDTO2);
        storeImageDTO2.setId(storeImageDTO1.getId());
        assertThat(storeImageDTO1).isEqualTo(storeImageDTO2);
        storeImageDTO2.setId(2L);
        assertThat(storeImageDTO1).isNotEqualTo(storeImageDTO2);
        storeImageDTO1.setId(null);
        assertThat(storeImageDTO1).isNotEqualTo(storeImageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(storeImageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(storeImageMapper.fromId(null)).isNull();
    }
}
