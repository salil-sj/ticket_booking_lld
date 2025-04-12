package com.lld.ticketBooking.ticketBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Theatre {
    private String id;
    private String name;
    List<Screen> screens;
    //...
}
