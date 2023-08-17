package com.movierating.reviewapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/addReview/{userId}/{movieId}")
    public ResponseEntity<String> addReview(@RequestBody ReviewDto reviewDto, @PathVariable String userId, @PathVariable String movieId){
        Review review = new Review();
        review.setReview(reviewDto.getReview());
        review.setUserid(userId);
        review.setMovieid(movieId);
        Long id = reviewService.addReview(review);
        return new ResponseEntity<>("REVIEW added "+ id, HttpStatus.OK);
    }

    @GetMapping("/userReview/{userId}")
    public ResponseEntity<List<ReviewDto>> getReviewByUser(@PathVariable String userId){
        System.out.println("Controller REVIEW===>");
        List<ReviewDto> reviews = reviewService.getReviewByUser(userId);
        for (ReviewDto reviewDto : reviews){
            System.out.println("Controller REVIEW===>"+reviewDto.getReview()+" "+ reviewDto.getMovieDto().getMovieName());
        }
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }
}
