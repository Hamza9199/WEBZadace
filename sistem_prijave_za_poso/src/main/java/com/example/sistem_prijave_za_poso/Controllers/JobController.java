package com.example.sistem_prijave_za_poso.Controllers;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistem_prijave_za_poso.Dto.JobDto;
import com.example.sistem_prijave_za_poso.Dto.KorisnikDto;
import com.example.sistem_prijave_za_poso.Repositories.JobRepository;
import com.example.sistem_prijave_za_poso.Services.JobService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class JobController {

    private final JobService jobService;



    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping( "/job" )
	public ResponseEntity<JobDto> createJob (@RequestBody JobDto jobDto)
	{
		JobDto savedJob = jobService.createJob(jobDto);
		return new ResponseEntity<>(savedJob, org.springframework.http.HttpStatus.CREATED);
	}

    
}
