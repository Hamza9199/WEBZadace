package com.example.sistem_prijave_za_poso.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "korisnik")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Email ne može biti prazan")
    @Size(min = 5, max = 50, message = "Email mora biti između 5 i 50 karaktera")
    @JsonProperty("email")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Sifra ne može biti prazna")
    @Size(min = 5, max = 50, message = "Sifra mora biti između 5 i 50 karaktera")
    @JsonIgnore // 
    @Column(name = "password")
    private String password;

    public Korisnik() {}

    public Korisnik(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
