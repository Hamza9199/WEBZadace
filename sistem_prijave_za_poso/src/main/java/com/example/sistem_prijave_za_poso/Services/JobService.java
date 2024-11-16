package com.example.sistem_prijave_za_poso.Services;

import com.example.sistem_prijave_za_poso.Dto.JobDto;

import java.util.List;

public interface JobService {

    JobDto createJob(JobDto jobDto);

    JobDto getJobById(int id);

    List<JobDto> getAllJobs();

    JobDto updateJob(int id, JobDto jobDto);

    void deleteJob(int id);

    List<JobDto> getJobsByCategory(int kategorijaId); 

    List<JobDto> filterJobsByCategory(String category);

}
