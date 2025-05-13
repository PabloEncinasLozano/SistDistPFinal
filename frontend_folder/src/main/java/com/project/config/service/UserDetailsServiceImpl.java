package com.project.config.service;

import com.project.users.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginService LoginService; // Inyección de dependencia del LoginService

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("Email (en user detail): " + email); // QUITAR DESPUES

        // Buscar el usuario por su email utilizando el LoginService
        String password = LoginService.getPassByEmail(email);

        if (password == null) {
            // Crear una instancia de nuestro Custom UserDetails utilizando los datos del usuario
            throw new UsernameNotFoundException("Usuario con el email " + email + " no encontrado");
        }

        System.out.println("Contraseña: " + password); // QUITAR DESPUES

        // Crea una instancia de BCryptPasswordEncoder
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
        // Codifica la contraseña obtenida de la API
        //String encodedPassword = passwordEncoder.encode(password);

        return User.withUsername(email)
                .password(password)
                .authorities("USER") // Aquí se selecciona el rol del usuario
                .build();
        
    }
    
}
