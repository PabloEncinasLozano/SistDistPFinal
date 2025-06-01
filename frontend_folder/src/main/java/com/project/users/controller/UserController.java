package com.project.users.controller;

import com.project.users.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.users.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    
    @GetMapping("/userInfo")
    public String mostrarUserInfo(ModelMap interfazConPantalla, @RequestParam (value = "error", required = false) String error) {


        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        User usuario = userService.getUserByEmail(email);


        interfazConPantalla.addAttribute("usuario", usuario);

        if (error!= null){
            
            interfazConPantalla.addAttribute("error",true);  
        }
        return "userInfo";
    }


    @PostMapping("/userInfo/changeName")
    public String cambiarUserName(ModelMap interfazConPantalla, @RequestParam ("new_name") String new_name) {


        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        boolean exito = userService.changeName(email, new_name);

        if (!exito) {
            interfazConPantalla.addAttribute("error", "Error al cambiar el nombre");
        }

        return "redirect:/userInfo"; // Redirige a la misma página para mostrar la info actualizada
    }



    @PostMapping("/userInfo/changeSurname")
    public String cambiarUserSurname(ModelMap interfazConPantalla, @RequestParam ("new_surname") String new_surname) {


        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        boolean exito = userService.changeSurname(email, new_surname);

        if (!exito) {
            interfazConPantalla.addAttribute("error", "Error al cambiar el apellido");
        }

        return "redirect:/userInfo";
    }


    @PostMapping("/userInfo/changeEmail")
    public String cambiarUserEmail(ModelMap interfazConPantalla, @RequestParam ("new_email") String new_email, HttpServletRequest request,RedirectAttributes redirectAttributes) {


        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        boolean exito = userService.changeEmail(email, new_email);

        if (exito) {

            // Invalidar sesión actual
            request.getSession().invalidate();

            SecurityContextHolder.clearContext();

            redirectAttributes.addFlashAttribute("emailCambiado", true);

            return "redirect:/login";

        } else {
            redirectAttributes.addFlashAttribute("error", true);

            return "redirect:/userInfo";
        }
    }


    @PostMapping("/userInfo/changePassword")
    public String cambiarUserPassword(ModelMap interfazConPantalla, @RequestParam ("new_password") String new_password, HttpServletRequest request,RedirectAttributes redirectAttributes) {


        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el email del usuario autenticado

        boolean exito = userService.changePassword(email, new_password);

        if (exito) {

            // Invalidar sesión actual
            request.getSession().invalidate();

            SecurityContextHolder.clearContext();

            redirectAttributes.addFlashAttribute("passwordCambiada", true);

            return "redirect:/login";

        } else {	
            redirectAttributes.addFlashAttribute("errorPassword", true);

            return "redirect:/userInfo";
        }
    }



    
}
