import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {
    private JTextField amountField;
    private JTextField descriptionField;
    private JComboBox<String> categoryComboBox;
    private JButton addButton;
    private JTextArea transactionArea;
    private List<Transaction> transactions;

    public MainWindow() {
        setTitle("Personal Finance Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(new String[]{"Income", "Expense"});
        inputPanel.add(categoryComboBox);
        addButton = new JButton("Add Transaction");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        transactionArea = new JTextArea();
        transactionArea.setEditable(false);
        add(new JScrollPane(transactionArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton reportButton = new JButton("Generate Report");
        buttonPanel.add(reportButton);

        add(buttonPanel, BorderLayout.SOUTH);

        transactions = DataManager.loadTransactions();
        updateTransactionDisplay();

        addButton.addActionListener(e -> addTransaction());
        reportButton.addActionListener(e -> generateReport());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                DataManager.saveTransactions(transactions);
            }
        });
    }

    private void addTransaction() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String description = descriptionField.getText();
            String category = (String) categoryComboBox.getSelectedItem();

            Transaction transaction = new Transaction(amount, description, category);
            transactions.add(transaction);
            updateTransactionDisplay();

            amountField.setText("");
            descriptionField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a number.");
        }
    }

    private void updateTransactionDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Transaction t : transactions) {
            sb.append(t.toString()).append("\n");
        }
        transactionArea.setText(sb.toString());
    }

    private void generateReport() {
        String report = ReportGenerator.generateSimpleReport(transactions);
        JOptionPane.showMessageDialog(this, report, "Financial Report", JOptionPane.INFORMATION_MESSAGE);
    }
}