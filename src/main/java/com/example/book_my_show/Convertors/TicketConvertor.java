package com.example.book_my_show.Convertors;

import com.example.book_my_show.Entities.Ticket;
import com.example.book_my_show.EntryDtos.TicketEntryDto;

public class TicketConvertor {
    public static Ticket convertDtoToEntity(TicketEntryDto ticketEntryDto) {
        Ticket ticket = new Ticket();
        //nothing to convert
        return ticket;
    }
}
