package com.project.pokeapi.controller;


import com.project.pokeapi.service.PokeapiService;
import com.project.pokeapi.dto.Pokeapidto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PokeapiController {
    private final PokeapiService pokeapiservice;

    public PokeapiController(com.project.pokeapi.service.PokeapiService pokeapiservice) {
        this.pokeapiservice = pokeapiservice;
    }

    @GetMapping("/showPokeAPI")
    public String mostrarBuscarPokemon(ModelMap interfazConPantalla){
        interfazConPantalla.addAttribute("Pokemon", new Pokeapidto());
        return "pokeAPI";
    }

    @GetMapping("/searchPokeAPI")
    public String searchPokemon( ModelMap interfazConPantalla, String id){

        String infoPokemonObtenida = pokeapiservice.getPokemon(id);

        interfazConPantalla.addAttribute("Pokemon", infoPokemonObtenida);

        return "pokeapi_info"; //devolver nombre del archivo html
        
    }

}
