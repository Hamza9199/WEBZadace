package com.example.sistem_prijave_za_poso.Repositories;

import com.example.sistem_prijave_za_poso.Models.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCategoryRepository extends JpaRepository<JobCategory, Integer> {
}
