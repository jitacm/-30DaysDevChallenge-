import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private JLabel statusLabel;
    private JLabel userChoiceLabel;
    private JLabel computerChoiceLabel;

    public GamePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(50, 50, 50));

        // Top panel for status
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(30, 30, 30));
        statusLabel = new JLabel("Make your move!");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(statusLabel);

        // Center panel for choices
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 3, 10, 10));
        centerPanel.setBackground(new Color(50, 50, 50));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton rockButton = createButton("Rock");
        JButton paperButton = createButton("Paper");
        JButton scissorsButton = createButton("Scissors");

        centerPanel.add(rockButton);
        centerPanel.add(paperButton);
        centerPanel.add(scissorsButton);

        // Bottom panel for user and computer choice
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(new Color(50, 50, 50));

        userChoiceLabel = new JLabel("Your Choice: ");
        userChoiceLabel.setForeground(Color.WHITE);
        userChoiceLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        computerChoiceLabel = new JLabel("Computer's Choice: ");
        computerChoiceLabel.setForeground(Color.WHITE);
        computerChoiceLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        bottomPanel.add(userChoiceLabel);
        bottomPanel.add(computerChoiceLabel);

        // Adding panels to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame(text);
            }
        });

        return button;
    }

    private void playGame(String userChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[(int) (Math.random() * 3)];

        userChoiceLabel.setText("Your Choice: " + userChoice);
        computerChoiceLabel.setText("Computer's Choice: " + computerChoice);

        if (userChoice.equals(computerChoice)) {
            statusLabel.setText("It's a tie!");
        } else if (
            (userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
            (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
            (userChoice.equals("Scissors") && computerChoice.equals("Paper"))
        ) {
            statusLabel.setText("You win!");
        } else {
            statusLabel.setText("You lose!");
        }
    }
}
