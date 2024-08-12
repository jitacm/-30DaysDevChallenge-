import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PalindromeChecker extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;

    public PalindromeChecker() {
        setTitle("Palindrome Checker");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel promptLabel = new JLabel("Enter a string:");
        promptLabel.setBounds(30, 30, 100, 25);
        add(promptLabel);

        inputField = new JTextField();
        inputField.setBounds(140, 30, 200, 25);
        add(inputField);

        JButton checkButton = new JButton("Check");
        checkButton.setBounds(140, 70, 80, 25);
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPalindrome();
            }
        });
        add(checkButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(240, 70, 80, 25);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        add(clearButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(30, 110, 320, 25);
        add(resultLabel);
    }

    private void checkPalindrome() {
        String text = inputField.getText().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversedText = new StringBuilder(text).reverse().toString();

        if (text.equals(reversedText)) {
            resultLabel.setText("The input is a palindrome!");
        } else {
            resultLabel.setText("The input is not a palindrome.");
        }
    }

    private void clearFields() {
        inputField.setText("");
        resultLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PalindromeChecker().setVisible(true);
            }
        });
    }
}
