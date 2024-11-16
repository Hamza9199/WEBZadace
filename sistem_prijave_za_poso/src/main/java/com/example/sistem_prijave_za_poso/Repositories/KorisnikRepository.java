package com.example.sistem_prijave_za_poso.Repositories;

import com.example.sistem_prijave_za_poso.Models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>
{
	Optional<Korisnik> findByEmail (String email);
}
