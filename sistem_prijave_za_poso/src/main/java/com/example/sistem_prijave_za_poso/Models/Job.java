package com.example.sistem_prijave_za_poso.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "poslovi")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "naziv", nullable = false)
    private String naziv;

    @NotBlank
    @Column(name = "opis", nullable = false)
    private String opis;

    @NotNull
    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "kategorija_id", nullable = false)
    private JobCategory kategorija;

    public Job() {}

    public Job(int id, String naziv, String opis, int rating, JobCategory kategorija) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.rating = rating;
        this.kategorija = kategorija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public JobCategory getKategorija() {
        return kategorija;
    }

    public void setKategorija(JobCategory kategorija) {
        this.kategorija = kategorija;
    }
}
