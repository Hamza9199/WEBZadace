package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;
import com.example.sistem_prijave_za_poso.Dto.UpdatePasswordDto;
import com.example.sistem_prijave_za_poso.Services.KorisnikService;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
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
	private final @Lazy PasswordEncoder passwordEncoder;


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

	

	@PostMapping("/korisnik")
	public ResponseEntity<KorisnikDto> createKorisnik(@RequestBody KorisnikDto korisnikDto) {
		if (korisnikDto.getPassword() != null && !korisnikDto.getPassword().isEmpty()) {
			String hashedPassword = passwordEncoder.encode(korisnikDto.getPassword());
			korisnikDto.setPassword(hashedPassword);
		}

		KorisnikDto savedKorisnik = korisnikService.createKorisnik(korisnikDto);
		return new ResponseEntity<>(savedKorisnik, HttpStatus.CREATED);
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


	@PutMapping("/korisnik/{id}")
	public ResponseEntity<KorisnikDto> updateKorisnik(@PathVariable("id") int korisnikId, @RequestBody KorisnikDto korisnikDto) {
		if (korisnikDto.getPassword() != null && !korisnikDto.getPassword().isEmpty()) {
			String hashedPassword = passwordEncoder.encode(korisnikDto.getPassword());
			korisnikDto.setPassword(hashedPassword);
		}

		KorisnikDto updatedKorisnik = korisnikService.updateKorisnik(korisnikId, korisnikDto);
		return ResponseEntity.ok(updatedKorisnik);
	}
	

	
	@PutMapping("/korisnik/{id}/password")
    public ResponseEntity<?> updateSifra(@PathVariable("id") int korisnikId, @RequestBody UpdatePasswordDto passwordDto) {
		
				
		KorisnikDto korisnik = korisnikService.getKorisnikById(korisnikId);		


		if(!passwordEncoder.matches(passwordDto.getOldPassword(), korisnik.getPassword())){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("lose");
		}		
		
		String h = passwordEncoder.encode(passwordDto.getNewPassword());	

		passwordDto.setNewPassword(h);

		korisnikService.updateSifra(korisnikId, passwordDto);

        return ResponseEntity.ok("Lozinka uspješno promijenjena.");
    }


	@DeleteMapping( "/korisnik/{id}" )
	public ResponseEntity<String> deleteKorisnik (@PathVariable( "id" ) int korisnikId)
	{
		korisnikService.deleteKorisnik(korisnikId);
		return ResponseEntity.ok("Korisnik sa id " + korisnikId + " je obrisan");
	}


	@GetMapping("/korisnik/{id}/is-admin")
    public ResponseEntity<Boolean> isAdmin(@PathVariable int id) {
        boolean isAdmin = korisnikService.isAdmin(id);
        return ResponseEntity.ok(isAdmin);
    }

}