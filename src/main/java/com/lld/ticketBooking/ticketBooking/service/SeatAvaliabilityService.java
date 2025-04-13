package com.lld.ticketBooking.ticketBooking.service;

import com.lld.ticketBooking.ticketBooking.model.Seat;
import com.lld.ticketBooking.ticketBooking.model.Show;
import com.lld.ticketBooking.ticketBooking.providers.InMemorySeatLockProvider;
import com.lld.ticketBooking.ticketBooking.providers.SeatLockProvider;

import java.util.List;

public class SeatAvaliabilityService {

    private TheatreService theatreService;
    private ShowService showService;
    private BookingService bookingService;
    private SeatLockProvider seatLockProvider;

    public SeatAvaliabilityService() {
        this.showService= ShowService.getInstance();
        this.theatreService= TheatreService.getInstance();
        this.bookingService = BookingService.getInstance();
        this.seatLockProvider= InMemorySeatLockProvider.getInstance();
    }

    public List<Seat> getAvaliableSeats(String showId){
        Show show = showService.getShow(showId);
        final List<Seat> allSeats = show.getScreen().getSeats();
        final List<Seat> unavaliableSeats = getUnAvaliableSeats(show);
        allSeats.removeAll(unavaliableSeats);
        return  allSeats;
    }

    private List<Seat> getUnAvaliableSeats(Show show){
        final List<Seat> unavaliableSeats = bookingService.getBookedSeats(show); // seats which are booked.
        unavaliableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavaliableSeats;
    }
}
