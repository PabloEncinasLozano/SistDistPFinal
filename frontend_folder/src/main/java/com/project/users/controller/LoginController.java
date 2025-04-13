package com.project.users.controller;

import com.project.users.service.LoginService;
import com.project.users.dto.Usersdto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService LoginService) {
        this.loginService = LoginService;
    }

    @GetMapping("/login")
        public String mostrarLogin(ModelMap interfazConPantalla) {

            interfazConPantalla.addAttribute("usuario", new Usersdto());
            return "users";
        }


    @GetMapping("/login/validar")
        public String validarLogin(Usersdto usuario, ModelMap interfazConPantalla) {


            String password = usuario.getPassword();
            String email = usuario.getEmail();
            boolean esValido = loginService.userValidation(email, password);

            if (esValido) {
                // Redirigir al cotalogo
                return "redirect:/home"; // Cambiar esto a catalogo de tienda (pagina principal)
            } else {
                // Mostrar mensaje de error
                interfazConPantalla.addAttribute("error", "Usuario o contraseña incorrectos");
                return "users"; // Volver a mostrar login
            }
        }

}
