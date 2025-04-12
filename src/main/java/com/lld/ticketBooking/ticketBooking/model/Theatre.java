package com.lld.ticketBooking.ticketBooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Theatre {
    private String id;
    private String name;
    List<Screen> screens;
    //...

    public Theatre(@NonNull String id , @NonNull String name)
    {
        this.id=id;
        this.name=name;
    }

    public void addScreen(@NonNull final Screen screen)
    {
        this.screens.add(screen);
    }
}
