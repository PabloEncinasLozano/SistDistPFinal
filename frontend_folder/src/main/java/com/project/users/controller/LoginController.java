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


/**
 * Controlador para manejar las operaciones de inicio de sesión y eliminación de usuarios.
 * Proporciona métodos para mostrar el formulario de inicio de sesión y procesar la eliminación de usuarios.
 */
@Controller
public class LoginController {
 
    private final LoginService loginService;

    /**
     * Constructor del controlador que inyecta el servicio de inicio de sesión.
     *
     * @param LoginService Servicio de inicio de sesión utilizado para manejar la lógica de negocio.
     */
    public LoginController(LoginService LoginService) {
        this.loginService = LoginService;
    }

    
    /**
     * Muestra el formulario de inicio de sesión.
     *
     * @param interfazConPantalla Modelo que se utiliza para pasar datos a la vista.
     * @param error Parámetro opcional que indica si hubo un error en el inicio de sesión.
     * @return Nombre de la vista de inicio de sesión.
     */
    @GetMapping("/login")
    public String mostrarLogin(ModelMap interfazConPantalla, @RequestParam (value = "error", required = false) String error) {

        interfazConPantalla.addAttribute("usuario", new Usersdto());

        if (error!= null){
            
            interfazConPantalla.addAttribute("loginError",true);  
        }
        return "login";
    }


    /**
     * Procesa la eliminación de un usuario.
     * Invalida la sesión del usuario y redirige al formulario de inicio de sesión.
     *
     * @param request Objeto HttpServletRequest para acceder a la sesión actual.
     * @return Redirección al formulario de inicio de sesión con un mensaje de éxito o error.
     */
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
