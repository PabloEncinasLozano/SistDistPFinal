package com.project.products.service;

import com.project.products.dto.Productdto;
import com.project.products.model.Product;
import com.project.products.repository.ProductRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productrepo;

    public ProductService(com.project.products.repository.ProductRepo productrepo){
        this.productrepo = productrepo;
    }

    public List<Product> listaProducts(){
        return productrepo.findAll();
    }
}
