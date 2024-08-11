import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double result;
    private String operator;
    private boolean startNewNumber;
    private boolean operatorPressed;

    public Calculator() {
        // Setting up the frame
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 40));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        // Button labels
        String[] buttons = {
                "C", "←", "M+", "M-",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        // Adding buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 30));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        // Initialize variables
        result = 0;
        operator = "=";
        startNewNumber = true;
        operatorPressed = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789.".contains(command)) {
            if (startNewNumber) {
                if (command.equals(".")) {
                    display.setText("0" + command);
                } else {
                    display.setText(command);
                }
                startNewNumber = false;
            } else if (command.equals(".") && display.getText().contains(".")) {
                return; // Ignore multiple decimals
            } else {
                display.setText(display.getText() + command);
            }
            operatorPressed = false;
        } else if (command.equals("C")) {
            result = 0;
            display.setText("0");
            operator = "=";
            startNewNumber = true;
            operatorPressed = false;
        } else if (command.equals("←")) {
            String text = display.getText();
            if (text.length() > 1) {
                display.setText(text.substring(0, text.length() - 1));
            } else {
                display.setText("0");
                startNewNumber = true;
            }
        } else if (command.equals("M+")) {
            result += Double.parseDouble(display.getText());
            startNewNumber = true;
            operatorPressed = false;
        } else if (command.equals("M-")) {
            result -= Double.parseDouble(display.getText());
            startNewNumber = true;
            operatorPressed = false;
        } else {
            if (!operatorPressed) {
                calculate(Double.parseDouble(display.getText()));
                display.setText("" + result);
                operator = command;
                startNewNumber = true;
                operatorPressed = true;
            } else {
                operator = command;
            }
        }
    }

    private void calculate(double number) {
        switch (operator) {
            case "+":
                result += number;
                break;
            case "-":
                result -= number;
                break;
            case "*":
                result *= number;
                break;
            case "/":
                if (number != 0) {
                    result /= number;
                } else {
                    display.setText("Error");
                    startNewNumber = true;
                }
                break;
            case "=":
                result = number;
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
