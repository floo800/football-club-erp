package com.mnc.football.repository;

import com.mnc.football.domain.WeeklyProgram;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WeeklyProgram entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WeeklyProgramRepository extends JpaRepository<WeeklyProgram, Long> {
}
