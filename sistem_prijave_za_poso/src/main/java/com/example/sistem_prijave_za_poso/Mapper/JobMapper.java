package com.example.sistem_prijave_za_poso.Mapper;

import com.example.sistem_prijave_za_poso.Dto.JobDto;
import com.example.sistem_prijave_za_poso.Models.Job;

public class JobMapper {

    public static JobDto mapToJobDto(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job entity must not be null");
        }

        return new JobDto(
                job.getId(),
                job.getNaziv(),
                job.getOpis(),
                job.getRating(),
                job.getKategorija() 
        );
    }

    public static Job mapToJob(JobDto jobDto) {
        if (jobDto == null) {
            throw new IllegalArgumentException("JobDto must not be null");
        }

        return new Job(
                jobDto.getId(),
                jobDto.getNaziv(),
                jobDto.getOpis(),
                jobDto.getRating(),
                jobDto.getKategorija() 
        );
    }
}

