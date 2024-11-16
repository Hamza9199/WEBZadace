package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Models.Korisnik;
import com.example.sistem_prijave_za_poso.Repositories.KorisnikRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(KorisnikRepository korisnikRepository, PasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/api/register", consumes = "application/json")
    public Korisnik createUser(@RequestBody Korisnik k) {
        k.setPassword(passwordEncoder.encode(k.getPassword()));
        return korisnikRepository.save(k);
    }
}
