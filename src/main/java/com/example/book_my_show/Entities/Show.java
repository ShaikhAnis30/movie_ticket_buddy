package com.example.book_my_show.Entities;

import com.example.book_my_show.Enums.ShowType;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Shows")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    @Enumerated(EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    //parent(show)
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<ShowSeat> listOfShowSeats = new ArrayList<>();
    //mapping done form show to showSeat


    //also one theater have many Shows
    //child(show)
    @ManyToOne
    @JoinColumn
    private Theater theater;
    //mapping from show to theater is done


    //child(show)
    @ManyToOne
    @JoinColumn
    private Movie movie;
    //mapping from Show to Movie done


    //parent(Show)
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Ticket> bookedTickets = new ArrayList<>();
}
