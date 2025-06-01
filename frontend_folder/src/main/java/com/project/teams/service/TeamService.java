package com.project.teams.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.teams.model.Team;
import com.project.teams.repository.TeamRepository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

        // Buscar el equipo por el email del usuario
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




    /*
    //TEMPORAL, CAMBIARLO MAS ADELANTE
    private final int user_id = 1; // Cambia el user_id según sea necesario


    public List<ItemsCollection> listaCollections() {
        String url_with_user = url + "?user_id=" + user_id; // Cambia el user_id según sea necesario

        // Realiza la llamada a la API y obtiene la respuesta
        ResponseEntity<List<ItemsCollection>> response = resttemplate.exchange(url_with_user, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ItemsCollection>>() {});
        return response.getBody();
    }




    public Map<String,String> createNewCollection(String collection_name){
        try {

            String url = "http://api:8000/create_collection"; // URL de la API para crear una nueva colección


            Map<String,Object> dict_name_userid= new HashMap<>();
            dict_name_userid.put("name_collection", collection_name);
            dict_name_userid.put("user_id", user_id);

            // Conexion con API

            HttpHeaders headers = new HttpHeaders(); //Define tipo de archivo que le va a llegar
            headers.set("Content-Type", "application/json");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(dict_name_userid, headers); //Hacer como si fuera JSON
            
            //Se hace la peticion a la API
            ResponseEntity<Map> response = resttemplate.postForEntity(url, request, Map.class);

            return response.getBody();

        } catch (Exception e){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("mensaje", "Error al conectar con la API Flask");
            return errorMap;
        }
    }
    */
}