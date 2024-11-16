package com.example.sistem_prijave_za_poso.Models;

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

    public Review() {}

    public Review(int id, String sadrzaj, int ocjena) {
        this.id = id;
        this.sadrzaj = sadrzaj;
        this.ocjena = ocjena;
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
}
