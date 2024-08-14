## Features

- **Create Account**: Allows users to create a new bank account.
- **Access Account**: Lets users access an existing account to perform operations.
- **Deposit**: Add funds to the account.
- **Withdraw**: Remove funds from the account.
- **Check Balance**: View the current balance of the account.

## Classes

### `BankAccount`

Represents an individual bank account.

- **Attributes**:
  - `account_number`: The unique identifier for the account.
  - `account_name`: The name of the account holder.
  - `balance`: The current balance of the account (default is 0).

- **Methods**:
  - `deposit(amount)`: Deposits the specified amount into the account.
  - `withdraw(amount)`: Withdraws the specified amount from the account.
  - `check_balance()`: Prints the current balance of the account.

### `BankSystem`

Manages multiple bank accounts.

- **Attributes**:
  - `accounts`: A dictionary storing `BankAccount` objects, with account numbers as keys.

- **Methods**:
  - `create_account(account_number, account_name)`: Creates a new account with the given number and name.
  - `access_account(account_number)`: Retrieves an account by its number.

## Usage

1. Run the script with Python:
   bash
   python bank_account.py
   

2. Choose from the following options:
   - **1**: Create a new account.
   - **2**: Access an existing account.
   - **3**: Quit the application.

3. When accessing an account, you can:
   - **1**: Deposit money into the account.
   - **2**: Withdraw money from the account.
   - **3**: Check the account balance.
   - **4**: Return to the main menu.

## Example

bash
1. Create Account
2. Access Account
3. Quit
Choose an option: 1
Enter account number: 123456
Enter account name: John Doe
Account created successfully.

1. Create Account
2. Access Account
3. Quit
Choose an option: 2
Enter account number: 123456
1. Deposit
2. Withdraw
3. Check Balance
4. Back
Choose an option: 3
Account balance: $0


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


Save this content to a file named README.md in the same directory as your bank_account.py script. It provides an overview of the functionality, usage, and how to interact with your script.