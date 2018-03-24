package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.Item;
import com.suchaz.app.repository.ItemRepository;
import com.suchaz.app.service.ItemService;
import com.suchaz.app.service.dto.ItemDTO;
import com.suchaz.app.service.mapper.ItemMapper;
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
import com.suchaz.app.domain.enumeration.Status;
/**
 * Test class for the ItemResource REST controller.
 *
 * @see ItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class ItemResourceIntTest {

    private static final String DEFAULT_ITEM_ID = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_ITEM_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_ITEM_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_ITEM_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_BEST_PRICE = 1D;
    private static final Double UPDATED_BEST_PRICE = 2D;

    private static final Double DEFAULT_SELLING_PRICE = 1D;
    private static final Double UPDATED_SELLING_PRICE = 2D;

    private static final String DEFAULT_CUSTOMER_AVG_RATING = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_AVG_RATING = "BBBBBBBBBB";

    private static final String DEFAULT_SUCHAZ_AVG_RATING = "AAAAAAAAAA";
    private static final String UPDATED_SUCHAZ_AVG_RATING = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.INACTIVE;

    private static final String DEFAULT_ITEM_URL = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_URL = "BBBBBBBBBB";

    private static final String DEFAULT_BRAND = "AAAAAAAAAA";
    private static final String UPDATED_BRAND = "BBBBBBBBBB";

    private static final String DEFAULT_COLORS = "AAAAAAAAAA";
    private static final String UPDATED_COLORS = "BBBBBBBBBB";

    private static final Status DEFAULT_AVAILIBITY = Status.ACTIVE;
    private static final Status UPDATED_AVAILIBITY = Status.INACTIVE;

    private static final Long DEFAULT_LAST_REFRESHED = 1L;
    private static final Long UPDATED_LAST_REFRESHED = 2L;

    private static final String DEFAULT_SEARCH_KEYWORDS = "AAAAAAAAAA";
    private static final String UPDATED_SEARCH_KEYWORDS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_DATE = 1L;
    private static final Long UPDATED_CREATED_DATE = 2L;

    private static final Long DEFAULT_LAST_UPDATED_DATE = 1L;
    private static final Long UPDATED_LAST_UPDATED_DATE = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_FEATURED = false;
    private static final Boolean UPDATED_IS_FEATURED = true;

    private static final Long DEFAULT_LAST_FEATURED_UPD_DATE = 1L;
    private static final Long UPDATED_LAST_FEATURED_UPD_DATE = 2L;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemMockMvc;

    private Item item;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemResource itemResource = new ItemResource(itemService);
        this.restItemMockMvc = MockMvcBuilders.standaloneSetup(itemResource)
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
    public static Item createEntity(EntityManager em) {
        Item item = new Item()
            .itemId(DEFAULT_ITEM_ID)
            .title(DEFAULT_TITLE)
            .vendorItemType(DEFAULT_VENDOR_ITEM_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .vendorItemCode(DEFAULT_VENDOR_ITEM_CODE)
            .bestPrice(DEFAULT_BEST_PRICE)
            .sellingPrice(DEFAULT_SELLING_PRICE)
            .customerAvgRating(DEFAULT_CUSTOMER_AVG_RATING)
            .suchazAvgRating(DEFAULT_SUCHAZ_AVG_RATING)
            .status(DEFAULT_STATUS)
            .itemURL(DEFAULT_ITEM_URL)
            .brand(DEFAULT_BRAND)
            .colors(DEFAULT_COLORS)
            .availibity(DEFAULT_AVAILIBITY)
            .lastRefreshed(DEFAULT_LAST_REFRESHED)
            .searchKeywords(DEFAULT_SEARCH_KEYWORDS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .isFeatured(DEFAULT_IS_FEATURED)
            .lastFeaturedUPDDate(DEFAULT_LAST_FEATURED_UPD_DATE);
        return item;
    }

    @Before
    public void initTest() {
        item = createEntity(em);
    }

    @Test
    @Transactional
    public void createItem() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create the Item
        ItemDTO itemDTO = itemMapper.toDto(item);
        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isCreated());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate + 1);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testItem.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testItem.getVendorItemType()).isEqualTo(DEFAULT_VENDOR_ITEM_TYPE);
        assertThat(testItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItem.getVendorItemCode()).isEqualTo(DEFAULT_VENDOR_ITEM_CODE);
        assertThat(testItem.getBestPrice()).isEqualTo(DEFAULT_BEST_PRICE);
        assertThat(testItem.getSellingPrice()).isEqualTo(DEFAULT_SELLING_PRICE);
        assertThat(testItem.getCustomerAvgRating()).isEqualTo(DEFAULT_CUSTOMER_AVG_RATING);
        assertThat(testItem.getSuchazAvgRating()).isEqualTo(DEFAULT_SUCHAZ_AVG_RATING);
        assertThat(testItem.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItem.getItemURL()).isEqualTo(DEFAULT_ITEM_URL);
        assertThat(testItem.getBrand()).isEqualTo(DEFAULT_BRAND);
        assertThat(testItem.getColors()).isEqualTo(DEFAULT_COLORS);
        assertThat(testItem.getAvailibity()).isEqualTo(DEFAULT_AVAILIBITY);
        assertThat(testItem.getLastRefreshed()).isEqualTo(DEFAULT_LAST_REFRESHED);
        assertThat(testItem.getSearchKeywords()).isEqualTo(DEFAULT_SEARCH_KEYWORDS);
        assertThat(testItem.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItem.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testItem.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testItem.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testItem.isIsFeatured()).isEqualTo(DEFAULT_IS_FEATURED);
        assertThat(testItem.getLastFeaturedUPDDate()).isEqualTo(DEFAULT_LAST_FEATURED_UPD_DATE);
    }

    @Test
    @Transactional
    public void createItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create the Item with an existing ID
        item.setId(1L);
        ItemDTO itemDTO = itemMapper.toDto(item);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setItemId(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setTitle(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVendorItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setVendorItemCode(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSellingPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setSellingPrice(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setStatus(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkItemURLIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setItemURL(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvailibityIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setAvailibity(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastRefreshedIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setLastRefreshed(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setCreatedDate(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemRepository.findAll().size();
        // set the field null
        item.setCreatedBy(null);

        // Create the Item, which fails.
        ItemDTO itemDTO = itemMapper.toDto(item);

        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isBadRequest());

        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItems() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get all the itemList
        restItemMockMvc.perform(get("/api/items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(item.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].vendorItemType").value(hasItem(DEFAULT_VENDOR_ITEM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].vendorItemCode").value(hasItem(DEFAULT_VENDOR_ITEM_CODE.toString())))
            .andExpect(jsonPath("$.[*].bestPrice").value(hasItem(DEFAULT_BEST_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].sellingPrice").value(hasItem(DEFAULT_SELLING_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].customerAvgRating").value(hasItem(DEFAULT_CUSTOMER_AVG_RATING.toString())))
            .andExpect(jsonPath("$.[*].suchazAvgRating").value(hasItem(DEFAULT_SUCHAZ_AVG_RATING.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].itemURL").value(hasItem(DEFAULT_ITEM_URL.toString())))
            .andExpect(jsonPath("$.[*].brand").value(hasItem(DEFAULT_BRAND.toString())))
            .andExpect(jsonPath("$.[*].colors").value(hasItem(DEFAULT_COLORS.toString())))
            .andExpect(jsonPath("$.[*].availibity").value(hasItem(DEFAULT_AVAILIBITY.toString())))
            .andExpect(jsonPath("$.[*].lastRefreshed").value(hasItem(DEFAULT_LAST_REFRESHED.intValue())))
            .andExpect(jsonPath("$.[*].searchKeywords").value(hasItem(DEFAULT_SEARCH_KEYWORDS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].isFeatured").value(hasItem(DEFAULT_IS_FEATURED.booleanValue())))
            .andExpect(jsonPath("$.[*].lastFeaturedUPDDate").value(hasItem(DEFAULT_LAST_FEATURED_UPD_DATE.intValue())));
    }

    @Test
    @Transactional
    public void getItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", item.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(item.getId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.vendorItemType").value(DEFAULT_VENDOR_ITEM_TYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.vendorItemCode").value(DEFAULT_VENDOR_ITEM_CODE.toString()))
            .andExpect(jsonPath("$.bestPrice").value(DEFAULT_BEST_PRICE.doubleValue()))
            .andExpect(jsonPath("$.sellingPrice").value(DEFAULT_SELLING_PRICE.doubleValue()))
            .andExpect(jsonPath("$.customerAvgRating").value(DEFAULT_CUSTOMER_AVG_RATING.toString()))
            .andExpect(jsonPath("$.suchazAvgRating").value(DEFAULT_SUCHAZ_AVG_RATING.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.itemURL").value(DEFAULT_ITEM_URL.toString()))
            .andExpect(jsonPath("$.brand").value(DEFAULT_BRAND.toString()))
            .andExpect(jsonPath("$.colors").value(DEFAULT_COLORS.toString()))
            .andExpect(jsonPath("$.availibity").value(DEFAULT_AVAILIBITY.toString()))
            .andExpect(jsonPath("$.lastRefreshed").value(DEFAULT_LAST_REFRESHED.intValue()))
            .andExpect(jsonPath("$.searchKeywords").value(DEFAULT_SEARCH_KEYWORDS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.isFeatured").value(DEFAULT_IS_FEATURED.booleanValue()))
            .andExpect(jsonPath("$.lastFeaturedUPDDate").value(DEFAULT_LAST_FEATURED_UPD_DATE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingItem() throws Exception {
        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Update the item
        Item updatedItem = itemRepository.findOne(item.getId());
        // Disconnect from session so that the updates on updatedItem are not directly saved in db
        em.detach(updatedItem);
        updatedItem
            .itemId(UPDATED_ITEM_ID)
            .title(UPDATED_TITLE)
            .vendorItemType(UPDATED_VENDOR_ITEM_TYPE)
            .description(UPDATED_DESCRIPTION)
            .vendorItemCode(UPDATED_VENDOR_ITEM_CODE)
            .bestPrice(UPDATED_BEST_PRICE)
            .sellingPrice(UPDATED_SELLING_PRICE)
            .customerAvgRating(UPDATED_CUSTOMER_AVG_RATING)
            .suchazAvgRating(UPDATED_SUCHAZ_AVG_RATING)
            .status(UPDATED_STATUS)
            .itemURL(UPDATED_ITEM_URL)
            .brand(UPDATED_BRAND)
            .colors(UPDATED_COLORS)
            .availibity(UPDATED_AVAILIBITY)
            .lastRefreshed(UPDATED_LAST_REFRESHED)
            .searchKeywords(UPDATED_SEARCH_KEYWORDS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .isFeatured(UPDATED_IS_FEATURED)
            .lastFeaturedUPDDate(UPDATED_LAST_FEATURED_UPD_DATE);
        ItemDTO itemDTO = itemMapper.toDto(updatedItem);

        restItemMockMvc.perform(put("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isOk());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testItem.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testItem.getVendorItemType()).isEqualTo(UPDATED_VENDOR_ITEM_TYPE);
        assertThat(testItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItem.getVendorItemCode()).isEqualTo(UPDATED_VENDOR_ITEM_CODE);
        assertThat(testItem.getBestPrice()).isEqualTo(UPDATED_BEST_PRICE);
        assertThat(testItem.getSellingPrice()).isEqualTo(UPDATED_SELLING_PRICE);
        assertThat(testItem.getCustomerAvgRating()).isEqualTo(UPDATED_CUSTOMER_AVG_RATING);
        assertThat(testItem.getSuchazAvgRating()).isEqualTo(UPDATED_SUCHAZ_AVG_RATING);
        assertThat(testItem.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItem.getItemURL()).isEqualTo(UPDATED_ITEM_URL);
        assertThat(testItem.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testItem.getColors()).isEqualTo(UPDATED_COLORS);
        assertThat(testItem.getAvailibity()).isEqualTo(UPDATED_AVAILIBITY);
        assertThat(testItem.getLastRefreshed()).isEqualTo(UPDATED_LAST_REFRESHED);
        assertThat(testItem.getSearchKeywords()).isEqualTo(UPDATED_SEARCH_KEYWORDS);
        assertThat(testItem.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItem.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testItem.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testItem.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testItem.isIsFeatured()).isEqualTo(UPDATED_IS_FEATURED);
        assertThat(testItem.getLastFeaturedUPDDate()).isEqualTo(UPDATED_LAST_FEATURED_UPD_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Create the Item
        ItemDTO itemDTO = itemMapper.toDto(item);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restItemMockMvc.perform(put("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemDTO)))
            .andExpect(status().isCreated());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);
        int databaseSizeBeforeDelete = itemRepository.findAll().size();

        // Get the item
        restItemMockMvc.perform(delete("/api/items/{id}", item.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Item.class);
        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setId(item1.getId());
        assertThat(item1).isEqualTo(item2);
        item2.setId(2L);
        assertThat(item1).isNotEqualTo(item2);
        item1.setId(null);
        assertThat(item1).isNotEqualTo(item2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemDTO.class);
        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setId(1L);
        ItemDTO itemDTO2 = new ItemDTO();
        assertThat(itemDTO1).isNotEqualTo(itemDTO2);
        itemDTO2.setId(itemDTO1.getId());
        assertThat(itemDTO1).isEqualTo(itemDTO2);
        itemDTO2.setId(2L);
        assertThat(itemDTO1).isNotEqualTo(itemDTO2);
        itemDTO1.setId(null);
        assertThat(itemDTO1).isNotEqualTo(itemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(itemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(itemMapper.fromId(null)).isNull();
    }
}
