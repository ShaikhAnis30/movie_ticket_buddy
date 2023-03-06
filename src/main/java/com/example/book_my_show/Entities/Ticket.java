package com.example.book_my_show.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "Ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movieName;

    private String theaterName;

    private LocalDate showDate;

    private LocalTime showTime;

    private int totalAmount;

    private String bookedSeats; //List of requested seats in String form "1p, 2p, 3p, 4p"

    private String ticketId = UUID.randomUUID().toString();

    //child(Ticket)
    @ManyToOne
    @JoinColumn
    private User user;
    // Ticket to User mapping Done


    //child(Ticket)
    @ManyToOne       //There are many tickets of one show
    @JoinColumn
    private Show show;
    //mapping form Ticket to show done
}
