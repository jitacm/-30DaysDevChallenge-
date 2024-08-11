import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFourGame extends JFrame {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private JButton[][] buttons;
    private boolean redTurn = true;
    private final Color RED = Color.RED;
    private final Color YELLOW = Color.YELLOW;
    private final Color EMPTY = Color.WHITE;
    private final Color BORDER_COLOR = Color.BLACK;

    public ConnectFourGame() {
        setTitle("Connect Four");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(ROWS, COLS));
        initializeBoard();
        setVisible(true);
    }

    private void initializeBoard() {
        buttons = new JButton[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JButton button = new JButton();
                button.setBackground(EMPTY);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
                button.setPreferredSize(new Dimension(100, 100));
                button.addActionListener(new ButtonClickListener(row, col));
                buttons[row][col] = button;
                add(button);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int col;

        public ButtonClickListener(int row, int col) {
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int r = ROWS - 1; r >= 0; r--) {
                if (buttons[r][col].getBackground() == EMPTY) {
                    buttons[r][col].setBackground(redTurn ? RED : YELLOW);
                    if (checkWin(r, col)) {
                        JOptionPane.showMessageDialog(null, (redTurn ? "Red" : "Yellow") + " wins!");
                        resetBoard();
                    } else {
                        redTurn = !redTurn;
                    }
                    break;
                }
            }
        }
    }

    private boolean checkWin(int row, int col) {
        Color color = buttons[row][col].getBackground();
        return checkDirection(row, col, 1, 0, color) || // Horizontal
               checkDirection(row, col, 0, 1, color) || // Vertical
               checkDirection(row, col, 1, 1, color) || // Diagonal \
               checkDirection(row, col, 1, -1, color);  // Diagonal /
    }

    private boolean checkDirection(int row, int col, int dRow, int dCol, Color color) {
        int count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS && buttons[r][c].getBackground() == color) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }
        return false;
    }

    private void resetBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                buttons[row][col].setBackground(EMPTY);
            }
        }
        redTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConnectFourGame::new);
    }
}
