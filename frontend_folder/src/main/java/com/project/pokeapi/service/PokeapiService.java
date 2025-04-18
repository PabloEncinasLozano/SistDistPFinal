package com.project.pokeapi.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PokeapiService {
    private final RestTemplate resttemplate;



    public PokeapiService(){

        this.resttemplate = new RestTemplate();
    }

    public String getPokemon(String idPokemon){
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
    }
}
