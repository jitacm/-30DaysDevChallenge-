#include <iostream>
using namespace std;

const int TOTAL_SEATS = 20;
void displaySeats(bool seats[]) {
    cout << "Seats:\n";
    for (int i = 0; i < TOTAL_SEATS; ++i) {
        if (seats[i]) {
            cout << "X "; 
        } else {
            cout << i + 1 << " ";  
        }
          if ((i + 1) % 5 == 0) {
            cout << endl;
        }
    }
    cout << endl;
}
int main() {
    bool seats[TOTAL_SEATS] = {false};  
      int choice;
    do {
        cout << "\nBus Reservation System\n";
        cout << "\nMenu\n";
        cout << "1. Reserve a Seat\n";
        cout << "2. Cancel Reservation\n";
        cout << "3. Display Seating Arrangement\n";
        cout << "4. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1: {
                
                int seatNumber;
                cout << "Enter seat number to reserve (1-" << TOTAL_SEATS << "): ";
                cin >> seatNumber;

                if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
                    cout << "Invalid seat number. Please enter a valid seat number.\n";
                } else if (seats[seatNumber - 1]) {
                    cout << "Sorry, seat " << seatNumber << " is already reserved.\n";
                } else {
                    seats[seatNumber - 1] = true;
                    cout << "Seat " << seatNumber << " reserved successfully.\n";
                }
                break;
            }
            case 2: {
                 int seatNumber;
                cout << "Enter seat number to cancel reservation (1-" << TOTAL_SEATS << "): ";
                cin >> seatNumber;

                if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
                    cout << "Invalid seat number. Please enter a valid seat number.\n";
                } else if (!seats[seatNumber - 1]) {
                    cout << "Seat " << seatNumber << " is not reserved.\n";
                } else {
                    seats[seatNumber - 1] = false;
                    cout << "Reservation for seat " << seatNumber << " cancelled successfully.\n";
                }
                break;
            }
            case 3:
                displaySeats(seats);
                break;
            case 4:
                cout << "Exiting the program.\n";
                break;
            default:
                cout << "Invalid choice. Please enter a valid choice.\n";
        }

    } while (choice != 4);

    return 0;
}