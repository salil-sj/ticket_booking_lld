package com.lld.ticketBooking.ticketBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Screen {
    private String id;
    private String name;
    private Theatre theatre;
    private List<Seat> seats;

}
