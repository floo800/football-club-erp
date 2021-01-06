package com.mnc.football.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mnc.football.domain.Training} entity.
 */
public class TrainingDTO implements Serializable {
    
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String name;


    private Long teamId;

    private Long weeklyProgramId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getWeeklyProgramId() {
        return weeklyProgramId;
    }

    public void setWeeklyProgramId(Long weeklyProgramId) {
        this.weeklyProgramId = weeklyProgramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrainingDTO)) {
            return false;
        }

        return id != null && id.equals(((TrainingDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrainingDTO{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", name='" + getName() + "'" +
            ", teamId=" + getTeamId() +
            ", weeklyProgramId=" + getWeeklyProgramId() +
            "}";
    }
}
