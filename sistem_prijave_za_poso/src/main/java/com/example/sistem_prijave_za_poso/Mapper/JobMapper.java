package com.example.sistem_prijave_za_poso.Mapper;

import com.example.sistem_prijave_za_poso.Dto.JobDto;
import com.example.sistem_prijave_za_poso.Models.Job;
import com.example.sistem_prijave_za_poso.Models.JobCategory;

public class JobMapper {

    public static JobDto mapToJobDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getNaziv(),
                job.getOpis(),
                job.getRating(),
                job.getKategorija().getId() 
        );
    }

    public static JobDto mapToJobDto(JobCategory jobCategory) {
        return new JobDto(
                0, 
                jobCategory.getNaziv(), 
                "", 
                0, 
                jobCategory.getId() 
        );
    }

    public static Job mapToJob(JobDto jobDto, JobCategory kategorija) {
        return new Job(
                jobDto.getId(),
                jobDto.getNaziv(),
                jobDto.getOpis(),
                jobDto.getRating(),
                kategorija 
        );
    }
}
