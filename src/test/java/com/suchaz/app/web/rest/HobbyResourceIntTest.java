package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.Hobby;
import com.suchaz.app.repository.HobbyRepository;
import com.suchaz.app.service.HobbyService;
import com.suchaz.app.service.dto.HobbyDTO;
import com.suchaz.app.service.mapper.HobbyMapper;
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
 * Test class for the HobbyResource REST controller.
 *
 * @see HobbyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class HobbyResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_HOBBY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HOBBY_CODE = "BBBBBBBBBB";

    private static final Status DEFAULT_IS_EXPOSED_TO_MENU = Status.ACTIVE;
    private static final Status UPDATED_IS_EXPOSED_TO_MENU = Status.INACTIVE;

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
    private HobbyRepository hobbyRepository;

    @Autowired
    private HobbyMapper hobbyMapper;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHobbyMockMvc;

    private Hobby hobby;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HobbyResource hobbyResource = new HobbyResource(hobbyService);
        this.restHobbyMockMvc = MockMvcBuilders.standaloneSetup(hobbyResource)
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
    public static Hobby createEntity(EntityManager em) {
        Hobby hobby = new Hobby()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .hobbyCode(DEFAULT_HOBBY_CODE)
            .isExposedToMenu(DEFAULT_IS_EXPOSED_TO_MENU)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return hobby;
    }

    @Before
    public void initTest() {
        hobby = createEntity(em);
    }

    @Test
    @Transactional
    public void createHobby() throws Exception {
        int databaseSizeBeforeCreate = hobbyRepository.findAll().size();

        // Create the Hobby
        HobbyDTO hobbyDTO = hobbyMapper.toDto(hobby);
        restHobbyMockMvc.perform(post("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isCreated());

        // Validate the Hobby in the database
        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeCreate + 1);
        Hobby testHobby = hobbyList.get(hobbyList.size() - 1);
        assertThat(testHobby.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHobby.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testHobby.getHobbyCode()).isEqualTo(DEFAULT_HOBBY_CODE);
        assertThat(testHobby.getIsExposedToMenu()).isEqualTo(DEFAULT_IS_EXPOSED_TO_MENU);
        assertThat(testHobby.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testHobby.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testHobby.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testHobby.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testHobby.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createHobbyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hobbyRepository.findAll().size();

        // Create the Hobby with an existing ID
        hobby.setId(1L);
        HobbyDTO hobbyDTO = hobbyMapper.toDto(hobby);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHobbyMockMvc.perform(post("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hobby in the database
        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyRepository.findAll().size();
        // set the field null
        hobby.setName(null);

        // Create the Hobby, which fails.
        HobbyDTO hobbyDTO = hobbyMapper.toDto(hobby);

        restHobbyMockMvc.perform(post("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isBadRequest());

        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHobbyCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyRepository.findAll().size();
        // set the field null
        hobby.setHobbyCode(null);

        // Create the Hobby, which fails.
        HobbyDTO hobbyDTO = hobbyMapper.toDto(hobby);

        restHobbyMockMvc.perform(post("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isBadRequest());

        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsExposedToMenuIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyRepository.findAll().size();
        // set the field null
        hobby.setIsExposedToMenu(null);

        // Create the Hobby, which fails.
        HobbyDTO hobbyDTO = hobbyMapper.toDto(hobby);

        restHobbyMockMvc.perform(post("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isBadRequest());

        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyRepository.findAll().size();
        // set the field null
        hobby.setStatus(null);

        // Create the Hobby, which fails.
        HobbyDTO hobbyDTO = hobbyMapper.toDto(hobby);

        restHobbyMockMvc.perform(post("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isBadRequest());

        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyRepository.findAll().size();
        // set the field null
        hobby.setCreatedDate(null);

        // Create the Hobby, which fails.
        HobbyDTO hobbyDTO = hobbyMapper.toDto(hobby);

        restHobbyMockMvc.perform(post("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isBadRequest());

        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = hobbyRepository.findAll().size();
        // set the field null
        hobby.setCreatedBy(null);

        // Create the Hobby, which fails.
        HobbyDTO hobbyDTO = hobbyMapper.toDto(hobby);

        restHobbyMockMvc.perform(post("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isBadRequest());

        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHobbies() throws Exception {
        // Initialize the database
        hobbyRepository.saveAndFlush(hobby);

        // Get all the hobbyList
        restHobbyMockMvc.perform(get("/api/hobbies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hobby.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].hobbyCode").value(hasItem(DEFAULT_HOBBY_CODE.toString())))
            .andExpect(jsonPath("$.[*].isExposedToMenu").value(hasItem(DEFAULT_IS_EXPOSED_TO_MENU.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getHobby() throws Exception {
        // Initialize the database
        hobbyRepository.saveAndFlush(hobby);

        // Get the hobby
        restHobbyMockMvc.perform(get("/api/hobbies/{id}", hobby.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hobby.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.hobbyCode").value(DEFAULT_HOBBY_CODE.toString()))
            .andExpect(jsonPath("$.isExposedToMenu").value(DEFAULT_IS_EXPOSED_TO_MENU.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHobby() throws Exception {
        // Get the hobby
        restHobbyMockMvc.perform(get("/api/hobbies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHobby() throws Exception {
        // Initialize the database
        hobbyRepository.saveAndFlush(hobby);
        int databaseSizeBeforeUpdate = hobbyRepository.findAll().size();

        // Update the hobby
        Hobby updatedHobby = hobbyRepository.findOne(hobby.getId());
        // Disconnect from session so that the updates on updatedHobby are not directly saved in db
        em.detach(updatedHobby);
        updatedHobby
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .hobbyCode(UPDATED_HOBBY_CODE)
            .isExposedToMenu(UPDATED_IS_EXPOSED_TO_MENU)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        HobbyDTO hobbyDTO = hobbyMapper.toDto(updatedHobby);

        restHobbyMockMvc.perform(put("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isOk());

        // Validate the Hobby in the database
        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeUpdate);
        Hobby testHobby = hobbyList.get(hobbyList.size() - 1);
        assertThat(testHobby.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHobby.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testHobby.getHobbyCode()).isEqualTo(UPDATED_HOBBY_CODE);
        assertThat(testHobby.getIsExposedToMenu()).isEqualTo(UPDATED_IS_EXPOSED_TO_MENU);
        assertThat(testHobby.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testHobby.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHobby.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testHobby.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testHobby.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingHobby() throws Exception {
        int databaseSizeBeforeUpdate = hobbyRepository.findAll().size();

        // Create the Hobby
        HobbyDTO hobbyDTO = hobbyMapper.toDto(hobby);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHobbyMockMvc.perform(put("/api/hobbies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hobbyDTO)))
            .andExpect(status().isCreated());

        // Validate the Hobby in the database
        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteHobby() throws Exception {
        // Initialize the database
        hobbyRepository.saveAndFlush(hobby);
        int databaseSizeBeforeDelete = hobbyRepository.findAll().size();

        // Get the hobby
        restHobbyMockMvc.perform(delete("/api/hobbies/{id}", hobby.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Hobby> hobbyList = hobbyRepository.findAll();
        assertThat(hobbyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hobby.class);
        Hobby hobby1 = new Hobby();
        hobby1.setId(1L);
        Hobby hobby2 = new Hobby();
        hobby2.setId(hobby1.getId());
        assertThat(hobby1).isEqualTo(hobby2);
        hobby2.setId(2L);
        assertThat(hobby1).isNotEqualTo(hobby2);
        hobby1.setId(null);
        assertThat(hobby1).isNotEqualTo(hobby2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HobbyDTO.class);
        HobbyDTO hobbyDTO1 = new HobbyDTO();
        hobbyDTO1.setId(1L);
        HobbyDTO hobbyDTO2 = new HobbyDTO();
        assertThat(hobbyDTO1).isNotEqualTo(hobbyDTO2);
        hobbyDTO2.setId(hobbyDTO1.getId());
        assertThat(hobbyDTO1).isEqualTo(hobbyDTO2);
        hobbyDTO2.setId(2L);
        assertThat(hobbyDTO1).isNotEqualTo(hobbyDTO2);
        hobbyDTO1.setId(null);
        assertThat(hobbyDTO1).isNotEqualTo(hobbyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(hobbyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(hobbyMapper.fromId(null)).isNull();
    }
}
