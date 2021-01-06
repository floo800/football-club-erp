package com.mnc.football.web.rest;

import com.mnc.football.FootballClubErpApp;
import com.mnc.football.domain.Children;
import com.mnc.football.repository.ChildrenRepository;
import com.mnc.football.service.ChildrenService;
import com.mnc.football.service.dto.ChildrenDTO;
import com.mnc.football.service.mapper.ChildrenMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ChildrenResource} REST controller.
 */
@SpringBootTest(classes = FootballClubErpApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ChildrenResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BIRTH_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_BIRTH_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_CITY = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PARENT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_KEY = "BBBBBBBBBB";

    @Autowired
    private ChildrenRepository childrenRepository;

    @Autowired
    private ChildrenMapper childrenMapper;

    @Autowired
    private ChildrenService childrenService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChildrenMockMvc;

    private Children children;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Children createEntity(EntityManager em) {
        Children children = new Children()
            .name(DEFAULT_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .birthDate(DEFAULT_BIRTH_DATE)
            .birthCountry(DEFAULT_BIRTH_COUNTRY)
            .birthCity(DEFAULT_BIRTH_CITY)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .parentKey(DEFAULT_PARENT_KEY);
        return children;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Children createUpdatedEntity(EntityManager em) {
        Children children = new Children()
            .name(UPDATED_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .birthCountry(UPDATED_BIRTH_COUNTRY)
            .birthCity(UPDATED_BIRTH_CITY)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .parentKey(UPDATED_PARENT_KEY);
        return children;
    }

    @BeforeEach
    public void initTest() {
        children = createEntity(em);
    }

    @Test
    @Transactional
    public void createChildren() throws Exception {
        int databaseSizeBeforeCreate = childrenRepository.findAll().size();
        // Create the Children
        ChildrenDTO childrenDTO = childrenMapper.toDto(children);
        restChildrenMockMvc.perform(post("/api/children")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(childrenDTO)))
            .andExpect(status().isCreated());

        // Validate the Children in the database
        List<Children> childrenList = childrenRepository.findAll();
        assertThat(childrenList).hasSize(databaseSizeBeforeCreate + 1);
        Children testChildren = childrenList.get(childrenList.size() - 1);
        assertThat(testChildren.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testChildren.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testChildren.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testChildren.getBirthCountry()).isEqualTo(DEFAULT_BIRTH_COUNTRY);
        assertThat(testChildren.getBirthCity()).isEqualTo(DEFAULT_BIRTH_CITY);
        assertThat(testChildren.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testChildren.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testChildren.getParentKey()).isEqualTo(DEFAULT_PARENT_KEY);
    }

    @Test
    @Transactional
    public void createChildrenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = childrenRepository.findAll().size();

        // Create the Children with an existing ID
        children.setId(1L);
        ChildrenDTO childrenDTO = childrenMapper.toDto(children);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChildrenMockMvc.perform(post("/api/children")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(childrenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Children in the database
        List<Children> childrenList = childrenRepository.findAll();
        assertThat(childrenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllChildren() throws Exception {
        // Initialize the database
        childrenRepository.saveAndFlush(children);

        // Get all the childrenList
        restChildrenMockMvc.perform(get("/api/children?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(children.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].birthCountry").value(hasItem(DEFAULT_BIRTH_COUNTRY)))
            .andExpect(jsonPath("$.[*].birthCity").value(hasItem(DEFAULT_BIRTH_CITY)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].parentKey").value(hasItem(DEFAULT_PARENT_KEY)));
    }
    
    @Test
    @Transactional
    public void getChildren() throws Exception {
        // Initialize the database
        childrenRepository.saveAndFlush(children);

        // Get the children
        restChildrenMockMvc.perform(get("/api/children/{id}", children.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(children.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.birthCountry").value(DEFAULT_BIRTH_COUNTRY))
            .andExpect(jsonPath("$.birthCity").value(DEFAULT_BIRTH_CITY))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.parentKey").value(DEFAULT_PARENT_KEY));
    }
    @Test
    @Transactional
    public void getNonExistingChildren() throws Exception {
        // Get the children
        restChildrenMockMvc.perform(get("/api/children/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChildren() throws Exception {
        // Initialize the database
        childrenRepository.saveAndFlush(children);

        int databaseSizeBeforeUpdate = childrenRepository.findAll().size();

        // Update the children
        Children updatedChildren = childrenRepository.findById(children.getId()).get();
        // Disconnect from session so that the updates on updatedChildren are not directly saved in db
        em.detach(updatedChildren);
        updatedChildren
            .name(UPDATED_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .birthCountry(UPDATED_BIRTH_COUNTRY)
            .birthCity(UPDATED_BIRTH_CITY)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .parentKey(UPDATED_PARENT_KEY);
        ChildrenDTO childrenDTO = childrenMapper.toDto(updatedChildren);

        restChildrenMockMvc.perform(put("/api/children")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(childrenDTO)))
            .andExpect(status().isOk());

        // Validate the Children in the database
        List<Children> childrenList = childrenRepository.findAll();
        assertThat(childrenList).hasSize(databaseSizeBeforeUpdate);
        Children testChildren = childrenList.get(childrenList.size() - 1);
        assertThat(testChildren.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testChildren.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testChildren.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testChildren.getBirthCountry()).isEqualTo(UPDATED_BIRTH_COUNTRY);
        assertThat(testChildren.getBirthCity()).isEqualTo(UPDATED_BIRTH_CITY);
        assertThat(testChildren.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testChildren.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testChildren.getParentKey()).isEqualTo(UPDATED_PARENT_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingChildren() throws Exception {
        int databaseSizeBeforeUpdate = childrenRepository.findAll().size();

        // Create the Children
        ChildrenDTO childrenDTO = childrenMapper.toDto(children);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChildrenMockMvc.perform(put("/api/children")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(childrenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Children in the database
        List<Children> childrenList = childrenRepository.findAll();
        assertThat(childrenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChildren() throws Exception {
        // Initialize the database
        childrenRepository.saveAndFlush(children);

        int databaseSizeBeforeDelete = childrenRepository.findAll().size();

        // Delete the children
        restChildrenMockMvc.perform(delete("/api/children/{id}", children.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Children> childrenList = childrenRepository.findAll();
        assertThat(childrenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
