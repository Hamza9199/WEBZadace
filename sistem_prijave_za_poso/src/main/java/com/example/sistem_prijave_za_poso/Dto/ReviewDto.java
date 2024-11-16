package com.example.sistem_prijave_za_poso.Dto;

public class ReviewDto {
    private int id;
    private String sadrzaj;
    private int ocjena;

    public ReviewDto() {}

    public ReviewDto(int id, String sadrzaj, int ocjena) {
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
