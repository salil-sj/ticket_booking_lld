package com.lld.ticketBooking.ticketBooking.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Booking {
    private String id;
    private Show show;
    private List<Seat> seatsBooked;
    private String user;
    private BookingStatus bookingStatus;

}
