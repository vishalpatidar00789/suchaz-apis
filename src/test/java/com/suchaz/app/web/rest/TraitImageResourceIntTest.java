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
import com.suchaz.app.domain.TraitImage;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.TraitImageRepository;
import com.suchaz.app.service.TraitImageService;
import com.suchaz.app.service.dto.TraitImageDTO;
import com.suchaz.app.service.mapper.TraitImageMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the TraitImageResource REST controller.
 *
 * @see TraitImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class TraitImageResourceIntTest {

    private static final String DEFAULT_PERSONALITY_IMAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PERSONALITY_IMAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONALITY_IMAGE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PERSONALITY_IMAGE_DESC = "BBBBBBBBBB";

    private static final Long DEFAULT_PERSONALITY_IMAGE_SIZE = 1L;
    private static final Long UPDATED_PERSONALITY_IMAGE_SIZE = 2L;

    private static final byte[] DEFAULT_PERSONALITY_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PERSONALITY_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PERSONALITY_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PERSONALITY_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PERSONALITY_IMAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PERSONALITY_IMAGE_TYPE = "BBBBBBBBBB";

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
    private TraitImageRepository traitImageRepository;

    @Autowired
    private TraitImageMapper traitImageMapper;

    @Autowired
    private TraitImageService traitImageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTraitImageMockMvc;

    private TraitImage traitImage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TraitImageResource traitImageResource = new TraitImageResource(traitImageService);
        this.restTraitImageMockMvc = MockMvcBuilders.standaloneSetup(traitImageResource)
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
    public static TraitImage createEntity(EntityManager em) {
        TraitImage traitImage = new TraitImage()
            .personalityImageName(DEFAULT_PERSONALITY_IMAGE_NAME)
            .personalityImageDesc(DEFAULT_PERSONALITY_IMAGE_DESC)
            .personalityImageSize(DEFAULT_PERSONALITY_IMAGE_SIZE)
            .personalityImage(DEFAULT_PERSONALITY_IMAGE)
            .personalityImageContentType(DEFAULT_PERSONALITY_IMAGE_CONTENT_TYPE)
            .personalityImageType(DEFAULT_PERSONALITY_IMAGE_TYPE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return traitImage;
    }

    @Before
    public void initTest() {
        traitImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createTraitImage() throws Exception {
        int databaseSizeBeforeCreate = traitImageRepository.findAll().size();

        // Create the TraitImage
        TraitImageDTO traitImageDTO = traitImageMapper.toDto(traitImage);
        restTraitImageMockMvc.perform(post("/api/trait-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitImageDTO)))
            .andExpect(status().isCreated());

        // Validate the TraitImage in the database
        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeCreate + 1);
        TraitImage testTraitImage = traitImageList.get(traitImageList.size() - 1);
        assertThat(testTraitImage.getPersonalityImageName()).isEqualTo(DEFAULT_PERSONALITY_IMAGE_NAME);
        assertThat(testTraitImage.getPersonalityImageDesc()).isEqualTo(DEFAULT_PERSONALITY_IMAGE_DESC);
        assertThat(testTraitImage.getPersonalityImageSize()).isEqualTo(DEFAULT_PERSONALITY_IMAGE_SIZE);
        assertThat(testTraitImage.getPersonalityImage()).isEqualTo(DEFAULT_PERSONALITY_IMAGE);
        assertThat(testTraitImage.getPersonalityImageContentType()).isEqualTo(DEFAULT_PERSONALITY_IMAGE_CONTENT_TYPE);
        assertThat(testTraitImage.getPersonalityImageType()).isEqualTo(DEFAULT_PERSONALITY_IMAGE_TYPE);
        assertThat(testTraitImage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTraitImage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTraitImage.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testTraitImage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTraitImage.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createTraitImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = traitImageRepository.findAll().size();

        // Create the TraitImage with an existing ID
        traitImage.setId(1L);
        TraitImageDTO traitImageDTO = traitImageMapper.toDto(traitImage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTraitImageMockMvc.perform(post("/api/trait-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TraitImage in the database
        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPersonalityImageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitImageRepository.findAll().size();
        // set the field null
        traitImage.setPersonalityImageName(null);

        // Create the TraitImage, which fails.
        TraitImageDTO traitImageDTO = traitImageMapper.toDto(traitImage);

        restTraitImageMockMvc.perform(post("/api/trait-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitImageDTO)))
            .andExpect(status().isBadRequest());

        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPersonalityImageSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitImageRepository.findAll().size();
        // set the field null
        traitImage.setPersonalityImageSize(null);

        // Create the TraitImage, which fails.
        TraitImageDTO traitImageDTO = traitImageMapper.toDto(traitImage);

        restTraitImageMockMvc.perform(post("/api/trait-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitImageDTO)))
            .andExpect(status().isBadRequest());

        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitImageRepository.findAll().size();
        // set the field null
        traitImage.setStatus(null);

        // Create the TraitImage, which fails.
        TraitImageDTO traitImageDTO = traitImageMapper.toDto(traitImage);

        restTraitImageMockMvc.perform(post("/api/trait-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitImageDTO)))
            .andExpect(status().isBadRequest());

        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitImageRepository.findAll().size();
        // set the field null
        traitImage.setCreatedDate(null);

        // Create the TraitImage, which fails.
        TraitImageDTO traitImageDTO = traitImageMapper.toDto(traitImage);

        restTraitImageMockMvc.perform(post("/api/trait-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitImageDTO)))
            .andExpect(status().isBadRequest());

        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitImageRepository.findAll().size();
        // set the field null
        traitImage.setCreatedBy(null);

        // Create the TraitImage, which fails.
        TraitImageDTO traitImageDTO = traitImageMapper.toDto(traitImage);

        restTraitImageMockMvc.perform(post("/api/trait-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitImageDTO)))
            .andExpect(status().isBadRequest());

        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTraitImages() throws Exception {
        // Initialize the database
        traitImageRepository.saveAndFlush(traitImage);

        // Get all the traitImageList
        restTraitImageMockMvc.perform(get("/api/trait-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(traitImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].personalityImageName").value(hasItem(DEFAULT_PERSONALITY_IMAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].personalityImageDesc").value(hasItem(DEFAULT_PERSONALITY_IMAGE_DESC.toString())))
            .andExpect(jsonPath("$.[*].personalityImageSize").value(hasItem(DEFAULT_PERSONALITY_IMAGE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].personalityImageContentType").value(hasItem(DEFAULT_PERSONALITY_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].personalityImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_PERSONALITY_IMAGE))))
            .andExpect(jsonPath("$.[*].personalityImageType").value(hasItem(DEFAULT_PERSONALITY_IMAGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getTraitImage() throws Exception {
        // Initialize the database
        traitImageRepository.saveAndFlush(traitImage);

        // Get the traitImage
        restTraitImageMockMvc.perform(get("/api/trait-images/{id}", traitImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(traitImage.getId().intValue()))
            .andExpect(jsonPath("$.personalityImageName").value(DEFAULT_PERSONALITY_IMAGE_NAME.toString()))
            .andExpect(jsonPath("$.personalityImageDesc").value(DEFAULT_PERSONALITY_IMAGE_DESC.toString()))
            .andExpect(jsonPath("$.personalityImageSize").value(DEFAULT_PERSONALITY_IMAGE_SIZE.intValue()))
            .andExpect(jsonPath("$.personalityImageContentType").value(DEFAULT_PERSONALITY_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.personalityImage").value(Base64Utils.encodeToString(DEFAULT_PERSONALITY_IMAGE)))
            .andExpect(jsonPath("$.personalityImageType").value(DEFAULT_PERSONALITY_IMAGE_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTraitImage() throws Exception {
        // Get the traitImage
        restTraitImageMockMvc.perform(get("/api/trait-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTraitImage() throws Exception {
        // Initialize the database
        traitImageRepository.saveAndFlush(traitImage);
        int databaseSizeBeforeUpdate = traitImageRepository.findAll().size();

        // Update the traitImage
        TraitImage updatedTraitImage = traitImageRepository.findOne(traitImage.getId());
        // Disconnect from session so that the updates on updatedTraitImage are not directly saved in db
        em.detach(updatedTraitImage);
        updatedTraitImage
            .personalityImageName(UPDATED_PERSONALITY_IMAGE_NAME)
            .personalityImageDesc(UPDATED_PERSONALITY_IMAGE_DESC)
            .personalityImageSize(UPDATED_PERSONALITY_IMAGE_SIZE)
            .personalityImage(UPDATED_PERSONALITY_IMAGE)
            .personalityImageContentType(UPDATED_PERSONALITY_IMAGE_CONTENT_TYPE)
            .personalityImageType(UPDATED_PERSONALITY_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        TraitImageDTO traitImageDTO = traitImageMapper.toDto(updatedTraitImage);

        restTraitImageMockMvc.perform(put("/api/trait-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitImageDTO)))
            .andExpect(status().isOk());

        // Validate the TraitImage in the database
        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeUpdate);
        TraitImage testTraitImage = traitImageList.get(traitImageList.size() - 1);
        assertThat(testTraitImage.getPersonalityImageName()).isEqualTo(UPDATED_PERSONALITY_IMAGE_NAME);
        assertThat(testTraitImage.getPersonalityImageDesc()).isEqualTo(UPDATED_PERSONALITY_IMAGE_DESC);
        assertThat(testTraitImage.getPersonalityImageSize()).isEqualTo(UPDATED_PERSONALITY_IMAGE_SIZE);
        assertThat(testTraitImage.getPersonalityImage()).isEqualTo(UPDATED_PERSONALITY_IMAGE);
        assertThat(testTraitImage.getPersonalityImageContentType()).isEqualTo(UPDATED_PERSONALITY_IMAGE_CONTENT_TYPE);
        assertThat(testTraitImage.getPersonalityImageType()).isEqualTo(UPDATED_PERSONALITY_IMAGE_TYPE);
        assertThat(testTraitImage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTraitImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTraitImage.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testTraitImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTraitImage.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingTraitImage() throws Exception {
        int databaseSizeBeforeUpdate = traitImageRepository.findAll().size();

        // Create the TraitImage
        TraitImageDTO traitImageDTO = traitImageMapper.toDto(traitImage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTraitImageMockMvc.perform(put("/api/trait-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(traitImageDTO)))
            .andExpect(status().isCreated());

        // Validate the TraitImage in the database
        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTraitImage() throws Exception {
        // Initialize the database
        traitImageRepository.saveAndFlush(traitImage);
        int databaseSizeBeforeDelete = traitImageRepository.findAll().size();

        // Get the traitImage
        restTraitImageMockMvc.perform(delete("/api/trait-images/{id}", traitImage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TraitImage> traitImageList = traitImageRepository.findAll();
        assertThat(traitImageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TraitImage.class);
        TraitImage traitImage1 = new TraitImage();
        traitImage1.setId(1L);
        TraitImage traitImage2 = new TraitImage();
        traitImage2.setId(traitImage1.getId());
        assertThat(traitImage1).isEqualTo(traitImage2);
        traitImage2.setId(2L);
        assertThat(traitImage1).isNotEqualTo(traitImage2);
        traitImage1.setId(null);
        assertThat(traitImage1).isNotEqualTo(traitImage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TraitImageDTO.class);
        TraitImageDTO traitImageDTO1 = new TraitImageDTO();
        traitImageDTO1.setId(1L);
        TraitImageDTO traitImageDTO2 = new TraitImageDTO();
        assertThat(traitImageDTO1).isNotEqualTo(traitImageDTO2);
        traitImageDTO2.setId(traitImageDTO1.getId());
        assertThat(traitImageDTO1).isEqualTo(traitImageDTO2);
        traitImageDTO2.setId(2L);
        assertThat(traitImageDTO1).isNotEqualTo(traitImageDTO2);
        traitImageDTO1.setId(null);
        assertThat(traitImageDTO1).isNotEqualTo(traitImageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(traitImageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(traitImageMapper.fromId(null)).isNull();
    }
}
