
# 🎟️ Ticket Booking System – Intuitive Design Overview

This project demonstrates a **Low-Level Design (LLD)** of a ticket booking system, similar to what you might see in a real-world movie booking platform like BookMyShow.

## 🔄 Core Flow – Intuition First

### 1. **Entities in the System**
- **Movie**: A film available for watching.
- **Show**: A scheduled screening of a movie in a particular auditorium at a specific time.
- **Theatre**: Venue containing multiple auditoriums.
- **Seat**: A physical seat in an auditorium for a show.
- **User**: Person booking the tickets.

### 2. **Booking Flow**
1. **User selects a movie and city**.
2. The system shows **available theatres and shows** for that movie.
3. User selects a **show and preferred seats**.
4. The system attempts to **lock those seats** using an in-memory mechanism (to avoid race conditions).
5. If locking succeeds, the user proceeds to **confirm the booking** within the lock timeout.
6. After confirmation, a **Booking is created** and seats are marked as booked.
7. If timeout expires before confirmation, seats are released automatically (not by deletion but by checking for expiration during access).

### 3. **Seat Locking Intuition**
- When a user selects seats, the system locks them for that user using `SeatLockProvider`.
- Locking is **temporary**. No other user can book or lock those seats during this time.
- No separate thread or cleanup — instead, when someone checks availability or validates a booking, the system checks if existing locks have **expired**.
- Expired locks are **ignored**, making them effectively released.

### 4. **Thread-Safety and Data Management**
- The system uses **in-memory storage** with Maps and Lists to simulate databases.
- Thread-safety isn't implemented deeply, as the goal is to focus on core logic and LLD principles.

### 5. **Why This Design is Great for LLD Interviews**
- It separates concerns: locking, booking, searching, and storing entities are all handled by distinct classes.
- Demonstrates understanding of real-world constraints like race conditions, lock expiration, and modular design.
- Easy to plug into a persistent storage or scale with more services (e.g., using Redis for locks, a real DB, etc.)

---

This intuitive breakdown is helpful when explaining the **architecture and design decisions** during interviews, even if you don’t go line-by-line through the code.

#
- Here right now things are done in memory, but in real time we would use databse and redis lock(Check Hello Interview Video for more detail)
