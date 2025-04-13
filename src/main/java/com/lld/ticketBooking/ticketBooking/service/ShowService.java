package com.lld.ticketBooking.ticketBooking.service;

import com.lld.ticketBooking.ticketBooking.exceptions.NotFoundException;
import com.lld.ticketBooking.ticketBooking.exceptions.ScreenAlreadyOccupiedException;
import com.lld.ticketBooking.ticketBooking.model.Movie;
import com.lld.ticketBooking.ticketBooking.model.Screen;
import com.lld.ticketBooking.ticketBooking.model.Show;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {

    private static final ShowService INSTANCE = new ShowService();
    private static final Map<String, Show> shows = new HashMap<>();
    private MovieService movieService;
    private TheatreService theatreService;

    private ShowService() {
        this.movieService = MovieService.getInstance();
        this.theatreService = TheatreService.getInstance();
    }

    public static ShowService getInstance() {
        return INSTANCE;
    }


    public Show createShow(String movieId, String screenId, Date startTime, Integer durationInMinutes) {
        String showId = UUID.randomUUID().toString();
        Movie movie = this.movieService.getMovie(movieId);
        Screen screen = theatreService.getScreen(screenId);
        if (!checkIfShowCreationAllowed(screen, startTime, durationInMinutes)) {
            throw new ScreenAlreadyOccupiedException("This screen is already occupied....");
        }
        Show show = new Show(showId, movie, screen, startTime, durationInMinutes);
        return show;
    }

    public Show getShow(String showId) {
        if(!shows.containsKey(showId)){
            throw new NotFoundException("Show not found");
        }
        return shows.get(showId);
    }

    private boolean checkIfShowCreationAllowed(Screen screen, Date startTime, Integer durationInSeconds) {
        //TODO:
        return true;
    }



}
