# Expense Tracker

A simple Python script for tracking expenses. This script allows you to add expenses, view all expenses, and get a summary of your expenses.

## Features

- Add an expense with a description, amount, and category.
- View all recorded expenses.
- Get a summary of total expenses and expenses categorized by type.

## Requirements

- Python 3.x

## Usage

### 1. Clone the Repository


- git clone https://github.com/yourusername/expense-tracker.git
- cd expense-tracker
- Run the Script ----  python expense_tracker.py


# Create an instance of ExpenseTracker
tracker = ExpenseTracker()

# Add some expenses
tracker.add_expense("Lunch", 15, "Food")
tracker.add_expense("Bus ticket", 2.5, "Transport")
tracker.add_expense("Groceries", 55, "Food")

# View all expenses
print("\nAll Expenses:")
tracker.view_expenses()

# Get a summary of expenses
print("\nExpense Summary:")
tracker.get_summary()
