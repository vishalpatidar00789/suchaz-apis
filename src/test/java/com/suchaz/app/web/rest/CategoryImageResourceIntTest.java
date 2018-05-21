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
import com.suchaz.app.domain.CategoryImage;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.CategoryImageRepository;
import com.suchaz.app.service.CategoryImageService;
import com.suchaz.app.service.dto.CategoryImageDTO;
import com.suchaz.app.service.mapper.CategoryImageMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the CategoryImageResource REST controller.
 *
 * @see CategoryImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class CategoryImageResourceIntTest {

    private static final String DEFAULT_CATEGORY_IMAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_IMAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_IMAGE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_IMAGE_DESC = "BBBBBBBBBB";

    private static final Long DEFAULT_CATEGORY_IMAGE_SIZE = 1L;
    private static final Long UPDATED_CATEGORY_IMAGE_SIZE = 2L;

    private static final byte[] DEFAULT_CATEGORY_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CATEGORY_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_CATEGORY_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CATEGORY_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_CATEGORY_IMAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_IMAGE_TYPE = "BBBBBBBBBB";

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
    private CategoryImageRepository categoryImageRepository;

    @Autowired
    private CategoryImageMapper categoryImageMapper;

    @Autowired
    private CategoryImageService categoryImageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCategoryImageMockMvc;

    private CategoryImage categoryImage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategoryImageResource categoryImageResource = new CategoryImageResource(categoryImageService);
        this.restCategoryImageMockMvc = MockMvcBuilders.standaloneSetup(categoryImageResource)
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
    public static CategoryImage createEntity(EntityManager em) {
        CategoryImage categoryImage = new CategoryImage()
            .categoryImageName(DEFAULT_CATEGORY_IMAGE_NAME)
            .categoryImageDesc(DEFAULT_CATEGORY_IMAGE_DESC)
            .categoryImageSize(DEFAULT_CATEGORY_IMAGE_SIZE)
            .categoryImage(DEFAULT_CATEGORY_IMAGE)
            .categoryImageContentType(DEFAULT_CATEGORY_IMAGE_CONTENT_TYPE)
            .categoryImageType(DEFAULT_CATEGORY_IMAGE_TYPE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return categoryImage;
    }

    @Before
    public void initTest() {
        categoryImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategoryImage() throws Exception {
        int databaseSizeBeforeCreate = categoryImageRepository.findAll().size();

        // Create the CategoryImage
        CategoryImageDTO categoryImageDTO = categoryImageMapper.toDto(categoryImage);
        restCategoryImageMockMvc.perform(post("/api/category-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryImageDTO)))
            .andExpect(status().isCreated());

        // Validate the CategoryImage in the database
        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeCreate + 1);
        CategoryImage testCategoryImage = categoryImageList.get(categoryImageList.size() - 1);
        assertThat(testCategoryImage.getCategoryImageName()).isEqualTo(DEFAULT_CATEGORY_IMAGE_NAME);
        assertThat(testCategoryImage.getCategoryImageDesc()).isEqualTo(DEFAULT_CATEGORY_IMAGE_DESC);
        assertThat(testCategoryImage.getCategoryImageSize()).isEqualTo(DEFAULT_CATEGORY_IMAGE_SIZE);
        assertThat(testCategoryImage.getCategoryImage()).isEqualTo(DEFAULT_CATEGORY_IMAGE);
        assertThat(testCategoryImage.getCategoryImageContentType()).isEqualTo(DEFAULT_CATEGORY_IMAGE_CONTENT_TYPE);
        assertThat(testCategoryImage.getCategoryImageType()).isEqualTo(DEFAULT_CATEGORY_IMAGE_TYPE);
        assertThat(testCategoryImage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCategoryImage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testCategoryImage.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testCategoryImage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCategoryImage.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createCategoryImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoryImageRepository.findAll().size();

        // Create the CategoryImage with an existing ID
        categoryImage.setId(1L);
        CategoryImageDTO categoryImageDTO = categoryImageMapper.toDto(categoryImage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryImageMockMvc.perform(post("/api/category-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategoryImage in the database
        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCategoryImageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryImageRepository.findAll().size();
        // set the field null
        categoryImage.setCategoryImageName(null);

        // Create the CategoryImage, which fails.
        CategoryImageDTO categoryImageDTO = categoryImageMapper.toDto(categoryImage);

        restCategoryImageMockMvc.perform(post("/api/category-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryImageDTO)))
            .andExpect(status().isBadRequest());

        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryImageSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryImageRepository.findAll().size();
        // set the field null
        categoryImage.setCategoryImageSize(null);

        // Create the CategoryImage, which fails.
        CategoryImageDTO categoryImageDTO = categoryImageMapper.toDto(categoryImage);

        restCategoryImageMockMvc.perform(post("/api/category-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryImageDTO)))
            .andExpect(status().isBadRequest());

        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryImageRepository.findAll().size();
        // set the field null
        categoryImage.setStatus(null);

        // Create the CategoryImage, which fails.
        CategoryImageDTO categoryImageDTO = categoryImageMapper.toDto(categoryImage);

        restCategoryImageMockMvc.perform(post("/api/category-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryImageDTO)))
            .andExpect(status().isBadRequest());

        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryImageRepository.findAll().size();
        // set the field null
        categoryImage.setCreatedDate(null);

        // Create the CategoryImage, which fails.
        CategoryImageDTO categoryImageDTO = categoryImageMapper.toDto(categoryImage);

        restCategoryImageMockMvc.perform(post("/api/category-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryImageDTO)))
            .andExpect(status().isBadRequest());

        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryImageRepository.findAll().size();
        // set the field null
        categoryImage.setCreatedBy(null);

        // Create the CategoryImage, which fails.
        CategoryImageDTO categoryImageDTO = categoryImageMapper.toDto(categoryImage);

        restCategoryImageMockMvc.perform(post("/api/category-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryImageDTO)))
            .andExpect(status().isBadRequest());

        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategoryImages() throws Exception {
        // Initialize the database
        categoryImageRepository.saveAndFlush(categoryImage);

        // Get all the categoryImageList
        restCategoryImageMockMvc.perform(get("/api/category-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryImageName").value(hasItem(DEFAULT_CATEGORY_IMAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].categoryImageDesc").value(hasItem(DEFAULT_CATEGORY_IMAGE_DESC.toString())))
            .andExpect(jsonPath("$.[*].categoryImageSize").value(hasItem(DEFAULT_CATEGORY_IMAGE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].categoryImageContentType").value(hasItem(DEFAULT_CATEGORY_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].categoryImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_CATEGORY_IMAGE))))
            .andExpect(jsonPath("$.[*].categoryImageType").value(hasItem(DEFAULT_CATEGORY_IMAGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getCategoryImage() throws Exception {
        // Initialize the database
        categoryImageRepository.saveAndFlush(categoryImage);

        // Get the categoryImage
        restCategoryImageMockMvc.perform(get("/api/category-images/{id}", categoryImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(categoryImage.getId().intValue()))
            .andExpect(jsonPath("$.categoryImageName").value(DEFAULT_CATEGORY_IMAGE_NAME.toString()))
            .andExpect(jsonPath("$.categoryImageDesc").value(DEFAULT_CATEGORY_IMAGE_DESC.toString()))
            .andExpect(jsonPath("$.categoryImageSize").value(DEFAULT_CATEGORY_IMAGE_SIZE.intValue()))
            .andExpect(jsonPath("$.categoryImageContentType").value(DEFAULT_CATEGORY_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.categoryImage").value(Base64Utils.encodeToString(DEFAULT_CATEGORY_IMAGE)))
            .andExpect(jsonPath("$.categoryImageType").value(DEFAULT_CATEGORY_IMAGE_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategoryImage() throws Exception {
        // Get the categoryImage
        restCategoryImageMockMvc.perform(get("/api/category-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoryImage() throws Exception {
        // Initialize the database
        categoryImageRepository.saveAndFlush(categoryImage);
        int databaseSizeBeforeUpdate = categoryImageRepository.findAll().size();

        // Update the categoryImage
        CategoryImage updatedCategoryImage = categoryImageRepository.findOne(categoryImage.getId());
        // Disconnect from session so that the updates on updatedCategoryImage are not directly saved in db
        em.detach(updatedCategoryImage);
        updatedCategoryImage
            .categoryImageName(UPDATED_CATEGORY_IMAGE_NAME)
            .categoryImageDesc(UPDATED_CATEGORY_IMAGE_DESC)
            .categoryImageSize(UPDATED_CATEGORY_IMAGE_SIZE)
            .categoryImage(UPDATED_CATEGORY_IMAGE)
            .categoryImageContentType(UPDATED_CATEGORY_IMAGE_CONTENT_TYPE)
            .categoryImageType(UPDATED_CATEGORY_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        CategoryImageDTO categoryImageDTO = categoryImageMapper.toDto(updatedCategoryImage);

        restCategoryImageMockMvc.perform(put("/api/category-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryImageDTO)))
            .andExpect(status().isOk());

        // Validate the CategoryImage in the database
        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeUpdate);
        CategoryImage testCategoryImage = categoryImageList.get(categoryImageList.size() - 1);
        assertThat(testCategoryImage.getCategoryImageName()).isEqualTo(UPDATED_CATEGORY_IMAGE_NAME);
        assertThat(testCategoryImage.getCategoryImageDesc()).isEqualTo(UPDATED_CATEGORY_IMAGE_DESC);
        assertThat(testCategoryImage.getCategoryImageSize()).isEqualTo(UPDATED_CATEGORY_IMAGE_SIZE);
        assertThat(testCategoryImage.getCategoryImage()).isEqualTo(UPDATED_CATEGORY_IMAGE);
        assertThat(testCategoryImage.getCategoryImageContentType()).isEqualTo(UPDATED_CATEGORY_IMAGE_CONTENT_TYPE);
        assertThat(testCategoryImage.getCategoryImageType()).isEqualTo(UPDATED_CATEGORY_IMAGE_TYPE);
        assertThat(testCategoryImage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCategoryImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testCategoryImage.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testCategoryImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCategoryImage.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingCategoryImage() throws Exception {
        int databaseSizeBeforeUpdate = categoryImageRepository.findAll().size();

        // Create the CategoryImage
        CategoryImageDTO categoryImageDTO = categoryImageMapper.toDto(categoryImage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCategoryImageMockMvc.perform(put("/api/category-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoryImageDTO)))
            .andExpect(status().isCreated());

        // Validate the CategoryImage in the database
        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCategoryImage() throws Exception {
        // Initialize the database
        categoryImageRepository.saveAndFlush(categoryImage);
        int databaseSizeBeforeDelete = categoryImageRepository.findAll().size();

        // Get the categoryImage
        restCategoryImageMockMvc.perform(delete("/api/category-images/{id}", categoryImage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CategoryImage> categoryImageList = categoryImageRepository.findAll();
        assertThat(categoryImageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryImage.class);
        CategoryImage categoryImage1 = new CategoryImage();
        categoryImage1.setId(1L);
        CategoryImage categoryImage2 = new CategoryImage();
        categoryImage2.setId(categoryImage1.getId());
        assertThat(categoryImage1).isEqualTo(categoryImage2);
        categoryImage2.setId(2L);
        assertThat(categoryImage1).isNotEqualTo(categoryImage2);
        categoryImage1.setId(null);
        assertThat(categoryImage1).isNotEqualTo(categoryImage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryImageDTO.class);
        CategoryImageDTO categoryImageDTO1 = new CategoryImageDTO();
        categoryImageDTO1.setId(1L);
        CategoryImageDTO categoryImageDTO2 = new CategoryImageDTO();
        assertThat(categoryImageDTO1).isNotEqualTo(categoryImageDTO2);
        categoryImageDTO2.setId(categoryImageDTO1.getId());
        assertThat(categoryImageDTO1).isEqualTo(categoryImageDTO2);
        categoryImageDTO2.setId(2L);
        assertThat(categoryImageDTO1).isNotEqualTo(categoryImageDTO2);
        categoryImageDTO1.setId(null);
        assertThat(categoryImageDTO1).isNotEqualTo(categoryImageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(categoryImageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(categoryImageMapper.fromId(null)).isNull();
    }
}
