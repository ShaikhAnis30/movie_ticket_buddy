package com.example.book_my_show.Repositories;

import com.example.book_my_show.Entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query(value = "select * from shows where movie_id=:movieId and theater_id=:theaterId", nativeQuery = true)
    Show getShowTime(int movieId, int theaterId);
}

