package com.project.teams.service;

import org.springframework.stereotype.Service;

import com.project.teams.model.PokemonTeam;
import com.project.teams.model.PokemonTeamKey;
import com.project.teams.model.Team;
import com.project.teams.repository.PokemonTeamRepository;
import com.project.teams.repository.TeamRepository;

import jakarta.transaction.Transactional;

@Service
public class PokemonTeamService {

    private final PokemonTeamRepository pokemonTeamRepo;
    private final TeamRepository teamRepo;


    public PokemonTeamService(PokemonTeamRepository pokemonTeamRepo, TeamRepository teamRepo) {
        this.pokemonTeamRepo = pokemonTeamRepo;
        this.teamRepo = teamRepo;
    }




    //Añadir un pokemon al equipo
    public boolean addPokemon(String email, String nombrePokemon, int teamId){

        // Buscar el equipo por el id
        Team equipo = teamRepo.findById(teamId).orElse(null);

        if (equipo == null) {
            return false; // No se encontró ningún equipo con ese id
        }

        if (equipo.getPokemons().size() == 6) {
            return false; // Nuemro máximo de Pokémon alcanzado
        }

        PokemonTeamKey idPokemon = new PokemonTeamKey(teamId, nombrePokemon.toLowerCase());
        PokemonTeam nuevoPokemon = new PokemonTeam(idPokemon, equipo);
        
        try {
            pokemonTeamRepo.save(nuevoPokemon);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //Borrar un pokemon del equipo
    @Transactional
    public boolean removePokemon(String nombrePokemon, int teamId){

        // Buscar el equipo por el id
        Team equipo = teamRepo.findById(teamId).orElse(null);

        if (equipo == null) {
            return false; // No se encontró ningún equipo con ese id
        }

        PokemonTeamKey idPokemon = new PokemonTeamKey(teamId, nombrePokemon.toLowerCase());


        pokemonTeamRepo.deleteById(idPokemon);

        return true;
    }


    
    //cambiar nombre de un pokemon del equipo
    @Transactional
    public boolean changeNamePokemon(String nombreActual, String nuevoNombrePokemon, int teamId){

        // Buscar el equipo por el id
        Team equipo = teamRepo.findById(teamId).orElse(null);

        if (equipo == null) {
            return false; // No se encontró ningún equipo con ese id
        }

        PokemonTeamKey idActual = new PokemonTeamKey(teamId, nombreActual.toLowerCase());

        pokemonTeamRepo.deleteById(idActual);

        PokemonTeamKey idNuevoPokemon = new PokemonTeamKey(teamId, nuevoNombrePokemon.toLowerCase());
        PokemonTeam nuevoPokemon = new PokemonTeam(idNuevoPokemon, equipo);

        pokemonTeamRepo.save(nuevoPokemon);

        return true;
    }


    public Team getPokemonTeamById(int id, String email) {
        Team team = teamRepo.findById(id).orElse(null);
        if (team == null || !team.getEmail().equals(email)) {
            return null;
        }
        return team;
    }
    
}
