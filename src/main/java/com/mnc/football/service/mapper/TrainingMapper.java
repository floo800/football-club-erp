package com.mnc.football.service.mapper;


import com.mnc.football.domain.*;
import com.mnc.football.service.dto.TrainingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Training} and its DTO {@link TrainingDTO}.
 */
@Mapper(componentModel = "spring", uses = {TeamMapper.class, WeeklyProgramMapper.class})
public interface TrainingMapper extends EntityMapper<TrainingDTO, Training> {

    @Mapping(source = "team.id", target = "teamId")
    @Mapping(source = "weeklyProgram.id", target = "weeklyProgramId")
    TrainingDTO toDto(Training training);

    @Mapping(source = "teamId", target = "team")
    @Mapping(source = "weeklyProgramId", target = "weeklyProgram")
    Training toEntity(TrainingDTO trainingDTO);

    default Training fromId(Long id) {
        if (id == null) {
            return null;
        }
        Training training = new Training();
        training.setId(id);
        return training;
    }
}
