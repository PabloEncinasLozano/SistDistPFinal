package com.project.users.service;

import com.project.users.model.User;
import com.project.users.repository.UsersRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {
    /* 
    private final RestTemplate resttemplate;
    private final String url = "http://api:8000/register";

    public RegisterService(){
        this.resttemplate = new RestTemplate();
    }
    */

    @Autowired 
    private UsersRepository userRepo;

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
       


    /*
    public String registerUser(String email, String password, String nombre, String apellido){
        try {

            Map<String,String> datosUserDict= new HashMap<>();
            datosUserDict.put("email", email);
            datosUserDict.put("password", password);
            datosUserDict.put("name", nombre);
            datosUserDict.put("surname", apellido);

            // Conexion con API

            HttpHeaders headers = new HttpHeaders(); //Define tipo de archivo que le va a llegar
            headers.set("Content-Type", "application/json");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(datosUserDict, headers); //Hacer como si fuera JSON
            
            //Se hace la peticion a la API
            ResponseEntity<String> response = resttemplate.postForEntity(url, request, String.class);
            
            //Tomar el error como un string
            return response.getBody();
        
        } catch (HttpClientErrorException | HttpServerErrorException e){
            return e.getResponseBodyAsString();
            
        } catch (Exception e){
            return "Error al conectar con la API Flask";
        }
    }
     */
}
