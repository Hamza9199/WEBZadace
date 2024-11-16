package com.example.sistem_prijave_za_poso.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistem_prijave_za_poso.Models.Job;


@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{
    
}
