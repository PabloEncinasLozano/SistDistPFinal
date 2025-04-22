package com.project.pokeapi.controller;


import com.project.pokeapi.service.PokeapiService;
import com.project.pokeapi.dto.Pokeapidto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PokeapiController {
    private final PokeapiService pokeapiservice;

    public PokeapiController(com.project.pokeapi.service.PokeapiService pokeapiservice) {
        this.pokeapiservice = pokeapiservice;
    }

    @GetMapping("/pokeAPI")
    public String mostrarBuscarPokemon(ModelMap interfazConPantalla){
        interfazConPantalla.addAttribute("Pokemon", new Pokeapidto());
        return "pokeapi";
    }




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
