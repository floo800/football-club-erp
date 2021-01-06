package com.mnc.football.service.mapper;


import com.mnc.football.domain.*;
import com.mnc.football.service.dto.WeeklyProgramDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WeeklyProgram} and its DTO {@link WeeklyProgramDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WeeklyProgramMapper extends EntityMapper<WeeklyProgramDTO, WeeklyProgram> {


    @Mapping(target = "trainings", ignore = true)
    @Mapping(target = "removeTrainings", ignore = true)
    WeeklyProgram toEntity(WeeklyProgramDTO weeklyProgramDTO);

    default WeeklyProgram fromId(Long id) {
        if (id == null) {
            return null;
        }
        WeeklyProgram weeklyProgram = new WeeklyProgram();
        weeklyProgram.setId(id);
        return weeklyProgram;
    }
}
