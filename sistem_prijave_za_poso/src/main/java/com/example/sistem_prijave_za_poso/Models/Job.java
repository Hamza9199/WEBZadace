package com.example.sistem_prijave_za_poso.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "poslovi")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 2)
    @JsonProperty("imeFirme")
    @Column(name = "imeFirme")
    private String imeFirme;

    @NotBlank
    @Size(min = 2)
    @JsonProperty("naziv")
    @Column(name = "naziv")
    private String naziv;

    @NotBlank
    @JsonProperty("deskripcija")
    @Column(name = "deskripcija")
    private String deskripcija;

    private Job() {}

    public Job(int id, String imeFirme, String naziv, String deskripcija) {
        this.id = id;
        this.imeFirme = imeFirme;
        this.naziv = naziv;
        this.deskripcija = deskripcija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImeFirme() {
        return imeFirme;
    }

    public void setImeFirme(String imeFirme) {
        this.imeFirme = imeFirme;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDeskripcija() {
        return deskripcija;
    }

    public void setDeskripcija(String deskripcija) {
        this.deskripcija = deskripcija;
    }
}
