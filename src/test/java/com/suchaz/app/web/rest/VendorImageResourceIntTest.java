package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.VendorImage;
import com.suchaz.app.repository.VendorImageRepository;
import com.suchaz.app.service.VendorImageService;
import com.suchaz.app.service.dto.VendorImageDTO;
import com.suchaz.app.service.mapper.VendorImageMapper;
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
 * Test class for the VendorImageResource REST controller.
 *
 * @see VendorImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class VendorImageResourceIntTest {

    private static final String DEFAULT_VENDOR_IMAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_IMAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_IMAGE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_IMAGE_DESC = "BBBBBBBBBB";

    private static final Long DEFAULT_VENDOR_IMAGE_SIZE = 1L;
    private static final Long UPDATED_VENDOR_IMAGE_SIZE = 2L;

    private static final byte[] DEFAULT_VENDOR_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VENDOR_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_VENDOR_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VENDOR_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_VENDOR_IMAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_IMAGE_TYPE = "BBBBBBBBBB";

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
    private VendorImageRepository vendorImageRepository;

    @Autowired
    private VendorImageMapper vendorImageMapper;

    @Autowired
    private VendorImageService vendorImageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVendorImageMockMvc;

    private VendorImage vendorImage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VendorImageResource vendorImageResource = new VendorImageResource(vendorImageService);
        this.restVendorImageMockMvc = MockMvcBuilders.standaloneSetup(vendorImageResource)
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
    public static VendorImage createEntity(EntityManager em) {
        VendorImage vendorImage = new VendorImage()
            .vendorImageName(DEFAULT_VENDOR_IMAGE_NAME)
            .vendorImageDesc(DEFAULT_VENDOR_IMAGE_DESC)
            .vendorImageSize(DEFAULT_VENDOR_IMAGE_SIZE)
            .vendorImage(DEFAULT_VENDOR_IMAGE)
            .vendorImageContentType(DEFAULT_VENDOR_IMAGE_CONTENT_TYPE)
            .vendorImageType(DEFAULT_VENDOR_IMAGE_TYPE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return vendorImage;
    }

    @Before
    public void initTest() {
        vendorImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createVendorImage() throws Exception {
        int databaseSizeBeforeCreate = vendorImageRepository.findAll().size();

        // Create the VendorImage
        VendorImageDTO vendorImageDTO = vendorImageMapper.toDto(vendorImage);
        restVendorImageMockMvc.perform(post("/api/vendor-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorImageDTO)))
            .andExpect(status().isCreated());

        // Validate the VendorImage in the database
        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeCreate + 1);
        VendorImage testVendorImage = vendorImageList.get(vendorImageList.size() - 1);
        assertThat(testVendorImage.getVendorImageName()).isEqualTo(DEFAULT_VENDOR_IMAGE_NAME);
        assertThat(testVendorImage.getVendorImageDesc()).isEqualTo(DEFAULT_VENDOR_IMAGE_DESC);
        assertThat(testVendorImage.getVendorImageSize()).isEqualTo(DEFAULT_VENDOR_IMAGE_SIZE);
        assertThat(testVendorImage.getVendorImage()).isEqualTo(DEFAULT_VENDOR_IMAGE);
        assertThat(testVendorImage.getVendorImageContentType()).isEqualTo(DEFAULT_VENDOR_IMAGE_CONTENT_TYPE);
        assertThat(testVendorImage.getVendorImageType()).isEqualTo(DEFAULT_VENDOR_IMAGE_TYPE);
        assertThat(testVendorImage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVendorImage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testVendorImage.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testVendorImage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testVendorImage.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createVendorImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vendorImageRepository.findAll().size();

        // Create the VendorImage with an existing ID
        vendorImage.setId(1L);
        VendorImageDTO vendorImageDTO = vendorImageMapper.toDto(vendorImage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVendorImageMockMvc.perform(post("/api/vendor-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VendorImage in the database
        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkVendorImageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorImageRepository.findAll().size();
        // set the field null
        vendorImage.setVendorImageName(null);

        // Create the VendorImage, which fails.
        VendorImageDTO vendorImageDTO = vendorImageMapper.toDto(vendorImage);

        restVendorImageMockMvc.perform(post("/api/vendor-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorImageDTO)))
            .andExpect(status().isBadRequest());

        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVendorImageSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorImageRepository.findAll().size();
        // set the field null
        vendorImage.setVendorImageSize(null);

        // Create the VendorImage, which fails.
        VendorImageDTO vendorImageDTO = vendorImageMapper.toDto(vendorImage);

        restVendorImageMockMvc.perform(post("/api/vendor-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorImageDTO)))
            .andExpect(status().isBadRequest());

        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorImageRepository.findAll().size();
        // set the field null
        vendorImage.setStatus(null);

        // Create the VendorImage, which fails.
        VendorImageDTO vendorImageDTO = vendorImageMapper.toDto(vendorImage);

        restVendorImageMockMvc.perform(post("/api/vendor-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorImageDTO)))
            .andExpect(status().isBadRequest());

        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorImageRepository.findAll().size();
        // set the field null
        vendorImage.setCreatedDate(null);

        // Create the VendorImage, which fails.
        VendorImageDTO vendorImageDTO = vendorImageMapper.toDto(vendorImage);

        restVendorImageMockMvc.perform(post("/api/vendor-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorImageDTO)))
            .andExpect(status().isBadRequest());

        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorImageRepository.findAll().size();
        // set the field null
        vendorImage.setCreatedBy(null);

        // Create the VendorImage, which fails.
        VendorImageDTO vendorImageDTO = vendorImageMapper.toDto(vendorImage);

        restVendorImageMockMvc.perform(post("/api/vendor-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorImageDTO)))
            .andExpect(status().isBadRequest());

        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVendorImages() throws Exception {
        // Initialize the database
        vendorImageRepository.saveAndFlush(vendorImage);

        // Get all the vendorImageList
        restVendorImageMockMvc.perform(get("/api/vendor-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vendorImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].vendorImageName").value(hasItem(DEFAULT_VENDOR_IMAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].vendorImageDesc").value(hasItem(DEFAULT_VENDOR_IMAGE_DESC.toString())))
            .andExpect(jsonPath("$.[*].vendorImageSize").value(hasItem(DEFAULT_VENDOR_IMAGE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].vendorImageContentType").value(hasItem(DEFAULT_VENDOR_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].vendorImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_VENDOR_IMAGE))))
            .andExpect(jsonPath("$.[*].vendorImageType").value(hasItem(DEFAULT_VENDOR_IMAGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getVendorImage() throws Exception {
        // Initialize the database
        vendorImageRepository.saveAndFlush(vendorImage);

        // Get the vendorImage
        restVendorImageMockMvc.perform(get("/api/vendor-images/{id}", vendorImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vendorImage.getId().intValue()))
            .andExpect(jsonPath("$.vendorImageName").value(DEFAULT_VENDOR_IMAGE_NAME.toString()))
            .andExpect(jsonPath("$.vendorImageDesc").value(DEFAULT_VENDOR_IMAGE_DESC.toString()))
            .andExpect(jsonPath("$.vendorImageSize").value(DEFAULT_VENDOR_IMAGE_SIZE.intValue()))
            .andExpect(jsonPath("$.vendorImageContentType").value(DEFAULT_VENDOR_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.vendorImage").value(Base64Utils.encodeToString(DEFAULT_VENDOR_IMAGE)))
            .andExpect(jsonPath("$.vendorImageType").value(DEFAULT_VENDOR_IMAGE_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVendorImage() throws Exception {
        // Get the vendorImage
        restVendorImageMockMvc.perform(get("/api/vendor-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVendorImage() throws Exception {
        // Initialize the database
        vendorImageRepository.saveAndFlush(vendorImage);
        int databaseSizeBeforeUpdate = vendorImageRepository.findAll().size();

        // Update the vendorImage
        VendorImage updatedVendorImage = vendorImageRepository.findOne(vendorImage.getId());
        // Disconnect from session so that the updates on updatedVendorImage are not directly saved in db
        em.detach(updatedVendorImage);
        updatedVendorImage
            .vendorImageName(UPDATED_VENDOR_IMAGE_NAME)
            .vendorImageDesc(UPDATED_VENDOR_IMAGE_DESC)
            .vendorImageSize(UPDATED_VENDOR_IMAGE_SIZE)
            .vendorImage(UPDATED_VENDOR_IMAGE)
            .vendorImageContentType(UPDATED_VENDOR_IMAGE_CONTENT_TYPE)
            .vendorImageType(UPDATED_VENDOR_IMAGE_TYPE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        VendorImageDTO vendorImageDTO = vendorImageMapper.toDto(updatedVendorImage);

        restVendorImageMockMvc.perform(put("/api/vendor-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorImageDTO)))
            .andExpect(status().isOk());

        // Validate the VendorImage in the database
        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeUpdate);
        VendorImage testVendorImage = vendorImageList.get(vendorImageList.size() - 1);
        assertThat(testVendorImage.getVendorImageName()).isEqualTo(UPDATED_VENDOR_IMAGE_NAME);
        assertThat(testVendorImage.getVendorImageDesc()).isEqualTo(UPDATED_VENDOR_IMAGE_DESC);
        assertThat(testVendorImage.getVendorImageSize()).isEqualTo(UPDATED_VENDOR_IMAGE_SIZE);
        assertThat(testVendorImage.getVendorImage()).isEqualTo(UPDATED_VENDOR_IMAGE);
        assertThat(testVendorImage.getVendorImageContentType()).isEqualTo(UPDATED_VENDOR_IMAGE_CONTENT_TYPE);
        assertThat(testVendorImage.getVendorImageType()).isEqualTo(UPDATED_VENDOR_IMAGE_TYPE);
        assertThat(testVendorImage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVendorImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testVendorImage.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testVendorImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testVendorImage.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingVendorImage() throws Exception {
        int databaseSizeBeforeUpdate = vendorImageRepository.findAll().size();

        // Create the VendorImage
        VendorImageDTO vendorImageDTO = vendorImageMapper.toDto(vendorImage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVendorImageMockMvc.perform(put("/api/vendor-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorImageDTO)))
            .andExpect(status().isCreated());

        // Validate the VendorImage in the database
        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVendorImage() throws Exception {
        // Initialize the database
        vendorImageRepository.saveAndFlush(vendorImage);
        int databaseSizeBeforeDelete = vendorImageRepository.findAll().size();

        // Get the vendorImage
        restVendorImageMockMvc.perform(delete("/api/vendor-images/{id}", vendorImage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VendorImage> vendorImageList = vendorImageRepository.findAll();
        assertThat(vendorImageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VendorImage.class);
        VendorImage vendorImage1 = new VendorImage();
        vendorImage1.setId(1L);
        VendorImage vendorImage2 = new VendorImage();
        vendorImage2.setId(vendorImage1.getId());
        assertThat(vendorImage1).isEqualTo(vendorImage2);
        vendorImage2.setId(2L);
        assertThat(vendorImage1).isNotEqualTo(vendorImage2);
        vendorImage1.setId(null);
        assertThat(vendorImage1).isNotEqualTo(vendorImage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VendorImageDTO.class);
        VendorImageDTO vendorImageDTO1 = new VendorImageDTO();
        vendorImageDTO1.setId(1L);
        VendorImageDTO vendorImageDTO2 = new VendorImageDTO();
        assertThat(vendorImageDTO1).isNotEqualTo(vendorImageDTO2);
        vendorImageDTO2.setId(vendorImageDTO1.getId());
        assertThat(vendorImageDTO1).isEqualTo(vendorImageDTO2);
        vendorImageDTO2.setId(2L);
        assertThat(vendorImageDTO1).isNotEqualTo(vendorImageDTO2);
        vendorImageDTO1.setId(null);
        assertThat(vendorImageDTO1).isNotEqualTo(vendorImageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vendorImageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vendorImageMapper.fromId(null)).isNull();
    }
}
