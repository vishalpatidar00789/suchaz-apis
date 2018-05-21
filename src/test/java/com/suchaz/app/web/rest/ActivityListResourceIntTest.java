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
import com.suchaz.app.domain.ActivityList;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.ActivityListRepository;
import com.suchaz.app.service.ActivityListService;
import com.suchaz.app.service.dto.ActivityListDTO;
import com.suchaz.app.service.mapper.ActivityListMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the ActivityListResource REST controller.
 *
 * @see ActivityListResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class ActivityListResourceIntTest {

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
    private ActivityListRepository activityListRepository;

    @Autowired
    private ActivityListMapper activityListMapper;

    @Autowired
    private ActivityListService activityListService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restActivityListMockMvc;

    private ActivityList activityList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActivityListResource activityListResource = new ActivityListResource(activityListService);
        this.restActivityListMockMvc = MockMvcBuilders.standaloneSetup(activityListResource)
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
    public static ActivityList createEntity(EntityManager em) {
        ActivityList activityList = new ActivityList()
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return activityList;
    }

    @Before
    public void initTest() {
        activityList = createEntity(em);
    }

    @Test
    @Transactional
    public void createActivityList() throws Exception {
        int databaseSizeBeforeCreate = activityListRepository.findAll().size();

        // Create the ActivityList
        ActivityListDTO activityListDTO = activityListMapper.toDto(activityList);
        restActivityListMockMvc.perform(post("/api/activity-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListDTO)))
            .andExpect(status().isCreated());

        // Validate the ActivityList in the database
        List<ActivityList> activityListList = activityListRepository.findAll();
        assertThat(activityListList).hasSize(databaseSizeBeforeCreate + 1);
        ActivityList testActivityList = activityListList.get(activityListList.size() - 1);
        assertThat(testActivityList.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testActivityList.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testActivityList.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testActivityList.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testActivityList.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createActivityListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activityListRepository.findAll().size();

        // Create the ActivityList with an existing ID
        activityList.setId(1L);
        ActivityListDTO activityListDTO = activityListMapper.toDto(activityList);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivityListMockMvc.perform(post("/api/activity-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityList in the database
        List<ActivityList> activityListList = activityListRepository.findAll();
        assertThat(activityListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = activityListRepository.findAll().size();
        // set the field null
        activityList.setStatus(null);

        // Create the ActivityList, which fails.
        ActivityListDTO activityListDTO = activityListMapper.toDto(activityList);

        restActivityListMockMvc.perform(post("/api/activity-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListDTO)))
            .andExpect(status().isBadRequest());

        List<ActivityList> activityListList = activityListRepository.findAll();
        assertThat(activityListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = activityListRepository.findAll().size();
        // set the field null
        activityList.setCreatedDate(null);

        // Create the ActivityList, which fails.
        ActivityListDTO activityListDTO = activityListMapper.toDto(activityList);

        restActivityListMockMvc.perform(post("/api/activity-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListDTO)))
            .andExpect(status().isBadRequest());

        List<ActivityList> activityListList = activityListRepository.findAll();
        assertThat(activityListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = activityListRepository.findAll().size();
        // set the field null
        activityList.setCreatedBy(null);

        // Create the ActivityList, which fails.
        ActivityListDTO activityListDTO = activityListMapper.toDto(activityList);

        restActivityListMockMvc.perform(post("/api/activity-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListDTO)))
            .andExpect(status().isBadRequest());

        List<ActivityList> activityListList = activityListRepository.findAll();
        assertThat(activityListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllActivityLists() throws Exception {
        // Initialize the database
        activityListRepository.saveAndFlush(activityList);

        // Get all the activityListList
        restActivityListMockMvc.perform(get("/api/activity-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activityList.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getActivityList() throws Exception {
        // Initialize the database
        activityListRepository.saveAndFlush(activityList);

        // Get the activityList
        restActivityListMockMvc.perform(get("/api/activity-lists/{id}", activityList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(activityList.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingActivityList() throws Exception {
        // Get the activityList
        restActivityListMockMvc.perform(get("/api/activity-lists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActivityList() throws Exception {
        // Initialize the database
        activityListRepository.saveAndFlush(activityList);
        int databaseSizeBeforeUpdate = activityListRepository.findAll().size();

        // Update the activityList
        ActivityList updatedActivityList = activityListRepository.findOne(activityList.getId());
        // Disconnect from session so that the updates on updatedActivityList are not directly saved in db
        em.detach(updatedActivityList);
        updatedActivityList
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        ActivityListDTO activityListDTO = activityListMapper.toDto(updatedActivityList);

        restActivityListMockMvc.perform(put("/api/activity-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListDTO)))
            .andExpect(status().isOk());

        // Validate the ActivityList in the database
        List<ActivityList> activityListList = activityListRepository.findAll();
        assertThat(activityListList).hasSize(databaseSizeBeforeUpdate);
        ActivityList testActivityList = activityListList.get(activityListList.size() - 1);
        assertThat(testActivityList.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testActivityList.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testActivityList.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testActivityList.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testActivityList.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingActivityList() throws Exception {
        int databaseSizeBeforeUpdate = activityListRepository.findAll().size();

        // Create the ActivityList
        ActivityListDTO activityListDTO = activityListMapper.toDto(activityList);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restActivityListMockMvc.perform(put("/api/activity-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityListDTO)))
            .andExpect(status().isCreated());

        // Validate the ActivityList in the database
        List<ActivityList> activityListList = activityListRepository.findAll();
        assertThat(activityListList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteActivityList() throws Exception {
        // Initialize the database
        activityListRepository.saveAndFlush(activityList);
        int databaseSizeBeforeDelete = activityListRepository.findAll().size();

        // Get the activityList
        restActivityListMockMvc.perform(delete("/api/activity-lists/{id}", activityList.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ActivityList> activityListList = activityListRepository.findAll();
        assertThat(activityListList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivityList.class);
        ActivityList activityList1 = new ActivityList();
        activityList1.setId(1L);
        ActivityList activityList2 = new ActivityList();
        activityList2.setId(activityList1.getId());
        assertThat(activityList1).isEqualTo(activityList2);
        activityList2.setId(2L);
        assertThat(activityList1).isNotEqualTo(activityList2);
        activityList1.setId(null);
        assertThat(activityList1).isNotEqualTo(activityList2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivityListDTO.class);
        ActivityListDTO activityListDTO1 = new ActivityListDTO();
        activityListDTO1.setId(1L);
        ActivityListDTO activityListDTO2 = new ActivityListDTO();
        assertThat(activityListDTO1).isNotEqualTo(activityListDTO2);
        activityListDTO2.setId(activityListDTO1.getId());
        assertThat(activityListDTO1).isEqualTo(activityListDTO2);
        activityListDTO2.setId(2L);
        assertThat(activityListDTO1).isNotEqualTo(activityListDTO2);
        activityListDTO1.setId(null);
        assertThat(activityListDTO1).isNotEqualTo(activityListDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(activityListMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(activityListMapper.fromId(null)).isNull();
    }
}
