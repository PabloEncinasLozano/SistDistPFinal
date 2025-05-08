package com.project.collections.service;


import com.project.collections.model.ItemsCollection;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class ItemsCollectionService {

    private final RestTemplate resttemplate;

    private final String url = "http://api:8000/list_user_collections"; // URL de la API para obtener los productos
    

    public ItemsCollectionService() {
        this.resttemplate = new RestTemplate();
    }

    //TEMPORAL, CAMBIARLO MAS ADELANTE
    private final int user_id = 1; // Cambia el user_id según sea necesario


    public List<ItemsCollection> listaCollections() {
        String url_with_user = url + "?user_id=" + user_id; // Cambia el user_id según sea necesario

        // Realiza la llamada a la API y obtiene la respuesta
        ResponseEntity<List<ItemsCollection>> response = resttemplate.exchange(url_with_user, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ItemsCollection>>() {});
        return response.getBody();
    }




    public Map<String,String> createNewCollection(String collection_name){
        try {

            String url = "http://api:8000/create_collection"; // URL de la API para crear una nueva colección


            Map<String,Object> dict_name_userid= new HashMap<>();
            dict_name_userid.put("name_collection", collection_name);
            dict_name_userid.put("user_id", user_id);

            // Conexion con API

            HttpHeaders headers = new HttpHeaders(); //Define tipo de archivo que le va a llegar
            headers.set("Content-Type", "application/json");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(dict_name_userid, headers); //Hacer como si fuera JSON
            
            //Se hace la peticion a la API
            ResponseEntity<Map> response = resttemplate.postForEntity(url, request, Map.class);

            return response.getBody();

        } catch (Exception e){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("mensaje", "Error al conectar con la API Flask");
            return errorMap;
        }
    }
}
