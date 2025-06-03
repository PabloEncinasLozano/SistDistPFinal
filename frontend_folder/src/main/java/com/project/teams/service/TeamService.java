package com.project.teams.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.teams.model.Team;
import com.project.teams.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    //private final RestTemplate resttemplate;

    //private final String url = "http://api:8000/list_user_collections"; // URL de la API para obtener los productos
    

    //public TeamService() {
    //    this.resttemplate = new RestTemplate();
    //}

    @Autowired
    private TeamRepository teamRepo;


    //Mostrar la lista de equipos pokemon
    public List<Team> listaEquipos(String email) {

        List<Team> listaEquipos = teamRepo.findByEmail(email);

        return listaEquipos;
    }

    //Crear un nuevo equipo pokemon
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


    public boolean eliminarEquipo(int idEquipo, String email) {

        // Comprobar si el equipo existe y pertenece al usuario
        Team equipo = teamRepo.findById(idEquipo).orElse(null);
        if (equipo == null || !equipo.getEmail().equals(email)) {
            return false;
        }

        teamRepo.delete(equipo);
        return true;
    }


    public Team getTeamById(int id, String email) {

        // Comprobar si el equipo existe y pertenece al usuario
        Team equipo = teamRepo.findById(id).orElse(null);
        if (equipo == null || !equipo.getEmail().equals(email)) {
            return null; 
        }

        return equipo;
    }


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