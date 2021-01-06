package com.mnc.football.web.rest;

import com.mnc.football.service.ChildrenService;
import com.mnc.football.web.rest.errors.BadRequestAlertException;
import com.mnc.football.service.dto.ChildrenDTO;

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
 * REST controller for managing {@link com.mnc.football.domain.Children}.
 */
@RestController
@RequestMapping("/api")
public class ChildrenResource {

    private final Logger log = LoggerFactory.getLogger(ChildrenResource.class);

    private static final String ENTITY_NAME = "children";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChildrenService childrenService;

    public ChildrenResource(ChildrenService childrenService) {
        this.childrenService = childrenService;
    }

    /**
     * {@code POST  /children} : Create a new children.
     *
     * @param childrenDTO the childrenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new childrenDTO, or with status {@code 400 (Bad Request)} if the children has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/children")
    public ResponseEntity<ChildrenDTO> createChildren(@RequestBody ChildrenDTO childrenDTO) throws URISyntaxException {
        log.debug("REST request to save Children : {}", childrenDTO);
        if (childrenDTO.getId() != null) {
            throw new BadRequestAlertException("A new children cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChildrenDTO result = childrenService.save(childrenDTO);
        return ResponseEntity.created(new URI("/api/children/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /children} : Updates an existing children.
     *
     * @param childrenDTO the childrenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated childrenDTO,
     * or with status {@code 400 (Bad Request)} if the childrenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the childrenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/children")
    public ResponseEntity<ChildrenDTO> updateChildren(@RequestBody ChildrenDTO childrenDTO) throws URISyntaxException {
        log.debug("REST request to update Children : {}", childrenDTO);
        if (childrenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChildrenDTO result = childrenService.save(childrenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, childrenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /children} : get all the children.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of children in body.
     */
    @GetMapping("/children")
    public List<ChildrenDTO> getAllChildren() {
        log.debug("REST request to get all Children");
        return childrenService.findAll();
    }

    /**
     * {@code GET  /children/:id} : get the "id" children.
     *
     * @param id the id of the childrenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the childrenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/children/{id}")
    public ResponseEntity<ChildrenDTO> getChildren(@PathVariable Long id) {
        log.debug("REST request to get Children : {}", id);
        Optional<ChildrenDTO> childrenDTO = childrenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(childrenDTO);
    }

    /**
     * {@code DELETE  /children/:id} : delete the "id" children.
     *
     * @param id the id of the childrenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/children/{id}")
    public ResponseEntity<Void> deleteChildren(@PathVariable Long id) {
        log.debug("REST request to delete Children : {}", id);
        childrenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
