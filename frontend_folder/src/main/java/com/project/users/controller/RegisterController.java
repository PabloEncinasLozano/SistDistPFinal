package com.project.users.controller;


import com.project.users.service.RegisterService;
import com.project.users.dto.Usersdto;
import com.project.users.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.authentication.AuthenticationManager;


/**
 * Controlador para manejar el registro de nuevos usuarios.
 * Proporciona métodos para mostrar el formulario de registro y procesar el registro.
 */
@Controller
public class RegisterController {
    

    private final RegisterService registerService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;


    /**
     * Constructor de la clase RegisterController.
     * 
     * @param registerService Servicio para manejar el registro de usuarios.
     * @param authenticationManager Manejador de autenticación.
     * @param userDetailsService Servicio para cargar detalles del usuario.
     */
    public RegisterController(RegisterService registerService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.registerService = registerService;
        this.userDetailsService = userDetailsService;
    }


    /**
     * Muestra el formulario de registro.
     * 
     * @param interfazConPantalla Modelo para la vista.
     * @return Nombre de la vista del formulario de registro.
     */
    @GetMapping("/register")
    public String mostrarRegistro(ModelMap interfazConPantalla) {

        interfazConPantalla.addAttribute("usuario", new Usersdto());
        return "register";
    }


    /**
     * Procesa el registro de un nuevo usuario.
     * 
     * @param usuariodto DTO que contiene los datos del usuario a registrar.
     * @param interfazConPantalla Modelo para la vista.
     * @param redirectAttributes Atributos para redirección.
     * @return Redirección a la página de inicio de sesión o al formulario de registro con errores.
     */
    @PostMapping("/register")
    public String procesarRegistro(@ModelAttribute("usuario") Usersdto usuariodto, ModelMap interfazConPantalla, RedirectAttributes redirectAttributes) {

        try {

            User usuario = new User();

            usuario.setEmail(usuariodto.getEmail());
            usuario.setPassword(usuariodto.getPassword());
            usuario.setName(usuariodto.getName());
            usuario.setSurname(usuariodto.getSurname());

            registerService.registerUser(usuario);  

            redirectAttributes.addFlashAttribute("registroExitoso", true);

            return "redirect:/login";
            

        } catch (IllegalArgumentException e) {
            interfazConPantalla.addAttribute("error", e.getMessage()); // Mostrar el error en el HTML
            return "register";
        }  
    }
    
}
