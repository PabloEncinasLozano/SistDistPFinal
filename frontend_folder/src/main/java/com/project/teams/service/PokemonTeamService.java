package com.project.teams.service;

import org.springframework.stereotype.Service;

import com.project.teams.model.PokemonTeam;
import com.project.teams.model.PokemonTeamKey;
import com.project.teams.model.Team;
import com.project.teams.repository.PokemonTeamRepository;
import com.project.teams.repository.TeamRepository;

import jakarta.transaction.Transactional;

/**
 * Servicio para manejar las operaciones relacionadas con los equipos de Pokémon.
 * Permite añadir, eliminar y modificar Pokémon en un equipo, así como obtener un equipo por su ID.
 */
@Service
public class PokemonTeamService {

    private final PokemonTeamRepository pokemonTeamRepo;
    private final TeamRepository teamRepo;


    /**
     * Constructor que inyecta los repositorios necesarios.
     *
     * @param pokemonTeamRepo Repositorio para manejar las operaciones de Pokémon en equipos.
     * @param teamRepo Repositorio para manejar las operaciones de equipos.
     */
    public PokemonTeamService(PokemonTeamRepository pokemonTeamRepo, TeamRepository teamRepo) {
        this.pokemonTeamRepo = pokemonTeamRepo;
        this.teamRepo = teamRepo;
    }


    /**
     * Añade un Pokemon a un equipo.
     *
     * @param email El correo electrónico del usuario que posee el equipo.
     * @param nombrePokemon El nombre del Pokémon a añadir.
     * @param teamId El ID del equipo al que se añadirá el Pokémon.
     * @return true si el Pokémon se añadió correctamente, false en caso contrario.
     */
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


    /**
     * Elimina un Pokémon de un equipo.
     *
     * @param nombrePokemon El nombre del Pokémon a eliminar.
     * @param teamId El ID del equipo del que se eliminará el Pokémon.
     * @return true si el Pokémon se eliminó correctamente, false en caso contrario.
     */
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


    
    /**
     * Cambia el nombre de un Pokémon en un equipo.
     *
     * @param nombreActual El nombre actual del Pokémon.
     * @param nuevoNombrePokemon El nuevo nombre para el Pokémon.
     * @param teamId El ID del equipo al que pertenece el Pokémon.
     * @return true si el nombre se cambió correctamente, false en caso contrario.
     */
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


    /**
     * Obtiene un equipo de Pokémon por su ID y el correo electrónico del propietario.
     *
     * @param id El ID del equipo.
     * @param email El correo electrónico del propietario del equipo.
     * @return El equipo de Pokémon si existe y pertenece al propietario, null en caso contrario.
     */
    public Team getPokemonTeamById(int id, String email) {
        Team team = teamRepo.findById(id).orElse(null);
        if (team == null || !team.getEmail().equals(email)) {
            return null;
        }
        return team;
    }
    
}
