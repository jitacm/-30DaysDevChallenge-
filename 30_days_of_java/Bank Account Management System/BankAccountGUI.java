import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAccountGUI {
    private JFrame frame;
    private JTextField accountNumberField;
    private JTextField accountHolderField;
    private JTextField balanceField;
    private JTextArea resultArea;
    private BankSystem bankSystem;

    public BankAccountGUI() {
        bankSystem = new BankSystem();
        frame = new JFrame("Bank Account Management System");

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        inputPanel.add(new JLabel("Account Number:"));
        accountNumberField = new JTextField();
        inputPanel.add(accountNumberField);

        inputPanel.add(new JLabel("Account Holder:"));
        accountHolderField = new JTextField();
        inputPanel.add(accountHolderField);

        inputPanel.add(new JLabel("Balance:"));
        balanceField = new JTextField();
        inputPanel.add(balanceField);

        frame.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JButton createAccountButton = new JButton("Create Account");
        buttonPanel.add(createAccountButton);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

        JButton depositButton = new JButton("Deposit");
        buttonPanel.add(depositButton);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        JButton withdrawButton = new JButton("Withdraw");
        buttonPanel.add(withdrawButton);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        JButton checkBalanceButton = new JButton("Check Balance");
        buttonPanel.add(checkBalanceButton);
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        frame.add(buttonPanel, BorderLayout.CENTER);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void createAccount() {
        try {
            String accountNumber = accountNumberField.getText();
            String accountHolder = accountHolderField.getText();
            double balance = Double.parseDouble(balanceField.getText());

            bankSystem.createAccount(accountNumber, accountHolder, balance);
            resultArea.setText("Account created successfully.\nAccount Number: " + accountNumber);
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid balance. Please enter a valid number.");
        } catch (Exception e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    private void deposit() {
        try {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(balanceField.getText());

            if (bankSystem.deposit(accountNumber, amount)) {
                resultArea.setText("Deposit successful.\nNew Balance: " + bankSystem.getAccount(accountNumber).getBalance());
            } else {
                resultArea.setText("Deposit failed. Account not found.");
            }
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid amount. Please enter a valid number.");
        } catch (Exception e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    private void withdraw() {
        try {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(balanceField.getText());

            if (bankSystem.withdraw(accountNumber, amount)) {
                resultArea.setText("Withdrawal successful.\nNew Balance: " + bankSystem.getAccount(accountNumber).getBalance());
            } else {
                resultArea.setText("Withdrawal failed. Account not found or insufficient balance.");
            }
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid amount. Please enter a valid number.");
        } catch (Exception e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    private void checkBalance() {
        try {
            String accountNumber = accountNumberField.getText();
            Account account = bankSystem.getAccount(accountNumber);
            
            if (account != null) {
                resultArea.setText("Account Balance: $" + account.getBalance() + 
                                   "\nAccount Holder: " + account.getAccountHolderName());
            } else {
                resultArea.setText("Account not found. Please check the account number.");
            }
        } catch (Exception e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BankAccountGUI();
    }
}
