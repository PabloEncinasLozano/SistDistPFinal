package com.project.teams.controller;

import java.util.List;
import java.util.Map;

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



@Controller
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }



    @GetMapping("/teamsList")
    public String mostrarItemCollections(ModelMap interfazConPantalla) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        System.out.println("Email del usuario autenticado: " + email); //QUITAR DESPUES
        List<Team> teamList = teamService.listaEquipos(email);

        System.out.println("Email del usuario autenticado: " + email); //QUITAR DESPUES
        interfazConPantalla.addAttribute("listaEquipos", teamList);
        return "teams"; //devolver nombre del archivo html
    }


    @PostMapping("/teamsList/create")
    public String crateItemCollections(@RequestParam ("new_team_name") String name_team, ModelMap interfazConPantalla) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado
        
        boolean exito = teamService.crearEquipo(name_team, email);
    
        if (!exito) {
            interfazConPantalla.addAttribute("error", true);
        }


        return "redirect:/teamsList"; //Volver a la pantalla de colecciones

    }


    @GetMapping("/teamsList/{id}")
    public String mostrarEquipo(@PathVariable ("id") int id, ModelMap interfazConPantalla) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        Team equipo = teamService.getTeamById(id, email);
        

        if (equipo == null) {
            interfazConPantalla.addAttribute("error", true);
            return "redirect:/teamsList"; 
        }


        interfazConPantalla.addAttribute("team", equipo);
    

        return "teamInfo"; //Volver a la pantalla de colecciones

    }


    @GetMapping("/teamsList/{id}/changeName")
    public String cambiarTeamName(@PathVariable ("id") int id, @RequestParam("new_name") String new_name, ModelMap interfazConPantalla) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado


        boolean exito = teamService.cambiarName(email, new_name, id);
        

        if (!exito) {
            interfazConPantalla.addAttribute("error", "Error al cambiar el nombre");
        }

        return "redirect:/teamInfo"; //Volver a la pantalla de colecciones

    }


    /* 
    @PostMapping("/itemCollections/create")
    public String crateItemCollections(@RequestParam ("name_collection") String name_collection, ModelMap interfazConPantalla) {
        
        Map<String, String> creationSuccess = itemCollectionService.createNewCollection(name_collection);
    
        String mensaje = creationSuccess.getOrDefault("mensaje", "Error al crear la colección");


        if (mensaje.equals("Coleccion Creada Correctamente")){
            interfazConPantalla.addAttribute("success", mensaje);
        }else{
            interfazConPantalla.addAttribute("error", mensaje); 
        }

        List<ItemsCollection> collectionList = itemCollectionService.listaCollections();
        interfazConPantalla.addAttribute("itemCollection", collectionList);

        return "itemCollectionsScreen"; //Volver a la pantalla de colecciones

    }
    */
    
}
