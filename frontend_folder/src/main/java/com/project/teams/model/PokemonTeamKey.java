package com.project.teams.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PokemonTeamKey {

    @Column(name = "team_id")
    private int teamId;

    @Column(name = "pokemon_name")
    private String name;
    
}
