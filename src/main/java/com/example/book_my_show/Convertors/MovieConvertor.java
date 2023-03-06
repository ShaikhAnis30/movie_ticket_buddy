package com.example.book_my_show.Convertors;

import com.example.book_my_show.Entities.Movie;
import com.example.book_my_show.EntryDtos.MovieEntryDto;

public class MovieConvertor {

    public static Movie convertDtoToEntity(MovieEntryDto movieEntryDto) {
        Movie movie = Movie.builder().movieName(movieEntryDto.getMovieName())
                .genre(movieEntryDto.getGenre()).rating(movieEntryDto.getRating())
                .duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage())
                .build();
        return movie;
    }
}
