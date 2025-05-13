package com.project.products.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Productdto {
    private long id;
    private String name;
    private String description;
    private String type;
    private Double price;
}
