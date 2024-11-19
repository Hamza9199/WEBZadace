package com.example.sistem_prijave_za_poso.Services.Imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sistem_prijave_za_poso.Dto.JobKorisnikDto;
import com.example.sistem_prijave_za_poso.Mapper.JobKorisnikMapper;
import com.example.sistem_prijave_za_poso.Models.JobKorisnik;
import com.example.sistem_prijave_za_poso.Repositories.JobKorisnikRepository;
import com.example.sistem_prijave_za_poso.Services.JobKorisnikService;


@Service
public class JobKorisnikServiceImp implements JobKorisnikService {

    private final JobKorisnikRepository jobKorisnikRepository;

    public JobKorisnikServiceImp(JobKorisnikRepository jobKorisnikRepository) {
        this.jobKorisnikRepository = jobKorisnikRepository;
    }

    @Override
    public JobKorisnikDto createJobKorisnik(JobKorisnikDto jobKorisnikDto) {
        
        JobKorisnik jobKorisnik = JobKorisnikMapper.mapToJobKorisnik(jobKorisnikDto);
        JobKorisnik savedJobKorisnik = jobKorisnikRepository.save(jobKorisnik);
        return JobKorisnikMapper.mapToJobKorisnikDto(savedJobKorisnik);
    }

    @Override
    public JobKorisnikDto getJobKorisnikById(int id) {
        JobKorisnik jobKorisnik = jobKorisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava sa ID-em " + id + " nije pronađena."));
        return JobKorisnikMapper.mapToJobKorisnikDto(jobKorisnik);
    }

    @Override
    public List<JobKorisnikDto> getAllJobsKorisnik() {
        List<JobKorisnik> jobKorisnikList = jobKorisnikRepository.findAll();
        return jobKorisnikList.stream()
                .map(JobKorisnikMapper::mapToJobKorisnikDto)
                .toList();
    }

    @Override
    public JobKorisnikDto updateJob(int id, JobKorisnikDto jobKorisnikDto) {
        JobKorisnik jobKorisnik = jobKorisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava sa ID-em " + id + " nije pronađena."));
        jobKorisnik.seKorisnikId(jobKorisnikDto.getKorisnikId());
        jobKorisnik.setPosaoId(jobKorisnikDto.getPosaoId());
        JobKorisnik updatedJobKorisnik = jobKorisnikRepository.save(jobKorisnik);
        return JobKorisnikMapper.mapToJobKorisnikDto(updatedJobKorisnik);
    }

    @Override
    public void deleteJobKorisnik(int id) {
        jobKorisnikRepository.deleteById(id);
    }

    @Override
    public List<JobKorisnikDto> getAllJobsByKorisnik(int korisnikId) {
        List<JobKorisnik> jobKorisnikList = jobKorisnikRepository.findByKorisnikId(korisnikId);
        return jobKorisnikList.stream()
                .map(JobKorisnikMapper::mapToJobKorisnikDto)
                .toList();
    }

    @Override
    public int getBrojPrijavaZaPosao(int posaoId) {
        return jobKorisnikRepository.countByPosaoId(posaoId);
    }

    @Override
    public List<Integer> getKorisniciZaPosao(int posaoId) {
        return jobKorisnikRepository.findKorisniciByPosaoId(posaoId);
    }

    @Override
    public List<String> getEmailsForJob(int posaoId) {
        return jobKorisnikRepository.findEmailsByPosaoId(posaoId);
    }

    public boolean isUserAlreadyApplied(int korisnikId, int posaoId) {
        return jobKorisnikRepository.existsByKorisnikIdAndPosaoId(korisnikId, posaoId);
    }

}
