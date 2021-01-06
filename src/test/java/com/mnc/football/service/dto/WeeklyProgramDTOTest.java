package com.mnc.football.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mnc.football.web.rest.TestUtil;

public class WeeklyProgramDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeeklyProgramDTO.class);
        WeeklyProgramDTO weeklyProgramDTO1 = new WeeklyProgramDTO();
        weeklyProgramDTO1.setId(1L);
        WeeklyProgramDTO weeklyProgramDTO2 = new WeeklyProgramDTO();
        assertThat(weeklyProgramDTO1).isNotEqualTo(weeklyProgramDTO2);
        weeklyProgramDTO2.setId(weeklyProgramDTO1.getId());
        assertThat(weeklyProgramDTO1).isEqualTo(weeklyProgramDTO2);
        weeklyProgramDTO2.setId(2L);
        assertThat(weeklyProgramDTO1).isNotEqualTo(weeklyProgramDTO2);
        weeklyProgramDTO1.setId(null);
        assertThat(weeklyProgramDTO1).isNotEqualTo(weeklyProgramDTO2);
    }
}
