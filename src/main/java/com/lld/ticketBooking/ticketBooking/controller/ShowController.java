package com.lld.ticketBooking.ticketBooking.controller;

import com.lld.ticketBooking.ticketBooking.model.Seat;
import com.lld.ticketBooking.ticketBooking.service.BookingService;
import com.lld.ticketBooking.ticketBooking.service.SeatAvaliabilityService;
import com.lld.ticketBooking.ticketBooking.service.ShowService;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;


public class ShowController {

    private ShowService showService;
    private SeatAvaliabilityService seatAvaliabilityService;

    public ShowController(){
        this.showService = ShowService.getInstance();
        this.seatAvaliabilityService = SeatAvaliabilityService.getInstance();
    }

    public String createShow(String movieId, String screenId, Date startTime, Integer durationInMinutes){
        return this.showService.createShow(movieId,screenId,startTime,durationInMinutes).getId();
    }

    public List<Seat> getAvaliableSeats(String showId){
        return seatAvaliabilityService.getAvaliableSeats(showId);
    }


}
