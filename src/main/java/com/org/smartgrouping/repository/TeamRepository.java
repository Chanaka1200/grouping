package com.org.smartgrouping.repository;

import com.org.smartgrouping.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Iterable<Team> findAllByTeamId(int id);
}
