package com.example.sistem_prijave_za_poso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sistem_prijave_za_poso.Models.*;

import java.util.List;

@Repository
public interface JobKorisnikRepository extends JpaRepository<JobKorisnik, Integer> {

    boolean existsByKorisnikIdAndPosaoId(int korisnikId, int posaoId);

    @Query("SELECT COUNT(jk) FROM JobKorisnik jk WHERE jk.posaoId = :posaoId")
    int countByPosaoId(@Param("posaoId") int posaoId);

    @Query("SELECT jk.korisnikId FROM JobKorisnik jk WHERE jk.posaoId = :posaoId")
    List<Integer> findKorisniciByPosaoId(@Param("posaoId") int posaoId);

    List<JobKorisnik> findByKorisnikId(int korisnikId);

    @Query("SELECT k.email FROM JobKorisnik jk JOIN Korisnik k ON jk.korisnikId = k.id WHERE jk.posaoId = :posaoId")
    List<String> findEmailsByPosaoId(@Param("posaoId") int posaoId);

}
