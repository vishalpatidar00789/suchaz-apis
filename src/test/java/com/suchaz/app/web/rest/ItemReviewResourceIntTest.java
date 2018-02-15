package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.ItemReview;
import com.suchaz.app.repository.ItemReviewRepository;
import com.suchaz.app.service.ItemReviewService;
import com.suchaz.app.service.dto.ItemReviewDTO;
import com.suchaz.app.service.mapper.ItemReviewMapper;
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

/**
 * Test class for the ItemReviewResource REST controller.
 *
 * @see ItemReviewResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class ItemReviewResourceIntTest {

    private static final String DEFAULT_RATING_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_RATING_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_RATING_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_RATING_COMMENT = "BBBBBBBBBB";

    @Autowired
    private ItemReviewRepository itemReviewRepository;

    @Autowired
    private ItemReviewMapper itemReviewMapper;

    @Autowired
    private ItemReviewService itemReviewService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemReviewMockMvc;

    private ItemReview itemReview;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemReviewResource itemReviewResource = new ItemReviewResource(itemReviewService);
        this.restItemReviewMockMvc = MockMvcBuilders.standaloneSetup(itemReviewResource)
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
    public static ItemReview createEntity(EntityManager em) {
        ItemReview itemReview = new ItemReview()
            .ratingValue(DEFAULT_RATING_VALUE)
            .ratingComment(DEFAULT_RATING_COMMENT);
        return itemReview;
    }

    @Before
    public void initTest() {
        itemReview = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemReview() throws Exception {
        int databaseSizeBeforeCreate = itemReviewRepository.findAll().size();

        // Create the ItemReview
        ItemReviewDTO itemReviewDTO = itemReviewMapper.toDto(itemReview);
        restItemReviewMockMvc.perform(post("/api/item-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemReviewDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemReview in the database
        List<ItemReview> itemReviewList = itemReviewRepository.findAll();
        assertThat(itemReviewList).hasSize(databaseSizeBeforeCreate + 1);
        ItemReview testItemReview = itemReviewList.get(itemReviewList.size() - 1);
        assertThat(testItemReview.getRatingValue()).isEqualTo(DEFAULT_RATING_VALUE);
        assertThat(testItemReview.getRatingComment()).isEqualTo(DEFAULT_RATING_COMMENT);
    }

    @Test
    @Transactional
    public void createItemReviewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemReviewRepository.findAll().size();

        // Create the ItemReview with an existing ID
        itemReview.setId(1L);
        ItemReviewDTO itemReviewDTO = itemReviewMapper.toDto(itemReview);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemReviewMockMvc.perform(post("/api/item-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemReviewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemReview in the database
        List<ItemReview> itemReviewList = itemReviewRepository.findAll();
        assertThat(itemReviewList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRatingValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemReviewRepository.findAll().size();
        // set the field null
        itemReview.setRatingValue(null);

        // Create the ItemReview, which fails.
        ItemReviewDTO itemReviewDTO = itemReviewMapper.toDto(itemReview);

        restItemReviewMockMvc.perform(post("/api/item-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemReviewDTO)))
            .andExpect(status().isBadRequest());

        List<ItemReview> itemReviewList = itemReviewRepository.findAll();
        assertThat(itemReviewList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemReviews() throws Exception {
        // Initialize the database
        itemReviewRepository.saveAndFlush(itemReview);

        // Get all the itemReviewList
        restItemReviewMockMvc.perform(get("/api/item-reviews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemReview.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratingValue").value(hasItem(DEFAULT_RATING_VALUE.toString())))
            .andExpect(jsonPath("$.[*].ratingComment").value(hasItem(DEFAULT_RATING_COMMENT.toString())));
    }

    @Test
    @Transactional
    public void getItemReview() throws Exception {
        // Initialize the database
        itemReviewRepository.saveAndFlush(itemReview);

        // Get the itemReview
        restItemReviewMockMvc.perform(get("/api/item-reviews/{id}", itemReview.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemReview.getId().intValue()))
            .andExpect(jsonPath("$.ratingValue").value(DEFAULT_RATING_VALUE.toString()))
            .andExpect(jsonPath("$.ratingComment").value(DEFAULT_RATING_COMMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemReview() throws Exception {
        // Get the itemReview
        restItemReviewMockMvc.perform(get("/api/item-reviews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemReview() throws Exception {
        // Initialize the database
        itemReviewRepository.saveAndFlush(itemReview);
        int databaseSizeBeforeUpdate = itemReviewRepository.findAll().size();

        // Update the itemReview
        ItemReview updatedItemReview = itemReviewRepository.findOne(itemReview.getId());
        // Disconnect from session so that the updates on updatedItemReview are not directly saved in db
        em.detach(updatedItemReview);
        updatedItemReview
            .ratingValue(UPDATED_RATING_VALUE)
            .ratingComment(UPDATED_RATING_COMMENT);
        ItemReviewDTO itemReviewDTO = itemReviewMapper.toDto(updatedItemReview);

        restItemReviewMockMvc.perform(put("/api/item-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemReviewDTO)))
            .andExpect(status().isOk());

        // Validate the ItemReview in the database
        List<ItemReview> itemReviewList = itemReviewRepository.findAll();
        assertThat(itemReviewList).hasSize(databaseSizeBeforeUpdate);
        ItemReview testItemReview = itemReviewList.get(itemReviewList.size() - 1);
        assertThat(testItemReview.getRatingValue()).isEqualTo(UPDATED_RATING_VALUE);
        assertThat(testItemReview.getRatingComment()).isEqualTo(UPDATED_RATING_COMMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingItemReview() throws Exception {
        int databaseSizeBeforeUpdate = itemReviewRepository.findAll().size();

        // Create the ItemReview
        ItemReviewDTO itemReviewDTO = itemReviewMapper.toDto(itemReview);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restItemReviewMockMvc.perform(put("/api/item-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemReviewDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemReview in the database
        List<ItemReview> itemReviewList = itemReviewRepository.findAll();
        assertThat(itemReviewList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteItemReview() throws Exception {
        // Initialize the database
        itemReviewRepository.saveAndFlush(itemReview);
        int databaseSizeBeforeDelete = itemReviewRepository.findAll().size();

        // Get the itemReview
        restItemReviewMockMvc.perform(delete("/api/item-reviews/{id}", itemReview.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemReview> itemReviewList = itemReviewRepository.findAll();
        assertThat(itemReviewList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemReview.class);
        ItemReview itemReview1 = new ItemReview();
        itemReview1.setId(1L);
        ItemReview itemReview2 = new ItemReview();
        itemReview2.setId(itemReview1.getId());
        assertThat(itemReview1).isEqualTo(itemReview2);
        itemReview2.setId(2L);
        assertThat(itemReview1).isNotEqualTo(itemReview2);
        itemReview1.setId(null);
        assertThat(itemReview1).isNotEqualTo(itemReview2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemReviewDTO.class);
        ItemReviewDTO itemReviewDTO1 = new ItemReviewDTO();
        itemReviewDTO1.setId(1L);
        ItemReviewDTO itemReviewDTO2 = new ItemReviewDTO();
        assertThat(itemReviewDTO1).isNotEqualTo(itemReviewDTO2);
        itemReviewDTO2.setId(itemReviewDTO1.getId());
        assertThat(itemReviewDTO1).isEqualTo(itemReviewDTO2);
        itemReviewDTO2.setId(2L);
        assertThat(itemReviewDTO1).isNotEqualTo(itemReviewDTO2);
        itemReviewDTO1.setId(null);
        assertThat(itemReviewDTO1).isNotEqualTo(itemReviewDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(itemReviewMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(itemReviewMapper.fromId(null)).isNull();
    }
}
