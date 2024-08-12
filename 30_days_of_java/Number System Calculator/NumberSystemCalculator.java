import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberSystemCalculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private JTextArea resultArea;
    private JComboBox<String> inputBase;
    private JComboBox<String> outputBase;
    private JButton convertButton, clearButton, copyButton;

    public NumberSystemCalculator() {
        setTitle("Number System Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Input Number:", JLabel.RIGHT));
        inputField = new JTextField();
        inputPanel.add(inputField);

        inputPanel.add(new JLabel("From:", JLabel.RIGHT));
        inputBase = new JComboBox<>(new String[]{"Decimal", "Binary", "Octal", "Hexadecimal"});
        inputPanel.add(inputBase);

        inputPanel.add(new JLabel("To:", JLabel.RIGHT));
        outputBase = new JComboBox<>(new String[]{"Decimal", "Binary", "Octal", "Hexadecimal"});
        inputPanel.add(outputBase);

        add(inputPanel, BorderLayout.NORTH);

        // Result panel
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Conversion Result"));
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        buttonPanel.add(convertButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields());
        buttonPanel.add(clearButton);

        copyButton = new JButton("Copy");
        copyButton.addActionListener(e -> copyResult());
        buttonPanel.add(copyButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = inputField.getText().trim();
            if (input.isEmpty()) {
                showError("Please enter a number to convert.");
                return;
            }

            int fromBase = getBase((String) inputBase.getSelectedItem());
            int toBase = getBase((String) outputBase.getSelectedItem());

            String result = convertNumber(input, fromBase, toBase);
            resultArea.setText("Converted Number: " + result);
        } catch (NumberFormatException ex) {
            showError("Invalid input number! Please check the format of the number.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private int getBase(String base) {
        switch (base) {
            case "Binary":
                return 2;
            case "Octal":
                return 8;
            case "Hexadecimal":
                return 16;
            default:
                return 10;
        }
    }

    private String convertNumber(String number, int fromBase, int toBase) {
        return Integer.toString(Integer.parseInt(number, fromBase), toBase).toUpperCase();
    }

    private void clearFields() {
        inputField.setText("");
        resultArea.setText("");
        inputBase.setSelectedIndex(0);
        outputBase.setSelectedIndex(0);
    }

    private void copyResult() {
        String result = resultArea.getText();
        if (!result.isEmpty()) {
            StringSelection stringSelection = new StringSelection(result);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            JOptionPane.showMessageDialog(this, "Result copied to clipboard!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showError("No result to copy!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberSystemCalculator calculator = new NumberSystemCalculator();
            calculator.setVisible(true);
        });
    }
}
