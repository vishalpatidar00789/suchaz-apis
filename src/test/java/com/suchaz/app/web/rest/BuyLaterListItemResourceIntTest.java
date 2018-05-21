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
import com.suchaz.app.domain.BuyLaterListItem;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.BuyLaterListItemRepository;
import com.suchaz.app.service.BuyLaterListItemService;
import com.suchaz.app.service.dto.BuyLaterListItemDTO;
import com.suchaz.app.service.mapper.BuyLaterListItemMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the BuyLaterListItemResource REST controller.
 *
 * @see BuyLaterListItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class BuyLaterListItemResourceIntTest {

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
    private BuyLaterListItemRepository buyLaterListItemRepository;

    @Autowired
    private BuyLaterListItemMapper buyLaterListItemMapper;

    @Autowired
    private BuyLaterListItemService buyLaterListItemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBuyLaterListItemMockMvc;

    private BuyLaterListItem buyLaterListItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BuyLaterListItemResource buyLaterListItemResource = new BuyLaterListItemResource(buyLaterListItemService);
        this.restBuyLaterListItemMockMvc = MockMvcBuilders.standaloneSetup(buyLaterListItemResource)
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
    public static BuyLaterListItem createEntity(EntityManager em) {
        BuyLaterListItem buyLaterListItem = new BuyLaterListItem()
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return buyLaterListItem;
    }

    @Before
    public void initTest() {
        buyLaterListItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createBuyLaterListItem() throws Exception {
        int databaseSizeBeforeCreate = buyLaterListItemRepository.findAll().size();

        // Create the BuyLaterListItem
        BuyLaterListItemDTO buyLaterListItemDTO = buyLaterListItemMapper.toDto(buyLaterListItem);
        restBuyLaterListItemMockMvc.perform(post("/api/buy-later-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListItemDTO)))
            .andExpect(status().isCreated());

        // Validate the BuyLaterListItem in the database
        List<BuyLaterListItem> buyLaterListItemList = buyLaterListItemRepository.findAll();
        assertThat(buyLaterListItemList).hasSize(databaseSizeBeforeCreate + 1);
        BuyLaterListItem testBuyLaterListItem = buyLaterListItemList.get(buyLaterListItemList.size() - 1);
        assertThat(testBuyLaterListItem.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBuyLaterListItem.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBuyLaterListItem.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testBuyLaterListItem.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBuyLaterListItem.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createBuyLaterListItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buyLaterListItemRepository.findAll().size();

        // Create the BuyLaterListItem with an existing ID
        buyLaterListItem.setId(1L);
        BuyLaterListItemDTO buyLaterListItemDTO = buyLaterListItemMapper.toDto(buyLaterListItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuyLaterListItemMockMvc.perform(post("/api/buy-later-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuyLaterListItem in the database
        List<BuyLaterListItem> buyLaterListItemList = buyLaterListItemRepository.findAll();
        assertThat(buyLaterListItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = buyLaterListItemRepository.findAll().size();
        // set the field null
        buyLaterListItem.setStatus(null);

        // Create the BuyLaterListItem, which fails.
        BuyLaterListItemDTO buyLaterListItemDTO = buyLaterListItemMapper.toDto(buyLaterListItem);

        restBuyLaterListItemMockMvc.perform(post("/api/buy-later-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListItemDTO)))
            .andExpect(status().isBadRequest());

        List<BuyLaterListItem> buyLaterListItemList = buyLaterListItemRepository.findAll();
        assertThat(buyLaterListItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = buyLaterListItemRepository.findAll().size();
        // set the field null
        buyLaterListItem.setCreatedDate(null);

        // Create the BuyLaterListItem, which fails.
        BuyLaterListItemDTO buyLaterListItemDTO = buyLaterListItemMapper.toDto(buyLaterListItem);

        restBuyLaterListItemMockMvc.perform(post("/api/buy-later-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListItemDTO)))
            .andExpect(status().isBadRequest());

        List<BuyLaterListItem> buyLaterListItemList = buyLaterListItemRepository.findAll();
        assertThat(buyLaterListItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = buyLaterListItemRepository.findAll().size();
        // set the field null
        buyLaterListItem.setCreatedBy(null);

        // Create the BuyLaterListItem, which fails.
        BuyLaterListItemDTO buyLaterListItemDTO = buyLaterListItemMapper.toDto(buyLaterListItem);

        restBuyLaterListItemMockMvc.perform(post("/api/buy-later-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListItemDTO)))
            .andExpect(status().isBadRequest());

        List<BuyLaterListItem> buyLaterListItemList = buyLaterListItemRepository.findAll();
        assertThat(buyLaterListItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBuyLaterListItems() throws Exception {
        // Initialize the database
        buyLaterListItemRepository.saveAndFlush(buyLaterListItem);

        // Get all the buyLaterListItemList
        restBuyLaterListItemMockMvc.perform(get("/api/buy-later-list-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buyLaterListItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getBuyLaterListItem() throws Exception {
        // Initialize the database
        buyLaterListItemRepository.saveAndFlush(buyLaterListItem);

        // Get the buyLaterListItem
        restBuyLaterListItemMockMvc.perform(get("/api/buy-later-list-items/{id}", buyLaterListItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(buyLaterListItem.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBuyLaterListItem() throws Exception {
        // Get the buyLaterListItem
        restBuyLaterListItemMockMvc.perform(get("/api/buy-later-list-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBuyLaterListItem() throws Exception {
        // Initialize the database
        buyLaterListItemRepository.saveAndFlush(buyLaterListItem);
        int databaseSizeBeforeUpdate = buyLaterListItemRepository.findAll().size();

        // Update the buyLaterListItem
        BuyLaterListItem updatedBuyLaterListItem = buyLaterListItemRepository.findOne(buyLaterListItem.getId());
        // Disconnect from session so that the updates on updatedBuyLaterListItem are not directly saved in db
        em.detach(updatedBuyLaterListItem);
        updatedBuyLaterListItem
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        BuyLaterListItemDTO buyLaterListItemDTO = buyLaterListItemMapper.toDto(updatedBuyLaterListItem);

        restBuyLaterListItemMockMvc.perform(put("/api/buy-later-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListItemDTO)))
            .andExpect(status().isOk());

        // Validate the BuyLaterListItem in the database
        List<BuyLaterListItem> buyLaterListItemList = buyLaterListItemRepository.findAll();
        assertThat(buyLaterListItemList).hasSize(databaseSizeBeforeUpdate);
        BuyLaterListItem testBuyLaterListItem = buyLaterListItemList.get(buyLaterListItemList.size() - 1);
        assertThat(testBuyLaterListItem.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBuyLaterListItem.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBuyLaterListItem.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testBuyLaterListItem.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBuyLaterListItem.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingBuyLaterListItem() throws Exception {
        int databaseSizeBeforeUpdate = buyLaterListItemRepository.findAll().size();

        // Create the BuyLaterListItem
        BuyLaterListItemDTO buyLaterListItemDTO = buyLaterListItemMapper.toDto(buyLaterListItem);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBuyLaterListItemMockMvc.perform(put("/api/buy-later-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buyLaterListItemDTO)))
            .andExpect(status().isCreated());

        // Validate the BuyLaterListItem in the database
        List<BuyLaterListItem> buyLaterListItemList = buyLaterListItemRepository.findAll();
        assertThat(buyLaterListItemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBuyLaterListItem() throws Exception {
        // Initialize the database
        buyLaterListItemRepository.saveAndFlush(buyLaterListItem);
        int databaseSizeBeforeDelete = buyLaterListItemRepository.findAll().size();

        // Get the buyLaterListItem
        restBuyLaterListItemMockMvc.perform(delete("/api/buy-later-list-items/{id}", buyLaterListItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BuyLaterListItem> buyLaterListItemList = buyLaterListItemRepository.findAll();
        assertThat(buyLaterListItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuyLaterListItem.class);
        BuyLaterListItem buyLaterListItem1 = new BuyLaterListItem();
        buyLaterListItem1.setId(1L);
        BuyLaterListItem buyLaterListItem2 = new BuyLaterListItem();
        buyLaterListItem2.setId(buyLaterListItem1.getId());
        assertThat(buyLaterListItem1).isEqualTo(buyLaterListItem2);
        buyLaterListItem2.setId(2L);
        assertThat(buyLaterListItem1).isNotEqualTo(buyLaterListItem2);
        buyLaterListItem1.setId(null);
        assertThat(buyLaterListItem1).isNotEqualTo(buyLaterListItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuyLaterListItemDTO.class);
        BuyLaterListItemDTO buyLaterListItemDTO1 = new BuyLaterListItemDTO();
        buyLaterListItemDTO1.setId(1L);
        BuyLaterListItemDTO buyLaterListItemDTO2 = new BuyLaterListItemDTO();
        assertThat(buyLaterListItemDTO1).isNotEqualTo(buyLaterListItemDTO2);
        buyLaterListItemDTO2.setId(buyLaterListItemDTO1.getId());
        assertThat(buyLaterListItemDTO1).isEqualTo(buyLaterListItemDTO2);
        buyLaterListItemDTO2.setId(2L);
        assertThat(buyLaterListItemDTO1).isNotEqualTo(buyLaterListItemDTO2);
        buyLaterListItemDTO1.setId(null);
        assertThat(buyLaterListItemDTO1).isNotEqualTo(buyLaterListItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(buyLaterListItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(buyLaterListItemMapper.fromId(null)).isNull();
    }
}
