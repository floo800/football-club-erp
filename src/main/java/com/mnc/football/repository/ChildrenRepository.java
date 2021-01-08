package com.mnc.football.repository;

import com.mnc.football.domain.Children;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Children entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChildrenRepository extends JpaRepository<Children, Long> {

    @Query("select children from Children children where children.parent.login = ?#{principal.username}")
    List<Children> findByParentIsCurrentUser();
}
