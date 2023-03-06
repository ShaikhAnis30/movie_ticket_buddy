package com.example.book_my_show.Entities;


import com.example.book_my_show.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Theater_Seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private String seatNo;


    //child of Theater
    @ManyToOne
    @JoinColumn
    private Theater theater;


}
