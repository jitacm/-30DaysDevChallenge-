#include <iostream>
#include <vector>
#include <string>

class Expense {
public:
    Expense(std::string description, double amount)
        : description(description), amount(amount) {}

    std::string getDescription() const { return description; }
    double getAmount() const { return amount; }

private:
    std::string description;
    double amount;
};

class ExpenseTracker {
public:
    void addExpense(const Expense& expense) {
        expenses.push_back(expense);
    }

    void viewExpenses() const {
        for (const auto& expense : expenses) {
            std::cout << "Description: " << expense.getDescription()
                      << ", Amount: $" << expense.getAmount() << std::endl;
        }
    }

    void deleteExpense(const std::string& description) {
        for (auto it = expenses.begin(); it != expenses.end(); ++it) {
            if (it->getDescription() == description) {
                expenses.erase(it);
                std::cout << "Expense deleted successfully.\n";
                return;
            }
        }
        std::cout << "Expense not found.\n";
    }

private:
    std::vector<Expense> expenses;
};

int main() {
    ExpenseTracker tracker;

    while (true) {
        std::cout << "\nExpense Tracker\n";
        std::cout << "1. Add Expense\n";
        std::cout << "2. View Expenses\n";
        std::cout << "3. Delete Expense\n";
        std::cout << "4. Exit\n";
        std::cout << "Enter your choice: ";

        int choice;
        std::cin >> choice;

        if (choice == 1) {
            std::string description;
            double amount;

            std::cout << "Enter expense description: ";
            std::cin.ignore(); 
            std::getline(std::cin, description);
            std::cout << "Enter expense amount: ";
            std::cin >> amount;

            tracker.addExpense(Expense(description, amount));
        } else if (choice == 2) {
            tracker.viewExpenses();
        } else if (choice == 3) {
            std::string description;
            std::cout << "Enter expense description to delete: ";
            std::cin.ignore(); 
            std::getline(std::cin, description);

            tracker.deleteExpense(description);
        } else if (choice == 4) {
            break;
        } else {
            std::cout << "Invalid choice. Please try again.\n";
        }
    }

    return 0;
}