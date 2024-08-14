# bank_account.py

class BankAccount:
    def __init__(self, account_number, account_name, balance=0):
        self.account_number = account_number
        self.account_name = account_name
        self.balance = balance

    def deposit(self, amount):
        self.balance += amount
        print(f"Deposited ${amount}. New balance: ${self.balance}")

    def withdraw(self, amount):
        if amount > self.balance:
            print("Insufficient balance.")
        else:
            self.balance -= amount
            print(f"Withdrew ${amount}. New balance: ${self.balance}")

    def check_balance(self):
        print(f"Account balance: ${self.balance}")


class BankSystem:
    def __init__(self):
        self.accounts = {}

    def create_account(self, account_number, account_name):
        if account_number in self.accounts:
            print("Account already exists.")
        else:
            self.accounts[account_number] = BankAccount(account_number, account_name)
            print("Account created successfully.")

    def access_account(self, account_number):
        if account_number in self.accounts:
            return self.accounts[account_number]
        else:
            print("Account does not exist.")
            return None


def main():
    bank_system = BankSystem()

    while True:
        print("\n1. Create Account")
        print("2. Access Account")
        print("3. Quit")

        choice = input("Choose an option: ")

        if choice == "1":
            account_number = input("Enter account number: ")
            account_name = input("Enter account name: ")
            bank_system.create_account(account_number, account_name)
        elif choice == "2":
            account_number = input("Enter account number: ")
            account = bank_system.access_account(account_number)
            if account:
                while True:
                    print("\n1. Deposit")
                    print("2. Withdraw")
                    print("3. Check Balance")
                    print("4. Back")

                    choice = input("Choose an option: ")

                    if choice == "1":
                        amount = float(input("Enter amount to deposit: "))
                        account.deposit(amount)
                    elif choice == "2":
                        amount = float(input("Enter amount to withdraw: "))
                        account.withdraw(amount)
                    elif choice == "3":
                        account.check_balance()
                    elif choice == "4":
                        break
                    else:
                        print("Invalid option. Please choose again.")
        elif choice == "3":
            break
        else:
            print("Invalid option. Please choose again.")


if __name__ == "__main__":
    main()
