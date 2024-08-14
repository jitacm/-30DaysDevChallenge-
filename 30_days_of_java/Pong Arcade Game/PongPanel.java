import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongPanel extends JPanel implements ActionListener, KeyListener {

    // Game elements
    private int paddle1Y = 250, paddle2Y = 250; // Paddle positions
    private int ballX = 390, ballY = 290; // Ball position
    private int ballXDir = -1, ballYDir = -1; // Ball movement direction
    private int paddleHeight = 100, paddleWidth = 10, ballSize = 20;

    // Timer for game loop
    private Timer timer;

    public PongPanel() {
        // Set background color and initialize key listener
        this.setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        // Initialize the timer for the game loop
        timer = new Timer(10, this); // Adjust the delay (in milliseconds) as needed
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g); // Call the method to draw the game elements
    }

    private void draw(Graphics g) {
        // Draw paddles
        g.setColor(Color.WHITE);
        g.fillRect(30, paddle1Y, paddleWidth, paddleHeight);
        g.fillRect(750, paddle2Y, paddleWidth, paddleHeight);

        // Draw ball
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, ballSize, ballSize);

        // Draw middle line
        g.setColor(Color.WHITE);
        g.drawLine(400, 0, 400, 600);
    }

    private void moveBall() {
        ballX += ballXDir;
        ballY += ballYDir;

        // Ball collision with top and bottom borders
        if (ballY <= 0 || ballY >= getHeight() - ballSize) {
            ballYDir = -ballYDir;
        }

        // Ball collision with paddles
        if (ballX <= 40 && ballY >= paddle1Y && ballY <= paddle1Y + paddleHeight) {
            ballXDir = -ballXDir;
        } else if (ballX >= getWidth() - 50 && ballY >= paddle2Y && ballY <= paddle2Y + paddleHeight) {
            ballXDir = -ballXDir;
        }

        // Ball out of bounds (reset)
        if (ballX < 0 || ballX > getWidth()) {
            ballX = 390;
            ballY = 290;
        }
    }

    private void movePaddles(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W && paddle1Y > 0) {
            paddle1Y -= 15;
        }
        if (key == KeyEvent.VK_S && paddle1Y < getHeight() - paddleHeight) {
            paddle1Y += 15;
        }
        if (key == KeyEvent.VK_UP && paddle2Y > 0) {
            paddle2Y -= 15;
        }
        if (key == KeyEvent.VK_DOWN && paddle2Y < getHeight() - paddleHeight) {
            paddle2Y += 15;
        }
    }

    // Implementing the game loop via the ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        moveBall(); // Update ball position
        repaint();  // Redraw the panel
    }

    @Override
    public void keyPressed(KeyEvent e) {
        movePaddles(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
