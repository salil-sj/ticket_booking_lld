package com.lld.ticketBooking.ticketBooking.controller;

import com.lld.ticketBooking.ticketBooking.model.Theatre;
import com.lld.ticketBooking.ticketBooking.service.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;


public class TheatreController {

    private final TheatreService theatreService = TheatreService.getInstance();

    public String createTheatre(@NonNull final  String theatreName){
        return this.theatreService.createTheatre(theatreName).getId();
    }

    public String createScreenInTheatre(final String screenName , final String theatreId){
        return this.theatreService.createScreenInTheatre(screenName, theatreId).getId();
    }

    //screen/Audi:
    public String createSeatsInScreen(Integer rowNo, Integer seatNo, String screenId){
        return  this.theatreService.createSeatsInScreen(rowNo,seatNo, screenId).getId();
    }
}
