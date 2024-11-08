package com.example.sistem_prijave_za_poso.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;
import com.example.sistem_prijave_za_poso.Services.KorisnikService;



@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class KorisnikController {

    private final KorisnikService korisnikService ;

    public KorisnikController (KorisnikService korisnikService){
        this.korisnikService = korisnikService;
    }



    @PostMapping("/korisnik")
    public ResponseEntity<KorisnikDto> createKorisnik(@RequestBody KorisnikDto korisnikDto) {
        KorisnikDto savedKorisnik = korisnikService.createKorisnik(korisnikDto);
        return new ResponseEntity<>(savedKorisnik, org.springframework.http.HttpStatus.CREATED);
    }   


    @GetMapping("/korisnik/{Id}")
    public ResponseEntity<KorisnikDto> getKorisnikById(int korisnikId) {
        KorisnikDto korisnik = korisnikService.getKorisnikById(korisnikId);
        return ResponseEntity.ok(korisnik);
    }
    
    
}