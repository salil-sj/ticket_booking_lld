package com.lld.ticketBooking.ticketBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class Seat {
    private String id;
    private int rowNo;
    private int seatNo;
}
