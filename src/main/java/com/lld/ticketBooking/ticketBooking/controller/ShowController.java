package com.lld.ticketBooking.ticketBooking.controller;

import com.lld.ticketBooking.ticketBooking.service.ShowService;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;


public class ShowController {

    private ShowService showService;

    public ShowController(){
        this.showService = ShowService.getInstance();
    }

    public String createShow(String movieId, String screenId, Date startTime, Integer durationInMinutes){
        return this.showService.createShow(movieId,screenId,startTime,durationInMinutes).getId();
    }

    public List<String> getAvaliableSeats(String showId){

    }


}
