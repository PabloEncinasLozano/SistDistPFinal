package com.project.users.controller;

import com.project.users.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.project.users.dto.Usersdto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class LoginController {
 
    private final LoginService loginService;

    public LoginController(LoginService LoginService) {
        this.loginService = LoginService;
    }

    
    @GetMapping("/login")
    public String mostrarLogin(ModelMap interfazConPantalla, @RequestParam (value = "error", required = false) String error) {


        interfazConPantalla.addAttribute("usuario", new Usersdto());

        if (error!= null){
            
            interfazConPantalla.addAttribute("loginError",true);  
        }
        return "login";
    }



    @PostMapping("/borrarUsuario")
    public String borrarUsuario(HttpServletRequest request ){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        

        if (auth != null && auth.isAuthenticated()){
            String email = auth.getName();

            boolean estado = loginService.deleteUserByEmail(email);
            

            if (estado) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate(); // Invalida la sesión actual
                    
                }
                
                return "redirect:/login?borrado=" + estado; // Redirige al login 
            }
        }

        return "redirect:/?error=true";

    }


}
