import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleInterestCalculator extends JFrame {

    private JTextField principalField, rateField, timeField, resultField;
    private JButton calculateButton, resetButton;

    public SimpleInterestCalculator() {
        setTitle("Simple Interest Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Principal (P): "), gbc);
        principalField = new JTextField(15);
        gbc.gridx = 1;
        add(principalField, gbc);

        // Rate of Interest
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Rate of Interest (R): "), gbc);
        rateField = new JTextField(15);
        gbc.gridx = 1;
        add(rateField, gbc);

        // Time Period
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Time Period (T): "), gbc);
        timeField = new JTextField(15);
        gbc.gridx = 1;
        add(timeField, gbc);

        // Result
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Simple Interest (SI): "), gbc);
        resultField = new JTextField(15);
        resultField.setEditable(false);
        gbc.gridx = 1;
        add(resultField, gbc);

        // Buttons
        calculateButton = new JButton("Calculate");
        resetButton = new JButton("Reset");

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(calculateButton, gbc);
        gbc.gridx = 1;
        add(resetButton, gbc);

        // Action Listeners
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double principal = Double.parseDouble(principalField.getText());
                    double rate = Double.parseDouble(rateField.getText());
                    double time = Double.parseDouble(timeField.getText());
                    double simpleInterest = (principal * rate * time) / 100;
                    resultField.setText(String.format("%.2f", simpleInterest));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principalField.setText("");
                rateField.setText("");
                timeField.setText("");
                resultField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleInterestCalculator().setVisible(true);
            }
        });
    }
}
