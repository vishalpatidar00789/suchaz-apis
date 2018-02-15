package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.BuyLaterList;
import com.suchaz.app.repository.BuyLaterListRepository;
import com.suchaz.app.service.BuyLaterListService;
import com.suchaz.app.service.dto.BuyLaterListDTO;
import com.suchaz.app.service.mapper.BuyLaterListMapper;
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

import javax.persistence.EntityManager;
import java.util.List;

import static com.suchaz.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.suchaz.app.domain.enumeration.Status;
/**
 * Test class for the BuyLaterListResource REST controller.
 *
 * @see BuyLaterListResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class BuyLaterListResourceIntTest {

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
    private BuyLaterListRepository buyLaterListRepository;

    @Autowired
    private BuyLaterListMapper buyLaterListMapper;

    @Autowired
    private BuyLaterListService buyLaterListService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBuyLaterListMockMvc;

    private BuyLaterList buyLaterList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BuyLaterListResource buyLaterListResource = new BuyLaterListResource(buyLaterListService);
        this.restBuyLaterListMockMvc = MockMvcBuilders.standaloneSetup(buyLaterListResource)
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
    public static BuyLaterList createEntity(EntityManager em) {
        BuyLaterList buyLaterList = new BuyLaterList()
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return buyLaterList;
    }

    @Before
    public void initTest() {
        buyLaterList = createEntity(em);
    }

    @Test
    @Transactional
    public void createBuyLaterList() throws Exception {
        int databaseSizeBeforeCreate = buyLaterListRepository.findAll().size();

        // Create the BuyLaterList
        BuyLaterListDTO buyLaterListDTO = buyLaterListMapper.toDto(buyLaterList);
        restBuyLaterListMockMvc.perform(post("/api/buy-later-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListDTO)))
            .andExpect(status().isCreated());

        // Validate the BuyLaterList in the database
        List<BuyLaterList> buyLaterListList = buyLaterListRepository.findAll();
        assertThat(buyLaterListList).hasSize(databaseSizeBeforeCreate + 1);
        BuyLaterList testBuyLaterList = buyLaterListList.get(buyLaterListList.size() - 1);
        assertThat(testBuyLaterList.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBuyLaterList.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBuyLaterList.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testBuyLaterList.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBuyLaterList.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createBuyLaterListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buyLaterListRepository.findAll().size();

        // Create the BuyLaterList with an existing ID
        buyLaterList.setId(1L);
        BuyLaterListDTO buyLaterListDTO = buyLaterListMapper.toDto(buyLaterList);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuyLaterListMockMvc.perform(post("/api/buy-later-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuyLaterList in the database
        List<BuyLaterList> buyLaterListList = buyLaterListRepository.findAll();
        assertThat(buyLaterListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = buyLaterListRepository.findAll().size();
        // set the field null
        buyLaterList.setStatus(null);

        // Create the BuyLaterList, which fails.
        BuyLaterListDTO buyLaterListDTO = buyLaterListMapper.toDto(buyLaterList);

        restBuyLaterListMockMvc.perform(post("/api/buy-later-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListDTO)))
            .andExpect(status().isBadRequest());

        List<BuyLaterList> buyLaterListList = buyLaterListRepository.findAll();
        assertThat(buyLaterListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = buyLaterListRepository.findAll().size();
        // set the field null
        buyLaterList.setCreatedDate(null);

        // Create the BuyLaterList, which fails.
        BuyLaterListDTO buyLaterListDTO = buyLaterListMapper.toDto(buyLaterList);

        restBuyLaterListMockMvc.perform(post("/api/buy-later-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListDTO)))
            .andExpect(status().isBadRequest());

        List<BuyLaterList> buyLaterListList = buyLaterListRepository.findAll();
        assertThat(buyLaterListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = buyLaterListRepository.findAll().size();
        // set the field null
        buyLaterList.setCreatedBy(null);

        // Create the BuyLaterList, which fails.
        BuyLaterListDTO buyLaterListDTO = buyLaterListMapper.toDto(buyLaterList);

        restBuyLaterListMockMvc.perform(post("/api/buy-later-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListDTO)))
            .andExpect(status().isBadRequest());

        List<BuyLaterList> buyLaterListList = buyLaterListRepository.findAll();
        assertThat(buyLaterListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBuyLaterLists() throws Exception {
        // Initialize the database
        buyLaterListRepository.saveAndFlush(buyLaterList);

        // Get all the buyLaterListList
        restBuyLaterListMockMvc.perform(get("/api/buy-later-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buyLaterList.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getBuyLaterList() throws Exception {
        // Initialize the database
        buyLaterListRepository.saveAndFlush(buyLaterList);

        // Get the buyLaterList
        restBuyLaterListMockMvc.perform(get("/api/buy-later-lists/{id}", buyLaterList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(buyLaterList.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBuyLaterList() throws Exception {
        // Get the buyLaterList
        restBuyLaterListMockMvc.perform(get("/api/buy-later-lists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBuyLaterList() throws Exception {
        // Initialize the database
        buyLaterListRepository.saveAndFlush(buyLaterList);
        int databaseSizeBeforeUpdate = buyLaterListRepository.findAll().size();

        // Update the buyLaterList
        BuyLaterList updatedBuyLaterList = buyLaterListRepository.findOne(buyLaterList.getId());
        // Disconnect from session so that the updates on updatedBuyLaterList are not directly saved in db
        em.detach(updatedBuyLaterList);
        updatedBuyLaterList
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        BuyLaterListDTO buyLaterListDTO = buyLaterListMapper.toDto(updatedBuyLaterList);

        restBuyLaterListMockMvc.perform(put("/api/buy-later-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListDTO)))
            .andExpect(status().isOk());

        // Validate the BuyLaterList in the database
        List<BuyLaterList> buyLaterListList = buyLaterListRepository.findAll();
        assertThat(buyLaterListList).hasSize(databaseSizeBeforeUpdate);
        BuyLaterList testBuyLaterList = buyLaterListList.get(buyLaterListList.size() - 1);
        assertThat(testBuyLaterList.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBuyLaterList.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBuyLaterList.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testBuyLaterList.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBuyLaterList.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingBuyLaterList() throws Exception {
        int databaseSizeBeforeUpdate = buyLaterListRepository.findAll().size();

        // Create the BuyLaterList
        BuyLaterListDTO buyLaterListDTO = buyLaterListMapper.toDto(buyLaterList);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBuyLaterListMockMvc.perform(put("/api/buy-later-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListDTO)))
            .andExpect(status().isCreated());

        // Validate the BuyLaterList in the database
        List<BuyLaterList> buyLaterListList = buyLaterListRepository.findAll();
        assertThat(buyLaterListList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBuyLaterList() throws Exception {
        // Initialize the database
        buyLaterListRepository.saveAndFlush(buyLaterList);
        int databaseSizeBeforeDelete = buyLaterListRepository.findAll().size();

        // Get the buyLaterList
        restBuyLaterListMockMvc.perform(delete("/api/buy-later-lists/{id}", buyLaterList.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BuyLaterList> buyLaterListList = buyLaterListRepository.findAll();
        assertThat(buyLaterListList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuyLaterList.class);
        BuyLaterList buyLaterList1 = new BuyLaterList();
        buyLaterList1.setId(1L);
        BuyLaterList buyLaterList2 = new BuyLaterList();
        buyLaterList2.setId(buyLaterList1.getId());
        assertThat(buyLaterList1).isEqualTo(buyLaterList2);
        buyLaterList2.setId(2L);
        assertThat(buyLaterList1).isNotEqualTo(buyLaterList2);
        buyLaterList1.setId(null);
        assertThat(buyLaterList1).isNotEqualTo(buyLaterList2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuyLaterListDTO.class);
        BuyLaterListDTO buyLaterListDTO1 = new BuyLaterListDTO();
        buyLaterListDTO1.setId(1L);
        BuyLaterListDTO buyLaterListDTO2 = new BuyLaterListDTO();
        assertThat(buyLaterListDTO1).isNotEqualTo(buyLaterListDTO2);
        buyLaterListDTO2.setId(buyLaterListDTO1.getId());
        assertThat(buyLaterListDTO1).isEqualTo(buyLaterListDTO2);
        buyLaterListDTO2.setId(2L);
        assertThat(buyLaterListDTO1).isNotEqualTo(buyLaterListDTO2);
        buyLaterListDTO1.setId(null);
        assertThat(buyLaterListDTO1).isNotEqualTo(buyLaterListDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(buyLaterListMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(buyLaterListMapper.fromId(null)).isNull();
    }
}
