package com.example.sistem_prijave_za_poso.Services;

import java.util.Map;

import com.example.sistem_prijave_za_poso.Models.JobCategory;

public interface StatisticsService {
    double getAverageRating();
    Map<JobCategory, Long> getJobCountByCategory();
    long getTotalJobs();
}
