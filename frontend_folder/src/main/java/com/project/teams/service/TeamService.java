package com.project.teams.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.teams.model.Team;
import com.project.teams.repository.TeamRepository;

import java.util.List;

/**
 * Servicio para manejar las operaciones relacionadas con los equipos de Pokémon.
 * Proporciona métodos para listar, crear, eliminar y modificar equipos.
 */
@Service
public class TeamService {


    @Autowired
    private TeamRepository teamRepo;


    /**
     * Lista todos los equipos de Pokémon asociados a un usuario.
     *
     * @param email El correo electrónico del usuario.
     * @return Una lista de equipos asociados al usuario.
     */
    public List<Team> listaEquipos(String email) {

        List<Team> listaEquipos = teamRepo.findByEmail(email);

        return listaEquipos;
    }

    
    /**
     * Crea un nuevo equipo de Pokémon.
     *
     * @param nombreEquipo El nombre del equipo.
     * @param email El correo electrónico del usuario que crea el equipo.
     * @return true si el equipo se creó correctamente, false si ya existe un equipo con ese nombre.
     */
    public boolean crearEquipo(String nombreEquipo, String email){

        if (teamRepo.existsByNameAndEmail(nombreEquipo, email)) {
            return false; // Un equipo con ese nombre ya existe
        }

        Team nuevoEquipo = new Team();
        nuevoEquipo.setName(nombreEquipo);
        nuevoEquipo.setEmail(email);
        teamRepo.save(nuevoEquipo);
        return true;
    }


    /**
     * Elimina un equipo de Pokémon.
     *
     * @param idEquipo El ID del equipo a eliminar.
     * @param email El correo electrónico del usuario que solicita la eliminación.
     * @return true si el equipo se eliminó correctamente, false si no existe o no pertenece al usuario.
     */
    public boolean eliminarEquipo(int idEquipo, String email) {

        // Comprobar si el equipo existe y pertenece al usuario
        Team equipo = teamRepo.findById(idEquipo).orElse(null);
        if (equipo == null || !equipo.getEmail().equals(email)) {
            return false;
        }

        teamRepo.delete(equipo);
        return true;
    }


    /**
     * Obtiene un equipo de Pokémon por su ID.
     *
     * @param id El ID del equipo.
     * @param email El correo electrónico del usuario que solicita el equipo.
     * @return El equipo si existe y pertenece al usuario, null en caso contrario.
     */
    public Team getTeamById(int id, String email) {

        // Comprobar si el equipo existe y pertenece al usuario
        Team equipo = teamRepo.findById(id).orElse(null);
        if (equipo == null || !equipo.getEmail().equals(email)) {
            return null; 
        }

        return equipo;
    }


    /**
     * Cambia el nombre de un equipo de Pokémon.
     *
     * @param email El correo electrónico del usuario que solicita el cambio.
     * @param new_name El nuevo nombre del equipo.
     * @param id El ID del equipo a modificar.
     * @return true si el nombre se cambió correctamente, false si ya existe un equipo con ese nombre o si no se encontró el equipo.
     */
    public boolean cambiarName (String email, String new_name, int id) {

        // Buscar el equipo por el id
        Team equipo = teamRepo.findById(id).orElse(null);
        if (equipo == null) {
            return false; // No se encontró ningún equipo con ese id
        }

        if (teamRepo.existsByNameAndEmail(new_name, email)) {
            return false; // Ya existe un equipo con ese nombre
        }

        // Cambiar el nombre
        equipo.setName(new_name);
        teamRepo.save(equipo);
        return true;
    }

}