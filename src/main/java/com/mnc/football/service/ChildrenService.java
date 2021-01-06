package com.mnc.football.service;

import com.mnc.football.service.dto.ChildrenDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mnc.football.domain.Children}.
 */
public interface ChildrenService {

    /**
     * Save a children.
     *
     * @param childrenDTO the entity to save.
     * @return the persisted entity.
     */
    ChildrenDTO save(ChildrenDTO childrenDTO);

    /**
     * Get all the children.
     *
     * @return the list of entities.
     */
    List<ChildrenDTO> findAll();


    /**
     * Get the "id" children.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChildrenDTO> findOne(Long id);

    /**
     * Delete the "id" children.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
