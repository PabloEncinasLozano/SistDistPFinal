package com.project.users.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.users.model.User;
import com.project.users.repository.UserRepository;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepo;


    public User getUserByEmail(String email) {

        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty()) { // Si no se encuentra el usuario a partir del email
            return null;
        }

        User usuario = user.get();
        return new User(usuario.getEmail(),usuario.getPassword(), usuario.getName(), usuario.getSurname());
    }



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
