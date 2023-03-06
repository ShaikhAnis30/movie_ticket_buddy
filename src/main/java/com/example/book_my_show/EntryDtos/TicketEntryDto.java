package com.example.book_my_show.EntryDtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketEntryDto {

    private List<String> requestedSeats = new ArrayList<>();

    private int userId; //this user want to book

    private int showId;//tickets of this show

}
