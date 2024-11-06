package com.example.sistem_prijave_za_poso.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Korisnik {

    @Id
    @GeneratedValue
    private int id;

    private String ime;

    private String prezime;

    private String email;

    private String phone;

    private String password;

    private String role;


    /*
    public Korisnik() {

    }

    public Korisnik(String ime, String prezime, String email, String phone, String password, String role) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public Korisnik(int id, String ime, String prezime, String email, String phone, String password, String role) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }*/


    
}
