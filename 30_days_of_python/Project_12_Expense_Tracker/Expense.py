expenses = []
spending_limit = 500  # Define your spending limit here

def show_expenses():
    if not expenses:
        print("No expenses recorded.")
    else:
        for idx, expense in enumerate(expenses, 1):
            print(f"{idx}. {expense['description']}: ${expense['amount']:.2f}")

def add_expense(description, amount):
    expenses.append({'description': description, 'amount': amount})
    print(f"Expense '{description}' of ${amount:.2f} added.")

def remove_expense(index):
    if 0 < index <= len(expenses):
        removed_expense = expenses.pop(index - 1)
        print(f"Removed expense '{removed_expense['description']}'")
    else:
        print("Invalid index")

def show_total_expense():
    total = sum(expense['amount'] for expense in expenses)
    print(f"Total Expenses: ${total:.2f}")

def compare_expenses():
    total = sum(expense['amount'] for expense in expenses)
    if total > spending_limit:
        print(f"Warning: You have exceeded your spending limit of ${spending_limit:.2f}!")
        print(f"Summary: You have overspent by ${total - spending_limit:.2f}.")
    else:
        print(f"You are within your spending limit of ${spending_limit:.2f}.")
        print(f"Summary: You have saved ${spending_limit - total:.2f} this month.")

while True:
    print("\nExpense Tracker")
    show_expenses()
    show_total_expense()  # Show the total expenses
    print("\nOptions: 1. Add Expense  2. Remove Expense  3. Show Summary  4. Exit")
    choice = input("Enter choice: ")

    if choice == '1':
        description = input("Enter expense description: ")
        amount = float(input("Enter expense amount: "))
        add_expense(description, amount)
    elif choice == '2':
        index = int(input("Enter expense number to remove: "))
        remove_expense(index)
    elif choice == '3':
        compare_expenses()
    elif choice == '4':
        break
    else:
        print("Invalid choice")
