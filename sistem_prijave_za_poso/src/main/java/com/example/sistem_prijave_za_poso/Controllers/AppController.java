package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Dto.JobDto;
import com.example.sistem_prijave_za_poso.Models.Job;
import com.example.sistem_prijave_za_poso.Models.JobCategory;
import com.example.sistem_prijave_za_poso.Services.JobCategoryService;
import com.example.sistem_prijave_za_poso.Services.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/jobs")
public class AppController {

    private final JobCategoryService jobCategoryService;
    private final JobService jobService;

    public AppController(JobCategoryService jobCategoryService, JobService jobService) {
        this.jobCategoryService = jobCategoryService;
        this.jobService = jobService;
    }

    @PostMapping("/categories")
    public ResponseEntity<JobCategory> addCategory(@RequestBody JobCategory category) {
        JobCategory savedCategory = jobCategoryService.addCategory(category);
        return ResponseEntity.status(201).body(savedCategory);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<JobCategory>> getAllCategories() {
        List<JobCategory> categories = jobCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<JobCategory> updateCategory(@PathVariable int id, @RequestBody JobCategory category) {
        JobCategory updatedCategory = jobCategoryService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        jobCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<JobDto>> filterJobsByCategory(@RequestParam String category) {
        List<JobDto> filteredJobs = jobService.filterJobsByCategory(category);
        return ResponseEntity.ok(filteredJobs);
    }
}
