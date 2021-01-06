package com.mnc.football.service;

import com.mnc.football.service.dto.TrainingDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mnc.football.domain.Training}.
 */
public interface TrainingService {

    /**
     * Save a training.
     *
     * @param trainingDTO the entity to save.
     * @return the persisted entity.
     */
    TrainingDTO save(TrainingDTO trainingDTO);

    /**
     * Get all the trainings.
     *
     * @return the list of entities.
     */
    List<TrainingDTO> findAll();


    /**
     * Get the "id" training.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TrainingDTO> findOne(Long id);

    /**
     * Delete the "id" training.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
