package com.example.sistem_prijave_za_poso.Mapper;

import com.example.sistem_prijave_za_poso.Dto.ReviewDto;
import com.example.sistem_prijave_za_poso.Models.Review;

public class ReviewMapper {

    public static ReviewDto mapToReviewDto(Review review) {
        return new ReviewDto(review.getId(), review.getSadrzaj(), review.getOcjena(), review.getKorisnikId(), review.getPosaoId());
    }

    public static Review mapToReview(ReviewDto reviewDto) {
        return new Review(reviewDto.getId(), reviewDto.getSadrzaj(), reviewDto.getOcjena(), reviewDto.getKorisnikId(), reviewDto.getPosaoId());
    }
}
