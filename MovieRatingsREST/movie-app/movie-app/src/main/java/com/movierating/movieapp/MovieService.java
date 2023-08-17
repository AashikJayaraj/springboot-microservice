package com.movierating.movieapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class MovieService {
    @Autowired
    MovieRepo movieRepo;

    public Long addMovie(Movie movie){
        return movieRepo.save(movie).getId();
    }

    public Movie findById(String movieId) {
        return movieRepo.findById(Long.valueOf(movieId)).orElseThrow(()-> new RuntimeException("MOVIE not found"));
    }
}
