package com.project.teams.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.teams.model.Team;
import com.project.teams.service.TeamService;


/**
 * Controlador para manejar las peticiones relacionadas con los equipos de Pokémon.
 * Permite crear, eliminar y modificar equipos, así como listar los equipos del usuario autenticado.
 */
@Controller
public class TeamController {

    private final TeamService teamService;

    /**
     * Constructor que inyecta el servicio TeamService.
     *
     * @param teamService Servicio para manejar la lógica de equipos de Pokémon.
     */
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    /**
     * Muestra la página de equipos de Pokémon.
     *
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @return Nombre de la vista HTML para mostrar los equipos.
     */
    @GetMapping("/teamsList")
    public String mostrarEquipos(ModelMap interfazConPantalla) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        List<Team> teamList = teamService.listaEquipos(email);

        interfazConPantalla.addAttribute("listaEquipos", teamList);
        return "teams"; //devolver nombre del archivo html
    }


    /**
     * Muestra la página para crear un nuevo equipo de Pokémon.
     *
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @return Nombre de la vista HTML para crear un equipo.
     */
    @PostMapping("/teamsList/create")
    public String crearEquipo(@RequestParam ("new_team_name") String name_team, ModelMap interfazConPantalla, RedirectAttributes redirectAttributes) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado
        
        boolean exito = teamService.crearEquipo(name_team, email);
    
        if (!exito) {
            redirectAttributes.addFlashAttribute("error", true);
        }


        return "redirect:/teamsList"; //Volver a la pantalla de colecciones

    }


    /**
     * Muestra la página de información de un equipo de Pokémon.
     *
     * @param id El ID del equipo.
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @return Nombre de la vista HTML con los datos del equipo.
     */
    @PostMapping("/teamsList/{id}/removeTeam")
    public String borrarEquipo(@PathVariable ("id") int id, @RequestParam ("id_team") int id_team, ModelMap interfazConPantalla) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado
        
        boolean exito = teamService.eliminarEquipo(id_team, email);
    
        if (!exito) {
            interfazConPantalla.addAttribute("error", true);
        }

        return "redirect:/teamsList"; //Volver a la pantalla de colecciones
    }


    /**
     * Muestra la página de información de un equipo de Pokémon.
     *
     * @param id El ID del equipo.
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @return Nombre de la vista HTML con los datos del equipo.
     */
    @PostMapping("/teamsList/{id}/changeName")
    public String cambiarNombreEquipo(@PathVariable ("id") int id, @RequestParam("new_name") String new_name, ModelMap interfazConPantalla) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        boolean exito = teamService.cambiarName(email, new_name, id);

        if (!exito) {
            interfazConPantalla.addAttribute("error", "Error al cambiar el nombre");
        }

        return "redirect:/teamsList/"+id; //Volver a la pantalla de colecciones
    }
    
}
