import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarCipherGUI {

    private JFrame frame;
    private JTextField inputField;
    private JTextField shiftField;
    private JTextArea resultArea;
    
    public void createAndShowGUI() {
        // Frame setup
        frame = new JFrame("Caesar Cipher Encryption/Decryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Input panel setup
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel inputLabel = new JLabel("Enter Text:");
        inputField = new JTextField();

        JLabel shiftLabel = new JLabel("Shift Value:");
        shiftField = new JTextField();

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(shiftLabel);
        inputPanel.add(shiftField);

        // Buttons panel setup
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");

        encryptButton.addActionListener(new EncryptButtonListener());
        decryptButton.addActionListener(new DecryptButtonListener());

        buttonsPanel.add(encryptButton);
        buttonsPanel.add(decryptButton);

        // Result area setup
        resultArea = new JTextArea();
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Caesar Cipher Algorithm: Encrypt
    private String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                character = (char) ((character - base + shift) % 26 + base);
            }
            result.append(character);
        }

        return result.toString();
    }

    // Caesar Cipher Algorithm: Decrypt
    private String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    // Event listener for Encrypt button
    private class EncryptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String inputText = inputField.getText();
                int shiftValue = Integer.parseInt(shiftField.getText());
                String encryptedText = encrypt(inputText, shiftValue);
                resultArea.setText("Encrypted Text: " + encryptedText);
            } catch (NumberFormatException ex) {
                resultArea.setText("Please enter a valid shift value.");
            }
        }
    }

    // Event listener for Decrypt button
    private class DecryptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String inputText = inputField.getText();
                int shiftValue = Integer.parseInt(shiftField.getText());
                String decryptedText = decrypt(inputText, shiftValue);
                resultArea.setText("Decrypted Text: " + decryptedText);
            } catch (NumberFormatException ex) {
                resultArea.setText("Please enter a valid shift value.");
            }
        }
    }
}
