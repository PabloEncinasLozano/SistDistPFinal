package com.project.users.service;

import com.project.users.model.Users;
import com.project.users.repository.LoginRepo;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepo loginRepo ;
    
    public LoginService(LoginRepo loginRepo) {
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
