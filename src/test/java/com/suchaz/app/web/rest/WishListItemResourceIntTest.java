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
import com.suchaz.app.domain.WishListItem;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.WishListItemRepository;
import com.suchaz.app.service.WishListItemService;
import com.suchaz.app.service.dto.WishListItemDTO;
import com.suchaz.app.service.mapper.WishListItemMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the WishListItemResource REST controller.
 *
 * @see WishListItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class WishListItemResourceIntTest {

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
    private WishListItemRepository wishListItemRepository;

    @Autowired
    private WishListItemMapper wishListItemMapper;

    @Autowired
    private WishListItemService wishListItemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWishListItemMockMvc;

    private WishListItem wishListItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WishListItemResource wishListItemResource = new WishListItemResource(wishListItemService);
        this.restWishListItemMockMvc = MockMvcBuilders.standaloneSetup(wishListItemResource)
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
    public static WishListItem createEntity(EntityManager em) {
        WishListItem wishListItem = new WishListItem()
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return wishListItem;
    }

    @Before
    public void initTest() {
        wishListItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createWishListItem() throws Exception {
        int databaseSizeBeforeCreate = wishListItemRepository.findAll().size();

        // Create the WishListItem
        WishListItemDTO wishListItemDTO = wishListItemMapper.toDto(wishListItem);
        restWishListItemMockMvc.perform(post("/api/wish-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListItemDTO)))
            .andExpect(status().isCreated());

        // Validate the WishListItem in the database
        List<WishListItem> wishListItemList = wishListItemRepository.findAll();
        assertThat(wishListItemList).hasSize(databaseSizeBeforeCreate + 1);
        WishListItem testWishListItem = wishListItemList.get(wishListItemList.size() - 1);
        assertThat(testWishListItem.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWishListItem.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testWishListItem.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testWishListItem.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testWishListItem.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createWishListItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wishListItemRepository.findAll().size();

        // Create the WishListItem with an existing ID
        wishListItem.setId(1L);
        WishListItemDTO wishListItemDTO = wishListItemMapper.toDto(wishListItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWishListItemMockMvc.perform(post("/api/wish-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WishListItem in the database
        List<WishListItem> wishListItemList = wishListItemRepository.findAll();
        assertThat(wishListItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = wishListItemRepository.findAll().size();
        // set the field null
        wishListItem.setStatus(null);

        // Create the WishListItem, which fails.
        WishListItemDTO wishListItemDTO = wishListItemMapper.toDto(wishListItem);

        restWishListItemMockMvc.perform(post("/api/wish-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListItemDTO)))
            .andExpect(status().isBadRequest());

        List<WishListItem> wishListItemList = wishListItemRepository.findAll();
        assertThat(wishListItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = wishListItemRepository.findAll().size();
        // set the field null
        wishListItem.setCreatedDate(null);

        // Create the WishListItem, which fails.
        WishListItemDTO wishListItemDTO = wishListItemMapper.toDto(wishListItem);

        restWishListItemMockMvc.perform(post("/api/wish-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListItemDTO)))
            .andExpect(status().isBadRequest());

        List<WishListItem> wishListItemList = wishListItemRepository.findAll();
        assertThat(wishListItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = wishListItemRepository.findAll().size();
        // set the field null
        wishListItem.setCreatedBy(null);

        // Create the WishListItem, which fails.
        WishListItemDTO wishListItemDTO = wishListItemMapper.toDto(wishListItem);

        restWishListItemMockMvc.perform(post("/api/wish-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListItemDTO)))
            .andExpect(status().isBadRequest());

        List<WishListItem> wishListItemList = wishListItemRepository.findAll();
        assertThat(wishListItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWishListItems() throws Exception {
        // Initialize the database
        wishListItemRepository.saveAndFlush(wishListItem);

        // Get all the wishListItemList
        restWishListItemMockMvc.perform(get("/api/wish-list-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wishListItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getWishListItem() throws Exception {
        // Initialize the database
        wishListItemRepository.saveAndFlush(wishListItem);

        // Get the wishListItem
        restWishListItemMockMvc.perform(get("/api/wish-list-items/{id}", wishListItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wishListItem.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWishListItem() throws Exception {
        // Get the wishListItem
        restWishListItemMockMvc.perform(get("/api/wish-list-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWishListItem() throws Exception {
        // Initialize the database
        wishListItemRepository.saveAndFlush(wishListItem);
        int databaseSizeBeforeUpdate = wishListItemRepository.findAll().size();

        // Update the wishListItem
        WishListItem updatedWishListItem = wishListItemRepository.findOne(wishListItem.getId());
        // Disconnect from session so that the updates on updatedWishListItem are not directly saved in db
        em.detach(updatedWishListItem);
        updatedWishListItem
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        WishListItemDTO wishListItemDTO = wishListItemMapper.toDto(updatedWishListItem);

        restWishListItemMockMvc.perform(put("/api/wish-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListItemDTO)))
            .andExpect(status().isOk());

        // Validate the WishListItem in the database
        List<WishListItem> wishListItemList = wishListItemRepository.findAll();
        assertThat(wishListItemList).hasSize(databaseSizeBeforeUpdate);
        WishListItem testWishListItem = wishListItemList.get(wishListItemList.size() - 1);
        assertThat(testWishListItem.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWishListItem.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testWishListItem.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testWishListItem.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testWishListItem.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingWishListItem() throws Exception {
        int databaseSizeBeforeUpdate = wishListItemRepository.findAll().size();

        // Create the WishListItem
        WishListItemDTO wishListItemDTO = wishListItemMapper.toDto(wishListItem);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWishListItemMockMvc.perform(put("/api/wish-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListItemDTO)))
            .andExpect(status().isCreated());

        // Validate the WishListItem in the database
        List<WishListItem> wishListItemList = wishListItemRepository.findAll();
        assertThat(wishListItemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteWishListItem() throws Exception {
        // Initialize the database
        wishListItemRepository.saveAndFlush(wishListItem);
        int databaseSizeBeforeDelete = wishListItemRepository.findAll().size();

        // Get the wishListItem
        restWishListItemMockMvc.perform(delete("/api/wish-list-items/{id}", wishListItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WishListItem> wishListItemList = wishListItemRepository.findAll();
        assertThat(wishListItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WishListItem.class);
        WishListItem wishListItem1 = new WishListItem();
        wishListItem1.setId(1L);
        WishListItem wishListItem2 = new WishListItem();
        wishListItem2.setId(wishListItem1.getId());
        assertThat(wishListItem1).isEqualTo(wishListItem2);
        wishListItem2.setId(2L);
        assertThat(wishListItem1).isNotEqualTo(wishListItem2);
        wishListItem1.setId(null);
        assertThat(wishListItem1).isNotEqualTo(wishListItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WishListItemDTO.class);
        WishListItemDTO wishListItemDTO1 = new WishListItemDTO();
        wishListItemDTO1.setId(1L);
        WishListItemDTO wishListItemDTO2 = new WishListItemDTO();
        assertThat(wishListItemDTO1).isNotEqualTo(wishListItemDTO2);
        wishListItemDTO2.setId(wishListItemDTO1.getId());
        assertThat(wishListItemDTO1).isEqualTo(wishListItemDTO2);
        wishListItemDTO2.setId(2L);
        assertThat(wishListItemDTO1).isNotEqualTo(wishListItemDTO2);
        wishListItemDTO1.setId(null);
        assertThat(wishListItemDTO1).isNotEqualTo(wishListItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(wishListItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(wishListItemMapper.fromId(null)).isNull();
    }
}
