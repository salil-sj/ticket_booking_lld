package com.lld.ticketBooking.ticketBooking.exceptions;

public class ScreenAlreadyOccupiedException extends RuntimeException {
    public ScreenAlreadyOccupiedException(String message) {
        super(message);
    }
}
