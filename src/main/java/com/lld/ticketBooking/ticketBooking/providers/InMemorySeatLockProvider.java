package com.lld.ticketBooking.ticketBooking.providers;

import com.lld.ticketBooking.ticketBooking.exceptions.SeatTmporarlyUnavaliableException;
import com.lld.ticketBooking.ticketBooking.model.Seat;
import com.lld.ticketBooking.ticketBooking.model.SeatLock;
import com.lld.ticketBooking.ticketBooking.model.Show;

import java.util.*;

public class InMemorySeatLockProvider implements SeatLockProvider{

    private static final Map<Show,Map<Seat, SeatLock>> locks = new HashMap<>();
    private final Integer lockTimeout;

    private static final InMemorySeatLockProvider INSTANCE = new InMemorySeatLockProvider(100000);

    private InMemorySeatLockProvider(Integer timeout){
        lockTimeout= timeout;
    }

    public static InMemorySeatLockProvider getInstance(){
        return INSTANCE;
    }

    @Override
    public void lockSeats(Show show, List<Seat> seats, String user) {
      for(Seat seat : seats){
          if(isSeatLocked(show,seat)){
              throw new SeatTmporarlyUnavaliableException("Seat not avaliable....");
          }
      }
        for(Seat seat : seats){
            lockSeat(show,seat,user,lockTimeout);
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat: seats){
            unlockSeat(show, seat);
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return false;
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        List<Seat> lockedSeats =new ArrayList<>();
        for(Seat s : locks.get(show).keySet()){
            if(isSeatLocked(show,s)){
                lockedSeats.add(s);
            }
        }
        return lockedSeats;
    }

    public boolean isSeatLocked(Show show , Seat seat){
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired();
    }

    public void lockSeat(Show show, Seat seat, String user , Integer lockTimeout){
        if(!locks.containsKey(show)){
            locks.put(show, new HashMap<>());
        }
        SeatLock lock = new SeatLock(seat, show, lockTimeout, new Date(), user);
        locks.get(show).put(seat, lock);
    }

    public void unlockSeat(Show show, Seat seat){
        if(!locks.containsKey(show)){
            return;
        }
        locks.get(show).remove(seat);
    }
}
