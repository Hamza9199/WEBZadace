package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;
import com.example.sistem_prijave_za_poso.Services.KorisnikService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin( "*" )
@RequestMapping( "/api" )
public class KorisnikController
{

	private final KorisnikService korisnikService;
	private final PasswordEncoder passwordEncoder;


	public KorisnikController (KorisnikService korisnikService, PasswordEncoder passwordEncoder)
	{
		this.korisnikService = korisnikService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginKorisnik(@RequestBody KorisnikDto korisnikDto) {
		KorisnikDto provjeriKorisnik = korisnikService.getKorisnikByEmail(korisnikDto.getEmail());

		if (provjeriKorisnik == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Korisnik sa datim emailom ne postoji.");
		}

		if (!passwordEncoder.matches(korisnikDto.getPassword(), provjeriKorisnik.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Neispravna lozinka.");
		}

		return ResponseEntity.ok(provjeriKorisnik);
	}




	@PostMapping("/register")
	public ResponseEntity<?> createKorisnik2(@RequestBody KorisnikDto korisnikDto) {
		KorisnikDto provjeriKorisnika = korisnikService.getKorisnikByEmail(korisnikDto.getEmail());

		if (provjeriKorisnika != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Korisnik sa datim emailom već postoji.");
		}

		KorisnikDto savedKorisnik = korisnikService.createKorisnik(korisnikDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedKorisnik);
	}

	

	@PostMapping( "/korisnik" )
	public ResponseEntity<KorisnikDto> createKorisnik (@RequestBody KorisnikDto korisnikDto)
	{
		KorisnikDto savedKorisnik = korisnikService.createKorisnik(korisnikDto);
		return new ResponseEntity<>(savedKorisnik, org.springframework.http.HttpStatus.CREATED);
	}


	@GetMapping( "/korisnik/{id}" )
	public ResponseEntity<KorisnikDto> getKorisnikById (@PathVariable( "id" ) int korisnikId)
	{
		KorisnikDto korisnik = korisnikService.getKorisnikById(korisnikId);
		return ResponseEntity.ok(korisnik);
	}


	@GetMapping( "/korisnici" )
	public ResponseEntity<List<KorisnikDto>> getAllKorisnici ()
	{
		List<KorisnikDto> korisnici = korisnikService.getAllKorisnik();

		return ResponseEntity.ok(korisnici);
	}


	@PutMapping( "/korisnik/{id}" )
	public ResponseEntity<KorisnikDto> updateKorisnik (@PathVariable( "id" ) int korisnikId, @RequestBody KorisnikDto korisnikDto)
	{
		KorisnikDto updatedKorisnik = korisnikService.updateKorisnik(korisnikId, korisnikDto);
		return ResponseEntity.ok(updatedKorisnik);
	}


	@DeleteMapping( "/korisnik/{id}" )
	public ResponseEntity<String> deleteKorisnik (@PathVariable( "id" ) int korisnikId)
	{
		korisnikService.deleteKorisnik(korisnikId);
		return ResponseEntity.ok("Korisnik sa id " + korisnikId + " je obrisan");
	}


}