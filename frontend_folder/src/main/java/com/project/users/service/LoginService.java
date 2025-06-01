package com.project.users.service;

import com.project.users.model.User;
import com.project.users.repository.UsersRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {



    @Autowired
    private UsersRepository userRepo;
    
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





    /* 
    private final RestTemplate resttemplate;
    private final String url = "http://api:8000/login";

    public LoginService(){
        this.resttemplate = new RestTemplate();
    }

    
    Login con seguridad personalizada personalzada y hecha manualmente
    Comentado para que no obstaculice el uso de spring security

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
    */



    /*

    public String getPassByEmail(String email){
            Map<String,String> dict_em_pass= new HashMap<>();
            dict_em_pass.put("email", email);

            HttpHeaders headers = new HttpHeaders(); //Define tipo de archivo que le va a llegar
            headers.set("Content-Type", "application/json");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(dict_em_pass, headers); //Hacer como si fuera JSON

            //Se hace la peticion a la API
            ResponseEntity<Map> response = resttemplate.postForEntity(url, request, Map.class);


            if (response.getStatusCode().is2xxSuccessful()) {
                Map body = response.getBody();

                return (String) body.get("password"); // Devuelve la contraseña
            }

             */
