package com.project.products.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Productdto {
    private long id;
    private String name;
    private String type;
}
