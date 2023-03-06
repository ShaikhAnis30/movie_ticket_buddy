package com.example.book_my_show.Controllers;

import com.example.book_my_show.EntryDtos.MovieEntryDto;
import com.example.book_my_show.Services.MovieService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto) {
        movieService.addMovie(movieEntryDto);
        return new ResponseEntity<>("Movie Created Successfully", HttpStatus.CREATED);
    }
}
