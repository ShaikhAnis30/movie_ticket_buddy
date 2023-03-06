package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.ShowConvertor;
import com.example.book_my_show.Entities.*;
import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Enums.ShowType;
import com.example.book_my_show.Repositories.MovieRepository;
import com.example.book_my_show.Repositories.ShowRepository;
import com.example.book_my_show.Repositories.TheaterRepository;
import org.apache.catalina.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    public void addShow(ShowEntryDto showEntryDto) throws Exception {
        Show show = ShowConvertor.convertDtoToEntity(showEntryDto); //3- Aur is movie ka ye ek Show hai

        //set foreign key attributes(mapping attributes)
        int movieId = showEntryDto.getMovieId(); //2- Ye movie Lagi hai
        int theaterId = showEntryDto.getTheaterId(); //1- Is theater me

        if(!movieRepository.existsById(movieId))
            throw new Exception("Movie does not exist by this id");
        if(!theaterRepository.existsById(theaterId))
            throw new Exception("Theater does not exist by this id");

        Movie movie = movieRepository.findById(movieId).get();
        Theater theater = theaterRepository.findById(theaterId).get();

        show.setMovie(movie);
        show.setTheater(theater);

        //creating showSeats
        List<ShowSeat> showSeatList = createShowSeats(showEntryDto, theater, show);
        show.setListOfShowSeats(showSeatList);

        //Imp step to avoid saving of show and seats 2 times is,
        //save show before saving movie and theater
        show = showRepository.save(show); //this will set the id attribute


        //now I have to update attributes of parent Entities
        //between Theater and Show
        List<Show> allShowsInTheater = theater.getListOfShows();
        allShowsInTheater.add(show);
        theater.setListOfShows(allShowsInTheater);
        theaterRepository.save(theater);//parent

        //between Movie and Show
        List<Show> allShowsOfThisMovie = movie.getShowList();
        allShowsOfThisMovie.add(show);
        movie.setShowList(allShowsOfThisMovie);
        movieRepository.save(movie);//parent

    }

    private List<ShowSeat> createShowSeats(ShowEntryDto showEntryDto, Theater theater, Show show) {
        int classicSeatPrice = showEntryDto.getClassicSeatPrice();
        int premiumSeatPrice = showEntryDto.getPremiumSeatPrice();

        List<ShowSeat> showSeatList = new ArrayList<>();
        List<TheaterSeat> allSeatsInTheater = theater.getTotalSeatsInTheater();
        for (TheaterSeat theaterSeat : allSeatsInTheater) {
            ShowSeat showSeat = ShowSeat.builder().seatTypes(theaterSeat.getSeatType())
                    .seatNo(theaterSeat.getSeatNo()).build();
            //SeatType and seatNo taken from TheaterSeat

            //now I will set price according to Classic and Premium Seat
            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)) {
                if(show.getShowType().equals(ShowType._2D))
                    showSeat.setPrice(classicSeatPrice);
                else
                    showSeat.setPrice(classicSeatPrice + 50); //50 extra for 3D
            }
            else {
                if (show.getShowType().equals(ShowType._2D))
                    showSeat.setPrice(premiumSeatPrice);
                else
                    showSeat.setPrice(premiumSeatPrice + 50); //50 extra for 3D
            }

            showSeat.setBooked(false);
            showSeat.setShow(show);  //all these seats belongs to this show
            showSeatList.add(showSeat);
        }
        return showSeatList;
    }

    //get show timing of particular movie and theater
    public String getShowTiming(int movieId, int theaterId) throws Exception {
        if(!movieRepository.existsById(movieId)) throw new Exception("Movie Does Not Exist");

        if(!theaterRepository.existsById(theaterId)) throw new Exception("Theater Does Not Exist");

        Show show = showRepository.getShowTime(movieId,theaterId);
        LocalTime time = show.getShowTime();
        return time.toString();
    }
}
