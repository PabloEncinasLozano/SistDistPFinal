package com.project.teams.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.teams.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{

    List<Team> findByEmail(String email);

    boolean existsByNameAndEmail(String name, String email);
    
}
