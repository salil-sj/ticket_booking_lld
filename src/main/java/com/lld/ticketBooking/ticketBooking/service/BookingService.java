package com.lld.ticketBooking.ticketBooking.service;

import com.lld.ticketBooking.ticketBooking.exceptions.NotFoundException;
import com.lld.ticketBooking.ticketBooking.model.Booking;
import com.lld.ticketBooking.ticketBooking.model.BookingStatus;
import com.lld.ticketBooking.ticketBooking.model.Seat;
import com.lld.ticketBooking.ticketBooking.model.Show;
import com.lld.ticketBooking.ticketBooking.providers.InMemorySeatLockProvider;
import com.lld.ticketBooking.ticketBooking.providers.SeatLockProvider;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private static final Map<String, Booking> bookings = new HashMap<>();

    private static final BookingService INSTANCE = new BookingService();
    private ShowService showService;
    private SeatAvaliabilityService seatAvaliabilityService;
    private SeatLockProvider seatLockProvider;

    private BookingService() {
        this.showService= ShowService.getInstance();
        this.seatAvaliabilityService= SeatAvaliabilityService.getInstance();
        this.seatLockProvider = InMemorySeatLockProvider.getInstance();
    }

    public static BookingService getInstance(){
        return INSTANCE;
    }

    public Booking createBooking(String userId, String showId, List<String>seatIds) {
        String id = UUID.randomUUID().toString();
        Show show = showService.getShow(showId);
        List<Seat> seatList= show.getScreen().getSeats().stream().filter(seat->seatIds.contains(seat.getId())).collect(Collectors.toList());
        boolean isAvaliable = seatAvaliabilityService.isSeatsAvaliable(showId,seatList);
        if(!isAvaliable){
            throw new NotFoundException("Sorry....seats not found....");
        }
        seatLockProvider.lockSeats(show,seatList,userId);
        Booking booking= new Booking(id,show, seatList,userId);
        bookings.put(id,booking);
        return booking;
    }

    public void confirmBooking(String bookingId){
        Booking booking = bookings.get(bookingId);
        booking.confirmBooking();
    }

    public List<Seat> getBookedSeats(Show show){
        return getAllBooking(show).stream()
                .filter(booking-> booking.getBookingStatus().equals(BookingStatus.CONFIRMED))
                .map(booking->booking.getSeatsBooked())
                .flatMap(seatList-> seatList.stream())
                .collect(Collectors.toList());
    }

    public List<Booking> getAllBooking(Show show){
        List<Booking> response = new ArrayList<>();
        for(Booking booking : bookings.values()){
            if(booking.getShow().equals(show)){
                response.add(booking);
            }
        }
        return response;
    }
}
