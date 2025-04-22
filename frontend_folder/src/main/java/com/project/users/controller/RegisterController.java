package com.project.users.controller;


import com.project.users.service.RegisterService;
import com.project.users.dto.Usersdto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String procesRegister(@RequestParam String email,@RequestParam String password,@RequestParam String name,@RequestParam String surname,ModelMap interfazConPantalla) {

        String registerSuccess = registerservice.registerUser(email, password, name, surname);

        System.out.println(registerSuccess);
       
        String mensaje = "";

        if (registerSuccess.contains("mensaje")){

            //Separar la parte de "mensaje" del JSON y quedarse solo con la parte
            // que interesa
            mensaje = registerSuccess.split(":")[1];

            // Eliminar las comillas dobles al principio y al final
            mensaje = mensaje.replace("\"", "").trim();

            mensaje = mensaje.substring(0, mensaje.length() - 1);

            System.out.println(mensaje);
        }

        if (registerSuccess.contains("Usuario Registrado Correctamente")){
            return "redirect:/";
        }else{

            interfazConPantalla.addAttribute("error", mensaje);
            return "register";

        }
    }

}
