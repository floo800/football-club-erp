package com.mnc.football.web.rest;

import com.mnc.football.FootballClubErpApp;
import com.mnc.football.domain.WeeklyProgram;
import com.mnc.football.repository.WeeklyProgramRepository;
import com.mnc.football.service.WeeklyProgramService;
import com.mnc.football.service.dto.WeeklyProgramDTO;
import com.mnc.football.service.mapper.WeeklyProgramMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link WeeklyProgramResource} REST controller.
 */
@SpringBootTest(classes = FootballClubErpApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WeeklyProgramResourceIT {

    private static final Integer DEFAULT_WEEK = 1;
    private static final Integer UPDATED_WEEK = 2;

    @Autowired
    private WeeklyProgramRepository weeklyProgramRepository;

    @Autowired
    private WeeklyProgramMapper weeklyProgramMapper;

    @Autowired
    private WeeklyProgramService weeklyProgramService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWeeklyProgramMockMvc;

    private WeeklyProgram weeklyProgram;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeeklyProgram createEntity(EntityManager em) {
        WeeklyProgram weeklyProgram = new WeeklyProgram()
            .week(DEFAULT_WEEK);
        return weeklyProgram;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeeklyProgram createUpdatedEntity(EntityManager em) {
        WeeklyProgram weeklyProgram = new WeeklyProgram()
            .week(UPDATED_WEEK);
        return weeklyProgram;
    }

    @BeforeEach
    public void initTest() {
        weeklyProgram = createEntity(em);
    }

    @Test
    @Transactional
    public void createWeeklyProgram() throws Exception {
        int databaseSizeBeforeCreate = weeklyProgramRepository.findAll().size();
        // Create the WeeklyProgram
        WeeklyProgramDTO weeklyProgramDTO = weeklyProgramMapper.toDto(weeklyProgram);
        restWeeklyProgramMockMvc.perform(post("/api/weekly-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weeklyProgramDTO)))
            .andExpect(status().isCreated());

        // Validate the WeeklyProgram in the database
        List<WeeklyProgram> weeklyProgramList = weeklyProgramRepository.findAll();
        assertThat(weeklyProgramList).hasSize(databaseSizeBeforeCreate + 1);
        WeeklyProgram testWeeklyProgram = weeklyProgramList.get(weeklyProgramList.size() - 1);
        assertThat(testWeeklyProgram.getWeek()).isEqualTo(DEFAULT_WEEK);
    }

    @Test
    @Transactional
    public void createWeeklyProgramWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = weeklyProgramRepository.findAll().size();

        // Create the WeeklyProgram with an existing ID
        weeklyProgram.setId(1L);
        WeeklyProgramDTO weeklyProgramDTO = weeklyProgramMapper.toDto(weeklyProgram);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWeeklyProgramMockMvc.perform(post("/api/weekly-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weeklyProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WeeklyProgram in the database
        List<WeeklyProgram> weeklyProgramList = weeklyProgramRepository.findAll();
        assertThat(weeklyProgramList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWeeklyPrograms() throws Exception {
        // Initialize the database
        weeklyProgramRepository.saveAndFlush(weeklyProgram);

        // Get all the weeklyProgramList
        restWeeklyProgramMockMvc.perform(get("/api/weekly-programs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(weeklyProgram.getId().intValue())))
            .andExpect(jsonPath("$.[*].week").value(hasItem(DEFAULT_WEEK)));
    }
    
    @Test
    @Transactional
    public void getWeeklyProgram() throws Exception {
        // Initialize the database
        weeklyProgramRepository.saveAndFlush(weeklyProgram);

        // Get the weeklyProgram
        restWeeklyProgramMockMvc.perform(get("/api/weekly-programs/{id}", weeklyProgram.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(weeklyProgram.getId().intValue()))
            .andExpect(jsonPath("$.week").value(DEFAULT_WEEK));
    }
    @Test
    @Transactional
    public void getNonExistingWeeklyProgram() throws Exception {
        // Get the weeklyProgram
        restWeeklyProgramMockMvc.perform(get("/api/weekly-programs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWeeklyProgram() throws Exception {
        // Initialize the database
        weeklyProgramRepository.saveAndFlush(weeklyProgram);

        int databaseSizeBeforeUpdate = weeklyProgramRepository.findAll().size();

        // Update the weeklyProgram
        WeeklyProgram updatedWeeklyProgram = weeklyProgramRepository.findById(weeklyProgram.getId()).get();
        // Disconnect from session so that the updates on updatedWeeklyProgram are not directly saved in db
        em.detach(updatedWeeklyProgram);
        updatedWeeklyProgram
            .week(UPDATED_WEEK);
        WeeklyProgramDTO weeklyProgramDTO = weeklyProgramMapper.toDto(updatedWeeklyProgram);

        restWeeklyProgramMockMvc.perform(put("/api/weekly-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weeklyProgramDTO)))
            .andExpect(status().isOk());

        // Validate the WeeklyProgram in the database
        List<WeeklyProgram> weeklyProgramList = weeklyProgramRepository.findAll();
        assertThat(weeklyProgramList).hasSize(databaseSizeBeforeUpdate);
        WeeklyProgram testWeeklyProgram = weeklyProgramList.get(weeklyProgramList.size() - 1);
        assertThat(testWeeklyProgram.getWeek()).isEqualTo(UPDATED_WEEK);
    }

    @Test
    @Transactional
    public void updateNonExistingWeeklyProgram() throws Exception {
        int databaseSizeBeforeUpdate = weeklyProgramRepository.findAll().size();

        // Create the WeeklyProgram
        WeeklyProgramDTO weeklyProgramDTO = weeklyProgramMapper.toDto(weeklyProgram);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWeeklyProgramMockMvc.perform(put("/api/weekly-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weeklyProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WeeklyProgram in the database
        List<WeeklyProgram> weeklyProgramList = weeklyProgramRepository.findAll();
        assertThat(weeklyProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWeeklyProgram() throws Exception {
        // Initialize the database
        weeklyProgramRepository.saveAndFlush(weeklyProgram);

        int databaseSizeBeforeDelete = weeklyProgramRepository.findAll().size();

        // Delete the weeklyProgram
        restWeeklyProgramMockMvc.perform(delete("/api/weekly-programs/{id}", weeklyProgram.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WeeklyProgram> weeklyProgramList = weeklyProgramRepository.findAll();
        assertThat(weeklyProgramList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
