package com.lld.ticketBooking.ticketBooking.model;

import com.lld.ticketBooking.ticketBooking.exceptions.InvalidStateException;
import lombok.Getter;

import java.util.List;

@Getter
public class Booking {
    private String id;
    private Show show;
    private List<Seat> seatsBooked;
    private String user;
    private BookingStatus bookingStatus;

    public Booking(String id, Show show, List<Seat> seatsBooked, String user) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus=BookingStatus.CREATED;
    }

    public void confirmBooking(){
        if(this.bookingStatus!=BookingStatus.CREATED){
            throw new InvalidStateException("Invalid state for confirming booking");
        }
        this.bookingStatus=BookingStatus.CONFIRMED;
    }

    public void expireBooking(){
        if(this.bookingStatus!=BookingStatus.CREATED){
            throw new InvalidStateException("Invalid state for confirming booking");
        }
        this.bookingStatus=BookingStatus.EXPIRED;
    }
}
