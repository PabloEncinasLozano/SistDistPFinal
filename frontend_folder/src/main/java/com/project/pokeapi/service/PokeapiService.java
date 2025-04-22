package com.project.pokeapi.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

@Service
public class PokeapiService {

    private final RestTemplate resttemplate;
    private final String url = "http://api:8000/pokeAPI?id=";

    public PokeapiService(){
        this.resttemplate = new RestTemplate();
    }

    public Map<String, Object> getPokemon(String idPokemon){

        // Llamada a la API Flask 
        ResponseEntity<Map> response = resttemplate.getForEntity(url + idPokemon, Map.class);

        return response.getBody();


        /* 
        try{
            String url = "http://api:8000/pokeAPI?id=" + idPokemon;

            ResponseEntity<String> response = resttemplate.getForEntity(url, String.class);


            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();

            } else {
                return "Error: No se pudo obtener informacion del Pokemon.";
            }
            
        } catch (Exception e) {
            return "Error al conectar con la API de Pokemon: " + e.getMessage();
        }
            */
    }
}
