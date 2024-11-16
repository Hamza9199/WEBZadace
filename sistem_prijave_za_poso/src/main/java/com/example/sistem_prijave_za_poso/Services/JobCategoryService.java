package com.example.sistem_prijave_za_poso.Services;

import com.example.sistem_prijave_za_poso.Models.JobCategory;

import java.util.List;

public interface JobCategoryService {
    JobCategory addCategory(JobCategory category);
    JobCategory updateCategory(int id, JobCategory category);
    void deleteCategory(int id);
    List<JobCategory> getAllCategories();
}
