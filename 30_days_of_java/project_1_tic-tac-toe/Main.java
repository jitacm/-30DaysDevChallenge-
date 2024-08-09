import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private char[][] board;
    private char currentPlayer;
    private JButton[][] buttons;

    public Main() {
        board = new char[3][3];
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        initializeBoard();
        createGUI();
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    private void createGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(" ");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col] == buttonClicked) {
                    if (board[row][col] == ' ') {
                        board[row][col] = currentPlayer;
                        buttonClicked.setText(String.valueOf(currentPlayer));
                        if (haveWon(currentPlayer)) {
                            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                            resetBoard();
                        } else if (isBoardFull()) {
                            JOptionPane.showMessageDialog(this, "It's a tie!");
                            resetBoard();
                        } else {
                            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid move. Try again!");
                    }
                }
            }
        }
    }

    private boolean haveWon(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        currentPlayer = 'X';
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
                buttons[row][col].setText(" ");
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
