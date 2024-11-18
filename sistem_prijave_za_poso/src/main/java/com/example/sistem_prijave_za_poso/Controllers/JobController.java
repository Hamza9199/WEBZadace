package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Dto.JobDto;
import com.example.sistem_prijave_za_poso.Services.JobService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto) {
        JobDto savedJob = jobService.createJob(jobDto); 
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }
    


    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable int id) {
        JobDto job = jobService.getJobById(id);
        return ResponseEntity.ok(job);
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> getAllJobs() {
        List<JobDto> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }


    @GetMapping("/kreator/{kreatorid}")
    public ResponseEntity<List<JobDto>> getAllJobsKreator(@PathVariable int kreatorid) {
        List<JobDto> jobs = jobService.getAllJobsKreator(kreatorid);
        return ResponseEntity.ok(jobs);
    }


    @PutMapping("/{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable int id, @RequestBody JobDto jobDto) {
        JobDto updatedJob = jobService.updateJob(id, jobDto);
        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable int id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    
}
