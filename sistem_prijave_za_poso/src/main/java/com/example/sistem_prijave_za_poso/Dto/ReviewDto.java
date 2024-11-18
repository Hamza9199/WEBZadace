package com.example.sistem_prijave_za_poso.Dto;

public class ReviewDto {
    private int id;
    private String sadrzaj;
    private int ocjena;
    private int korisnikid;
    private int posaoid;

    public ReviewDto() {}

    public ReviewDto(int id, String sadrzaj, int ocjena, int korisnikid, int posaoid) {
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
