package com.project.pokeapi.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

/**
 * Servicio para interactuar con la API de Pokemon.
 * Permite obtener información de un Pokémon por su ID o nombre.
 */
@Service
public class PokeapiService {

    private final RestTemplate resttemplate;
    private final String url = "http://api:8000/pokeAPI?id=";

    /**
     * Constructor que inicializa el RestTemplate.
     */
    public PokeapiService(){
        this.resttemplate = new RestTemplate();
    }

    /**
     * Método que obtiene la información de un Pokémon por su ID o nombre.
     *
     * @param idPokemon ID o nombre del Pokémon a buscar.
     * @return Un mapa con los datos del Pokémon.
     */
    public Map<String, Object> getPokemon(String idPokemon){

        // Llamada a la API Flask 
        ResponseEntity<Map> response = resttemplate.getForEntity(url + idPokemon, Map.class);

        return response.getBody();


    }
}
