package com.project.users.controller;


import com.project.users.service.RegisterService;
import com.project.users.dto.Usersdto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    

    private final RegisterService registerservice;

    public RegisterController(RegisterService registerservice) {
        this.registerservice = registerservice;
    }

    @GetMapping("/register")
    public String mostrarRegistro(ModelMap interfazConPantalla) {

        interfazConPantalla.addAttribute("usuario", new Usersdto());
        return "register";
        }



    @PostMapping("/register")
    public String procesRegister(String email, String password, String nombre, String apellido,ModelMap interfazConPantalla) {

        String registerSuccess = registerservice.registerUser(email, password, nombre, apellido);

        if (registerSuccess.contains("Valido")){
            return "redirect:/";
        }else{

            interfazConPantalla.addAttribute("Error", registerSuccess);
            return "register";

        }
    }

}
