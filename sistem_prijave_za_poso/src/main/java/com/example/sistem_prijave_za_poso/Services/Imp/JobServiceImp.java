package com.example.sistem_prijave_za_poso.Services.Imp;

import com.example.sistem_prijave_za_poso.Dto.JobDto;
import com.example.sistem_prijave_za_poso.Mapper.JobMapper;
import com.example.sistem_prijave_za_poso.Models.Job;
import com.example.sistem_prijave_za_poso.Repositories.JobRepository;
import com.example.sistem_prijave_za_poso.Services.JobService;
 

public class JobServiceImp implements JobService{

    private final JobRepository jobRepository;


    public JobServiceImp(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    
    @Override
	public JobDto createJob (JobDto jobDto)
	{

		Job job = JobMapper.mapToJob(jobDto);

		Job savedJob = jobRepository.save(job);

		return JobMapper.mapToJobDto(savedJob);
	}

    
}
