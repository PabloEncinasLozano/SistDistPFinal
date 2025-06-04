package com.project.config.service;

import com.project.users.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Clase que implementa UserDetailsService para cargar los detalles del usuario.
 * Se utiliza para autenticar usuarios basándose en su email y contraseña.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private LoginService LoginService; // Inyección de dependencia del LoginService

    /**
     * Método que carga los detalles del usuario por su email.
     * 
     * @param email El email del usuario a buscar.
     * @return UserDetails con la información del usuario.
     * @throws UsernameNotFoundException Si el usuario no se encuentra.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        // Buscar el usuario por su email utilizando el LoginService
        String password = LoginService.getPassByEmail(email);

        if (password == null) {
            // Crear una instancia de nuestro Custom UserDetails utilizando los datos del usuario
            throw new UsernameNotFoundException("Usuario con el email " + email + " no encontrado");
        }


        return User.withUsername(email)
                .password(password)
                .authorities("USER") // Aquí se selecciona el rol del usuario
                .build();
        
    }
    
}
