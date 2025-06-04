package com.project.pokeapi.controller;


import com.project.pokeapi.service.PokeapiService;
import com.project.pokeapi.dto.Pokeapidto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Controlador para manejar las peticiones relacionadas con la API de Pokemon.
 * Permite buscar información de un Pokémon por su ID o nombre.
 */
@Controller
public class PokeapiController {

    
    private final PokeapiService pokeapiservice;

    /**
     * Constructor que inyecta el servicio PokeapiService.
     *
     * @param pokeapiservice Servicio para interactuar con la API de Pokemon.
     */
    public PokeapiController(com.project.pokeapi.service.PokeapiService pokeapiservice) {
        this.pokeapiservice = pokeapiservice;
    }

    /**
     * Muestra la página para buscar un Pokémon.
     *
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @return Nombre de la vista HTML para buscar Pokémon.
     */
    @GetMapping("/pokeAPI")
    public String mostrarBuscarPokemon(ModelMap interfazConPantalla){
        interfazConPantalla.addAttribute("Pokemon", new Pokeapidto());
        return "pokeapi";
    }


    /**
     * Maneja la búsqueda de un Pokémon por su ID o nombre.
     *
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @param idPokemon ID o nombre del Pokémon a buscar.
     * @return Nombre de la vista HTML con los datos del Pokémon.
     */
    @PostMapping("/pokeAPI")
    public String searchPokemon( ModelMap interfazConPantalla,@RequestParam String idPokemon){
        try {
            Map<String, Object> datosPokemon = pokeapiservice.getPokemon(idPokemon);
            interfazConPantalla.addAttribute("datosPokemon", datosPokemon);

        } catch (Exception e) {
            interfazConPantalla.addAttribute("error", "No se pudo obtener la información del Pokémon");
        }

        return "pokeapi"; //devolver nombre del archivo html
        
    }

}
