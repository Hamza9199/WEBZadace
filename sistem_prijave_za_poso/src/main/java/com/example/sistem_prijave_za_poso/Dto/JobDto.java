package com.example.sistem_prijave_za_poso.Dto;

public class JobDto {

    private int id;
    private String imeFirme;
    private String naziv;
    private String deskripcija;

    private JobDto() {}

    public JobDto(int id, String imeFirme, String naziv, String deskripcija) {
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
