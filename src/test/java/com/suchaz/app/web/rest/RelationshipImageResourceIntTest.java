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
import com.suchaz.app.domain.RelationshipImage;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.RelationshipImageRepository;
import com.suchaz.app.service.RelationshipImageService;
import com.suchaz.app.service.dto.RelationshipImageDTO;
import com.suchaz.app.service.mapper.RelationshipImageMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the RelationshipImageResource REST controller.
 *
 * @see RelationshipImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class RelationshipImageResourceIntTest {

    private static final String DEFAULT_RELATIONSHIP_IMAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP_IMAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP_IMAGE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP_IMAGE_DESC = "BBBBBBBBBB";

    private static final Long DEFAULT_RELATIONSHIP_IMAGE_SIZE = 1L;
    private static final Long UPDATED_RELATIONSHIP_IMAGE_SIZE = 2L;

    private static final byte[] DEFAULT_RELATIONSHIP_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_RELATIONSHIP_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_RELATIONSHIP_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_RELATIONSHIP_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_RELATIONSHIP_IMAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP_IMAGE_TYPE = "BBBBBBBBBB";

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
    private RelationshipImageRepository relationshipImageRepository;

    @Autowired
    private RelationshipImageMapper relationshipImageMapper;

    @Autowired
    private RelationshipImageService relationshipImageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRelationshipImageMockMvc;

    private RelationshipImage relationshipImage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RelationshipImageResource relationshipImageResource = new RelationshipImageResource(relationshipImageService);
        this.restRelationshipImageMockMvc = MockMvcBuilders.standaloneSetup(relationshipImageResource)
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
    public static RelationshipImage createEntity(EntityManager em) {
        RelationshipImage relationshipImage = new RelationshipImage()
            .relationshipImageName(DEFAULT_RELATIONSHIP_IMAGE_NAME)
            .relationshipImageDesc(DEFAULT_RELATIONSHIP_IMAGE_DESC)
            .relationshipImageSize(DEFAULT_RELATIONSHIP_IMAGE_SIZE)
            .relationshipImage(DEFAULT_RELATIONSHIP_IMAGE)
            .relationshipImageContentType(DEFAULT_RELATIONSHIP_IMAGE_CONTENT_TYPE)
            .relationshipImageType(DEFAULT_RELATIONSHIP_IMAGE_TYPE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return relationshipImage;
    }

    @Before
    public void initTest() {
        relationshipImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelationshipImage() throws Exception {
        int databaseSizeBeforeCreate = relationshipImageRepository.findAll().size();

        // Create the RelationshipImage
        RelationshipImageDTO relationshipImageDTO = relationshipImageMapper.toDto(relationshipImage);
        restRelationshipImageMockMvc.perform(post("/api/relationship-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipImageDTO)))
            .andExpect(status().isCreated());

        // Validate the RelationshipImage in the database
        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeCreate + 1);
        RelationshipImage testRelationshipImage = relationshipImageList.get(relationshipImageList.size() - 1);
        assertThat(testRelationshipImage.getRelationshipImageName()).isEqualTo(DEFAULT_RELATIONSHIP_IMAGE_NAME);
        assertThat(testRelationshipImage.getRelationshipImageDesc()).isEqualTo(DEFAULT_RELATIONSHIP_IMAGE_DESC);
        assertThat(testRelationshipImage.getRelationshipImageSize()).isEqualTo(DEFAULT_RELATIONSHIP_IMAGE_SIZE);
        assertThat(testRelationshipImage.getRelationshipImage()).isEqualTo(DEFAULT_RELATIONSHIP_IMAGE);
        assertThat(testRelationshipImage.getRelationshipImageContentType()).isEqualTo(DEFAULT_RELATIONSHIP_IMAGE_CONTENT_TYPE);
        assertThat(testRelationshipImage.getRelationshipImageType()).isEqualTo(DEFAULT_RELATIONSHIP_IMAGE_TYPE);
        assertThat(testRelationshipImage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRelationshipImage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRelationshipImage.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testRelationshipImage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRelationshipImage.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createRelationshipImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relationshipImageRepository.findAll().size();

        // Create the RelationshipImage with an existing ID
        relationshipImage.setId(1L);
        RelationshipImageDTO relationshipImageDTO = relationshipImageMapper.toDto(relationshipImage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelationshipImageMockMvc.perform(post("/api/relationship-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelationshipImage in the database
        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRelationshipImageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipImageRepository.findAll().size();
        // set the field null
        relationshipImage.setRelationshipImageName(null);

        // Create the RelationshipImage, which fails.
        RelationshipImageDTO relationshipImageDTO = relationshipImageMapper.toDto(relationshipImage);

        restRelationshipImageMockMvc.perform(post("/api/relationship-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipImageDTO)))
            .andExpect(status().isBadRequest());

        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRelationshipImageSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipImageRepository.findAll().size();
        // set the field null
        relationshipImage.setRelationshipImageSize(null);

        // Create the RelationshipImage, which fails.
        RelationshipImageDTO relationshipImageDTO = relationshipImageMapper.toDto(relationshipImage);

        restRelationshipImageMockMvc.perform(post("/api/relationship-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipImageDTO)))
            .andExpect(status().isBadRequest());

        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipImageRepository.findAll().size();
        // set the field null
        relationshipImage.setStatus(null);

        // Create the RelationshipImage, which fails.
        RelationshipImageDTO relationshipImageDTO = relationshipImageMapper.toDto(relationshipImage);

        restRelationshipImageMockMvc.perform(post("/api/relationship-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipImageDTO)))
            .andExpect(status().isBadRequest());

        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipImageRepository.findAll().size();
        // set the field null
        relationshipImage.setCreatedDate(null);

        // Create the RelationshipImage, which fails.
        RelationshipImageDTO relationshipImageDTO = relationshipImageMapper.toDto(relationshipImage);

        restRelationshipImageMockMvc.perform(post("/api/relationship-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipImageDTO)))
            .andExpect(status().isBadRequest());

        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipImageRepository.findAll().size();
        // set the field null
        relationshipImage.setCreatedBy(null);

        // Create the RelationshipImage, which fails.
        RelationshipImageDTO relationshipImageDTO = relationshipImageMapper.toDto(relationshipImage);

        restRelationshipImageMockMvc.perform(post("/api/relationship-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipImageDTO)))
            .andExpect(status().isBadRequest());

        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRelationshipImages() throws Exception {
        // Initialize the database
        relationshipImageRepository.saveAndFlush(relationshipImage);

        // Get all the relationshipImageList
        restRelationshipImageMockMvc.perform(get("/api/relationship-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relationshipImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].relationshipImageName").value(hasItem(DEFAULT_RELATIONSHIP_IMAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].relationshipImageDesc").value(hasItem(DEFAULT_RELATIONSHIP_IMAGE_DESC.toString())))
            .andExpect(jsonPath("$.[*].relationshipImageSize").value(hasItem(DEFAULT_RELATIONSHIP_IMAGE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].relationshipImageContentType").value(hasItem(DEFAULT_RELATIONSHIP_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].relationshipImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_RELATIONSHIP_IMAGE))))
            .andExpect(jsonPath("$.[*].relationshipImageType").value(hasItem(DEFAULT_RELATIONSHIP_IMAGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getRelationshipImage() throws Exception {
        // Initialize the database
        relationshipImageRepository.saveAndFlush(relationshipImage);

        // Get the relationshipImage
        restRelationshipImageMockMvc.perform(get("/api/relationship-images/{id}", relationshipImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(relationshipImage.getId().intValue()))
            .andExpect(jsonPath("$.relationshipImageName").value(DEFAULT_RELATIONSHIP_IMAGE_NAME.toString()))
            .andExpect(jsonPath("$.relationshipImageDesc").value(DEFAULT_RELATIONSHIP_IMAGE_DESC.toString()))
            .andExpect(jsonPath("$.relationshipImageSize").value(DEFAULT_RELATIONSHIP_IMAGE_SIZE.intValue()))
            .andExpect(jsonPath("$.relationshipImageContentType").value(DEFAULT_RELATIONSHIP_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.relationshipImage").value(Base64Utils.encodeToString(DEFAULT_RELATIONSHIP_IMAGE)))
            .andExpect(jsonPath("$.relationshipImageType").value(DEFAULT_RELATIONSHIP_IMAGE_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRelationshipImage() throws Exception {
        // Get the relationshipImage
        restRelationshipImageMockMvc.perform(get("/api/relationship-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelationshipImage() throws Exception {
        // Initialize the database
        relationshipImageRepository.saveAndFlush(relationshipImage);
        int databaseSizeBeforeUpdate = relationshipImageRepository.findAll().size();

        // Update the relationshipImage
        RelationshipImage updatedRelationshipImage = relationshipImageRepository.findOne(relationshipImage.getId());
        // Disconnect from session so that the updates on updatedRelationshipImage are not directly saved in db
        em.detach(updatedRelationshipImage);
        updatedRelationshipImage
            .relationshipImageName(UPDATED_RELATIONSHIP_IMAGE_NAME)
            .relationshipImageDesc(UPDATED_RELATIONSHIP_IMAGE_DESC)
            .relationshipImageSize(UPDATED_RELATIONSHIP_IMAGE_SIZE)
            .relationshipImage(UPDATED_RELATIONSHIP_IMAGE)
            .relationshipImageContentType(UPDATED_RELATIONSHIP_IMAGE_CONTENT_TYPE)
            .relationshipImageType(UPDATED_RELATIONSHIP_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        RelationshipImageDTO relationshipImageDTO = relationshipImageMapper.toDto(updatedRelationshipImage);

        restRelationshipImageMockMvc.perform(put("/api/relationship-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipImageDTO)))
            .andExpect(status().isOk());

        // Validate the RelationshipImage in the database
        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeUpdate);
        RelationshipImage testRelationshipImage = relationshipImageList.get(relationshipImageList.size() - 1);
        assertThat(testRelationshipImage.getRelationshipImageName()).isEqualTo(UPDATED_RELATIONSHIP_IMAGE_NAME);
        assertThat(testRelationshipImage.getRelationshipImageDesc()).isEqualTo(UPDATED_RELATIONSHIP_IMAGE_DESC);
        assertThat(testRelationshipImage.getRelationshipImageSize()).isEqualTo(UPDATED_RELATIONSHIP_IMAGE_SIZE);
        assertThat(testRelationshipImage.getRelationshipImage()).isEqualTo(UPDATED_RELATIONSHIP_IMAGE);
        assertThat(testRelationshipImage.getRelationshipImageContentType()).isEqualTo(UPDATED_RELATIONSHIP_IMAGE_CONTENT_TYPE);
        assertThat(testRelationshipImage.getRelationshipImageType()).isEqualTo(UPDATED_RELATIONSHIP_IMAGE_TYPE);
        assertThat(testRelationshipImage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRelationshipImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRelationshipImage.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testRelationshipImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRelationshipImage.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingRelationshipImage() throws Exception {
        int databaseSizeBeforeUpdate = relationshipImageRepository.findAll().size();

        // Create the RelationshipImage
        RelationshipImageDTO relationshipImageDTO = relationshipImageMapper.toDto(relationshipImage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRelationshipImageMockMvc.perform(put("/api/relationship-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipImageDTO)))
            .andExpect(status().isCreated());

        // Validate the RelationshipImage in the database
        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRelationshipImage() throws Exception {
        // Initialize the database
        relationshipImageRepository.saveAndFlush(relationshipImage);
        int databaseSizeBeforeDelete = relationshipImageRepository.findAll().size();

        // Get the relationshipImage
        restRelationshipImageMockMvc.perform(delete("/api/relationship-images/{id}", relationshipImage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RelationshipImage> relationshipImageList = relationshipImageRepository.findAll();
        assertThat(relationshipImageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelationshipImage.class);
        RelationshipImage relationshipImage1 = new RelationshipImage();
        relationshipImage1.setId(1L);
        RelationshipImage relationshipImage2 = new RelationshipImage();
        relationshipImage2.setId(relationshipImage1.getId());
        assertThat(relationshipImage1).isEqualTo(relationshipImage2);
        relationshipImage2.setId(2L);
        assertThat(relationshipImage1).isNotEqualTo(relationshipImage2);
        relationshipImage1.setId(null);
        assertThat(relationshipImage1).isNotEqualTo(relationshipImage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelationshipImageDTO.class);
        RelationshipImageDTO relationshipImageDTO1 = new RelationshipImageDTO();
        relationshipImageDTO1.setId(1L);
        RelationshipImageDTO relationshipImageDTO2 = new RelationshipImageDTO();
        assertThat(relationshipImageDTO1).isNotEqualTo(relationshipImageDTO2);
        relationshipImageDTO2.setId(relationshipImageDTO1.getId());
        assertThat(relationshipImageDTO1).isEqualTo(relationshipImageDTO2);
        relationshipImageDTO2.setId(2L);
        assertThat(relationshipImageDTO1).isNotEqualTo(relationshipImageDTO2);
        relationshipImageDTO1.setId(null);
        assertThat(relationshipImageDTO1).isNotEqualTo(relationshipImageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(relationshipImageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(relationshipImageMapper.fromId(null)).isNull();
    }
}
