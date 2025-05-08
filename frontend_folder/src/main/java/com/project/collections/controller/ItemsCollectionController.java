package com.project.collections.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.collections.model.ItemsCollection;
import com.project.collections.service.ItemsCollectionService;



@Controller
public class ItemsCollectionController {

    private final ItemsCollectionService itemCollectionService;

    public ItemsCollectionController(ItemsCollectionService itemCollectionService) {
        this.itemCollectionService = itemCollectionService;
    }

    @GetMapping("/itemCollections")
    public String mostrarItemCollections(ModelMap interfazConPantalla) {
        List<ItemsCollection> collectionList = itemCollectionService.listaCollections();
        interfazConPantalla.addAttribute("itemCollection", collectionList);
        return "itemCollectionsScreen"; //devolver nombre del archivo html
    }


    @PostMapping("/itemCollections/create")
    public String crearItemCollections(@RequestParam ("name_collection") String name_collection, ModelMap interfazConPantalla) {
        
        Map<String, String> creationSuccess = itemCollectionService.createNewCollection(name_collection);
    
        String mensaje = creationSuccess.getOrDefault("mensaje", "Error al crear la colección");


        if (mensaje.equals("Coleccion Creada Correctamente")){
            interfazConPantalla.addAttribute("success", mensaje);
        }else{
            interfazConPantalla.addAttribute("error", mensaje); 
        }

        List<ItemsCollection> collectionList = itemCollectionService.listaCollections();
        interfazConPantalla.addAttribute("itemCollection", collectionList);

        return "itemCollectionsScreen"; //Volver a la pantalla de colecciones

    }
    
}
