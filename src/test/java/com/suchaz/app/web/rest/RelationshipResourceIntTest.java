package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.Relationship;
import com.suchaz.app.repository.RelationshipRepository;
import com.suchaz.app.service.RelationshipService;
import com.suchaz.app.service.dto.RelationshipDTO;
import com.suchaz.app.service.mapper.RelationshipMapper;
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
 * Test class for the RelationshipResource REST controller.
 *
 * @see RelationshipResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class RelationshipResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP_CODE = "BBBBBBBBBB";

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
    private RelationshipRepository relationshipRepository;

    @Autowired
    private RelationshipMapper relationshipMapper;

    @Autowired
    private RelationshipService relationshipService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRelationshipMockMvc;

    private Relationship relationship;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RelationshipResource relationshipResource = new RelationshipResource(relationshipService);
        this.restRelationshipMockMvc = MockMvcBuilders.standaloneSetup(relationshipResource)
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
    public static Relationship createEntity(EntityManager em) {
        Relationship relationship = new Relationship()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .relationshipCode(DEFAULT_RELATIONSHIP_CODE)
            .isExposedToMenu(DEFAULT_IS_EXPOSED_TO_MENU)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return relationship;
    }

    @Before
    public void initTest() {
        relationship = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelationship() throws Exception {
        int databaseSizeBeforeCreate = relationshipRepository.findAll().size();

        // Create the Relationship
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(relationship);
        restRelationshipMockMvc.perform(post("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isCreated());

        // Validate the Relationship in the database
        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeCreate + 1);
        Relationship testRelationship = relationshipList.get(relationshipList.size() - 1);
        assertThat(testRelationship.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRelationship.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRelationship.getRelationshipCode()).isEqualTo(DEFAULT_RELATIONSHIP_CODE);
        assertThat(testRelationship.getIsExposedToMenu()).isEqualTo(DEFAULT_IS_EXPOSED_TO_MENU);
        assertThat(testRelationship.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRelationship.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRelationship.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testRelationship.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRelationship.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createRelationshipWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relationshipRepository.findAll().size();

        // Create the Relationship with an existing ID
        relationship.setId(1L);
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(relationship);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelationshipMockMvc.perform(post("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Relationship in the database
        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipRepository.findAll().size();
        // set the field null
        relationship.setName(null);

        // Create the Relationship, which fails.
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(relationship);

        restRelationshipMockMvc.perform(post("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isBadRequest());

        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRelationshipCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipRepository.findAll().size();
        // set the field null
        relationship.setRelationshipCode(null);

        // Create the Relationship, which fails.
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(relationship);

        restRelationshipMockMvc.perform(post("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isBadRequest());

        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsExposedToMenuIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipRepository.findAll().size();
        // set the field null
        relationship.setIsExposedToMenu(null);

        // Create the Relationship, which fails.
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(relationship);

        restRelationshipMockMvc.perform(post("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isBadRequest());

        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipRepository.findAll().size();
        // set the field null
        relationship.setStatus(null);

        // Create the Relationship, which fails.
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(relationship);

        restRelationshipMockMvc.perform(post("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isBadRequest());

        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipRepository.findAll().size();
        // set the field null
        relationship.setCreatedDate(null);

        // Create the Relationship, which fails.
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(relationship);

        restRelationshipMockMvc.perform(post("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isBadRequest());

        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = relationshipRepository.findAll().size();
        // set the field null
        relationship.setCreatedBy(null);

        // Create the Relationship, which fails.
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(relationship);

        restRelationshipMockMvc.perform(post("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isBadRequest());

        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRelationships() throws Exception {
        // Initialize the database
        relationshipRepository.saveAndFlush(relationship);

        // Get all the relationshipList
        restRelationshipMockMvc.perform(get("/api/relationships?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relationship.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].relationshipCode").value(hasItem(DEFAULT_RELATIONSHIP_CODE.toString())))
            .andExpect(jsonPath("$.[*].isExposedToMenu").value(hasItem(DEFAULT_IS_EXPOSED_TO_MENU.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getRelationship() throws Exception {
        // Initialize the database
        relationshipRepository.saveAndFlush(relationship);

        // Get the relationship
        restRelationshipMockMvc.perform(get("/api/relationships/{id}", relationship.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(relationship.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.relationshipCode").value(DEFAULT_RELATIONSHIP_CODE.toString()))
            .andExpect(jsonPath("$.isExposedToMenu").value(DEFAULT_IS_EXPOSED_TO_MENU.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRelationship() throws Exception {
        // Get the relationship
        restRelationshipMockMvc.perform(get("/api/relationships/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelationship() throws Exception {
        // Initialize the database
        relationshipRepository.saveAndFlush(relationship);
        int databaseSizeBeforeUpdate = relationshipRepository.findAll().size();

        // Update the relationship
        Relationship updatedRelationship = relationshipRepository.findOne(relationship.getId());
        // Disconnect from session so that the updates on updatedRelationship are not directly saved in db
        em.detach(updatedRelationship);
        updatedRelationship
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .relationshipCode(UPDATED_RELATIONSHIP_CODE)
            .isExposedToMenu(UPDATED_IS_EXPOSED_TO_MENU)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(updatedRelationship);

        restRelationshipMockMvc.perform(put("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isOk());

        // Validate the Relationship in the database
        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeUpdate);
        Relationship testRelationship = relationshipList.get(relationshipList.size() - 1);
        assertThat(testRelationship.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRelationship.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRelationship.getRelationshipCode()).isEqualTo(UPDATED_RELATIONSHIP_CODE);
        assertThat(testRelationship.getIsExposedToMenu()).isEqualTo(UPDATED_IS_EXPOSED_TO_MENU);
        assertThat(testRelationship.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRelationship.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRelationship.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testRelationship.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRelationship.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingRelationship() throws Exception {
        int databaseSizeBeforeUpdate = relationshipRepository.findAll().size();

        // Create the Relationship
        RelationshipDTO relationshipDTO = relationshipMapper.toDto(relationship);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRelationshipMockMvc.perform(put("/api/relationships")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relationshipDTO)))
            .andExpect(status().isCreated());

        // Validate the Relationship in the database
        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRelationship() throws Exception {
        // Initialize the database
        relationshipRepository.saveAndFlush(relationship);
        int databaseSizeBeforeDelete = relationshipRepository.findAll().size();

        // Get the relationship
        restRelationshipMockMvc.perform(delete("/api/relationships/{id}", relationship.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Relationship> relationshipList = relationshipRepository.findAll();
        assertThat(relationshipList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Relationship.class);
        Relationship relationship1 = new Relationship();
        relationship1.setId(1L);
        Relationship relationship2 = new Relationship();
        relationship2.setId(relationship1.getId());
        assertThat(relationship1).isEqualTo(relationship2);
        relationship2.setId(2L);
        assertThat(relationship1).isNotEqualTo(relationship2);
        relationship1.setId(null);
        assertThat(relationship1).isNotEqualTo(relationship2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelationshipDTO.class);
        RelationshipDTO relationshipDTO1 = new RelationshipDTO();
        relationshipDTO1.setId(1L);
        RelationshipDTO relationshipDTO2 = new RelationshipDTO();
        assertThat(relationshipDTO1).isNotEqualTo(relationshipDTO2);
        relationshipDTO2.setId(relationshipDTO1.getId());
        assertThat(relationshipDTO1).isEqualTo(relationshipDTO2);
        relationshipDTO2.setId(2L);
        assertThat(relationshipDTO1).isNotEqualTo(relationshipDTO2);
        relationshipDTO1.setId(null);
        assertThat(relationshipDTO1).isNotEqualTo(relationshipDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(relationshipMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(relationshipMapper.fromId(null)).isNull();
    }
}
