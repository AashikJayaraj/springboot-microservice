package com.movierating.reviewapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    RestTemplate restTemplate;
    public Long addReview(Review review) {

        return reviewRepo.save(review).getId();
    }

    public List<ReviewDto> getReviewByUser(String userId) {
        List<Review> reviews = reviewRepo.findByUserid(userId);
        System.out.println("Service REVIEW===>");
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : reviews){
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setReview(review.getReview());
            MovieDto movieDto = restTemplate.getForObject("http://MOVIE-APP/movie/"+review.getMovieid(),MovieDto.class);
            reviewDto.setMovieDto(movieDto);
            reviewDtos.add(reviewDto);
            System.out.println("Service REVIEW===>"+reviewDto.getMovieDto().getMovieName());
        }

        return reviewDtos;
    }
}
