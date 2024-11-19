package com.example.sistem_prijave_za_poso.Dto;

public class JobKorisnikDto {
    

    public int id;

    public int korisnikId;

    public int posaoId;

    public JobKorisnikDto(){}

    public JobKorisnikDto(int id, int korisnikId, int posaoId){
        this.id = id;
        this.korisnikId = korisnikId;
        this.posaoId = posaoId;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getKorisnikId() {
        return korisnikId;
    }

    public void seKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;

    }


    public int getPosaoId() {
        return posaoId;
    }

    public void setPosaoId(int posaoId) {
        this.posaoId = posaoId;
    }
    

}

