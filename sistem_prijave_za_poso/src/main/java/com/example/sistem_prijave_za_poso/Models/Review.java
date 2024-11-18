package com.example.sistem_prijave_za_poso.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "recenzije")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "sadrzaj")
    private String sadrzaj;

    @Column(name = "ocjena")
    private int ocjena;
    
    @JsonProperty("korisnikid")
    private int korisnikid;

    @JsonProperty("posaoid")
    private int posaoid;

    public Review() {}

    public Review(int id, String sadrzaj, int ocjena, int korisnikid, int posaoid) {
        this.id = id;
        this.sadrzaj = sadrzaj;
        this.ocjena = ocjena;
        this.korisnikid = korisnikid;
        this.posaoid = posaoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public int getOcjena() {
        return ocjena;
    }

    public void setOcjena(int ocjena) {
        this.ocjena = ocjena;
    }

    public int getKorisnikId() {
        return korisnikid;
    }

    public void setKorisnikId(int korisnikid) {
        this.korisnikid = korisnikid;
    }

    public int getPosaoId() {
        return posaoid;
    }

    public void setPosaoId(int posaoid) {
        this.posaoid = posaoid;
    }
}
