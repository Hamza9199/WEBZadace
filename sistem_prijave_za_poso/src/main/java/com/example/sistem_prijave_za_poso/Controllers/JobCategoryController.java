package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Models.JobCategory;
import com.example.sistem_prijave_za_poso.Repositories.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kategorija")
public class JobCategoryController {

    private final JobCategoryRepository jobCategoryRepository;

    @Autowired
    public JobCategoryController(JobCategoryRepository jobCategoryRepository) {
        this.jobCategoryRepository = jobCategoryRepository;
    }

    @GetMapping
    public List<JobCategory> getAllCategories() {
        return jobCategoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCategory> getCategoryById(@PathVariable Integer id) {
        Optional<JobCategory> jobCategory = jobCategoryRepository.findById(id);
        return jobCategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JobCategory> createCategory(@RequestBody JobCategory jobCategory) {
        if (jobCategory.getNaziv() == null || jobCategory.getNaziv().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        JobCategory savedCategory = jobCategoryRepository.save(jobCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobCategory> updateCategory(@PathVariable Integer id, @RequestBody JobCategory jobCategory) {
        Optional<JobCategory> existingCategory = jobCategoryRepository.findById(id);
        
        if (existingCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        jobCategory.setId(id);  
        JobCategory updatedCategory = jobCategoryRepository.save(jobCategory);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        if (!jobCategoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        jobCategoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
