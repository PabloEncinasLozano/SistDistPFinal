package com.project.products.controller;


import com.project.products.model.Product;
import com.project.products.service.ProductService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controlador para manejar las peticiones relacionadas con los productos.
 * Permite mostrar una lista de productos en la vista.
 */
@Controller
public class ProductController {

    private final ProductService productservice;

    /**
     * Constructor que inyecta el servicio ProductService.
     *
     * @param productservice Servicio para manejar la lógica de productos.
     */
    public ProductController(com.project.products.service.ProductService productservice) {
        this.productservice = productservice;
    }

    /**
     * Muestra la página de productos.
     *
     * @param interfazConPantalla Modelo para pasar datos a la vista.
     * @return Nombre de la vista HTML para mostrar productos.
     */
    @GetMapping("/")
    public String mostrarProductos( ModelMap interfazConPantalla){
        List<Product> productList = productservice.listaProducts();
        interfazConPantalla.addAttribute("listaProductos",productList);
        

        return "productos"; //devolver nombre del archivo html
    }

}
