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
public class LoginService {


    private final RestTemplate resttemplate;
    private final String url = "http://api:8000/login";

    public LoginService(){
        this.resttemplate = new RestTemplate();
    }

    public String loginUser(String email, String password){
        try {

            Map<String,String> dict_em_pass= new HashMap<>();
            dict_em_pass.put("email", email);
            dict_em_pass.put("password", password);

            // Conexion con API

            HttpHeaders headers = new HttpHeaders(); //Define tipo de archivo que le va a llegar
            headers.set("Content-Type", "application/json");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(dict_em_pass, headers); //Hacer como si fuera JSON
            
            //Se hace la peticion a la API
            ResponseEntity<String> response = resttemplate.postForEntity(url, request, String.class);

            return response.getBody();

            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return e.getResponseBodyAsString();
        } catch (Exception e){
            return "Error al conectar con la API Flask";
        
        }
    } 

}
