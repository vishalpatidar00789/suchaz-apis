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
import org.springframework.util.Base64Utils;

import com.suchaz.app.SuchazapisApp;
import com.suchaz.app.domain.ItemImage;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.repository.ItemImageRepository;
import com.suchaz.app.service.ItemImageService;
import com.suchaz.app.service.dto.ItemImageDTO;
import com.suchaz.app.service.mapper.ItemImageMapper;
import com.suchaz.app.web.rest.errors.ExceptionTranslator;
/**
 * Test class for the ItemImageResource REST controller.
 *
 * @see ItemImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuchazapisApp.class)
public class ItemImageResourceIntTest {

    private static final String DEFAULT_ITEM_IMAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_IMAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_IMAGE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_IMAGE_DESC = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_IMAGE_SIZE = 1L;
    private static final Long UPDATED_ITEM_IMAGE_SIZE = 2L;

    private static final String DEFAULT_ITEM_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_IMAGE_URL = "BBBBBBBBBB";

    private static final byte[] DEFAULT_ITEM_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ITEM_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ITEM_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ITEM_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_ITEM_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_LAST_REFRESHED_DATE = 1L;
    private static final Long UPDATED_LAST_REFRESHED_DATE = 2L;

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
    private ItemImageRepository itemImageRepository;

    @Autowired
    private ItemImageMapper itemImageMapper;

    @Autowired
    private ItemImageService itemImageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemImageMockMvc;

    private ItemImage itemImage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemImageResource itemImageResource = new ItemImageResource(itemImageService);
        this.restItemImageMockMvc = MockMvcBuilders.standaloneSetup(itemImageResource)
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
    public static ItemImage createEntity(EntityManager em) {
        ItemImage itemImage = new ItemImage()
            .itemImageName(DEFAULT_ITEM_IMAGE_NAME)
            .itemImageDesc(DEFAULT_ITEM_IMAGE_DESC)
            .itemImageSize(DEFAULT_ITEM_IMAGE_SIZE)
            .itemImageURL(DEFAULT_ITEM_IMAGE_URL)
            .itemImage(DEFAULT_ITEM_IMAGE)
            .itemImageContentType(DEFAULT_ITEM_IMAGE_CONTENT_TYPE)
            .itemType(DEFAULT_ITEM_TYPE)
            .lastRefreshedDate(DEFAULT_LAST_REFRESHED_DATE)
            .status(DEFAULT_STATUS)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastUpdatedDate(DEFAULT_LAST_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return itemImage;
    }

    @Before
    public void initTest() {
        itemImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemImage() throws Exception {
        int databaseSizeBeforeCreate = itemImageRepository.findAll().size();

        // Create the ItemImage
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(itemImage);
        restItemImageMockMvc.perform(post("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemImage in the database
        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeCreate + 1);
        ItemImage testItemImage = itemImageList.get(itemImageList.size() - 1);
        assertThat(testItemImage.getItemImageName()).isEqualTo(DEFAULT_ITEM_IMAGE_NAME);
        assertThat(testItemImage.getItemImageDesc()).isEqualTo(DEFAULT_ITEM_IMAGE_DESC);
        assertThat(testItemImage.getItemImageSize()).isEqualTo(DEFAULT_ITEM_IMAGE_SIZE);
        assertThat(testItemImage.getItemImageURL()).isEqualTo(DEFAULT_ITEM_IMAGE_URL);
        assertThat(testItemImage.getItemImage()).isEqualTo(DEFAULT_ITEM_IMAGE);
        assertThat(testItemImage.getItemImageContentType()).isEqualTo(DEFAULT_ITEM_IMAGE_CONTENT_TYPE);
        assertThat(testItemImage.getItemType()).isEqualTo(DEFAULT_ITEM_TYPE);
        assertThat(testItemImage.getLastRefreshedDate()).isEqualTo(DEFAULT_LAST_REFRESHED_DATE);
        assertThat(testItemImage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemImage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testItemImage.getLastUpdatedDate()).isEqualTo(DEFAULT_LAST_UPDATED_DATE);
        assertThat(testItemImage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testItemImage.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createItemImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemImageRepository.findAll().size();

        // Create the ItemImage with an existing ID
        itemImage.setId(1L);
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(itemImage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemImageMockMvc.perform(post("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemImage in the database
        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkItemImageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemImageRepository.findAll().size();
        // set the field null
        itemImage.setItemImageName(null);

        // Create the ItemImage, which fails.
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(itemImage);

        restItemImageMockMvc.perform(post("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isBadRequest());

        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkItemImageSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemImageRepository.findAll().size();
        // set the field null
        itemImage.setItemImageSize(null);

        // Create the ItemImage, which fails.
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(itemImage);

        restItemImageMockMvc.perform(post("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isBadRequest());

        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastRefreshedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemImageRepository.findAll().size();
        // set the field null
        itemImage.setLastRefreshedDate(null);

        // Create the ItemImage, which fails.
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(itemImage);

        restItemImageMockMvc.perform(post("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isBadRequest());

        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemImageRepository.findAll().size();
        // set the field null
        itemImage.setStatus(null);

        // Create the ItemImage, which fails.
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(itemImage);

        restItemImageMockMvc.perform(post("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isBadRequest());

        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemImageRepository.findAll().size();
        // set the field null
        itemImage.setCreatedDate(null);

        // Create the ItemImage, which fails.
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(itemImage);

        restItemImageMockMvc.perform(post("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isBadRequest());

        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemImageRepository.findAll().size();
        // set the field null
        itemImage.setCreatedBy(null);

        // Create the ItemImage, which fails.
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(itemImage);

        restItemImageMockMvc.perform(post("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isBadRequest());

        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemImages() throws Exception {
        // Initialize the database
        itemImageRepository.saveAndFlush(itemImage);

        // Get all the itemImageList
        restItemImageMockMvc.perform(get("/api/item-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemImageName").value(hasItem(DEFAULT_ITEM_IMAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].itemImageDesc").value(hasItem(DEFAULT_ITEM_IMAGE_DESC.toString())))
            .andExpect(jsonPath("$.[*].itemImageSize").value(hasItem(DEFAULT_ITEM_IMAGE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].itemImageURL").value(hasItem(DEFAULT_ITEM_IMAGE_URL.toString())))
            .andExpect(jsonPath("$.[*].itemImageContentType").value(hasItem(DEFAULT_ITEM_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].itemImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_ITEM_IMAGE))))
            .andExpect(jsonPath("$.[*].itemType").value(hasItem(DEFAULT_ITEM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].lastRefreshedDate").value(hasItem(DEFAULT_LAST_REFRESHED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].lastUpdatedDate").value(hasItem(DEFAULT_LAST_UPDATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getItemImage() throws Exception {
        // Initialize the database
        itemImageRepository.saveAndFlush(itemImage);

        // Get the itemImage
        restItemImageMockMvc.perform(get("/api/item-images/{id}", itemImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemImage.getId().intValue()))
            .andExpect(jsonPath("$.itemImageName").value(DEFAULT_ITEM_IMAGE_NAME.toString()))
            .andExpect(jsonPath("$.itemImageDesc").value(DEFAULT_ITEM_IMAGE_DESC.toString()))
            .andExpect(jsonPath("$.itemImageSize").value(DEFAULT_ITEM_IMAGE_SIZE.intValue()))
            .andExpect(jsonPath("$.itemImageURL").value(DEFAULT_ITEM_IMAGE_URL.toString()))
            .andExpect(jsonPath("$.itemImageContentType").value(DEFAULT_ITEM_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.itemImage").value(Base64Utils.encodeToString(DEFAULT_ITEM_IMAGE)))
            .andExpect(jsonPath("$.itemType").value(DEFAULT_ITEM_TYPE.toString()))
            .andExpect(jsonPath("$.lastRefreshedDate").value(DEFAULT_LAST_REFRESHED_DATE.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.lastUpdatedDate").value(DEFAULT_LAST_UPDATED_DATE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemImage() throws Exception {
        // Get the itemImage
        restItemImageMockMvc.perform(get("/api/item-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemImage() throws Exception {
        // Initialize the database
        itemImageRepository.saveAndFlush(itemImage);
        int databaseSizeBeforeUpdate = itemImageRepository.findAll().size();

        // Update the itemImage
        ItemImage updatedItemImage = itemImageRepository.findOne(itemImage.getId());
        // Disconnect from session so that the updates on updatedItemImage are not directly saved in db
        em.detach(updatedItemImage);
        updatedItemImage
            .itemImageName(UPDATED_ITEM_IMAGE_NAME)
            .itemImageDesc(UPDATED_ITEM_IMAGE_DESC)
            .itemImageSize(UPDATED_ITEM_IMAGE_SIZE)
            .itemImageURL(UPDATED_ITEM_IMAGE_URL)
            .itemImage(UPDATED_ITEM_IMAGE)
            .itemImageContentType(UPDATED_ITEM_IMAGE_CONTENT_TYPE)
            .itemType(UPDATED_ITEM_TYPE)
            .lastRefreshedDate(UPDATED_LAST_REFRESHED_DATE)
            .status(UPDATED_STATUS)
            .createdDate(UPDATED_CREATED_DATE)
            .lastUpdatedDate(UPDATED_LAST_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(updatedItemImage);

        restItemImageMockMvc.perform(put("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isOk());

        // Validate the ItemImage in the database
        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeUpdate);
        ItemImage testItemImage = itemImageList.get(itemImageList.size() - 1);
        assertThat(testItemImage.getItemImageName()).isEqualTo(UPDATED_ITEM_IMAGE_NAME);
        assertThat(testItemImage.getItemImageDesc()).isEqualTo(UPDATED_ITEM_IMAGE_DESC);
        assertThat(testItemImage.getItemImageSize()).isEqualTo(UPDATED_ITEM_IMAGE_SIZE);
        assertThat(testItemImage.getItemImageURL()).isEqualTo(UPDATED_ITEM_IMAGE_URL);
        assertThat(testItemImage.getItemImage()).isEqualTo(UPDATED_ITEM_IMAGE);
        assertThat(testItemImage.getItemImageContentType()).isEqualTo(UPDATED_ITEM_IMAGE_CONTENT_TYPE);
        assertThat(testItemImage.getItemType()).isEqualTo(UPDATED_ITEM_TYPE);
        assertThat(testItemImage.getLastRefreshedDate()).isEqualTo(UPDATED_LAST_REFRESHED_DATE);
        assertThat(testItemImage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testItemImage.getLastUpdatedDate()).isEqualTo(UPDATED_LAST_UPDATED_DATE);
        assertThat(testItemImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testItemImage.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingItemImage() throws Exception {
        int databaseSizeBeforeUpdate = itemImageRepository.findAll().size();

        // Create the ItemImage
        ItemImageDTO itemImageDTO = itemImageMapper.toDto(itemImage);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restItemImageMockMvc.perform(put("/api/item-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemImageDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemImage in the database
        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteItemImage() throws Exception {
        // Initialize the database
        itemImageRepository.saveAndFlush(itemImage);
        int databaseSizeBeforeDelete = itemImageRepository.findAll().size();

        // Get the itemImage
        restItemImageMockMvc.perform(delete("/api/item-images/{id}", itemImage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemImage> itemImageList = itemImageRepository.findAll();
        assertThat(itemImageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemImage.class);
        ItemImage itemImage1 = new ItemImage();
        itemImage1.setId(1L);
        ItemImage itemImage2 = new ItemImage();
        itemImage2.setId(itemImage1.getId());
        assertThat(itemImage1).isEqualTo(itemImage2);
        itemImage2.setId(2L);
        assertThat(itemImage1).isNotEqualTo(itemImage2);
        itemImage1.setId(null);
        assertThat(itemImage1).isNotEqualTo(itemImage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemImageDTO.class);
        ItemImageDTO itemImageDTO1 = new ItemImageDTO();
        itemImageDTO1.setId(1L);
        ItemImageDTO itemImageDTO2 = new ItemImageDTO();
        assertThat(itemImageDTO1).isNotEqualTo(itemImageDTO2);
        itemImageDTO2.setId(itemImageDTO1.getId());
        assertThat(itemImageDTO1).isEqualTo(itemImageDTO2);
        itemImageDTO2.setId(2L);
        assertThat(itemImageDTO1).isNotEqualTo(itemImageDTO2);
        itemImageDTO1.setId(null);
        assertThat(itemImageDTO1).isNotEqualTo(itemImageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(itemImageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(itemImageMapper.fromId(null)).isNull();
    }
}
