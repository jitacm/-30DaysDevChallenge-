import javax.swing.*;
import java.awt.*;

public class PasswordManagerGUI extends JFrame {
    private PasswordManager manager;
    private JTextField categoryField, accountField, passwordField;
    private JButton addButton, generateButton, showPasswordButton;
    private JComboBox<String> categoryComboBox, accountComboBox;

    public PasswordManagerGUI(PasswordManager manager) {
        this.manager = manager;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Password Manager");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Category:"));
        categoryField = new JTextField();
        add(categoryField);

        add(new JLabel("Account:"));
        accountField = new JTextField();
        add(accountField);

        add(new JLabel("Password:"));
        passwordField = new JTextField();
        add(passwordField);

        addButton = new JButton("Add Password");
        addButton.addActionListener(e -> addPassword());
        add(addButton);

        generateButton = new JButton("Generate Password");
        generateButton.addActionListener(e -> generatePassword());
        add(generateButton);

        add(new JLabel("Select Category:"));
        categoryComboBox = new JComboBox<>();
        categoryComboBox.addActionListener(e -> updateAccountComboBox());
        add(categoryComboBox);

        add(new JLabel("Select Account:"));
        accountComboBox = new JComboBox<>();
        add(accountComboBox);

        showPasswordButton = new JButton("Show Password");
        showPasswordButton.addActionListener(e -> showPassword());
        add(showPasswordButton);

        updateCategoryComboBox();
    }

    private void addPassword() {
        String category = categoryField.getText();
        String account = accountField.getText();
        String password = passwordField.getText();
        
        if (!category.isEmpty() && !account.isEmpty() && !password.isEmpty()) {
            manager.addPassword(category, account, password);
            JOptionPane.showMessageDialog(this, "Password added successfully!");
            updateCategoryComboBox();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
        }
    }

    private void generatePassword() {
        String generatedPassword = manager.generatePassword(12);
        passwordField.setText(generatedPassword);
    }

    private void showPassword() {
        String category = (String) categoryComboBox.getSelectedItem();
        String account = (String) accountComboBox.getSelectedItem();
        
        if (category != null && account != null) {
            String password = manager.getPassword(category, account);
            if (password != null) {
                JOptionPane.showMessageDialog(this, "Password: " + password);
            } else {
                JOptionPane.showMessageDialog(this, "Password not found!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a category and account!");
        }
    }

    private void updateCategoryComboBox() {
        categoryComboBox.removeAllItems();
        for (String category : manager.getCategories()) {
            categoryComboBox.addItem(category);
        }
        updateAccountComboBox();
    }

    private void updateAccountComboBox() {
        accountComboBox.removeAllItems();
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        if (selectedCategory != null) {
            for (String account : manager.getAccountsInCategory(selectedCategory)) {
                accountComboBox.addItem(account);
            }
        }
    }

    private void clearFields() {
        categoryField.setText("");
        accountField.setText("");
        passwordField.setText("");
    }
}
