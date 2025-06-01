package com.project.users.model;

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
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "password")
    public String password;
    @Column(name = "email")
    public String email;
    @Column(name = "name")
    public String name;
    @Column(name = "surname")
    public String surname;



    public User(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}


