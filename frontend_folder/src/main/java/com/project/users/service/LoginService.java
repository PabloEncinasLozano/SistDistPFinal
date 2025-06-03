package com.project.users.service;

import com.project.users.model.User;
import com.project.users.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {



    @Autowired
    private UserRepository userRepo;
    
    public String getPassByEmail(String email){

        String dbPassword= userRepo.findByEmail(email).map(User::getPassword).orElse(null);

        return dbPassword;
    
    }

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
