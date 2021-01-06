package com.mnc.football.service.impl;

import com.mnc.football.service.ChildrenService;
import com.mnc.football.domain.Children;
import com.mnc.football.repository.ChildrenRepository;
import com.mnc.football.service.dto.ChildrenDTO;
import com.mnc.football.service.mapper.ChildrenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Children}.
 */
@Service
@Transactional
public class ChildrenServiceImpl implements ChildrenService {

    private final Logger log = LoggerFactory.getLogger(ChildrenServiceImpl.class);

    private final ChildrenRepository childrenRepository;

    private final ChildrenMapper childrenMapper;

    public ChildrenServiceImpl(ChildrenRepository childrenRepository, ChildrenMapper childrenMapper) {
        this.childrenRepository = childrenRepository;
        this.childrenMapper = childrenMapper;
    }

    @Override
    public ChildrenDTO save(ChildrenDTO childrenDTO) {
        log.debug("Request to save Children : {}", childrenDTO);
        Children children = childrenMapper.toEntity(childrenDTO);
        children = childrenRepository.save(children);
        return childrenMapper.toDto(children);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChildrenDTO> findAll() {
        log.debug("Request to get all Children");
        return childrenRepository.findAll().stream()
            .map(childrenMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ChildrenDTO> findOne(Long id) {
        log.debug("Request to get Children : {}", id);
        return childrenRepository.findById(id)
            .map(childrenMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Children : {}", id);
        childrenRepository.deleteById(id);
    }
}
