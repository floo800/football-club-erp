package com.mnc.football.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WeeklyProgramMapperTest {

    private WeeklyProgramMapper weeklyProgramMapper;

    @BeforeEach
    public void setUp() {
        weeklyProgramMapper = new WeeklyProgramMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(weeklyProgramMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(weeklyProgramMapper.fromId(null)).isNull();
    }
}
