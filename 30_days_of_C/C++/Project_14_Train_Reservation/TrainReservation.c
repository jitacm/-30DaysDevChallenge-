#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_TRAINS 10
#define MAX_BOOKINGS 100

typedef struct {
    int trainNumber;
    char trainName[50];
    char source[30];
    char destination[30];
    int seatsAvailable;
} Train;

typedef struct {
    int bookingID;
    int trainNumber;
    char passengerName[50];
    int seatNumber;
} Booking;

Train trains[MAX_TRAINS];
Booking bookings[MAX_BOOKINGS];

int trainCount = 0;
int bookingCount = 0;
int bookingIDCounter = 1;

void addTrain() {
    if (trainCount >= MAX_TRAINS) {
        printf("Cannot add more trains.\n");
        return;
    }

    Train train;
    printf("Enter train number: ");
    scanf("%d", &train.trainNumber);
    printf("Enter train name: ");
    scanf("%s", train.trainName);
    printf("Enter source station: ");
    scanf("%s", train.source);
    printf("Enter destination station: ");
    scanf("%s", train.destination);
    printf("Enter number of seats available: ");
    scanf("%d", &train.seatsAvailable);

    trains[trainCount] = train;
    trainCount++;
    printf("Train added successfully.\n");
}

void bookTicket() {
    if (bookingCount >= MAX_BOOKINGS) {
        printf("Cannot book more tickets.\n");
        return;
    }

    int trainNumber;
    printf("Enter train number to book: ");
    scanf("%d", &trainNumber);

    int trainIndex = -1;
    for (int i = 0; i < trainCount; i++) {
        if (trains[i].trainNumber == trainNumber) {
            trainIndex = i;
            break;
        }
    }

    if (trainIndex == -1) {
        printf("Train not found.\n");
        return;
    }

    if (trains[trainIndex].seatsAvailable <= 0) {
        printf("No seats available.\n");
        return;
    }

    Booking booking;
    booking.bookingID = bookingIDCounter++;
    booking.trainNumber = trainNumber;
    printf("Enter passenger name: ");
    scanf("%s", booking.passengerName);
    booking.seatNumber = trains[trainIndex].seatsAvailable;

    bookings[bookingCount] = booking;
    bookingCount++;
    trains[trainIndex].seatsAvailable--;

    printf("Ticket booked successfully. Booking ID: %d, Seat Number: %d\n", booking.bookingID, booking.seatNumber);
}

void displayBookings() {
    if (bookingCount == 0) {
        printf("No bookings available.\n");
        return;
    }

    for (int i = 0; i < bookingCount; i++) {
        printf("Booking ID: %d, Train Number: %d, Passenger Name: %s, Seat Number: %d\n", 
               bookings[i].bookingID, bookings[i].trainNumber, bookings[i].passengerName, bookings[i].seatNumber);
    }
}

int main() {
    int choice;
    while (1) {
        printf("\nTrain Reservation System\n");
        printf("1. Add Train\n");
        printf("2. Book Ticket\n");
        printf("3. Display Bookings\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                addTrain();
                break;
            case 2:
                bookTicket();
                break;
            case 3:
                displayBookings();
                break;
            case 4:
                exit(0);
            default:
                printf("Invalid choice. Please try again.\n");
        }
    }

    return 0;
}
