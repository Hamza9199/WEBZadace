package com.example.sistem_prijave_za_poso.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Table(name = "korisnik")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @JsonProperty("email")
    @Column(name = "email" , unique = true)
    private String email;

    @NotBlank
    @JsonProperty("password")
    @Column(name = "password")
    private String password;

    public Korisnik(int id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
        
    }

    public Korisnik(){}
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    

   
}

