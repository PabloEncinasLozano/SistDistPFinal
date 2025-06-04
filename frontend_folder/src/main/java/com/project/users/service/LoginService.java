package com.project.users.service;

import com.project.users.model.User;
import com.project.users.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Servicio para manejar las operaciones relacionadas con el inicio de sesión y la gestión de usuarios.
 * Proporciona métodos para obtener la contraseña por correo electrónico y eliminar usuarios por correo electrónico.
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepo;
    
    /**
     * Obtiene la contraseña del usuario asociado al correo electrónico proporcionado.
     *
     * @param email El correo electrónico del usuario.
     * @return La contraseña del usuario si se encuentra, o null si no existe un usuario con ese correo electrónico.
     */
    public String getPassByEmail(String email){

        String dbPassword= userRepo.findByEmail(email).map(User::getPassword).orElse(null);

        return dbPassword;
    
    }


    /**
     * Elimina un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario a eliminar.
     * @return true si el usuario fue eliminado correctamente, false si no se encontró un usuario con ese correo electrónico.
     */
    @Transactional
    public boolean deleteUserByEmail(String email){

        Optional<User> usuario = userRepo.findByEmail(email);


        if (usuario.isEmpty()) {
            System.out.println("No se encontro un usuario con el email: " + email);
            return false; 
        }

        userRepo.deleteByEmail(email);
        return true;
    } 

}
