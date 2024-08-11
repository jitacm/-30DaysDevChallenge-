import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSimulationSystem {

    private static Map<Integer, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample accounts
        accounts.put(1234, new Account(1234, 1000.0));
        accounts.put(5678, new Account(5678, 2000.0));

        System.out.println("Welcome to the ATM Simulation System!");

        while (true) {
            System.out.print("Enter your PIN: ");
            int pin = scanner.nextInt();
            Account account = accounts.get(pin);

            if (account == null) {
                System.out.println("Invalid PIN. Please try again.");
                continue;
            }

            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance(account);
                    break;
                case 2:
                    withdrawCash(account);
                    break;
                case 3:
                    depositCash(account);
                    break;
                case 4:
                    transactionHistory(account);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkBalance(Account account) {
        System.out.println("Current Balance: ₹" + account.getBalance());
    }

    private static void withdrawCash(Account account) {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();
        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds.");
        } else {
            account.withdraw(amount);
            System.out.println("Withdrawal successful. New balance: ₹" + account.getBalance());
        }
    }

    private static void depositCash(Account account) {
        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: ₹" + account.getBalance());
    }

    private static void transactionHistory(Account account) {
        System.out.println("Transaction History:");
        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}

class Account {
    private int pin;
    private double balance;
    private List<String> transactionHistory;

    public Account(int pin, double balance) {
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: ₹" + balance);
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
        transactionHistory.add("Withdrawn: ₹" + amount + " | New Balance: ₹" + balance);
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: ₹" + amount + " | New Balance: ₹" + balance);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}