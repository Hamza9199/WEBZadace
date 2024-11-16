package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Models.JobCategory;
import com.example.sistem_prijave_za_poso.Services.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/average-rating")
    public ResponseEntity<Double> getAverageRating() {
        double averageRating = statisticsService.getAverageRating();
        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/jobs-by-category")
    public ResponseEntity<Map<JobCategory, Long>> getJobCountByCategory() {
        Map<JobCategory, Long> jobCountByCategory = statisticsService.getJobCountByCategory();
        return ResponseEntity.ok(jobCountByCategory);
    }

    @GetMapping("/total-jobs")
    public ResponseEntity<Long> getTotalJobs() {
        long totalJobs = statisticsService.getTotalJobs();
        return ResponseEntity.ok(totalJobs);
    }
}
