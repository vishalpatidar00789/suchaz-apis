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
import com.suchaz.app.domain.OccasionImage;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.OccasionImageRepository;
import com.suchaz.app.service.OccasionImageService;
import com.suchaz.app.service.dto.OccasionImageDTO;
import com.suchaz.app.service.mapper.OccasionImageMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the OccasionImageResource REST controller.
 *
 * @see OccasionImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class OccasionImageResourceIntTest {

    private static final String DEFAULT_OCCASION_IMAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OCCASION_IMAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OCCASION_IMAGE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_OCCASION_IMAGE_DESC = "BBBBBBBBBB";

    private static final Long DEFAULT_OCCASION_IMAGE_SIZE = 1L;
    private static final Long UPDATED_OCCASION_IMAGE_SIZE = 2L;

    private static final byte[] DEFAULT_OCCASION_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_OCCASION_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_OCCASION_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_OCCASION_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_OCCASION_IMAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OCCASION_IMAGE_TYPE = "BBBBBBBBBB";

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
    private OccasionImageRepository occasionImageRepository;

    @Autowired
    private OccasionImageMapper occasionImageMapper;

    @Autowired
    private OccasionImageService occasionImageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOccasionImageMockMvc;

    private OccasionImage occasionImage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OccasionImageResource occasionImageResource = new OccasionImageResource(occasionImageService);
        this.restOccasionImageMockMvc = MockMvcBuilders.standaloneSetup(occasionImageResource)
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
    public static OccasionImage createEntity(EntityManager em) {
        OccasionImage occasionImage = new OccasionImage()
            .occasionImageName(DEFAULT_OCCASION_IMAGE_NAME)
            .occasionImageDesc(DEFAULT_OCCASION_IMAGE_DESC)
            .occasionImageSize(DEFAULT_OCCASION_IMAGE_SIZE)
            .occasionImage(DEFAULT_OCCASION_IMAGE)
            .occasionImageContentType(DEFAULT_OCCASION_IMAGE_CONTENT_TYPE)
            .occasionImageType(DEFAULT_OCCASION_IMAGE_TYPE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return occasionImage;
    }

    @Before
    public void initTest() {
        occasionImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createOccasionImage() throws Exception {
        int databaseSizeBeforeCreate = occasionImageRepository.findAll().size();

        // Create the OccasionImage
        OccasionImageDTO occasionImageDTO = occasionImageMapper.toDto(occasionImage);
        restOccasionImageMockMvc.perform(post("/api/occasion-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occasionImageDTO)))
            .andExpect(status().isCreated());

        // Validate the OccasionImage in the database
        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeCreate + 1);
        OccasionImage testOccasionImage = occasionImageList.get(occasionImageList.size() - 1);
        assertThat(testOccasionImage.getOccasionImageName()).isEqualTo(DEFAULT_OCCASION_IMAGE_NAME);
        assertThat(testOccasionImage.getOccasionImageDesc()).isEqualTo(DEFAULT_OCCASION_IMAGE_DESC);
        assertThat(testOccasionImage.getOccasionImageSize()).isEqualTo(DEFAULT_OCCASION_IMAGE_SIZE);
        assertThat(testOccasionImage.getOccasionImage()).isEqualTo(DEFAULT_OCCASION_IMAGE);
        assertThat(testOccasionImage.getOccasionImageContentType()).isEqualTo(DEFAULT_OCCASION_IMAGE_CONTENT_TYPE);
        assertThat(testOccasionImage.getOccasionImageType()).isEqualTo(DEFAULT_OCCASION_IMAGE_TYPE);
        assertThat(testOccasionImage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOccasionImage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOccasionImage.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testOccasionImage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOccasionImage.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createOccasionImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = occasionImageRepository.findAll().size();

        // Create the OccasionImage with an existing ID
        occasionImage.setId(1L);
        OccasionImageDTO occasionImageDTO = occasionImageMapper.toDto(occasionImage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOccasionImageMockMvc.perform(post("/api/occasion-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occasionImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OccasionImage in the database
        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkOccasionImageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = occasionImageRepository.findAll().size();
        // set the field null
        occasionImage.setOccasionImageName(null);

        // Create the OccasionImage, which fails.
        OccasionImageDTO occasionImageDTO = occasionImageMapper.toDto(occasionImage);

        restOccasionImageMockMvc.perform(post("/api/occasion-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occasionImageDTO)))
            .andExpect(status().isBadRequest());

        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOccasionImageSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = occasionImageRepository.findAll().size();
        // set the field null
        occasionImage.setOccasionImageSize(null);

        // Create the OccasionImage, which fails.
        OccasionImageDTO occasionImageDTO = occasionImageMapper.toDto(occasionImage);

        restOccasionImageMockMvc.perform(post("/api/occasion-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occasionImageDTO)))
            .andExpect(status().isBadRequest());

        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = occasionImageRepository.findAll().size();
        // set the field null
        occasionImage.setStatus(null);

        // Create the OccasionImage, which fails.
        OccasionImageDTO occasionImageDTO = occasionImageMapper.toDto(occasionImage);

        restOccasionImageMockMvc.perform(post("/api/occasion-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occasionImageDTO)))
            .andExpect(status().isBadRequest());

        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = occasionImageRepository.findAll().size();
        // set the field null
        occasionImage.setCreatedDate(null);

        // Create the OccasionImage, which fails.
        OccasionImageDTO occasionImageDTO = occasionImageMapper.toDto(occasionImage);

        restOccasionImageMockMvc.perform(post("/api/occasion-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occasionImageDTO)))
            .andExpect(status().isBadRequest());

        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = occasionImageRepository.findAll().size();
        // set the field null
        occasionImage.setCreatedBy(null);

        // Create the OccasionImage, which fails.
        OccasionImageDTO occasionImageDTO = occasionImageMapper.toDto(occasionImage);

        restOccasionImageMockMvc.perform(post("/api/occasion-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occasionImageDTO)))
            .andExpect(status().isBadRequest());

        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOccasionImages() throws Exception {
        // Initialize the database
        occasionImageRepository.saveAndFlush(occasionImage);

        // Get all the occasionImageList
        restOccasionImageMockMvc.perform(get("/api/occasion-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(occasionImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].occasionImageName").value(hasItem(DEFAULT_OCCASION_IMAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].occasionImageDesc").value(hasItem(DEFAULT_OCCASION_IMAGE_DESC.toString())))
            .andExpect(jsonPath("$.[*].occasionImageSize").value(hasItem(DEFAULT_OCCASION_IMAGE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].occasionImageContentType").value(hasItem(DEFAULT_OCCASION_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].occasionImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_OCCASION_IMAGE))))
            .andExpect(jsonPath("$.[*].occasionImageType").value(hasItem(DEFAULT_OCCASION_IMAGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getOccasionImage() throws Exception {
        // Initialize the database
        occasionImageRepository.saveAndFlush(occasionImage);

        // Get the occasionImage
        restOccasionImageMockMvc.perform(get("/api/occasion-images/{id}", occasionImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(occasionImage.getId().intValue()))
            .andExpect(jsonPath("$.occasionImageName").value(DEFAULT_OCCASION_IMAGE_NAME.toString()))
            .andExpect(jsonPath("$.occasionImageDesc").value(DEFAULT_OCCASION_IMAGE_DESC.toString()))
            .andExpect(jsonPath("$.occasionImageSize").value(DEFAULT_OCCASION_IMAGE_SIZE.intValue()))
            .andExpect(jsonPath("$.occasionImageContentType").value(DEFAULT_OCCASION_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.occasionImage").value(Base64Utils.encodeToString(DEFAULT_OCCASION_IMAGE)))
            .andExpect(jsonPath("$.occasionImageType").value(DEFAULT_OCCASION_IMAGE_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOccasionImage() throws Exception {
        // Get the occasionImage
        restOccasionImageMockMvc.perform(get("/api/occasion-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOccasionImage() throws Exception {
        // Initialize the database
        occasionImageRepository.saveAndFlush(occasionImage);
        int databaseSizeBeforeUpdate = occasionImageRepository.findAll().size();

        // Update the occasionImage
        OccasionImage updatedOccasionImage = occasionImageRepository.findOne(occasionImage.getId());
        // Disconnect from session so that the updates on updatedOccasionImage are not directly saved in db
        em.detach(updatedOccasionImage);
        updatedOccasionImage
            .occasionImageName(UPDATED_OCCASION_IMAGE_NAME)
            .occasionImageDesc(UPDATED_OCCASION_IMAGE_DESC)
            .occasionImageSize(UPDATED_OCCASION_IMAGE_SIZE)
            .occasionImage(UPDATED_OCCASION_IMAGE)
            .occasionImageContentType(UPDATED_OCCASION_IMAGE_CONTENT_TYPE)
            .occasionImageType(UPDATED_OCCASION_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        OccasionImageDTO occasionImageDTO = occasionImageMapper.toDto(updatedOccasionImage);

        restOccasionImageMockMvc.perform(put("/api/occasion-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occasionImageDTO)))
            .andExpect(status().isOk());

        // Validate the OccasionImage in the database
        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeUpdate);
        OccasionImage testOccasionImage = occasionImageList.get(occasionImageList.size() - 1);
        assertThat(testOccasionImage.getOccasionImageName()).isEqualTo(UPDATED_OCCASION_IMAGE_NAME);
        assertThat(testOccasionImage.getOccasionImageDesc()).isEqualTo(UPDATED_OCCASION_IMAGE_DESC);
        assertThat(testOccasionImage.getOccasionImageSize()).isEqualTo(UPDATED_OCCASION_IMAGE_SIZE);
        assertThat(testOccasionImage.getOccasionImage()).isEqualTo(UPDATED_OCCASION_IMAGE);
        assertThat(testOccasionImage.getOccasionImageContentType()).isEqualTo(UPDATED_OCCASION_IMAGE_CONTENT_TYPE);
        assertThat(testOccasionImage.getOccasionImageType()).isEqualTo(UPDATED_OCCASION_IMAGE_TYPE);
        assertThat(testOccasionImage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOccasionImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOccasionImage.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testOccasionImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOccasionImage.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingOccasionImage() throws Exception {
        int databaseSizeBeforeUpdate = occasionImageRepository.findAll().size();

        // Create the OccasionImage
        OccasionImageDTO occasionImageDTO = occasionImageMapper.toDto(occasionImage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOccasionImageMockMvc.perform(put("/api/occasion-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(occasionImageDTO)))
            .andExpect(status().isCreated());

        // Validate the OccasionImage in the database
        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOccasionImage() throws Exception {
        // Initialize the database
        occasionImageRepository.saveAndFlush(occasionImage);
        int databaseSizeBeforeDelete = occasionImageRepository.findAll().size();

        // Get the occasionImage
        restOccasionImageMockMvc.perform(delete("/api/occasion-images/{id}", occasionImage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OccasionImage> occasionImageList = occasionImageRepository.findAll();
        assertThat(occasionImageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OccasionImage.class);
        OccasionImage occasionImage1 = new OccasionImage();
        occasionImage1.setId(1L);
        OccasionImage occasionImage2 = new OccasionImage();
        occasionImage2.setId(occasionImage1.getId());
        assertThat(occasionImage1).isEqualTo(occasionImage2);
        occasionImage2.setId(2L);
        assertThat(occasionImage1).isNotEqualTo(occasionImage2);
        occasionImage1.setId(null);
        assertThat(occasionImage1).isNotEqualTo(occasionImage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OccasionImageDTO.class);
        OccasionImageDTO occasionImageDTO1 = new OccasionImageDTO();
        occasionImageDTO1.setId(1L);
        OccasionImageDTO occasionImageDTO2 = new OccasionImageDTO();
        assertThat(occasionImageDTO1).isNotEqualTo(occasionImageDTO2);
        occasionImageDTO2.setId(occasionImageDTO1.getId());
        assertThat(occasionImageDTO1).isEqualTo(occasionImageDTO2);
        occasionImageDTO2.setId(2L);
        assertThat(occasionImageDTO1).isNotEqualTo(occasionImageDTO2);
        occasionImageDTO1.setId(null);
        assertThat(occasionImageDTO1).isNotEqualTo(occasionImageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(occasionImageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(occasionImageMapper.fromId(null)).isNull();
    }
}
