package com.project.teams.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teamdto {
    @Column(name = "id")
    private long id;
    private String name_team;
    private String email;
}