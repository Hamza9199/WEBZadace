package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Services.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

   

    @GetMapping("/total-jobs")
    public ResponseEntity<Long> getTotalJobs() {
        long totalJobs = statisticsService.getTotalJobs();
        return ResponseEntity.ok(totalJobs);
    }
}
