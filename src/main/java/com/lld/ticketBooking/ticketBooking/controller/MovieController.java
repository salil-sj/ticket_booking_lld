package com.lld.ticketBooking.ticketBooking.controller;

import com.lld.ticketBooking.ticketBooking.model.Movie;
import com.lld.ticketBooking.ticketBooking.service.MovieService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    public String createMovie(String movieName) {
        return movieService.createMovie(movieName).getId();
    }
}
