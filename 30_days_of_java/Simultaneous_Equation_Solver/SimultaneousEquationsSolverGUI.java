import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimultaneousEquationsSolverGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simultaneous Equations Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // First equation inputs
        JLabel labelEquation1 = new JLabel("Enter coefficients for the first equation (a1*x + b1*y = c1):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(labelEquation1, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("a1:"), gbc);
        JTextField textFieldA1 = new JTextField(10);
        gbc.gridx = 1;
        panel.add(textFieldA1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("b1:"), gbc);
        JTextField textFieldB1 = new JTextField(10);
        gbc.gridx = 1;
        panel.add(textFieldB1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("c1:"), gbc);
        JTextField textFieldC1 = new JTextField(10);
        gbc.gridx = 1;
        panel.add(textFieldC1, gbc);

        // Second equation inputs
        JLabel labelEquation2 = new JLabel("Enter coefficients for the second equation (a2*x + b2*y = c2):");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(labelEquation2, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(new JLabel("a2:"), gbc);
        JTextField textFieldA2 = new JTextField(10);
        gbc.gridx = 3;
        panel.add(textFieldA2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(new JLabel("b2:"), gbc);
        JTextField textFieldB2 = new JTextField(10);
        gbc.gridx = 3;
        panel.add(textFieldB2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(new JLabel("c2:"), gbc);
        JTextField textFieldC2 = new JTextField(10);
        gbc.gridx = 3;
        panel.add(textFieldC2, gbc);

        // Solve button
        JButton solveButton = new JButton("Solve");
        solveButton.setBackground(new Color(0, 123, 255));
        solveButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(solveButton, gbc);

        // Result area
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(240, 240, 240));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(resultArea), gbc);

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double a1 = Double.parseDouble(textFieldA1.getText());
                    double b1 = Double.parseDouble(textFieldB1.getText());
                    double c1 = Double.parseDouble(textFieldC1.getText());
                    double a2 = Double.parseDouble(textFieldA2.getText());
                    double b2 = Double.parseDouble(textFieldB2.getText());
                    double c2 = Double.parseDouble(textFieldC2.getText());

                    double[] result = solveEquations(a1, b1, c1, a2, b2, c2);
                    if (result != null) {
                        resultArea.setText("The solution is:\n");
                        resultArea.append("x = " + result[0] + "\n");
                        resultArea.append("y = " + result[1] + "\n");
                    } else {
                        resultArea.setText("The system has no unique solution.");
                    }
                } catch (NumberFormatException ex) {
                    resultArea.setText("Please enter valid numbers for all coefficients.");
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static double[] solveEquations(double a1, double b1, double c1, double a2, double b2, double c2) {
        double determinant = a1 * b2 - a2 * b1;
        if (determinant == 0) {
            return null;
        }
        double x = (c1 * b2 - c2 * b1) / determinant;
        double y = (a1 * c2 - a2 * c1) / determinant;
        return new double[]{x, y};
    }
}
