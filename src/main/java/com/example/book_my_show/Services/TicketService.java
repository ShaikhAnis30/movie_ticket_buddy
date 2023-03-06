package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.TicketConvertor;
import com.example.book_my_show.Entities.Show;
import com.example.book_my_show.Entities.ShowSeat;
import com.example.book_my_show.Entities.Ticket;
import com.example.book_my_show.Entities.User;
import com.example.book_my_show.EntryDtos.TicketEntryDto;
import com.example.book_my_show.Repositories.ShowRepository;
import com.example.book_my_show.Repositories.TicketRepository;
import com.example.book_my_show.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public void bookTicket(TicketEntryDto ticketEntryDto) throws Exception {
        //Convert Dto To Entity
        Ticket ticket = TicketConvertor.convertDtoToEntity(ticketEntryDto);

        //check if seats requested by user are valid or not
        boolean isValidRequestedSeats = checkIfSeatsAreValid(ticketEntryDto);
        if(!isValidRequestedSeats) throw new Exception("Tickets are already Booked");

        //now at this point all the requested seats are valid
        //So I will calculate totalAmount
        int totalAmount = calculateTotalAmount(ticketEntryDto);

        //now setting attributes
        Show show = showRepository.findById(ticketEntryDto.getShowId()).get(); //found show
        User user = userRepository.findById(ticketEntryDto.getUserId()).get(); //found user

        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setTheaterName(show.getTheater().getName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTotalAmount(totalAmount);

        String requestedSeats = formattingOfBookedSeats(ticketEntryDto.getRequestedSeats());
        ticket.setBookedSeats(requestedSeats);

        //now set foreignKey attributes(mapping attributes)
        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket); //to avoid multiple entries

        //between User and Ticket
        List<Ticket> bookedTicketsOfUser = user.getBookedTickets();
        bookedTicketsOfUser.add(ticket);
        user.setBookedTickets(bookedTicketsOfUser);
        userRepository.save(user); //parent

        //between Show and Ticket
        List<Ticket> bookedTicketsOfThisShow = show.getBookedTickets();
        bookedTicketsOfThisShow.add(ticket);
        show.setBookedTickets(bookedTicketsOfThisShow);
        showRepository.save(show);

        String body = "Hi this is to confirm your booking for seatNo: " + requestedSeats  + " for the movie: " + ticket.getMovieName();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("nexobackend@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);
    }

    private String formattingOfBookedSeats(List<String> requestedSeats) {
        StringBuilder sb = new StringBuilder();
        for (String seatNo : requestedSeats) {
            sb.append(seatNo + ", ");
        }

        return sb.toString();
    }

    private boolean checkIfSeatsAreValid(TicketEntryDto ticketEntryDto) {
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int showId = ticketEntryDto.getShowId();
        Show show = showRepository.findById(showId).get();
        List<ShowSeat> allSeatsOfShow = show.getListOfShowSeats();

        for (ShowSeat showSeat : allSeatsOfShow) {
            String seatNo = showSeat.getSeatNo();

            if(requestedSeats.contains(seatNo)) {
                if(showSeat.isBooked())
                    return false;       //if any one of the requested seat is booked then false
            }
        }
        return true;
    }

    private int calculateTotalAmount(TicketEntryDto ticketEntryDto) {
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int showId = ticketEntryDto.getShowId();
        Show show = showRepository.findById(showId).get();
        List<ShowSeat> allSeatsOfShow = show.getListOfShowSeats();

        int totalAmount = 0;
        for (ShowSeat showSeat : allSeatsOfShow) {
            String seatNo = showSeat.getSeatNo();

            if(requestedSeats.contains(seatNo)) {
                totalAmount += showSeat.getPrice();
                showSeat.setBooked(true);
                showSeat.setBookedAt(new Date());
            }

        }
        return totalAmount;
    }
}
