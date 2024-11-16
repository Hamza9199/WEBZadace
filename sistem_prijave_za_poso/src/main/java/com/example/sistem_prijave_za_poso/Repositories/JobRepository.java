package com.example.sistem_prijave_za_poso.Repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistem_prijave_za_poso.Dto.JobDto;
import com.example.sistem_prijave_za_poso.Models.Job;
import com.example.sistem_prijave_za_poso.Models.JobCategory;


@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{

    Optional<JobCategory> findByKategorija(JobCategory kategorija);

    List<JobDto> findByKategorija2(String kategorija);
    
}
