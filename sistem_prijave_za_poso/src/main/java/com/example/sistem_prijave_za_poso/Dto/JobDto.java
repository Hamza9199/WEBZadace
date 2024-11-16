package com.example.sistem_prijave_za_poso.Dto;

public class JobDto {

    private int id;
    private String naziv;
    private String opis;
    private int rating;
    private int kategorijaId; 

    public JobDto() {}

    public JobDto(int id, String naziv, String opis, int rating, int kategorijaId) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.rating = rating;
        this.kategorijaId = kategorijaId;
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

    public int getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(int kategorijaId) {
        this.kategorijaId = kategorijaId;
    }
}
