package com.project.users.service;

import com.project.users.dto.Usersdto;
import com.project.users.model.User;
import com.project.users.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {


    @Autowired 
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User usuario){

        if (userRepo.findByEmail(usuario.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email ya registrado");
        }

        String encodedPassword=passwordEncoder.encode(usuario.getPassword());

        usuario.setPassword(encodedPassword);


        return userRepo.save(usuario);
    }
       
}
