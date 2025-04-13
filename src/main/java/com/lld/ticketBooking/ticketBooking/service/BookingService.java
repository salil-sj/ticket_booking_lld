package com.lld.ticketBooking.ticketBooking.service;

import com.lld.ticketBooking.ticketBooking.model.Booking;
import com.lld.ticketBooking.ticketBooking.model.BookingStatus;
import com.lld.ticketBooking.ticketBooking.model.Seat;
import com.lld.ticketBooking.ticketBooking.model.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingService {
    private static final Map<String, Booking> bookings = new HashMap<>();

    private static final BookingService INSTANCE = new BookingService();

    private BookingService() {}

    public static BookingService getInstance(){
        return INSTANCE;
    }

    public Booking createBooking(String userId, Show show, List<Seat>seats) {

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
