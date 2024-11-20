package com.example.sistem_prijave_za_poso.Services;

import java.util.List;


import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;


public interface KorisnikService {
    
    KorisnikDto createKorisnik(KorisnikDto korisnikDto);

    KorisnikDto getKorisnikById(int id);

    List<KorisnikDto>getAllKorisnik();

    KorisnikDto updateKorisnik(int id, KorisnikDto updatedKorisnik);

    void deleteKorisnik(int id);

    KorisnikDto getKorisnikByEmail(String email);

    boolean isAdmin(int id);

}
