package com.project.users.controller;

import com.project.users.service.LoginService;
import com.project.users.dto.Usersdto;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService LoginService) {
        this.loginService = LoginService;
    }

    @GetMapping("/login")
    public String mostrarLogin(ModelMap interfazConPantalla) {

        interfazConPantalla.addAttribute("usuario", new Usersdto());
        return "login";
        }



    @PostMapping("/login")
    public String processLogin(ModelMap interfazConPantalla,String email, String password){


        String loginSuccess = loginService.loginUser(email, password);

        String mensaje = "";

        if (loginSuccess.contains("mensaje")){

            //Separar la parte de "mensaje" del JSON y quedarse solo con la parte
            // que interesa
            mensaje = loginSuccess.split(":")[1];

            // Eliminar las comillas dobles al principio y al final
            mensaje = mensaje.replace("\"", "").trim();

            mensaje = mensaje.substring(0, mensaje.length() - 1);
        }


        if (mensaje.equals("Usuario Valido")){
            return "redirect:/"; //Pasar al catalogo
        }else{
            interfazConPantalla.addAttribute("error", mensaje);
            return "login"; //Mantenerse en el login por fallo al loguearse
        }
    }

}
