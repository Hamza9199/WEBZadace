package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;
import com.example.sistem_prijave_za_poso.Services.KorisnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin( "*" )
@RequestMapping( "/api" )
public class KorisnikController
{

	private final KorisnikService korisnikService;

	public KorisnikController (KorisnikService korisnikService)
	{
		this.korisnikService = korisnikService;
	}

	@GetMapping( "/login" )
	public String login ()
	{
		return "login";
	}

	@GetMapping( "/register" )
	public String register ()
	{
		return "register";
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