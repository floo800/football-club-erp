package com.mnc.football.service.impl;

import com.mnc.football.service.WeeklyProgramService;
import com.mnc.football.domain.WeeklyProgram;
import com.mnc.football.repository.WeeklyProgramRepository;
import com.mnc.football.service.dto.WeeklyProgramDTO;
import com.mnc.football.service.mapper.WeeklyProgramMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WeeklyProgram}.
 */
@Service
@Transactional
public class WeeklyProgramServiceImpl implements WeeklyProgramService {

    private final Logger log = LoggerFactory.getLogger(WeeklyProgramServiceImpl.class);

    private final WeeklyProgramRepository weeklyProgramRepository;

    private final WeeklyProgramMapper weeklyProgramMapper;

    public WeeklyProgramServiceImpl(WeeklyProgramRepository weeklyProgramRepository, WeeklyProgramMapper weeklyProgramMapper) {
        this.weeklyProgramRepository = weeklyProgramRepository;
        this.weeklyProgramMapper = weeklyProgramMapper;
    }

    @Override
    public WeeklyProgramDTO save(WeeklyProgramDTO weeklyProgramDTO) {
        log.debug("Request to save WeeklyProgram : {}", weeklyProgramDTO);
        WeeklyProgram weeklyProgram = weeklyProgramMapper.toEntity(weeklyProgramDTO);
        weeklyProgram = weeklyProgramRepository.save(weeklyProgram);
        return weeklyProgramMapper.toDto(weeklyProgram);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeeklyProgramDTO> findAll() {
        log.debug("Request to get all WeeklyPrograms");
        return weeklyProgramRepository.findAll().stream()
            .map(weeklyProgramMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WeeklyProgramDTO> findOne(Long id) {
        log.debug("Request to get WeeklyProgram : {}", id);
        return weeklyProgramRepository.findById(id)
            .map(weeklyProgramMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WeeklyProgram : {}", id);
        weeklyProgramRepository.deleteById(id);
    }
}
