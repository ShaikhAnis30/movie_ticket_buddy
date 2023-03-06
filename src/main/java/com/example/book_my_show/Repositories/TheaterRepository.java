package com.example.book_my_show.Repositories;

import com.example.book_my_show.Entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {
}
