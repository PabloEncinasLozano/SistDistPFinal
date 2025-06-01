package com.project.users.controller;


import com.project.users.service.RegisterService;
import com.project.users.dto.Usersdto;
import com.project.users.model.User;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;



@Controller
public class RegisterController {
    

    private final RegisterService registerService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;



    public RegisterController(RegisterService registerService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.registerService = registerService;
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/register")
    public String mostrarRegistro(ModelMap interfazConPantalla) {

        interfazConPantalla.addAttribute("usuario", new Usersdto());
        return "register";
    }


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
    /*  
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public String procesRegister(@RequestParam String email,@RequestParam String password,@RequestParam String name,@RequestParam String surname,ModelMap interfazConPantalla) {


        // Encriptamos la contraseña antes de pasarla
        String encryptedPassword = encoder.encode(password);
        

        String registerSuccess = registerservice.registerUser(email, encryptedPassword, name, surname);

       
        String mensaje = "";

        if (registerSuccess.contains("mensaje")){

            //Separar la parte de "mensaje" del JSON y quedarse solo con la parte
            // que interesa
            mensaje = registerSuccess.split(":")[1];

            // Eliminar las comillas dobles al principio y al final
            mensaje = mensaje.replace("\"", "").trim();

            mensaje = mensaje.substring(0, mensaje.length() - 1);

            System.out.println(mensaje); //QUITAR DESPUES
        }

        if (registerSuccess.contains("Usuario Registrado Correctamente")){
            return "redirect:/";
        }else{

            interfazConPantalla.addAttribute("error", mensaje);
            return "register";

        }
    }
    */

}
