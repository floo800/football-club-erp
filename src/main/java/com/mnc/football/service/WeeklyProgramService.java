package com.mnc.football.service;

import com.mnc.football.service.dto.WeeklyProgramDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mnc.football.domain.WeeklyProgram}.
 */
public interface WeeklyProgramService {

    /**
     * Save a weeklyProgram.
     *
     * @param weeklyProgramDTO the entity to save.
     * @return the persisted entity.
     */
    WeeklyProgramDTO save(WeeklyProgramDTO weeklyProgramDTO);

    /**
     * Get all the weeklyPrograms.
     *
     * @return the list of entities.
     */
    List<WeeklyProgramDTO> findAll();


    /**
     * Get the "id" weeklyProgram.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WeeklyProgramDTO> findOne(Long id);

    /**
     * Delete the "id" weeklyProgram.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
