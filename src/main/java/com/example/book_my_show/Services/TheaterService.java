package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.TheaterConvertor;
import com.example.book_my_show.Entities.Movie;
import com.example.book_my_show.Entities.Theater;
import com.example.book_my_show.Entities.TheaterSeat;
import com.example.book_my_show.EntryDtos.TheaterEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Repositories.MovieRepository;
import com.example.book_my_show.Repositories.TheaterRepository;
import com.example.book_my_show.Repositories.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public void addTheater(TheaterEntryDto theaterEntryDto) throws Exception {
        if(theaterEntryDto.getName() == null || theaterEntryDto.getLocation() == null)
            throw new Exception("Theater name or Location is Invalid");
        Theater theater = TheaterConvertor.convertDtoToEntity(theaterEntryDto);

        //I also have to generate TheaterSeats when Theater is created
        List<TheaterSeat> seatsInTheater = generateSeats(theaterEntryDto, theater);
        theater.setTotalSeatsInTheater(seatsInTheater);

        theaterRepository.save(theater);
    }

    private List<TheaterSeat> generateSeats(TheaterEntryDto theaterEntryDto, Theater theater) {
        int classicSeats = theaterEntryDto.getNoOfClassicSeat();
        int premiumSeats = theaterEntryDto.getNoOfPremiumSeat();

        List<TheaterSeat> totalTheaterSeats = new ArrayList<>();
        //generating classic seats
        for (int count = 1; count <= classicSeats; count++) {
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count + "C")
                    .theater(theater).build();
            totalTheaterSeats.add(theaterSeat);
        }

        //generating Premium Seats
        for (int count = 1; count <= premiumSeats; count++) {
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .seatType(SeatType.PREMIUM).seatNo(count + "P")
                    .theater(theater).build();
            totalTheaterSeats.add(theaterSeat);
        }

        //we have list of TheaterSeat, hence saveAll.
        //theaterSeatRepository.saveAll(totalTheaterSeats); no need as we will save Parent

        return totalTheaterSeats;
    }

    //get theaters that are showing a particular movie
//    public List<Theater> getTheatersShowingThisMovie(int movieId) {
//        List<Theater> resultList = new ArrayList<>();
//        Movie movie = movieRepository.findById(movieId).get();
//        resultList = movie.getTheaterList();
//        return resultList;
//    }
}
