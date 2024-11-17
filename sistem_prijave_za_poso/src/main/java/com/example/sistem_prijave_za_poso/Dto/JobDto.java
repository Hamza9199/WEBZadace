package com.example.sistem_prijave_za_poso.Dto;


public class JobDto {

    private int id;
    private String naziv;
    private String opis;
    private int rating;
    private String kategorija; 

    public JobDto() {}

    public JobDto(int id, String naziv, String opis, int rating, String kategorija) {
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

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }
}
