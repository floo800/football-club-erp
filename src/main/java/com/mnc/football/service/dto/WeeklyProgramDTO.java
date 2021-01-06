package com.mnc.football.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mnc.football.domain.WeeklyProgram} entity.
 */
public class WeeklyProgramDTO implements Serializable {
    
    private Long id;

    private Integer week;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeeklyProgramDTO)) {
            return false;
        }

        return id != null && id.equals(((WeeklyProgramDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WeeklyProgramDTO{" +
            "id=" + getId() +
            ", week=" + getWeek() +
            "}";
    }
}
