package com.project.users.service;

import com.project.users.model.Users;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.HashMap;

@Service
public class RegisterService {
    
    private final RestTemplate resttemplate;
    private final String url = "http://api:8000/register";

    public RegisterService(){
        this.resttemplate = new RestTemplate();
    }

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
}
