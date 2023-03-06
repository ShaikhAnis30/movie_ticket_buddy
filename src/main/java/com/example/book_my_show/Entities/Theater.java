package com.example.book_my_show.Entities;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Theater")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    //parent of TheaterSeat
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterSeat> totalSeatsInTheater = new ArrayList<>();


    //parent(Theater)
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Show> listOfShows = new ArrayList<>();
    //mapping from Theater to show is done


}
