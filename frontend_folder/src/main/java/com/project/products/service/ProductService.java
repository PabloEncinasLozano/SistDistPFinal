package com.project.products.service;


import com.project.products.model.Product;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Servicio para manejar la lógica de productos.
 * Permite obtener una lista de productos desde una API externa.
 */
@Service
public class ProductService {
    private final RestTemplate resttemplate;

    private final String url = "http://api:8000/";

    /**
     * Constructor que inicializa el RestTemplate.
     */
    public ProductService(){

        this.resttemplate = new RestTemplate();
    }

    /**
     * Método que obtiene la lista de productos desde la API.
     *
     * @return la lista de objetos Product.
     */
    public List<Product> listaProducts(){

        ResponseEntity<List<Product>> response = resttemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Product>>() {});

        return response.getBody();
    }
}
