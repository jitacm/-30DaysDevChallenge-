# ATM Simulation Program

This program simulates basic ATM functionalities such as checking balance, depositing money, withdrawing money, and changing PIN for an account holder.

## Features

- **Check Balance:** View the current balance of your account.
- **Deposit Money:** Add funds to your account.
- **Withdraw Money:** Withdraw funds from your account, ensuring sufficient balance.
- **Change PIN:** Update your PIN for security.

## Account Details

- **Account Holder:** John Doe
- **Initial PIN:** 1234
- **Initial Balance:** $1000.00

## How to Use

1. **Entering PIN**
   - Start the program by entering your PIN (`1234` initially).
   - If the entered PIN is incorrect, the program terminates.

2. **Main Menu Options**
   - Choose from the following options:
     - **1:** Check Balance
     - **2:** Deposit Money
     - **3:** Withdraw Money
     - **4:** Change PIN
     - **5:** Exit

3. **Transactions**
   - **Check Balance:** Displays the current balance.
   - **Deposit Money:** Adds a specified amount to the balance.
   - **Withdraw Money:** Deducts a specified amount, ensuring sufficient funds.
   - **Change PIN:** Updates the PIN after verifying the old PIN.

4. **Exiting the Program**
   - Choose option `5` to exit the program.

## Requirements

- This program requires a C compiler to build and run.

## How to Compile and Run

1. **Compile**
   - Open a terminal or command prompt.
   - Navigate to the directory containing the source code (`atm_simulation.c`).
   - Compile the program using the appropriate command for your C compiler. For example:
     ```bash
     gcc atm_simulation.c -o atm_simulation
     ```

2. **Run**
   - After compiling successfully, run the executable:
     ```bash
     ./atm_simulation
     ```
