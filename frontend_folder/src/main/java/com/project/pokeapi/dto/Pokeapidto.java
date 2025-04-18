package com.project.pokeapi.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pokeapidto {
    private long id;
    private String name;
    private String type;
}
