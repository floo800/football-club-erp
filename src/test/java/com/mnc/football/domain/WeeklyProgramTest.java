package com.mnc.football.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mnc.football.web.rest.TestUtil;

public class WeeklyProgramTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeeklyProgram.class);
        WeeklyProgram weeklyProgram1 = new WeeklyProgram();
        weeklyProgram1.setId(1L);
        WeeklyProgram weeklyProgram2 = new WeeklyProgram();
        weeklyProgram2.setId(weeklyProgram1.getId());
        assertThat(weeklyProgram1).isEqualTo(weeklyProgram2);
        weeklyProgram2.setId(2L);
        assertThat(weeklyProgram1).isNotEqualTo(weeklyProgram2);
        weeklyProgram1.setId(null);
        assertThat(weeklyProgram1).isNotEqualTo(weeklyProgram2);
    }
}
