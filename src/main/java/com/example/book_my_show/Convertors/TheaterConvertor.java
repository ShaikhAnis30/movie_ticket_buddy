package com.example.book_my_show.Convertors;

import com.example.book_my_show.Entities.Theater;
import com.example.book_my_show.Entities.TheaterSeat;
import com.example.book_my_show.EntryDtos.TheaterEntryDto;

import java.util.ArrayList;
import java.util.List;

public class TheaterConvertor {

    public static Theater convertDtoToEntity(TheaterEntryDto theaterEntryDto) {
        Theater theater = Theater.builder().name(theaterEntryDto.getName())
                .location(theaterEntryDto.getLocation()).build();
        return theater;
    }
}
