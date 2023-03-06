package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.MovieConvertor;
import com.example.book_my_show.Entities.Movie;
import com.example.book_my_show.EntryDtos.MovieEntryDto;
import com.example.book_my_show.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(MovieEntryDto  movieEntryDto) {
        Movie movie = MovieConvertor.convertDtoToEntity(movieEntryDto);

        movieRepository.save(movie);
    }

    //get movie with max no of shows

}
