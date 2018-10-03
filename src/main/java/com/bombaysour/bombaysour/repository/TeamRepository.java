package com.bombaysour.bombaysour.repository;

import com.bombaysour.bombaysour.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    List<Team> findAllByAvailable(Boolean available);

    Team findByAvailableAndId(Boolean available, Long id);
}
