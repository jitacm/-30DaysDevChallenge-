#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char name[50];
    int pin;
    double balance;
} Account;

Account account = {"John Doe", 1234, 1000.0}; 

void checkBalance() {
    printf("Current Balance: $%.2f\n", account.balance);
}

void depositMoney() {
    double amount;
    printf("Enter amount to deposit: $");
    scanf("%lf", &amount);
    if (amount <= 0) {
        printf("Invalid amount. Please enter a positive number.\n");
        return;
    }
    account.balance += amount;
    printf("Deposited successfully. New Balance: $%.2f\n", account.balance);
}

void withdrawMoney() {
    double amount;
    printf("Enter amount to withdraw: $");
    scanf("%lf", &amount);
    if (amount <= 0) {
        printf("Invalid amount. Please enter a positive number.\n");
        return;
    }
    if (amount > account.balance) {
        printf("Insufficient balance.\n");
        return;
    }
    account.balance -= amount;
    printf("Withdrawn successfully. New Balance: $%.2f\n", account.balance);
}

void changePIN() {
    int oldPIN, newPIN;
    printf("Enter old PIN: ");
    scanf("%d", &oldPIN);
    if (oldPIN != account.pin) {
        printf("Incorrect old PIN.\n");
        return;
    }
    printf("Enter new PIN: ");
    scanf("%d", &newPIN);
    account.pin = newPIN;
    printf("PIN changed successfully.\n");
}

int main() {
    int choice, enteredPIN;
    printf("Enter your PIN: ");
    scanf("%d", &enteredPIN);
    if (enteredPIN != account.pin) {
        printf("Incorrect PIN.\n");
        return 0;
    }

    while (1) {
        printf("\nATM System\n");
        printf("1. Check Balance\n");
        printf("2. Deposit Money\n");
        printf("3. Withdraw Money\n");
        printf("4. Change PIN\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                withdrawMoney();
                break;
            case 4:
                changePIN();
                break;
            case 5:
                printf("Thank you for using the ATM. Goodbye!\n");
                exit(0);
            default:
                printf("Invalid choice. Please try again.\n");
        }
    }

    return 0;
}
