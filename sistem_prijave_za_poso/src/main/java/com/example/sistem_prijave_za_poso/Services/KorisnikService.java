package com.example.sistem_prijave_za_poso.Services;

import org.springframework.stereotype.Service;

import com.example.sistem_prijave_za_poso.Repositories.KorisnikRepository;


import com.example.sistem_prijave_za_poso.Models.Korisnik;


@Service
public class KorisnikService {
    
    private final KorisnikRepository korisnikRepository;

    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }
  

    public Korisnik postKorisnik(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

}
