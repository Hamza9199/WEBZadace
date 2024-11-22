package com.example.sistem_prijave_za_poso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.sistem_prijave_za_poso.Models.Korisnik;
import com.example.sistem_prijave_za_poso.Repositories.KorisnikRepository;

import jakarta.annotation.PostConstruct;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.sistem_prijave_za_poso.Repositories")
public class SistemPrijaveZaPosoApplication {

	private final KorisnikRepository korisnikRepository;
	private final PasswordEncoder passwordEncoder;


	public SistemPrijaveZaPosoApplication(KorisnikRepository korisnikRepository, PasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

	

	public static void main(String[] args) {
		SpringApplication.run(SistemPrijaveZaPosoApplication.class, args);
	}


	@PostConstruct
    public void initAdminUser() {
        String email = "hamza.gacic.22@size.ba";
        String password = "hamza";

        if (korisnikRepository.findByEmail(email).isEmpty()) {
            Korisnik admin = new Korisnik();
            admin.setEmail(email);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setIsAdmin(true);

            korisnikRepository.save(admin);
            System.out.println("Admin korisnik kreiran: " + email);
        } else {
            System.out.println("Admin korisnik već postoji.");
        }
    }

}
