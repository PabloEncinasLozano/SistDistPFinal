package com.project.users.service;

import com.project.users.model.User;
import com.project.users.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Servicio para manejar el registro de nuevos usuarios.
 * Proporciona métodos para registrar un usuario y verificar si el email ya está registrado.
 */
@Service
public class RegisterService {


    @Autowired 
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param usuario El objeto User que contiene la información del nuevo usuario.
     * @return El usuario registrado.
     * @throws IllegalArgumentException si el email ya está registrado.
     */
    public User registerUser(User usuario){

        if (userRepo.findByEmail(usuario.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email ya registrado");
        }

        String encodedPassword=passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        return userRepo.save(usuario);
    }
       
}
