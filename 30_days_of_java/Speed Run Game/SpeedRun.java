import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SpeedRun extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int DINO_SIZE = 40;
    private static final int OBSTACLE_WIDTH = 20;
    private static final int OBSTACLE_HEIGHT = 40;
    private static final int GRAVITY = 1;
    private static final int BASE_OBSTACLE_INTERVAL = 2000; // Base interval in milliseconds for obstacle generation

    private int dinoX = 50;
    private int dinoY = HEIGHT - DINO_SIZE - 10;
    private int dinoVelocityY = 0;
    private int score = 0;
    private int difficulty = 1; // 1 = Easy, 2 = Medium, 3 = Hard
    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private Timer gameTimer;
    private Timer obstacleTimer;
    private Random random = new Random();

    public SpeedRun() {
        setTitle("SpeedRun");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (dinoY == HEIGHT - DINO_SIZE - 10) {
                        dinoVelocityY = -15; // jump
                    }
                }
            }
        });

        gameTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                gamePanel.repaint();
            }
        });

        obstacleTimer = new Timer(BASE_OBSTACLE_INTERVAL / difficulty, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addObstacle();
            }
        });

        gameTimer.start();
        obstacleTimer.start();
    }

    private void updateGame() {
        dinoY += dinoVelocityY;
        dinoVelocityY += GRAVITY;

        if (dinoY > HEIGHT - DINO_SIZE - 10) {
            dinoY = HEIGHT - DINO_SIZE - 10;
            dinoVelocityY = 0;
        }

        ArrayList<Rectangle> toRemove = new ArrayList<>();
        for (Rectangle obs : obstacles) {
            obs.x -= 5; // speed of obstacles
            if (obs.x + OBSTACLE_WIDTH < 0) {
                toRemove.add(obs);
                score++;
            }
            if (obs.intersects(new Rectangle(dinoX, dinoY, DINO_SIZE, DINO_SIZE))) {
                gameTimer.stop();
                obstacleTimer.stop();
                JOptionPane.showMessageDialog(this, "Game Over! Your score: " + score);
                System.exit(0);
            }
        }
        obstacles.removeAll(toRemove);
    }

    private void addObstacle() {
        int y = HEIGHT - OBSTACLE_HEIGHT - 10;
        int x = WIDTH + random.nextInt(300) + 50; // random distance between 50 and 350 pixels
        obstacles.add(new Rectangle(x, y, OBSTACLE_WIDTH, OBSTACLE_HEIGHT));
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GREEN);
            g.fillRect(dinoX, dinoY, DINO_SIZE, DINO_SIZE);

            g.setColor(Color.RED);
            for (Rectangle obs : obstacles) {
                g.fillRect(obs.x, obs.y, obs.width, obs.height);
            }

            g.setColor(Color.BLACK);
            g.drawString("Score: " + score, 10, 20);
            g.drawString("Difficulty: " + difficulty, 10, 40);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpeedRun game = new SpeedRun();
            game.setVisible(true);
        });
    }
}
