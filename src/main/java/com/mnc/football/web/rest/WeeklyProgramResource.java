package com.mnc.football.web.rest;

import com.mnc.football.service.WeeklyProgramService;
import com.mnc.football.web.rest.errors.BadRequestAlertException;
import com.mnc.football.service.dto.WeeklyProgramDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mnc.football.domain.WeeklyProgram}.
 */
@RestController
@RequestMapping("/api")
public class WeeklyProgramResource {

    private final Logger log = LoggerFactory.getLogger(WeeklyProgramResource.class);

    private static final String ENTITY_NAME = "weeklyProgram";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WeeklyProgramService weeklyProgramService;

    public WeeklyProgramResource(WeeklyProgramService weeklyProgramService) {
        this.weeklyProgramService = weeklyProgramService;
    }

    /**
     * {@code POST  /weekly-programs} : Create a new weeklyProgram.
     *
     * @param weeklyProgramDTO the weeklyProgramDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new weeklyProgramDTO, or with status {@code 400 (Bad Request)} if the weeklyProgram has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/weekly-programs")
    public ResponseEntity<WeeklyProgramDTO> createWeeklyProgram(@RequestBody WeeklyProgramDTO weeklyProgramDTO) throws URISyntaxException {
        log.debug("REST request to save WeeklyProgram : {}", weeklyProgramDTO);
        if (weeklyProgramDTO.getId() != null) {
            throw new BadRequestAlertException("A new weeklyProgram cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WeeklyProgramDTO result = weeklyProgramService.save(weeklyProgramDTO);
        return ResponseEntity.created(new URI("/api/weekly-programs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /weekly-programs} : Updates an existing weeklyProgram.
     *
     * @param weeklyProgramDTO the weeklyProgramDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated weeklyProgramDTO,
     * or with status {@code 400 (Bad Request)} if the weeklyProgramDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the weeklyProgramDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/weekly-programs")
    public ResponseEntity<WeeklyProgramDTO> updateWeeklyProgram(@RequestBody WeeklyProgramDTO weeklyProgramDTO) throws URISyntaxException {
        log.debug("REST request to update WeeklyProgram : {}", weeklyProgramDTO);
        if (weeklyProgramDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WeeklyProgramDTO result = weeklyProgramService.save(weeklyProgramDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, weeklyProgramDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /weekly-programs} : get all the weeklyPrograms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of weeklyPrograms in body.
     */
    @GetMapping("/weekly-programs")
    public List<WeeklyProgramDTO> getAllWeeklyPrograms() {
        log.debug("REST request to get all WeeklyPrograms");
        return weeklyProgramService.findAll();
    }

    /**
     * {@code GET  /weekly-programs/:id} : get the "id" weeklyProgram.
     *
     * @param id the id of the weeklyProgramDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the weeklyProgramDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/weekly-programs/{id}")
    public ResponseEntity<WeeklyProgramDTO> getWeeklyProgram(@PathVariable Long id) {
        log.debug("REST request to get WeeklyProgram : {}", id);
        Optional<WeeklyProgramDTO> weeklyProgramDTO = weeklyProgramService.findOne(id);
        return ResponseUtil.wrapOrNotFound(weeklyProgramDTO);
    }

    /**
     * {@code DELETE  /weekly-programs/:id} : delete the "id" weeklyProgram.
     *
     * @param id the id of the weeklyProgramDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/weekly-programs/{id}")
    public ResponseEntity<Void> deleteWeeklyProgram(@PathVariable Long id) {
        log.debug("REST request to delete WeeklyProgram : {}", id);
        weeklyProgramService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
