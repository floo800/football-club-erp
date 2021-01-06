package com.mnc.football.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A WeeklyProgram.
 */
@Entity
@Table(name = "weekly_program")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WeeklyProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "week")
    private Integer week;

    @OneToMany(mappedBy = "weeklyProgram")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Training> trainings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeek() {
        return week;
    }

    public WeeklyProgram week(Integer week) {
        this.week = week;
        return this;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public WeeklyProgram trainings(Set<Training> trainings) {
        this.trainings = trainings;
        return this;
    }

    public WeeklyProgram addTrainings(Training training) {
        this.trainings.add(training);
        training.setWeeklyProgram(this);
        return this;
    }

    public WeeklyProgram removeTrainings(Training training) {
        this.trainings.remove(training);
        training.setWeeklyProgram(null);
        return this;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeeklyProgram)) {
            return false;
        }
        return id != null && id.equals(((WeeklyProgram) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WeeklyProgram{" +
            "id=" + getId() +
            ", week=" + getWeek() +
            "}";
    }
}
