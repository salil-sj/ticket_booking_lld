package com.lld.ticketBooking.ticketBooking.service;

import com.lld.ticketBooking.ticketBooking.exceptions.NotFoundException;
import com.lld.ticketBooking.ticketBooking.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {

    private static final MovieService INSTANCE = new MovieService();
    private static final Map<String, Movie> movies = new HashMap<>();

    private MovieService() {}

    public static MovieService getInstance(){
        return  INSTANCE;
    }

    public Movie createMovie(String movieName) {
        Movie movie = new Movie(UUID.randomUUID().toString() , movieName);
        movies.put(movie.getId(), movie);
        return movie;
    }

    public Movie getMovie(String movieId){
        if(!movies.containsKey(movieId)){
            throw new NotFoundException("Movie with movie id: " + movieId +" not found!");
        }
        return movies.get(movieId);

    }

}
