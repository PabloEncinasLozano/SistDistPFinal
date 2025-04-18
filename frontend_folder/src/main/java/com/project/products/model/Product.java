package com.project.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "name")
    public String name;
    @Column(name = "description")
    public String description;
    @Column(name = "type")
    public String type;
    @Column(name = "price")
    public Double price;
}
