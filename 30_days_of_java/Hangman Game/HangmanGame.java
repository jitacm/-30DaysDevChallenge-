import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class HangmanGame extends JFrame {
    private static final String[] WORDS = {"JAVA", "SWING", "PROGRAMMING", "COMPUTER", "KEYBOARD"};
    private static final int MAX_ATTEMPTS = 10;
    private String selectedWord;
    private HashSet<Character> correctGuesses;
    private HashSet<Character> incorrectGuesses;
    private int attemptsLeft = MAX_ATTEMPTS;

    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JPanel lettersPanel;
    private JButton refreshButton;
    private JButton restartButton;

    public HangmanGame() {
        setTitle("Hangman Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        selectedWord = WORDS[(int) (Math.random() * WORDS.length)];
        correctGuesses = new HashSet<>();
        incorrectGuesses = new HashSet<>();

        wordLabel = new JLabel(getDisplayedWord(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft, SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        lettersPanel = new JPanel();
        lettersPanel.setLayout(new GridLayout(3, 9, 5, 5));
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton letterButton = new JButton(String.valueOf(c));
            letterButton.setFont(new Font("SansSerif", Font.BOLD, 18));
            letterButton.addActionListener(new LetterButtonListener());
            lettersPanel.add(letterButton);
        }

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        
        refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        refreshButton.addActionListener(e -> refreshGame());
        controlPanel.add(refreshButton);
        
        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        restartButton.addActionListener(e -> restartGame());
        controlPanel.add(restartButton);

        add(wordLabel, BorderLayout.NORTH);
        add(lettersPanel, BorderLayout.CENTER);
        add(attemptsLabel, BorderLayout.SOUTH);
        add(controlPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private String getDisplayedWord() {
        StringBuilder displayedWord = new StringBuilder();
        for (char c : selectedWord.toCharArray()) {
            if (correctGuesses.contains(c)) {
                displayedWord.append(c).append(" ");
            } else {
                displayedWord.append("_ ");
            }
        }
        return displayedWord.toString().trim();
    }

    private void updateGameState(char guessedLetter) {
        if (selectedWord.indexOf(guessedLetter) >= 0) {
            correctGuesses.add(guessedLetter);
        } else {
            incorrectGuesses.add(guessedLetter);
            attemptsLeft--;
        }

        wordLabel.setText(getDisplayedWord());
        attemptsLabel.setText("Attempts left: " + attemptsLeft);

        checkGameOver();
    }

    private void checkGameOver() {
        if (attemptsLeft == 0) {
            JOptionPane.showMessageDialog(this, "You lost! The word was: " + selectedWord, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        } else if (correctGuesses.size() == new HashSet<>(selectedWord.chars().mapToObj(c -> (char) c).toList()).size()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You've guessed the word: " + selectedWord, "You Win!", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        }
    }

    private void resetGame() {
        selectedWord = WORDS[(int) (Math.random() * WORDS.length)];
        correctGuesses.clear();
        incorrectGuesses.clear();
        attemptsLeft = MAX_ATTEMPTS;
        wordLabel.setText(getDisplayedWord());
        attemptsLabel.setText("Attempts left: " + attemptsLeft);

        for (Component component : lettersPanel.getComponents()) {
            if (component instanceof JButton) {
                component.setEnabled(true);
            }
        }
    }

    private void refreshGame() {
        resetGame();
    }

    private void restartGame() {
        correctGuesses.clear();
        incorrectGuesses.clear();
        attemptsLeft = MAX_ATTEMPTS;
        wordLabel.setText(getDisplayedWord());
        attemptsLabel.setText("Attempts left: " + attemptsLeft);

        for (Component component : lettersPanel.getComponents()) {
            if (component instanceof JButton) {
                component.setEnabled(true);
            }
        }
    }

    private class LetterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            char guessedLetter = button.getText().charAt(0);
            button.setEnabled(false);
            updateGameState(guessedLetter);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HangmanGame::new);
    }
}
