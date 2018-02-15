package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.ActivityListItem;
import com.suchaz.app.repository.ActivityListItemRepository;
import com.suchaz.app.service.ActivityListItemService;
import com.suchaz.app.service.dto.ActivityListItemDTO;
import com.suchaz.app.service.mapper.ActivityListItemMapper;
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
 * Test class for the ActivityListItemResource REST controller.
 *
 * @see ActivityListItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class ActivityListItemResourceIntTest {

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
    private ActivityListItemRepository activityListItemRepository;

    @Autowired
    private ActivityListItemMapper activityListItemMapper;

    @Autowired
    private ActivityListItemService activityListItemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restActivityListItemMockMvc;

    private ActivityListItem activityListItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActivityListItemResource activityListItemResource = new ActivityListItemResource(activityListItemService);
        this.restActivityListItemMockMvc = MockMvcBuilders.standaloneSetup(activityListItemResource)
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
    public static ActivityListItem createEntity(EntityManager em) {
        ActivityListItem activityListItem = new ActivityListItem()
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return activityListItem;
    }

    @Before
    public void initTest() {
        activityListItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createActivityListItem() throws Exception {
        int databaseSizeBeforeCreate = activityListItemRepository.findAll().size();

        // Create the ActivityListItem
        ActivityListItemDTO activityListItemDTO = activityListItemMapper.toDto(activityListItem);
        restActivityListItemMockMvc.perform(post("/api/activity-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListItemDTO)))
            .andExpect(status().isCreated());

        // Validate the ActivityListItem in the database
        List<ActivityListItem> activityListItemList = activityListItemRepository.findAll();
        assertThat(activityListItemList).hasSize(databaseSizeBeforeCreate + 1);
        ActivityListItem testActivityListItem = activityListItemList.get(activityListItemList.size() - 1);
        assertThat(testActivityListItem.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testActivityListItem.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testActivityListItem.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testActivityListItem.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testActivityListItem.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createActivityListItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activityListItemRepository.findAll().size();

        // Create the ActivityListItem with an existing ID
        activityListItem.setId(1L);
        ActivityListItemDTO activityListItemDTO = activityListItemMapper.toDto(activityListItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivityListItemMockMvc.perform(post("/api/activity-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityListItem in the database
        List<ActivityListItem> activityListItemList = activityListItemRepository.findAll();
        assertThat(activityListItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = activityListItemRepository.findAll().size();
        // set the field null
        activityListItem.setStatus(null);

        // Create the ActivityListItem, which fails.
        ActivityListItemDTO activityListItemDTO = activityListItemMapper.toDto(activityListItem);

        restActivityListItemMockMvc.perform(post("/api/activity-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListItemDTO)))
            .andExpect(status().isBadRequest());

        List<ActivityListItem> activityListItemList = activityListItemRepository.findAll();
        assertThat(activityListItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = activityListItemRepository.findAll().size();
        // set the field null
        activityListItem.setCreatedDate(null);

        // Create the ActivityListItem, which fails.
        ActivityListItemDTO activityListItemDTO = activityListItemMapper.toDto(activityListItem);

        restActivityListItemMockMvc.perform(post("/api/activity-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListItemDTO)))
            .andExpect(status().isBadRequest());

        List<ActivityListItem> activityListItemList = activityListItemRepository.findAll();
        assertThat(activityListItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = activityListItemRepository.findAll().size();
        // set the field null
        activityListItem.setCreatedBy(null);

        // Create the ActivityListItem, which fails.
        ActivityListItemDTO activityListItemDTO = activityListItemMapper.toDto(activityListItem);

        restActivityListItemMockMvc.perform(post("/api/activity-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListItemDTO)))
            .andExpect(status().isBadRequest());

        List<ActivityListItem> activityListItemList = activityListItemRepository.findAll();
        assertThat(activityListItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllActivityListItems() throws Exception {
        // Initialize the database
        activityListItemRepository.saveAndFlush(activityListItem);

        // Get all the activityListItemList
        restActivityListItemMockMvc.perform(get("/api/activity-list-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activityListItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getActivityListItem() throws Exception {
        // Initialize the database
        activityListItemRepository.saveAndFlush(activityListItem);

        // Get the activityListItem
        restActivityListItemMockMvc.perform(get("/api/activity-list-items/{id}", activityListItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(activityListItem.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingActivityListItem() throws Exception {
        // Get the activityListItem
        restActivityListItemMockMvc.perform(get("/api/activity-list-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActivityListItem() throws Exception {
        // Initialize the database
        activityListItemRepository.saveAndFlush(activityListItem);
        int databaseSizeBeforeUpdate = activityListItemRepository.findAll().size();

        // Update the activityListItem
        ActivityListItem updatedActivityListItem = activityListItemRepository.findOne(activityListItem.getId());
        // Disconnect from session so that the updates on updatedActivityListItem are not directly saved in db
        em.detach(updatedActivityListItem);
        updatedActivityListItem
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        ActivityListItemDTO activityListItemDTO = activityListItemMapper.toDto(updatedActivityListItem);

        restActivityListItemMockMvc.perform(put("/api/activity-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListItemDTO)))
            .andExpect(status().isOk());

        // Validate the ActivityListItem in the database
        List<ActivityListItem> activityListItemList = activityListItemRepository.findAll();
        assertThat(activityListItemList).hasSize(databaseSizeBeforeUpdate);
        ActivityListItem testActivityListItem = activityListItemList.get(activityListItemList.size() - 1);
        assertThat(testActivityListItem.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testActivityListItem.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testActivityListItem.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testActivityListItem.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testActivityListItem.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingActivityListItem() throws Exception {
        int databaseSizeBeforeUpdate = activityListItemRepository.findAll().size();

        // Create the ActivityListItem
        ActivityListItemDTO activityListItemDTO = activityListItemMapper.toDto(activityListItem);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restActivityListItemMockMvc.perform(put("/api/activity-list-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListItemDTO)))
            .andExpect(status().isCreated());

        // Validate the ActivityListItem in the database
        List<ActivityListItem> activityListItemList = activityListItemRepository.findAll();
        assertThat(activityListItemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteActivityListItem() throws Exception {
        // Initialize the database
        activityListItemRepository.saveAndFlush(activityListItem);
        int databaseSizeBeforeDelete = activityListItemRepository.findAll().size();

        // Get the activityListItem
        restActivityListItemMockMvc.perform(delete("/api/activity-list-items/{id}", activityListItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ActivityListItem> activityListItemList = activityListItemRepository.findAll();
        assertThat(activityListItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivityListItem.class);
        ActivityListItem activityListItem1 = new ActivityListItem();
        activityListItem1.setId(1L);
        ActivityListItem activityListItem2 = new ActivityListItem();
        activityListItem2.setId(activityListItem1.getId());
        assertThat(activityListItem1).isEqualTo(activityListItem2);
        activityListItem2.setId(2L);
        assertThat(activityListItem1).isNotEqualTo(activityListItem2);
        activityListItem1.setId(null);
        assertThat(activityListItem1).isNotEqualTo(activityListItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivityListItemDTO.class);
        ActivityListItemDTO activityListItemDTO1 = new ActivityListItemDTO();
        activityListItemDTO1.setId(1L);
        ActivityListItemDTO activityListItemDTO2 = new ActivityListItemDTO();
        assertThat(activityListItemDTO1).isNotEqualTo(activityListItemDTO2);
        activityListItemDTO2.setId(activityListItemDTO1.getId());
        assertThat(activityListItemDTO1).isEqualTo(activityListItemDTO2);
        activityListItemDTO2.setId(2L);
        assertThat(activityListItemDTO1).isNotEqualTo(activityListItemDTO2);
        activityListItemDTO1.setId(null);
        assertThat(activityListItemDTO1).isNotEqualTo(activityListItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(activityListItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(activityListItemMapper.fromId(null)).isNull();
    }
}
