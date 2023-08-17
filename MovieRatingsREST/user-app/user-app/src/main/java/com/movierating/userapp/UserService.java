package com.movierating.userapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    RestTemplate restTemplate;

    public Long saveUser(User user){
        return userRepo.save(user).getId();
    }

    public User getUserById(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("USER not found"));
        RatingDto[] ratings = restTemplate.getForObject("http://REVIEW-APP/review/userReview/1",RatingDto[].class);
        System.out.println("USER===>");
        List<RatingDto> ratingList = Arrays.asList(ratings);
        for(RatingDto rating : ratingList){
            System.out.println("USER===>"+rating.getReview()+" "+ rating.getMovieDto());
        }
        user.setRatings(Arrays.asList(ratings));
        return user;
    }
}
