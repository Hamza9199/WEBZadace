package com.example.sistem_prijave_za_poso.Services.Imp;

import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;
import com.example.sistem_prijave_za_poso.Mapper.KorisnikMapper;
import com.example.sistem_prijave_za_poso.Models.Korisnik;
import com.example.sistem_prijave_za_poso.Repositories.KorisnikRepository;
import com.example.sistem_prijave_za_poso.Services.KorisnikService;
import com.example.sistem_prijave_za_poso.Exception.ResourceNotFoundException;

import java.util.List;

import lombok.Data;

import org.springframework.stereotype.Service;

@Data
@Service
public class KorisnikServiceImp implements KorisnikService {

    private final KorisnikRepository korisnikRepository;

    public KorisnikServiceImp(KorisnikRepository korisnikRepository){
        this.korisnikRepository = korisnikRepository;
    }
    
    @Override
    public KorisnikDto createKorisnik(KorisnikDto korisnikDto) {

        Korisnik korisnik = KorisnikMapper.mapToKorisnik(korisnikDto);

        Korisnik savedKorisnik = korisnikRepository.save(korisnik);

        return KorisnikMapper.mapToKorisnikDto(savedKorisnik);
    }

    @Override
    public KorisnikDto getKorisnikById(int id) {

        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa " + id + " nepostoji"));


        return KorisnikMapper.mapToKorisnikDto(korisnik);
    }
    

    @Override
    public List<KorisnikDto> getAllKorisnik() {
        List<Korisnik> korisnici = korisnikRepository.findAll();

        return korisnici.stream()
                .map((korisnik) -> KorisnikMapper.mapToKorisnikDto(korisnik)).collect(java.util.stream.Collectors.toList());
    }
}
