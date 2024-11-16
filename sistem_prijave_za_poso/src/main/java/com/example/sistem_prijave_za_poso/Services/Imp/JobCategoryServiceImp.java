package com.example.sistem_prijave_za_poso.Services.Imp;

import com.example.sistem_prijave_za_poso.Exception.ResourceNotFoundException;
import com.example.sistem_prijave_za_poso.Models.JobCategory;
import com.example.sistem_prijave_za_poso.Repositories.JobCategoryRepository;
import com.example.sistem_prijave_za_poso.Services.JobCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobCategoryServiceImp implements JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    public JobCategoryServiceImp(JobCategoryRepository jobCategoryRepository) {
        this.jobCategoryRepository = jobCategoryRepository;
    }

    @Override
    public JobCategory addCategory(JobCategory category) {
        return jobCategoryRepository.save(category);
    }

    @Override
    public JobCategory updateCategory(int id, JobCategory category) {
        JobCategory existingCategory = jobCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategorija sa ID-em " + id + " nije pronađena."));
        existingCategory.setNaziv(category.getNaziv());
        return jobCategoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(int id) {
        JobCategory category = jobCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategorija sa ID-em " + id + " nije pronađena."));
        jobCategoryRepository.delete(category);
    }

    @Override
    public List<JobCategory> getAllCategories() {
        return jobCategoryRepository.findAll();
    }
}
