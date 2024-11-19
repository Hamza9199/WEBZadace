package com.example.sistem_prijave_za_poso.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistem_prijave_za_poso.Dto.JobKorisnikDto;
import com.example.sistem_prijave_za_poso.Services.Imp.JobKorisnikServiceImp;

@RestController
@RequestMapping("/api/job-korisnik")
public class JobKorisnikController {

    private final JobKorisnikServiceImp jobKorisnikService;

    public JobKorisnikController(JobKorisnikServiceImp jobKorisnikService) {
        this.jobKorisnikService = jobKorisnikService;
    }

    @PostMapping
    public ResponseEntity<JobKorisnikDto> createJobKorisnik(@RequestBody JobKorisnikDto jobKorisnikDto) {
        
        boolean isAlreadyApplied = jobKorisnikService.isUserAlreadyApplied(jobKorisnikDto.getKorisnikId(), jobKorisnikDto.getPosaoId());
    
        if (isAlreadyApplied) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(jobKorisnikService.createJobKorisnik(jobKorisnikDto));
    }

    @GetMapping("/{posaoId}/broj-prijava")
    public ResponseEntity<Integer> getBrojPrijavaZaPosao(@PathVariable int posaoId) {
        return ResponseEntity.ok(jobKorisnikService.getBrojPrijavaZaPosao(posaoId));
    }

    @GetMapping("/{posaoId}/korisnici")
    public ResponseEntity<List<Integer>> getKorisniciZaPosao(@PathVariable int posaoId) {
        return ResponseEntity.ok(jobKorisnikService.getKorisniciZaPosao(posaoId));
    }

    @GetMapping("/{posaoId}/emails")
    public ResponseEntity<List<String>> getEmailsForJob(@PathVariable int posaoId) {
        List<String> emails = jobKorisnikService.getEmailsForJob(posaoId);
        return ResponseEntity.ok(emails);
    }
}

