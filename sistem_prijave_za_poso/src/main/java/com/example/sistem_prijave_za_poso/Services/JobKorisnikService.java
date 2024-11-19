package com.example.sistem_prijave_za_poso.Services;

import java.util.List;

import com.example.sistem_prijave_za_poso.Dto.JobKorisnikDto;

public interface JobKorisnikService {
    JobKorisnikDto createJobKorisnik(JobKorisnikDto jobKorisnikDto);

    JobKorisnikDto getJobKorisnikById(int id);

    List<JobKorisnikDto> getAllJobsKorisnik();

    JobKorisnikDto updateJob(int id, JobKorisnikDto jobKorisnikDto);

    void deleteJobKorisnik(int id);

    List<JobKorisnikDto> getAllJobsByKorisnik(int korisnikId);

    public int getBrojPrijavaZaPosao(int posaoId);

    public List<Integer> getKorisniciZaPosao(int posaoId);

    public List<String> getEmailsForJob(int posaoId);

    
}
