# Train Reservation System

This program simulates a train reservation system where users can add trains, book tickets, and view bookings.

## Features

- **Add Train:** Add new trains with details such as train number, name, source, destination, and available seats.
- **Book Ticket:** Book a ticket for a specific train, assigning the next available seat.
- **Display Bookings:** View all bookings made, including booking ID, train number, passenger name, and seat number.

## Data Structures

- **Train Struct:** Contains details of each train including its number, name, source, destination, and available seats.
- **Booking Struct:** Stores information about each booking including booking ID, associated train number, passenger name, and assigned seat number.

## How to Use

1. **Adding a Train**
   - Choose option `1` from the menu.
   - Enter details for the new train when prompted.

2. **Booking a Ticket**
   - Choose option `2` from the menu.
   - Enter the train number to book a ticket.
   - Provide passenger name when prompted.

3. **Displaying Bookings**
   - Choose option `3` from the menu to view all current bookings.

4. **Exiting the Program**
   - Choose option `4` to exit the program.

## Requirements

- This program requires a C compiler to build and run.

## How to Compile and Run

1. **Compile**
   - Open a terminal or command prompt.
   - Navigate to the directory containing the source code (`train_reservation.c`).
   - Compile the program using the appropriate command for your C compiler. For example:
     ```bash
     gcc train_reservation.c -o train_reservation
     ```

2. **Run**
   - After compiling successfully, run the executable:
     ```bash
     ./train_reservation
     ```
