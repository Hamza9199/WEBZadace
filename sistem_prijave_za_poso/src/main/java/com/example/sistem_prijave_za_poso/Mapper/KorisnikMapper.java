package com.example.sistem_prijave_za_poso.Mapper;

import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;
import com.example.sistem_prijave_za_poso.Models.Korisnik;

public class KorisnikMapper {
    
    public static KorisnikDto mapToKorisnikDto(Korisnik korisnik){
        return new KorisnikDto(korisnik.getId(), korisnik.getEmail(), korisnik.getPassword());
    }

    public static Korisnik mapToKorisnik(KorisnikDto korisnikDto){
        return new Korisnik(korisnikDto.getId(), korisnikDto.getEmail(), korisnikDto.getPassword());
    }
}
