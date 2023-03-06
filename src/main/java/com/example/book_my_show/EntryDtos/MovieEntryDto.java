package com.example.book_my_show.EntryDtos;

import com.example.book_my_show.Entities.Theater;
import com.example.book_my_show.Enums.Genre;
import com.example.book_my_show.Enums.Language;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieEntryDto {
    private String movieName;

    private double rating;

    private int duration;

    private Language language;

    private Genre genre;

}
