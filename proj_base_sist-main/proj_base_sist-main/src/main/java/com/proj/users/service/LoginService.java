package com.proj.users.service;

import com.proj.users.repository.LoginRepo;
import com.proj.users.model.Users;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepo loginRepo ;

    public LoginService(com.proj.users.repository.LoginRepo loginRepo) {

        this.loginRepo = loginRepo;
    }

    public boolean userValidation(String email, String password){

        Users user = loginRepo.findByEmail(email);

        // revisar ususario existe
        if (user == null){
            return false;
        }

        // revisar contraseña cohincide
        if (!user.getPassword().equals(password)){
            return false;
        }

        // llega aqui, todo correcto
        return true;
    }

}

