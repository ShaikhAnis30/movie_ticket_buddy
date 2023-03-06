package com.example.book_my_show.EntryDtos;

import lombok.Data;

@Data
public class TheaterEntryDto {
    private String name;

    private String location;

    private int noOfClassicSeat; //how many classic seats you want

    private int noOfPremiumSeat; //how many premium seats you want
}
