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
import com.suchaz.app.domain.HobbyImage;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.HobbyImageRepository;
import com.suchaz.app.service.HobbyImageService;
import com.suchaz.app.service.dto.HobbyImageDTO;
import com.suchaz.app.service.mapper.HobbyImageMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the HobbyImageResource REST controller.
 *
 * @see HobbyImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class HobbyImageResourceIntTest {

    private static final String DEFAULT_HOBBY_IMAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOBBY_IMAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOBBY_IMAGE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_HOBBY_IMAGE_DESC = "BBBBBBBBBB";

    private static final Long DEFAULT_HOBBY_IMAGE_SIZE = 1L;
    private static final Long UPDATED_HOBBY_IMAGE_SIZE = 2L;

    private static final byte[] DEFAULT_HOBBY_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_HOBBY_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_HOBBY_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_HOBBY_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_HOBBY_IMAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_HOBBY_IMAGE_TYPE = "BBBBBBBBBB";

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
    private HobbyImageRepository hobbyImageRepository;

    @Autowired
    private HobbyImageMapper hobbyImageMapper;

    @Autowired
    private HobbyImageService hobbyImageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHobbyImageMockMvc;

    private HobbyImage hobbyImage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HobbyImageResource hobbyImageResource = new HobbyImageResource(hobbyImageService);
        this.restHobbyImageMockMvc = MockMvcBuilders.standaloneSetup(hobbyImageResource)
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
    public static HobbyImage createEntity(EntityManager em) {
        HobbyImage hobbyImage = new HobbyImage()
            .hobbyImageName(DEFAULT_HOBBY_IMAGE_NAME)
            .hobbyImageDesc(DEFAULT_HOBBY_IMAGE_DESC)
            .hobbyImageSize(DEFAULT_HOBBY_IMAGE_SIZE)
            .hobbyImage(DEFAULT_HOBBY_IMAGE)
            .hobbyImageContentType(DEFAULT_HOBBY_IMAGE_CONTENT_TYPE)
            .hobbyImageType(DEFAULT_HOBBY_IMAGE_TYPE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return hobbyImage;
    }

    @Before
    public void initTest() {
        hobbyImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createHobbyImage() throws Exception {
        int databaseSizeBeforeCreate = hobbyImageRepository.findAll().size();

        // Create the HobbyImage
        HobbyImageDTO hobbyImageDTO = hobbyImageMapper.toDto(hobbyImage);
        restHobbyImageMockMvc.perform(post("/api/hobby-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyImageDTO)))
            .andExpect(status().isCreated());

        // Validate the HobbyImage in the database
        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeCreate + 1);
        HobbyImage testHobbyImage = hobbyImageList.get(hobbyImageList.size() - 1);
        assertThat(testHobbyImage.getHobbyImageName()).isEqualTo(DEFAULT_HOBBY_IMAGE_NAME);
        assertThat(testHobbyImage.getHobbyImageDesc()).isEqualTo(DEFAULT_HOBBY_IMAGE_DESC);
        assertThat(testHobbyImage.getHobbyImageSize()).isEqualTo(DEFAULT_HOBBY_IMAGE_SIZE);
        assertThat(testHobbyImage.getHobbyImage()).isEqualTo(DEFAULT_HOBBY_IMAGE);
        assertThat(testHobbyImage.getHobbyImageContentType()).isEqualTo(DEFAULT_HOBBY_IMAGE_CONTENT_TYPE);
        assertThat(testHobbyImage.getHobbyImageType()).isEqualTo(DEFAULT_HOBBY_IMAGE_TYPE);
        assertThat(testHobbyImage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHobbyImage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testHobbyImage.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testHobbyImage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testHobbyImage.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createHobbyImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hobbyImageRepository.findAll().size();

        // Create the HobbyImage with an existing ID
        hobbyImage.setId(1L);
        HobbyImageDTO hobbyImageDTO = hobbyImageMapper.toDto(hobbyImage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHobbyImageMockMvc.perform(post("/api/hobby-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HobbyImage in the database
        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkHobbyImageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyImageRepository.findAll().size();
        // set the field null
        hobbyImage.setHobbyImageName(null);

        // Create the HobbyImage, which fails.
        HobbyImageDTO hobbyImageDTO = hobbyImageMapper.toDto(hobbyImage);

        restHobbyImageMockMvc.perform(post("/api/hobby-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyImageDTO)))
            .andExpect(status().isBadRequest());

        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHobbyImageSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyImageRepository.findAll().size();
        // set the field null
        hobbyImage.setHobbyImageSize(null);

        // Create the HobbyImage, which fails.
        HobbyImageDTO hobbyImageDTO = hobbyImageMapper.toDto(hobbyImage);

        restHobbyImageMockMvc.perform(post("/api/hobby-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyImageDTO)))
            .andExpect(status().isBadRequest());

        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyImageRepository.findAll().size();
        // set the field null
        hobbyImage.setStatus(null);

        // Create the HobbyImage, which fails.
        HobbyImageDTO hobbyImageDTO = hobbyImageMapper.toDto(hobbyImage);

        restHobbyImageMockMvc.perform(post("/api/hobby-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyImageDTO)))
            .andExpect(status().isBadRequest());

        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyImageRepository.findAll().size();
        // set the field null
        hobbyImage.setCreatedDate(null);

        // Create the HobbyImage, which fails.
        HobbyImageDTO hobbyImageDTO = hobbyImageMapper.toDto(hobbyImage);

        restHobbyImageMockMvc.perform(post("/api/hobby-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyImageDTO)))
            .andExpect(status().isBadRequest());

        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyImageRepository.findAll().size();
        // set the field null
        hobbyImage.setCreatedBy(null);

        // Create the HobbyImage, which fails.
        HobbyImageDTO hobbyImageDTO = hobbyImageMapper.toDto(hobbyImage);

        restHobbyImageMockMvc.perform(post("/api/hobby-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyImageDTO)))
            .andExpect(status().isBadRequest());

        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHobbyImages() throws Exception {
        // Initialize the database
        hobbyImageRepository.saveAndFlush(hobbyImage);

        // Get all the hobbyImageList
        restHobbyImageMockMvc.perform(get("/api/hobby-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hobbyImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].hobbyImageName").value(hasItem(DEFAULT_HOBBY_IMAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].hobbyImageDesc").value(hasItem(DEFAULT_HOBBY_IMAGE_DESC.toString())))
            .andExpect(jsonPath("$.[*].hobbyImageSize").value(hasItem(DEFAULT_HOBBY_IMAGE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].hobbyImageContentType").value(hasItem(DEFAULT_HOBBY_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].hobbyImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_HOBBY_IMAGE))))
            .andExpect(jsonPath("$.[*].hobbyImageType").value(hasItem(DEFAULT_HOBBY_IMAGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getHobbyImage() throws Exception {
        // Initialize the database
        hobbyImageRepository.saveAndFlush(hobbyImage);

        // Get the hobbyImage
        restHobbyImageMockMvc.perform(get("/api/hobby-images/{id}", hobbyImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hobbyImage.getId().intValue()))
            .andExpect(jsonPath("$.hobbyImageName").value(DEFAULT_HOBBY_IMAGE_NAME.toString()))
            .andExpect(jsonPath("$.hobbyImageDesc").value(DEFAULT_HOBBY_IMAGE_DESC.toString()))
            .andExpect(jsonPath("$.hobbyImageSize").value(DEFAULT_HOBBY_IMAGE_SIZE.intValue()))
            .andExpect(jsonPath("$.hobbyImageContentType").value(DEFAULT_HOBBY_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.hobbyImage").value(Base64Utils.encodeToString(DEFAULT_HOBBY_IMAGE)))
            .andExpect(jsonPath("$.hobbyImageType").value(DEFAULT_HOBBY_IMAGE_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHobbyImage() throws Exception {
        // Get the hobbyImage
        restHobbyImageMockMvc.perform(get("/api/hobby-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHobbyImage() throws Exception {
        // Initialize the database
        hobbyImageRepository.saveAndFlush(hobbyImage);
        int databaseSizeBeforeUpdate = hobbyImageRepository.findAll().size();

        // Update the hobbyImage
        HobbyImage updatedHobbyImage = hobbyImageRepository.findOne(hobbyImage.getId());
        // Disconnect from session so that the updates on updatedHobbyImage are not directly saved in db
        em.detach(updatedHobbyImage);
        updatedHobbyImage
            .hobbyImageName(UPDATED_HOBBY_IMAGE_NAME)
            .hobbyImageDesc(UPDATED_HOBBY_IMAGE_DESC)
            .hobbyImageSize(UPDATED_HOBBY_IMAGE_SIZE)
            .hobbyImage(UPDATED_HOBBY_IMAGE)
            .hobbyImageContentType(UPDATED_HOBBY_IMAGE_CONTENT_TYPE)
            .hobbyImageType(UPDATED_HOBBY_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        HobbyImageDTO hobbyImageDTO = hobbyImageMapper.toDto(updatedHobbyImage);

        restHobbyImageMockMvc.perform(put("/api/hobby-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyImageDTO)))
            .andExpect(status().isOk());

        // Validate the HobbyImage in the database
        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeUpdate);
        HobbyImage testHobbyImage = hobbyImageList.get(hobbyImageList.size() - 1);
        assertThat(testHobbyImage.getHobbyImageName()).isEqualTo(UPDATED_HOBBY_IMAGE_NAME);
        assertThat(testHobbyImage.getHobbyImageDesc()).isEqualTo(UPDATED_HOBBY_IMAGE_DESC);
        assertThat(testHobbyImage.getHobbyImageSize()).isEqualTo(UPDATED_HOBBY_IMAGE_SIZE);
        assertThat(testHobbyImage.getHobbyImage()).isEqualTo(UPDATED_HOBBY_IMAGE);
        assertThat(testHobbyImage.getHobbyImageContentType()).isEqualTo(UPDATED_HOBBY_IMAGE_CONTENT_TYPE);
        assertThat(testHobbyImage.getHobbyImageType()).isEqualTo(UPDATED_HOBBY_IMAGE_TYPE);
        assertThat(testHobbyImage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHobbyImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHobbyImage.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testHobbyImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testHobbyImage.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingHobbyImage() throws Exception {
        int databaseSizeBeforeUpdate = hobbyImageRepository.findAll().size();

        // Create the HobbyImage
        HobbyImageDTO hobbyImageDTO = hobbyImageMapper.toDto(hobbyImage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHobbyImageMockMvc.perform(put("/api/hobby-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyImageDTO)))
            .andExpect(status().isCreated());

        // Validate the HobbyImage in the database
        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteHobbyImage() throws Exception {
        // Initialize the database
        hobbyImageRepository.saveAndFlush(hobbyImage);
        int databaseSizeBeforeDelete = hobbyImageRepository.findAll().size();

        // Get the hobbyImage
        restHobbyImageMockMvc.perform(delete("/api/hobby-images/{id}", hobbyImage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HobbyImage> hobbyImageList = hobbyImageRepository.findAll();
        assertThat(hobbyImageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HobbyImage.class);
        HobbyImage hobbyImage1 = new HobbyImage();
        hobbyImage1.setId(1L);
        HobbyImage hobbyImage2 = new HobbyImage();
        hobbyImage2.setId(hobbyImage1.getId());
        assertThat(hobbyImage1).isEqualTo(hobbyImage2);
        hobbyImage2.setId(2L);
        assertThat(hobbyImage1).isNotEqualTo(hobbyImage2);
        hobbyImage1.setId(null);
        assertThat(hobbyImage1).isNotEqualTo(hobbyImage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HobbyImageDTO.class);
        HobbyImageDTO hobbyImageDTO1 = new HobbyImageDTO();
        hobbyImageDTO1.setId(1L);
        HobbyImageDTO hobbyImageDTO2 = new HobbyImageDTO();
        assertThat(hobbyImageDTO1).isNotEqualTo(hobbyImageDTO2);
        hobbyImageDTO2.setId(hobbyImageDTO1.getId());
        assertThat(hobbyImageDTO1).isEqualTo(hobbyImageDTO2);
        hobbyImageDTO2.setId(2L);
        assertThat(hobbyImageDTO1).isNotEqualTo(hobbyImageDTO2);
        hobbyImageDTO1.setId(null);
        assertThat(hobbyImageDTO1).isNotEqualTo(hobbyImageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(hobbyImageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(hobbyImageMapper.fromId(null)).isNull();
    }
}
