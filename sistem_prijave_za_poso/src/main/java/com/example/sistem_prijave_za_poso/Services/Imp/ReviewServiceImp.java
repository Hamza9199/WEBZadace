package com.example.sistem_prijave_za_poso.Services.Imp;

import com.example.sistem_prijave_za_poso.Dto.ReviewDto;
import com.example.sistem_prijave_za_poso.Exception.ResourceNotFoundException;
import com.example.sistem_prijave_za_poso.Mapper.ReviewMapper;
import com.example.sistem_prijave_za_poso.Models.Review;
import com.example.sistem_prijave_za_poso.Repositories.ReviewRepository;
import com.example.sistem_prijave_za_poso.Services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImp(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = ReviewMapper.mapToReview(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return ReviewMapper.mapToReviewDto(savedReview);
    }

    @Override
    public ReviewDto getReviewById(int id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recenzija sa ID-em " + id + " nije pronađena."));
        return ReviewMapper.mapToReviewDto(review);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::mapToReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto updateReview(int id, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recenzija sa ID-em " + id + " nije pronađena."));
        review.setSadrzaj(reviewDto.getSadrzaj());
        review.setOcjena(reviewDto.getOcjena());
        Review updatedReview = reviewRepository.save(review);
        return ReviewMapper.mapToReviewDto(updatedReview);
    }

    @Override
    public void deleteReview(int id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recenzija sa ID-em " + id + " nije pronađena."));
        reviewRepository.delete(review);
    }
}
