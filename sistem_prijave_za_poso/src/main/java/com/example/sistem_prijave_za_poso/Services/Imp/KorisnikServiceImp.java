package com.example.sistem_prijave_za_poso.Services.Imp;

import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;
import com.example.sistem_prijave_za_poso.Exception.ResourceNotFoundException;
import com.example.sistem_prijave_za_poso.Mapper.KorisnikMapper;
import com.example.sistem_prijave_za_poso.Models.Korisnik;
import com.example.sistem_prijave_za_poso.Repositories.KorisnikRepository;
import com.example.sistem_prijave_za_poso.Services.KorisnikService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikServiceImp implements KorisnikService, UserDetailsService
{

	private final KorisnikRepository korisnikRepository;

	public KorisnikServiceImp (KorisnikRepository korisnikRepository)
	{
		this.korisnikRepository = korisnikRepository;
	}

	@Override
	public KorisnikDto createKorisnik (KorisnikDto korisnikDto)
	{

		Korisnik korisnik = KorisnikMapper.mapToKorisnik(korisnikDto);

		Korisnik savedKorisnik = korisnikRepository.save(korisnik);

		return KorisnikMapper.mapToKorisnikDto(savedKorisnik);
	}

	@Override
	public KorisnikDto getKorisnikById (int id)
	{

		Korisnik korisnik = korisnikRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Korisnik sa " + id + " nepostoji"));


		return KorisnikMapper.mapToKorisnikDto(korisnik);
	}


	@Override
	public List<KorisnikDto> getAllKorisnik ()
	{
		List<Korisnik> korisnici = korisnikRepository.findAll();

		return korisnici.stream()
			.map((korisnik) -> KorisnikMapper.mapToKorisnikDto(korisnik)).collect(java.util.stream.Collectors.toList());
	}

	@Override
	public KorisnikDto updateKorisnik (int id, KorisnikDto updatedKorisnik)
	{

		Korisnik korisnik = korisnikRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Korisnik sa " + id + " nepostoji"));


		korisnik.setEmail(updatedKorisnik.getEmail());
		korisnik.setPassword(updatedKorisnik.getPassword());

		Korisnik updatedKorisnikObj = korisnikRepository.save(korisnik);

		return KorisnikMapper.mapToKorisnikDto(updatedKorisnikObj);
	}


	@Override
	public void deleteKorisnik (int id)
	{
		Korisnik korisnik = korisnikRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Korisnik sa " + id + " nepostoji"));

		korisnikRepository.delete(korisnik);
	}

	@Override
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException
	{
		Optional<Korisnik> korisnik = korisnikRepository.findByEmail(email);

		if ( korisnik.isPresent() )
		{
			var userObj = korisnik.get();
			return User
				.builder()
				.username(userObj.getEmail())
				.password(userObj.getPassword())
				.build();
		}
		else
		{
			throw new UsernameNotFoundException("Korisnik " + email + " ne postoji");
		}
	}
}