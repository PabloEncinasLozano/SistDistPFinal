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
@Table(name = "Usuarios")
public class Users implements Serializable {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column(name = "password")
    public String password;
    @Column(name = "email")
    public String email;
}


