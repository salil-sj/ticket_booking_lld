package com.lld.ticketBooking.ticketBooking.service;

import com.lld.ticketBooking.ticketBooking.exceptions.NotFoundException;
import com.lld.ticketBooking.ticketBooking.model.Screen;
import com.lld.ticketBooking.ticketBooking.model.Seat;
import com.lld.ticketBooking.ticketBooking.model.Theatre;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {

    private static final TheatreService INSTANCE = new TheatreService();
    /*
    We could have just made the fields public and accessed it using className.field,
     but then it would have defeated our encapsulation.
     Encapsulation says that you have to encapsulate the class and its related methods
     inside the class. So that would have been defeated.
     */
    private static final Map<String , Theatre> theatres = new HashMap<>();
    private static final Map<String , Screen> screens  = new HashMap<>();
    private static final Map<String , Seat> seats = new HashMap<>();

    private TheatreService(){}

    public static TheatreService getInstance(){
        return INSTANCE;
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
