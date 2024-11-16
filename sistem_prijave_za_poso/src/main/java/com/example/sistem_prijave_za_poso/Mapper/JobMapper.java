package com.example.sistem_prijave_za_poso.Mapper;

import com.example.sistem_prijave_za_poso.Dto.JobDto;
import com.example.sistem_prijave_za_poso.Models.Job;

public class JobMapper {
     public static JobDto mapToJobDto(Job job){
        return new JobDto(job.getId());
    }

    public static Job mapToJob(JobDto jobDto){
        return new Job(jobDto.getId());
    }
}
