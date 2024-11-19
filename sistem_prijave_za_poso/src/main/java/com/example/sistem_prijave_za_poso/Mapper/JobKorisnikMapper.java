package com.example.sistem_prijave_za_poso.Mapper;

import com.example.sistem_prijave_za_poso.Dto.JobKorisnikDto;
import com.example.sistem_prijave_za_poso.Models.JobKorisnik;

public class JobKorisnikMapper {
    public static JobKorisnikDto mapToJobKorisnikDto(JobKorisnik jobKorisnik) {
        return new JobKorisnikDto(jobKorisnik.getId(), jobKorisnik.getKorisnikId(), jobKorisnik.getPosaoId());
    }

    public static JobKorisnik mapToJobKorisnik(JobKorisnikDto jobKorisnikDto) {
        return new JobKorisnik(jobKorisnikDto.getId(), jobKorisnikDto.getKorisnikId(), jobKorisnikDto.getPosaoId());
    }
}
