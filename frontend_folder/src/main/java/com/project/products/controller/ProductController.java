package com.project.products.controller;

import com.project.products.model.Product;
import com.project.products.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productservice;

    public ProductController(com.project.products.service.ProductService productservice) {
        this.productservice = productservice;
    }

    @GetMapping("/")
    public String mostrarProductos( ModelMap interfazConPantalla){
        List<Product> productList = productservice.listaProducts();
        interfazConPantalla.addAttribute("listaProductos",productList);
        return "productos"; //devolver nombre del archivo html
    }

}
