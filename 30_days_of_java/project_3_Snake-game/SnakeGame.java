import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int boardWidth;
    private int boardHeight;
    private int tileSize = 25;

    // Snake
    private Tile snakeHead;
    private ArrayList<Tile> snakeBody;

    // Food
    private Tile food;
    private Random random;

    // Game logic
    private int velocityX;
    private int velocityY;
    private Timer gameLoop;

    private boolean gameOver = false;
    private int gameSpeed = 200;  // Set default speed

    public SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);

        initializeGame();

        // Key listener for controlling snake
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (velocityY != 1) { velocityX = 0; velocityY = -1; }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (velocityY != -1) { velocityX = 0; velocityY = 1; }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (velocityX != 1) { velocityX = -1; velocityY = 0; }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (velocityX != -1) { velocityX = 1; velocityY = 0; }
                        break;
                }
            }
        });
    }

    private void initializeGame() {
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<>();

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 1;
        velocityY = 0;

        gameOver = false;

        if (gameLoop != null) {
            gameLoop.stop();
        }
        gameLoop = new Timer(gameSpeed, this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Draw grid lines (optional)
        g.setColor(Color.gray);
        for (int i = 0; i <= boardWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
        }
        for (int i = 0; i <= boardHeight / tileSize; i++) {
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
        }

        // Draw food
        g.setColor(Color.red);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        // Draw snake head
        g.setColor(new Color(0, 255, 0));
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        // Draw snake body
        g.setColor(new Color(0, 200, 0));
        for (Tile part : snakeBody) {
            g.fill3DRect(part.x * tileSize, part.y * tileSize, tileSize, tileSize, true);
        }

        // Draw score or game over message
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.drawString("Game Over! Press 'Start' to play again.", tileSize, tileSize);
        } else {
            g.drawString("Score: " + snakeBody.size(), tileSize, tileSize);
        }
    }

    public void placeFood() {
        do {
            food.x = random.nextInt(boardWidth / tileSize);
            food.y = random.nextInt(boardHeight / tileSize);
        } while (isFoodOnSnake());
    }

    private boolean isFoodOnSnake() {
        if (snakeHead.x == food.x && snakeHead.y == food.y) {
            return true;
        }
        for (Tile part : snakeBody) {
            if (part.x == food.x && part.y == food.y) {
                return true;
            }
        }
        return false;
    }

    public void move() {
        if (gameOver) {
            return;
        }

        // Calculate new head position
        int newHeadX = snakeHead.x + velocityX;
        int newHeadY = snakeHead.y + velocityY;

        // Check if the new head position is out of bounds
        if (newHeadX < 0 || newHeadX >= boardWidth / tileSize ||
            newHeadY < 0 || newHeadY >= boardHeight / tileSize) {
            gameOver = true;
            gameLoop.stop();  // Stop the timer when game is over
            repaint();        // Request to repaint to show game over message
            return;
        }

        // Eat food
        if (newHeadX == food.x && newHeadY == food.y) {
            snakeBody.add(new Tile(snakeHead.x, snakeHead.y));
            placeFood();
        }

        // Move the snake body
        for (int i = snakeBody.size() - 1; i > 0; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }
        if (!snakeBody.isEmpty()) {
            snakeBody.get(0).x = snakeHead.x;
            snakeBody.get(0).y = snakeHead.y;
        }

        // Move the snake head
        snakeHead.x = newHeadX;
        snakeHead.y = newHeadY;

        // Check collision with itself
        for (Tile part : snakeBody) {
            if (snakeHead.x == part.x && snakeHead.y == part.y) {
                gameOver = true;
                gameLoop.stop();  // Stop the timer when game is over
                repaint();        // Request to repaint to show game over message
                break;
            }
        }

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
        }
    }

    private void startGame() {
        initializeGame();
        gameLoop.start();
        requestFocusInWindow();
    }

    private void setDifficulty(String difficulty) {
        if (difficulty.equals("Easy")) {
            gameSpeed = 200;
        } else if (difficulty.equals("Hard")) {
            gameSpeed = 100;
        }
        if (gameLoop != null) {
            gameLoop.setDelay(gameSpeed);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            SnakeGame snakeGame = new SnakeGame(500, 500);
            frame.setLayout(new BorderLayout());
            frame.add(snakeGame, BorderLayout.CENTER);

            JPanel controlPanel = new JPanel();
            JButton startButton = new JButton("Start");
            String[] difficultyLevels = { "Easy", "Hard" };
            JComboBox<String> difficultyBox = new JComboBox<>(difficultyLevels);

            startButton.addActionListener(e -> snakeGame.startGame());
            difficultyBox.addActionListener(e -> {
                String selectedDifficulty = (String) difficultyBox.getSelectedItem();
                snakeGame.setDifficulty(selectedDifficulty);
            });

            controlPanel.add(startButton);
            controlPanel.add(difficultyBox);

            frame.add(controlPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            // Set default difficulty to Easy
            snakeGame.setDifficulty("Easy");
        });
    }
}
