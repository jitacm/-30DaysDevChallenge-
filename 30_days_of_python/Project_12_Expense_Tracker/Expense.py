expenses = []

def show_expenses():
    if not expenses:
        print("No expenses recorded.")
    else:
        for idx, expense in enumerate(expenses, 1):
            print(f"{idx}. {expense['description']}: ${expense['amount']}")

def add_expense(description, amount):
    expenses.append({'description': description, 'amount': amount})
    print(f"Expense '{description}' of ${amount} added.")

def remove_expense(index):
    if 0 < index <= len(expenses):
        removed_expense = expenses.pop(index - 1)
        print(f"Removed expense '{removed_expense['description']}'")
    else:
        print("Invalid index")

while True:
    print("\nExpense Tracker")
    show_expenses()
    print("\nOptions: 1. Add Expense  2. Remove Expense  3. Exit")
    choice = input("Enter choice: ")

    if choice == '1':
        description = input("Enter expense description: ")
        amount = float(input("Enter expense amount: "))
        add_expense(description, amount)
    elif choice == '2':
        index = int(input("Enter expense number to remove: "))
        remove_expense(index)
    elif choice == '3':
        break
    else:
        print("Invalid choice")
