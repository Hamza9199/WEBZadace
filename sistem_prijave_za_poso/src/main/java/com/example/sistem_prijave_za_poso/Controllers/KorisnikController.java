package com.example.sistem_prijave_za_poso.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sistem_prijave_za_poso.Models.Korisnik;
import com.example.sistem_prijave_za_poso.Services.KorisnikService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KorisnikController {

    private final KorisnikService korisnikService;

    @PostMapping("/korisnik")
    public Korisnik postKorisnik(@RequestBody Korisnik korisnik) {
        return korisnikService.postKorisnik(korisnik);
    }
    
}