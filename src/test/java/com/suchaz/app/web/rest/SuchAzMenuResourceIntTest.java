package com.suchaz.app.web.rest;

import com.suchaz.app.SuchazapisApp;

import com.suchaz.app.domain.SuchAzMenu;
import com.suchaz.app.repository.SuchAzMenuRepository;
import com.suchaz.app.service.QuickViewService;
import com.suchaz.app.service.SuchAzMenuService;
import com.suchaz.app.service.dto.SuchAzMenuDTO;
import com.suchaz.app.service.mapper.SuchAzMenuMapper;
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
import org.springframework.util.Base64Utils;

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
 * Test class for the SuchAzMenuResource REST controller.
 *
 * @see SuchAzMenuResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class SuchAzMenuResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_MENU = "AAAAAAAAAA";
    private static final String UPDATED_MENU = "BBBBBBBBBB";

    private static final String DEFAULT_MENU_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MENU_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DISCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DISCRIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_MENU_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_MENU_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_MENU_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_MENU_IMAGE_CONTENT_TYPE = "image/png";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.INACTIVE;

    private static final Status DEFAULT_IS_EXPOSED_TO_MENU = Status.ACTIVE;
    private static final Status UPDATED_IS_EXPOSED_TO_MENU = Status.INACTIVE;

    private static final Long DEFAULT_CREATED_DATE = 1L;
    private static final Long UPDATED_CREATED_DATE = 2L;

    private static final Long DEFAULT_LAST_UPDATED_DATE = 1L;
    private static final Long UPDATED_LAST_UPDATED_DATE = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    @Autowired
    private SuchAzMenuRepository suchAzMenuRepository;

    @Autowired
    private SuchAzMenuMapper suchAzMenuMapper;

    @Autowired
    private SuchAzMenuService suchAzMenuService;

    @Autowired
    private QuickViewService quickViewService;

    
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSuchAzMenuMockMvc;

    private SuchAzMenu suchAzMenu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SuchAzMenuResource suchAzMenuResource = new SuchAzMenuResource(suchAzMenuService,quickViewService);
        this.restSuchAzMenuMockMvc = MockMvcBuilders.standaloneSetup(suchAzMenuResource)
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
    public static SuchAzMenu createEntity(EntityManager em) {
        SuchAzMenu suchAzMenu = new SuchAzMenu()
            .title(DEFAULT_TITLE)
            .menu(DEFAULT_MENU)
            .menuCode(DEFAULT_MENU_CODE)
            .discription(DEFAULT_DISCRIPTION)
            .menuImage(DEFAULT_MENU_IMAGE)
            .menuImageContentType(DEFAULT_MENU_IMAGE_CONTENT_TYPE)
            .status(DEFAULT_STATUS)
            .isExposedToMenu(DEFAULT_IS_EXPOSED_TO_MENU)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return suchAzMenu;
    }

    @Before
    public void initTest() {
        suchAzMenu = createEntity(em);
    }

    @Test
    @Transactional
    public void createSuchAzMenu() throws Exception {
        int databaseSizeBeforeCreate = suchAzMenuRepository.findAll().size();

        // Create the SuchAzMenu
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);
        restSuchAzMenuMockMvc.perform(post("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isCreated());

        // Validate the SuchAzMenu in the database
        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeCreate + 1);
        SuchAzMenu testSuchAzMenu = suchAzMenuList.get(suchAzMenuList.size() - 1);
        assertThat(testSuchAzMenu.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testSuchAzMenu.getMenu()).isEqualTo(DEFAULT_MENU);
        assertThat(testSuchAzMenu.getMenuCode()).isEqualTo(DEFAULT_MENU_CODE);
        assertThat(testSuchAzMenu.getDiscription()).isEqualTo(DEFAULT_DISCRIPTION);
        assertThat(testSuchAzMenu.getMenuImage()).isEqualTo(DEFAULT_MENU_IMAGE);
        assertThat(testSuchAzMenu.getMenuImageContentType()).isEqualTo(DEFAULT_MENU_IMAGE_CONTENT_TYPE);
        assertThat(testSuchAzMenu.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSuchAzMenu.getIsExposedToMenu()).isEqualTo(DEFAULT_IS_EXPOSED_TO_MENU);
        assertThat(testSuchAzMenu.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSuchAzMenu.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testSuchAzMenu.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSuchAzMenu.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createSuchAzMenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = suchAzMenuRepository.findAll().size();

        // Create the SuchAzMenu with an existing ID
        suchAzMenu.setId(1L);
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSuchAzMenuMockMvc.perform(post("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SuchAzMenu in the database
        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzMenuRepository.findAll().size();
        // set the field null
        suchAzMenu.setTitle(null);

        // Create the SuchAzMenu, which fails.
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);

        restSuchAzMenuMockMvc.perform(post("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMenuIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzMenuRepository.findAll().size();
        // set the field null
        suchAzMenu.setMenu(null);

        // Create the SuchAzMenu, which fails.
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);

        restSuchAzMenuMockMvc.perform(post("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMenuCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzMenuRepository.findAll().size();
        // set the field null
        suchAzMenu.setMenuCode(null);

        // Create the SuchAzMenu, which fails.
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);

        restSuchAzMenuMockMvc.perform(post("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzMenuRepository.findAll().size();
        // set the field null
        suchAzMenu.setStatus(null);

        // Create the SuchAzMenu, which fails.
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);

        restSuchAzMenuMockMvc.perform(post("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsExposedToMenuIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzMenuRepository.findAll().size();
        // set the field null
        suchAzMenu.setIsExposedToMenu(null);

        // Create the SuchAzMenu, which fails.
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);

        restSuchAzMenuMockMvc.perform(post("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzMenuRepository.findAll().size();
        // set the field null
        suchAzMenu.setCreatedDate(null);

        // Create the SuchAzMenu, which fails.
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);

        restSuchAzMenuMockMvc.perform(post("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = suchAzMenuRepository.findAll().size();
        // set the field null
        suchAzMenu.setCreatedBy(null);

        // Create the SuchAzMenu, which fails.
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);

        restSuchAzMenuMockMvc.perform(post("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSuchAzMenus() throws Exception {
        // Initialize the database
        suchAzMenuRepository.saveAndFlush(suchAzMenu);

        // Get all the suchAzMenuList
        restSuchAzMenuMockMvc.perform(get("/api/such-az-menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(suchAzMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].menu").value(hasItem(DEFAULT_MENU.toString())))
            .andExpect(jsonPath("$.[*].menuCode").value(hasItem(DEFAULT_MENU_CODE.toString())))
            .andExpect(jsonPath("$.[*].discription").value(hasItem(DEFAULT_DISCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].menuImageContentType").value(hasItem(DEFAULT_MENU_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].menuImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_MENU_IMAGE))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].isExposedToMenu").value(hasItem(DEFAULT_IS_EXPOSED_TO_MENU.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getSuchAzMenu() throws Exception {
        // Initialize the database
        suchAzMenuRepository.saveAndFlush(suchAzMenu);

        // Get the suchAzMenu
        restSuchAzMenuMockMvc.perform(get("/api/such-az-menus/{id}", suchAzMenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(suchAzMenu.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.menu").value(DEFAULT_MENU.toString()))
            .andExpect(jsonPath("$.menuCode").value(DEFAULT_MENU_CODE.toString()))
            .andExpect(jsonPath("$.discription").value(DEFAULT_DISCRIPTION.toString()))
            .andExpect(jsonPath("$.menuImageContentType").value(DEFAULT_MENU_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.menuImage").value(Base64Utils.encodeToString(DEFAULT_MENU_IMAGE)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.isExposedToMenu").value(DEFAULT_IS_EXPOSED_TO_MENU.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSuchAzMenu() throws Exception {
        // Get the suchAzMenu
        restSuchAzMenuMockMvc.perform(get("/api/such-az-menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSuchAzMenu() throws Exception {
        // Initialize the database
        suchAzMenuRepository.saveAndFlush(suchAzMenu);
        int databaseSizeBeforeUpdate = suchAzMenuRepository.findAll().size();

        // Update the suchAzMenu
        SuchAzMenu updatedSuchAzMenu = suchAzMenuRepository.findOne(suchAzMenu.getId());
        // Disconnect from session so that the updates on updatedSuchAzMenu are not directly saved in db
        em.detach(updatedSuchAzMenu);
        updatedSuchAzMenu
            .title(UPDATED_TITLE)
            .menu(UPDATED_MENU)
            .menuCode(UPDATED_MENU_CODE)
            .discription(UPDATED_DISCRIPTION)
            .menuImage(UPDATED_MENU_IMAGE)
            .menuImageContentType(UPDATED_MENU_IMAGE_CONTENT_TYPE)
            .status(UPDATED_STATUS)
            .isExposedToMenu(UPDATED_IS_EXPOSED_TO_MENU)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(updatedSuchAzMenu);

        restSuchAzMenuMockMvc.perform(put("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isOk());

        // Validate the SuchAzMenu in the database
        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeUpdate);
        SuchAzMenu testSuchAzMenu = suchAzMenuList.get(suchAzMenuList.size() - 1);
        assertThat(testSuchAzMenu.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSuchAzMenu.getMenu()).isEqualTo(UPDATED_MENU);
        assertThat(testSuchAzMenu.getMenuCode()).isEqualTo(UPDATED_MENU_CODE);
        assertThat(testSuchAzMenu.getDiscription()).isEqualTo(UPDATED_DISCRIPTION);
        assertThat(testSuchAzMenu.getMenuImage()).isEqualTo(UPDATED_MENU_IMAGE);
        assertThat(testSuchAzMenu.getMenuImageContentType()).isEqualTo(UPDATED_MENU_IMAGE_CONTENT_TYPE);
        assertThat(testSuchAzMenu.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSuchAzMenu.getIsExposedToMenu()).isEqualTo(UPDATED_IS_EXPOSED_TO_MENU);
        assertThat(testSuchAzMenu.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSuchAzMenu.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testSuchAzMenu.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSuchAzMenu.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingSuchAzMenu() throws Exception {
        int databaseSizeBeforeUpdate = suchAzMenuRepository.findAll().size();

        // Create the SuchAzMenu
        SuchAzMenuDTO suchAzMenuDTO = suchAzMenuMapper.toDto(suchAzMenu);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSuchAzMenuMockMvc.perform(put("/api/such-az-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(suchAzMenuDTO)))
            .andExpect(status().isCreated());

        // Validate the SuchAzMenu in the database
        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSuchAzMenu() throws Exception {
        // Initialize the database
        suchAzMenuRepository.saveAndFlush(suchAzMenu);
        int databaseSizeBeforeDelete = suchAzMenuRepository.findAll().size();

        // Get the suchAzMenu
        restSuchAzMenuMockMvc.perform(delete("/api/such-az-menus/{id}", suchAzMenu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SuchAzMenu> suchAzMenuList = suchAzMenuRepository.findAll();
        assertThat(suchAzMenuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuchAzMenu.class);
        SuchAzMenu suchAzMenu1 = new SuchAzMenu();
        suchAzMenu1.setId(1L);
        SuchAzMenu suchAzMenu2 = new SuchAzMenu();
        suchAzMenu2.setId(suchAzMenu1.getId());
        assertThat(suchAzMenu1).isEqualTo(suchAzMenu2);
        suchAzMenu2.setId(2L);
        assertThat(suchAzMenu1).isNotEqualTo(suchAzMenu2);
        suchAzMenu1.setId(null);
        assertThat(suchAzMenu1).isNotEqualTo(suchAzMenu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuchAzMenuDTO.class);
        SuchAzMenuDTO suchAzMenuDTO1 = new SuchAzMenuDTO();
        suchAzMenuDTO1.setId(1L);
        SuchAzMenuDTO suchAzMenuDTO2 = new SuchAzMenuDTO();
        assertThat(suchAzMenuDTO1).isNotEqualTo(suchAzMenuDTO2);
        suchAzMenuDTO2.setId(suchAzMenuDTO1.getId());
        assertThat(suchAzMenuDTO1).isEqualTo(suchAzMenuDTO2);
        suchAzMenuDTO2.setId(2L);
        assertThat(suchAzMenuDTO1).isNotEqualTo(suchAzMenuDTO2);
        suchAzMenuDTO1.setId(null);
        assertThat(suchAzMenuDTO1).isNotEqualTo(suchAzMenuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(suchAzMenuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(suchAzMenuMapper.fromId(null)).isNull();
    }
}
