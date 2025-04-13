package com.lld.ticketBooking.ticketBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Show {
    private String id;
    private Movie movie;
    private Screen screen;
    private Date startTime;
    private Integer durationInMinutes;
}
