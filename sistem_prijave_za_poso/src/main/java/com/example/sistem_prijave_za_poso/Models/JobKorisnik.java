package com.example.sistem_prijave_za_poso.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name =  "prijavljeniPoslovi")
public class JobKorisnik {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "korisnikId") 
    public int korisnikId;

    @Column(name = "posaoId")
    public int posaoId;

    public JobKorisnik(){}

    public JobKorisnik(int id, int korisnikId, int posaoId){
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
