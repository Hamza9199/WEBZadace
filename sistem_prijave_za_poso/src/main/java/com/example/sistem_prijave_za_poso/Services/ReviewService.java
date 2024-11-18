package com.example.sistem_prijave_za_poso.Services;

import com.example.sistem_prijave_za_poso.Dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto getReviewById(int id);
    List<ReviewDto> getAllReviews();
    ReviewDto updateReview(int id, ReviewDto reviewDto);
    void deleteReview(int id);
    List<ReviewDto> getAllReviewsKorisnik(int korisnikid);
    List<ReviewDto> getAllReviewsPosao(int posaoid);


}
