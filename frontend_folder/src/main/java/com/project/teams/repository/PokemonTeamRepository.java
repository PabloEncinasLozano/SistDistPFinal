package com.project.teams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.teams.model.PokemonTeam;
import com.project.teams.model.PokemonTeamKey;

public interface PokemonTeamRepository extends JpaRepository<PokemonTeam, Integer>{

    int countByTeamId(int teamId); //Contar pokemons en el equipo

    void deleteByTeamIdAndIdName(int teamId, String pokemonName); //Eliminar un pokemon de un equipo

    void deleteById(PokemonTeamKey teamId); //Eliminar un pokemon de un equipo por su id

    
}
