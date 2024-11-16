package com.example.sistem_prijave_za_poso.Services.Imp;

import com.example.sistem_prijave_za_poso.Models.Job;
import com.example.sistem_prijave_za_poso.Models.JobCategory;
import com.example.sistem_prijave_za_poso.Repositories.JobRepository;
import com.example.sistem_prijave_za_poso.Services.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImp implements StatisticsService {

    private final JobRepository jobRepository;

    public StatisticsServiceImp(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public double getAverageRating() {
        return jobRepository.findAll()
                .stream()
                .mapToInt(Job::getRating) 
                .average()
                .orElse(0.0); 
    }

    @Override
    public Map<JobCategory, Long> getJobCountByCategory() {
        return jobRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Job::getKategorija, Collectors.counting()));
    }

    @Override
    public long getTotalJobs() {
        return jobRepository.count();
    }
}
