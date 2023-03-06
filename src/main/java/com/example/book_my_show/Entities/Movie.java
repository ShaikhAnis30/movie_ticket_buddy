package com.example.book_my_show.Entities;

import com.example.book_my_show.Enums.Genre;
import com.example.book_my_show.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Movie")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String movieName;

    private double rating;

    private int duration;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;


    //child(Movie) --> ek movie ke bohot sare shows in different theaters
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();
    //mapping form Movie to show done

}
