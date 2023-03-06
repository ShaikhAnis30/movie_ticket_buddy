package com.example.book_my_show.Entities;


import com.example.book_my_show.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Show_seats")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;

    private int price;

    private String seatNo; //1P or 1C

    @Enumerated(value = EnumType.STRING)
    private SeatType seatTypes;

    private Date bookedAt;

    //child(showSeat)
    @ManyToOne
    @JoinColumn
    private Show show;
    //mapping done from showSeat to show
}
