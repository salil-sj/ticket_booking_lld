
# 🪑 In-Memory Seat Locking – Intuition and Optimization

## ✅ How In-Memory Locking Works 
- The `InMemorySeatLockProvider` maintains a map:  
  `Map<Show, Map<Seat, SeatLock>>` to track which user has locked which seat.
- When a seat is locked, it's stored along with:
    - The user who locked it
    - The timestamp
    - A timeout duration
- There is **no background task** to clean up expired locks.
- Instead, **each time** seats are accessed (e.g., for booking or viewing availability), the system:
    - Checks if the lock exists
    - Checks if it’s expired using `isLockExpired()`
    - Treats expired locks as non-existent (they remain in memory but don’t interfere)

## 💡 Availability Check Optimization
You had a method like this:
```java
public boolean isSeatsAvailable(String showId, List<Seat> seats) {
    List<Seat> availableSeats = getAvailableSeats(showId);
    return seats.stream()
                .filter(availableSeats::contains)
                .collect(Collectors.toList()).size() == seats.size();
}
```

✅ This works, but is not optimal due to:
- Creating a filtered list unnecessarily
- `contains()` on a List is O(n), and it’s called multiple times

🎯 **Optimized version** using `Set` and `allMatch`:
```java
public boolean isSeatsAvailable(String showId, List<Seat> seats) {
    Set<Seat> availableSeats = new HashSet<>(getAvailableSeats(showId));
    return seats.stream().allMatch(availableSeats::contains);
}
```

- `HashSet.contains()` is O(1)
- Cleaner and more readable
- More performant for large datasets

## 🧠 Interview Tip:
Your original approach is valid and shows understanding of streams and collections.  
Always mention the optimized version and **explain why** it’s better — that’s what impresses interviewers.
