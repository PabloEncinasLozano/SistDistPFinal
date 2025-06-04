package com.project.teams.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.teams.model.Team;
import com.project.teams.service.PokemonTeamService;


// Controlador para manejar las peticiones relacionadas con los equipos de Pokémon.
@Controller
public class PokemonTeamController {

    private final PokemonTeamService pokeTeamService;

    /**
     * Constructor que inyecta el servicio PokemonTeamService.
     *
     * @param pokeTeamService Servicio para manejar la lógica de equipos de Pokémon.
     */
    public PokemonTeamController(PokemonTeamService pokeTeamService) {
        this.pokeTeamService = pokeTeamService;
    }


    /**
     * Muestra la página de equipos de Pokémon.
     *
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @return Nombre de la vista HTML para mostrar los equipos.
     */
    @GetMapping("/teamsList/{id}")
    public String mostrarPokemonsEnEquipo(@PathVariable ("id") int id, ModelMap interfazConPantalla) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        Team equipo = pokeTeamService.getPokemonTeamById(id, email);
        

        if (equipo == null) {
            interfazConPantalla.addAttribute("error", true);
            return "redirect:/teamsList"; 
        }

        interfazConPantalla.addAttribute("team", equipo);
        interfazConPantalla.addAttribute("listaPokemons", equipo.getPokemons());
    

        return "teamInfo"; //Volver a la pantalla de colecciones

    }


    /**
     * Muestra la página de equipos de Pokémon.
     *
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @return Nombre de la vista HTML para mostrar los equipos.
     */
    @PostMapping("/teamsList/{id}/addPokemon")
    public String meterPokemonToTeam(@PathVariable ("id") int id, @RequestParam("new_pokemon") String new_pokemon, ModelMap interfazConPantalla, RedirectAttributes redirectAttributes) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        boolean exito = pokeTeamService.addPokemon(email, new_pokemon,id);

        if (!exito) {
            redirectAttributes.addFlashAttribute("errorMaxPokemons", true);
        }

        return "redirect:/teamsList/"+id; //Volver a la pantalla de colecciones

    }



    /**
     * Elimina un Pokémon del equipo.
     *
     * @param id El ID del equipo del que se eliminará el Pokémon.
     * @param name_pokemon El nombre del Pokémon a eliminar.
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @param redirectAttributes Atributos para redirección.
     * @return Redirección a la página del equipo después de eliminar el Pokémon.
     */
    @PostMapping("/teamsList/{id}/removePokemon")
    public String borraPokemonDeTeam(@PathVariable ("id") int id, @RequestParam("name_pokemon") String name_pokemon, ModelMap interfazConPantalla, RedirectAttributes redirectAttributes) {

        boolean exito = pokeTeamService.removePokemon(name_pokemon,id);
        
        if (!exito) {
            redirectAttributes.addFlashAttribute("error", true);
        }

        return "redirect:/teamsList/"+id; //Volver a la pantalla de colecciones

    }


    /**
     * Cambia el nombre de un Pokémon en el equipo.
     *
     * @param id El ID del equipo al que pertenece el Pokémon.
     * @param current_name El nombre actual del Pokémon.
     * @param new_name_pokemon El nuevo nombre para el Pokémon.
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @param redirectAttributes Atributos para redirección.
     * @return Redirección a la página del equipo después de cambiar el nombre del Pokémon.
     */
    @PostMapping("/teamsList/{id}/changePokemonName")
    public String cambiarNombrePokemon(@PathVariable ("id") int id, @RequestParam("current_name") String current_name,@RequestParam("new_name") String new_name_pokemon, ModelMap interfazConPantalla, RedirectAttributes redirectAttributes) {

        boolean exito = pokeTeamService.changeNamePokemon(current_name,new_name_pokemon,id);
        
        if (!exito) {
            redirectAttributes.addFlashAttribute("error", true);
        }

        return "redirect:/teamsList/"+id; //Volver a la pantalla de colecciones

    }
    
}
