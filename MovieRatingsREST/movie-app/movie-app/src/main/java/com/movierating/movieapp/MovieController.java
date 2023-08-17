package com.movierating.movieapp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody MovieDto movieDto){
        Movie movie = new Movie();
        System.out.println(movieDto.getMovieName());
        System.out.println(movieDto.getDirector());
        movie.setMovieName(movieDto.getMovieName());
        movie.setDirector(movieDto.getDirector());
        Long id = movieService.addMovie(movie);
        return new ResponseEntity<>("Movie Added "+id, HttpStatus.OK);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMoviebyId(@PathVariable String movieId){
        System.out.println("MOVIE===>");
        Movie movie = movieService.findById(movieId);
        MovieDto movieDto = modelMapper.map(movie,MovieDto.class);
        System.out.println("MOVIE===>"+movieDto.getMovieName());
        return new ResponseEntity<>(movieDto,HttpStatus.OK);
    }
}
