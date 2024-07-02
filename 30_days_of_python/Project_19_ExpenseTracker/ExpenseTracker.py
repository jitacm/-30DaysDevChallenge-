import datetime

class Expense:
    def __init__(self, description, amount, category):
        self.description = description
        self.amount = amount
        self.category = category
        self.date = datetime.datetime.now()

class ExpenseTracker:
    def __init__(self):
        self.expenses = []

    def add_expense(self, description, amount, category):
        new_expense = Expense(description, amount, category)
        self.expenses.append(new_expense)
        print(f"Added expense: {description}, Amount: {amount}, Category: {category}")

    def view_expenses(self):
        if not self.expenses:
            print("No expenses recorded.")
        else:
            for expense in self.expenses:
                print(f"Date: {expense.date}, Description: {expense.description}, Amount: {expense.amount}, Category: {expense.category}")

    def get_summary(self):
        if not self.expenses:
            print("No expenses to summarize.")
            return
        
        total_expenses = sum(expense.amount for expense in self.expenses)
        expenses_by_category = {}
        
        for expense in self.expenses:
            if expense.category in expenses_by_category:
                expenses_by_category[expense.category] += expense.amount
            else:
                expenses_by_category[expense.category] = expense.amount
        
        print(f"Total Expenses: {total_expenses}")
        print("Expenses by Category:")
        for category, amount in expenses_by_category.items():
            print(f"{category}: {amount}")

# Example usage
tracker = ExpenseTracker()
tracker.add_expense("Lunch", 15, "Food")
tracker.add_expense("Bus ticket", 2.5, "Transport")
tracker.add_expense("Groceries", 55, "Food")

print("\nAll Expenses:")
tracker.view_expenses()

print("\nExpense Summary:")
tracker.get_summary()
