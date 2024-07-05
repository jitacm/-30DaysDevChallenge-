#include <iostream>
#include <string>
#include <vector>

class Account {
public:
    Account(int accNumber, std::string accHolder, double balance)
        : accNumber(accNumber), accHolder(accHolder), balance(balance) {}

    int getAccNumber() const { return accNumber; }
    std::string getAccHolder() const { return accHolder; }
    double getBalance() const { return balance; }

    void deposit(double amount) {
        balance += amount;
    }

    bool withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

private:
    int accNumber;
    std::string accHolder;
    double balance;
};

class Bank {
public:
    void createAccount(int accNumber, std::string accHolder, double balance) {
        accounts.emplace_back(accNumber, accHolder, balance);
    }

    Account* findAccount(int accNumber) {
        for (auto& account : accounts) {
            if (account.getAccNumber() == accNumber) {
                return &account;
            }
        }
        return nullptr;
    }

    void displayAccountDetails(int accNumber) {
        Account* account = findAccount(accNumber);
        if (account) {
            std::cout << "Account Number: " << account->getAccNumber() << "\n";
            std::cout << "Account Holder: " << account->getAccHolder() << "\n";
            std::cout << "Balance: $" << account->getBalance() << "\n";
        } else {
            std::cout << "Account not found.\n";
        }
    }

private:
    std::vector<Account> accounts;
};

int main() {
    Bank bank;
    int choice;
    do {
        std::cout << "\nBank Management System\n";
        std::cout << "1. Create Account\n";
        std::cout << "2. Deposit Money\n";
        std::cout << "3. Withdraw Money\n";
        std::cout << "4. Display Account Details\n";
        std::cout << "5. Exit\n";
        std::cout << "Enter your choice: ";
        std::cin >> choice;

        int accNumber;
        std::string accHolder;
        double amount;
        switch (choice) {
        case 1:
            std::cout << "Enter Account Number: ";
            std::cin >> accNumber;
            std::cout << "Enter Account Holder Name: ";
            std::cin >> accHolder;
            std::cout << "Enter Initial Balance: ";
            std::cin >> amount;
            bank.createAccount(accNumber, accHolder, amount);
            std::cout << "Account created successfully.\n";
            break;

        case 2:
            std::cout << "Enter Account Number: ";
            std::cin >> accNumber;
            std::cout << "Enter Amount to Deposit: ";
            std::cin >> amount;
            {
                Account* account = bank.findAccount(accNumber);
                if (account) {
                    account->deposit(amount);
                    std::cout << "Amount deposited successfully.\n";
                } else {
                    std::cout << "Account not found.\n";
                }
            }
            break;

        case 3:
            std::cout << "Enter Account Number: ";
            std::cin >> accNumber;
            std::cout << "Enter Amount to Withdraw: ";
            std::cin >> amount;
            {
                Account* account = bank.findAccount(accNumber);
                if (account) {
                    if (account->withdraw(amount)) {
                        std::cout << "Amount withdrawn successfully.\n";
                    } else {
                        std::cout << "Insufficient balance.\n";
                    }
                } else {
                    std::cout << "Account not found.\n";
                }
            }
            break;

        case 4:
            std::cout << "Enter Account Number: ";
            std::cin >> accNumber;
            bank.displayAccountDetails(accNumber);
            break;

        case 5:
            std::cout << "Exiting...\n";
            break;

        default:
            std::cout << "Invalid choice. Please try again.\n";
        }
    } while (choice != 5);

    return 0;
}
