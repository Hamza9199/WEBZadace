package com.example.sistem_prijave_za_poso.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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

    
    @Column(name = "rating")
    private int rating;

    @Column(name= "kategorija")
    private String kategorija;

    @Column(name = "kreatorid")
    private int kreatorid; //foreign key od Korisnka koji je kreirao posao

    public Job() {}

    public Job(int id, String naziv, String opis, int rating, String kategorija, int kreatorid) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.rating = rating;
        this.kategorija = kategorija;
        this.kreatorid = kreatorid;
    }

    public int getKreatorid(){
        return kreatorid;
    }

    public void setKreatorid(int kreatorid){
        this.kreatorid = kreatorid;
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

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }
}
