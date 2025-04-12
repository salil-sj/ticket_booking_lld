package com.lld.ticketBooking.ticketBooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Seat {
    private String id;
    private int rowNo;
    private int seatNo;
}
