package com.project.users.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.users.model.User;
import com.project.users.repository.UserRepository;

/**
 * Servicio para manejar las operaciones relacionadas con los usuarios.
 * Proporciona métodos para obtener, modificar y eliminar usuarios por su correo electrónico.
 */
@Service
public class UserService {


    @Autowired
    private UserRepository userRepo;


    /**
     * Obtiene un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario.
     * @return Un objeto User con la información del usuario, o null si no se encuentra.
     */
    public User getUserByEmail(String email) {

        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty()) { // Si no se encuentra el usuario a partir del email
            return null;
        }

        User usuario = user.get();
        return new User(usuario.getEmail(),usuario.getPassword(), usuario.getName(), usuario.getSurname());
    }


    /**
     * Cambia el nombre de un usuario.
     *
     * @param email El correo electrónico del usuario.
     * @param name El nuevo nombre del usuario.
     * @return true si el nombre se cambió correctamente, false si no se encontró el usuario.
     */
    public boolean changeName(String email, String name) {

        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty()) { // Si no se encuentra el usuario a partir del email
            
            return false;
        }

        User usuario = user.get();

        usuario.setName(name);

        userRepo.save(usuario);
        return true;
    }


    /**
     * Cambia el apellido de un usuario.
     *
     * @param email El correo electrónico del usuario.
     * @param surname El nuevo apellido del usuario.
     * @return true si el apellido se cambió correctamente, false si no se encontró el usuario.
     */
    public boolean changeSurname(String email, String surname) {

        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty()) { // Si no se encuentra el usuario a partir del email
            
            return false;
        }

        User usuario = user.get();

        usuario.setSurname(surname);

        userRepo.save(usuario);
        return true;
    }


    /**
     * Cambia el correo electrónico de un usuario.
     *
     * @param email El correo electrónico actual del usuario.
     * @param newEmail El nuevo correo electrónico del usuario.
     * @return true si el correo electrónico se cambió correctamente, false si no se encontró el usuario o si el nuevo correo ya está en uso.
     */
    public boolean changeEmail(String email, String newEmail) {

        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty()) { // Si no se encuentra el usuario a partir del email
            
            return false;
        }

        if (userRepo.existsByEmail(newEmail)) { // Verifica si el nuevo email ya está en uso
            return false;
        }

        User usuario = user.get();

        usuario.setEmail(newEmail);

        userRepo.save(usuario);
        return true;
    }


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Cambia la contraseña de un usuario.
     *
     * @param email El correo electrónico del usuario.
     * @param newPassword La nueva contraseña del usuario.
     * @return true si la contraseña se cambió correctamente, false si no se encontró el usuario.
     */
    public boolean changePassword(String email, String newPassword) {

        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty()) { // Si no se encuentra el usuario a partir del email
            
            return false;
        }

        User usuario = user.get();

        String encodedPassword=passwordEncoder.encode(newPassword);

        usuario.setPassword(encodedPassword);

        userRepo.save(usuario);
        return true;
    }
    
}
