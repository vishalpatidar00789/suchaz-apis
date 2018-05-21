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

import com.suchaz.app.SuchazapisApp;
import com.suchaz.app.domain.Vendor;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.VendorRepository;
import com.suchaz.app.service.VendorService;
import com.suchaz.app.service.dto.VendorDTO;
import com.suchaz.app.service.mapper.VendorMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the VendorResource REST controller.
 *
 * @see VendorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class VendorResourceIntTest {

    private static final String DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_RATE_LIMIT = 1L;
    private static final Long UPDATED_RATE_LIMIT = 2L;

    private static final Long DEFAULT_START_DATE = 1L;
    private static final Long UPDATED_START_DATE = 2L;

    private static final Long DEFAULT_END_DATE = 1L;
    private static final Long UPDATED_END_DATE = 2L;

    private static final String DEFAULT_ACCESS_KEY = "AAAAAAAAAA";
    private static final String UPDATED_ACCESS_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_SECRET_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SECRET_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_ASSOCIATE_KEY = "AAAAAAAAAA";
    private static final String UPDATED_ASSOCIATE_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_AFFILITE_ID = "AAAAAAAAAA";
    private static final String UPDATED_AFFILITE_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_ACCESS_KEY_EXP_DATE = 1L;
    private static final Long UPDATED_ACCESS_KEY_EXP_DATE = 2L;

    private static final Long DEFAULT_SCRET_KEY_EXP_DATE = 1L;
    private static final Long UPDATED_SCRET_KEY_EXP_DATE = 2L;

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
    private VendorRepository vendorRepository;

    @Autowired
    private VendorMapper vendorMapper;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVendorMockMvc;

    private Vendor vendor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VendorResource vendorResource = new VendorResource(vendorService);
        this.restVendorMockMvc = MockMvcBuilders.standaloneSetup(vendorResource)
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
    public static Vendor createEntity(EntityManager em) {
        Vendor vendor = new Vendor()
            .vendorName(DEFAULT_VENDOR_NAME)
            .rateLimit(DEFAULT_RATE_LIMIT)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .accessKey(DEFAULT_ACCESS_KEY)
            .secretKey(DEFAULT_SECRET_KEY)
            .associateKey(DEFAULT_ASSOCIATE_KEY)
            .affiliteId(DEFAULT_AFFILITE_ID)
            .accessKeyExpDate(DEFAULT_ACCESS_KEY_EXP_DATE)
            .scretKeyExpDate(DEFAULT_SCRET_KEY_EXP_DATE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return vendor;
    }

    @Before
    public void initTest() {
        vendor = createEntity(em);
    }

    @Test
    @Transactional
    public void createVendor() throws Exception {
        int databaseSizeBeforeCreate = vendorRepository.findAll().size();

        // Create the Vendor
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);
        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isCreated());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeCreate + 1);
        Vendor testVendor = vendorList.get(vendorList.size() - 1);
        assertThat(testVendor.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
        assertThat(testVendor.getRateLimit()).isEqualTo(DEFAULT_RATE_LIMIT);
        assertThat(testVendor.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testVendor.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testVendor.getAccessKey()).isEqualTo(DEFAULT_ACCESS_KEY);
        assertThat(testVendor.getSecretKey()).isEqualTo(DEFAULT_SECRET_KEY);
        assertThat(testVendor.getAssociateKey()).isEqualTo(DEFAULT_ASSOCIATE_KEY);
        assertThat(testVendor.getAffiliteId()).isEqualTo(DEFAULT_AFFILITE_ID);
        assertThat(testVendor.getAccessKeyExpDate()).isEqualTo(DEFAULT_ACCESS_KEY_EXP_DATE);
        assertThat(testVendor.getScretKeyExpDate()).isEqualTo(DEFAULT_SCRET_KEY_EXP_DATE);
        assertThat(testVendor.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVendor.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testVendor.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testVendor.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testVendor.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createVendorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vendorRepository.findAll().size();

        // Create the Vendor with an existing ID
        vendor.setId(1L);
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkVendorNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setVendorName(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setStatus(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setCreatedDate(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = vendorRepository.findAll().size();
        // set the field null
        vendor.setCreatedBy(null);

        // Create the Vendor, which fails.
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isBadRequest());

        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVendors() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Get all the vendorList
        restVendorMockMvc.perform(get("/api/vendors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vendor.getId().intValue())))
            .andExpect(jsonPath("$.[*].vendorName").value(hasItem(DEFAULT_VENDOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].rateLimit").value(hasItem(DEFAULT_RATE_LIMIT.intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.intValue())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.intValue())))
            .andExpect(jsonPath("$.[*].accessKey").value(hasItem(DEFAULT_ACCESS_KEY.toString())))
            .andExpect(jsonPath("$.[*].secretKey").value(hasItem(DEFAULT_SECRET_KEY.toString())))
            .andExpect(jsonPath("$.[*].associateKey").value(hasItem(DEFAULT_ASSOCIATE_KEY.toString())))
            .andExpect(jsonPath("$.[*].affiliteId").value(hasItem(DEFAULT_AFFILITE_ID.toString())))
            .andExpect(jsonPath("$.[*].accessKeyExpDate").value(hasItem(DEFAULT_ACCESS_KEY_EXP_DATE.intValue())))
            .andExpect(jsonPath("$.[*].scretKeyExpDate").value(hasItem(DEFAULT_SCRET_KEY_EXP_DATE.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Get the vendor
        restVendorMockMvc.perform(get("/api/vendors/{id}", vendor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vendor.getId().intValue()))
            .andExpect(jsonPath("$.vendorName").value(DEFAULT_VENDOR_NAME.toString()))
            .andExpect(jsonPath("$.rateLimit").value(DEFAULT_RATE_LIMIT.intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.intValue()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.intValue()))
            .andExpect(jsonPath("$.accessKey").value(DEFAULT_ACCESS_KEY.toString()))
            .andExpect(jsonPath("$.secretKey").value(DEFAULT_SECRET_KEY.toString()))
            .andExpect(jsonPath("$.associateKey").value(DEFAULT_ASSOCIATE_KEY.toString()))
            .andExpect(jsonPath("$.affiliteId").value(DEFAULT_AFFILITE_ID.toString()))
            .andExpect(jsonPath("$.accessKeyExpDate").value(DEFAULT_ACCESS_KEY_EXP_DATE.intValue()))
            .andExpect(jsonPath("$.scretKeyExpDate").value(DEFAULT_SCRET_KEY_EXP_DATE.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVendor() throws Exception {
        // Get the vendor
        restVendorMockMvc.perform(get("/api/vendors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);
        int databaseSizeBeforeUpdate = vendorRepository.findAll().size();

        // Update the vendor
        Vendor updatedVendor = vendorRepository.findOne(vendor.getId());
        // Disconnect from session so that the updates on updatedVendor are not directly saved in db
        em.detach(updatedVendor);
        updatedVendor
            .vendorName(UPDATED_VENDOR_NAME)
            .rateLimit(UPDATED_RATE_LIMIT)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .accessKey(UPDATED_ACCESS_KEY)
            .secretKey(UPDATED_SECRET_KEY)
            .associateKey(UPDATED_ASSOCIATE_KEY)
            .affiliteId(UPDATED_AFFILITE_ID)
            .accessKeyExpDate(UPDATED_ACCESS_KEY_EXP_DATE)
            .scretKeyExpDate(UPDATED_SCRET_KEY_EXP_DATE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        VendorDTO vendorDTO = vendorMapper.toDto(updatedVendor);

        restVendorMockMvc.perform(put("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isOk());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeUpdate);
        Vendor testVendor = vendorList.get(vendorList.size() - 1);
        assertThat(testVendor.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
        assertThat(testVendor.getRateLimit()).isEqualTo(UPDATED_RATE_LIMIT);
        assertThat(testVendor.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testVendor.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testVendor.getAccessKey()).isEqualTo(UPDATED_ACCESS_KEY);
        assertThat(testVendor.getSecretKey()).isEqualTo(UPDATED_SECRET_KEY);
        assertThat(testVendor.getAssociateKey()).isEqualTo(UPDATED_ASSOCIATE_KEY);
        assertThat(testVendor.getAffiliteId()).isEqualTo(UPDATED_AFFILITE_ID);
        assertThat(testVendor.getAccessKeyExpDate()).isEqualTo(UPDATED_ACCESS_KEY_EXP_DATE);
        assertThat(testVendor.getScretKeyExpDate()).isEqualTo(UPDATED_SCRET_KEY_EXP_DATE);
        assertThat(testVendor.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVendor.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testVendor.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testVendor.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testVendor.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingVendor() throws Exception {
        int databaseSizeBeforeUpdate = vendorRepository.findAll().size();

        // Create the Vendor
        VendorDTO vendorDTO = vendorMapper.toDto(vendor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVendorMockMvc.perform(put("/api/vendors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vendorDTO)))
            .andExpect(status().isCreated());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);
        int databaseSizeBeforeDelete = vendorRepository.findAll().size();

        // Get the vendor
        restVendorMockMvc.perform(delete("/api/vendors/{id}", vendor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vendor.class);
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        Vendor vendor2 = new Vendor();
        vendor2.setId(vendor1.getId());
        assertThat(vendor1).isEqualTo(vendor2);
        vendor2.setId(2L);
        assertThat(vendor1).isNotEqualTo(vendor2);
        vendor1.setId(null);
        assertThat(vendor1).isNotEqualTo(vendor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VendorDTO.class);
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setId(1L);
        VendorDTO vendorDTO2 = new VendorDTO();
        assertThat(vendorDTO1).isNotEqualTo(vendorDTO2);
        vendorDTO2.setId(vendorDTO1.getId());
        assertThat(vendorDTO1).isEqualTo(vendorDTO2);
        vendorDTO2.setId(2L);
        assertThat(vendorDTO1).isNotEqualTo(vendorDTO2);
        vendorDTO1.setId(null);
        assertThat(vendorDTO1).isNotEqualTo(vendorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vendorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vendorMapper.fromId(null)).isNull();
    }
}
