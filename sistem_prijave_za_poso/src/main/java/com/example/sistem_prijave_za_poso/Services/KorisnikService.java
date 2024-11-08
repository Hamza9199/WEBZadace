package com.example.sistem_prijave_za_poso.Services;


import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;


public interface KorisnikService {
    
    KorisnikDto createKorisnik(KorisnikDto korisnikDto);

    KorisnikDto getKorisnikById(int id);

}
