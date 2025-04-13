package com.proj.users.controller;

import com.proj.users.service.LoginService;
import com.proj.users.dto.Usersdto;

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

            // Validar el usuario y la contraseña

            String password = usuario.getPassword();
            String email = usuario.getEmail();
            boolean esValido = loginService.userValidation(email, password);

            if (esValido) {
                // Si es válido, redirigir a la página de inicio o dashboard
                return "redirect:/home"; // Cambia esto a la ruta de tu página de inicio
            } else {
                // Si no es válido, mostrar un mensaje de error
                interfazConPantalla.addAttribute("error", "Usuario o contraseña incorrectos");
                return "users"; // Volver a mostrar la página de login
            }
        }

}
