package com.lld.ticketBooking.ticketBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Screen {
    private String id;
    private String name;
    private Theatre theatre;
    private List<Seat> seats;

    public Screen(String id, String name, Theatre theatre){
        this.id=id;
        this.name=name;
        this.theatre= theatre;
        this.seats = new ArrayList<>();
    }

    public void addSeats(@NonNull final Seat seat){
        this.seats.add(seat);
    }

}
