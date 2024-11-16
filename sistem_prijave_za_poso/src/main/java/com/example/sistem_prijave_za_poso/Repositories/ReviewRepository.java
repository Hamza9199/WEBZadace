package com.example.sistem_prijave_za_poso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistem_prijave_za_poso.Models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
}
