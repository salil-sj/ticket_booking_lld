package com.lld.ticketBooking.ticketBooking.service;

import com.lld.ticketBooking.ticketBooking.exceptions.NotFoundException;
import com.lld.ticketBooking.ticketBooking.model.Screen;
import com.lld.ticketBooking.ticketBooking.model.Seat;
import com.lld.ticketBooking.ticketBooking.model.Theatre;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {

    private final Map<String , Theatre> theatres;
    private final Map<String , Screen> screens;
    private final Map<String , Seat> seats;

    public TheatreService()
    {
        this.theatres= new HashMap<>();
        this.screens= new HashMap<>();
        this.seats= new HashMap<>();
    }

    public Theatre createTheatre(@NonNull final String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre); // storing in hashmap
        return theatre;
    }

    public Screen createScreenInTheatre(@NonNull final String screenName , @NonNull final String theatreId){
        Theatre theatre = getTheatre(theatreId);
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    public Screen createScreen(String screenName, Theatre theatre){

        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        screens.put(screenId , screen);
        return screen;
    }

    public Theatre getTheatre(@NonNull final String theatreId){
        if (!theatres.containsKey(theatreId)){
            throw new NotFoundException("Sorry...this theatre do not exists...!!");
        }
        return this.theatres.get(theatreId);
    }

    public Seat createSeatsInScreen(Integer rowNo, Integer seatNo, String screenId){
       Screen screen = getScreen(screenId);
       Seat seat = new Seat(UUID.randomUUID().toString(),rowNo,seatNo);
       screen.addSeats(seat);
       seats.put(seat.getId(), seat);
       return seat;
    }

    public Screen getScreen(String screenId){
        if(!screens.containsKey(screenId)){
            throw new NotFoundException("Sorry...this screen do not exists...!!");
        }
        return screens.get(screenId);
    }
}
