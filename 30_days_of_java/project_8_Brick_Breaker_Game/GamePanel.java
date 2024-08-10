import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Paddle paddle;
    private Ball ball;
    private Brick[][] bricks;
    private final int ROWS = 5;
    private final int COLS = 10;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);

        paddle = new Paddle();
        ball = new Ball();
        bricks = new Brick[ROWS][COLS];

        int brickWidth = 75;
        int brickHeight = 20;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                bricks[i][j] = new Brick(j * (brickWidth + 10) + 35, i * (brickHeight + 10) + 50, brickWidth, brickHeight);
            }
        }

        timer = new Timer(10, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                paddle.keyPressed(e);
            }
            public void keyReleased(KeyEvent e) {
                paddle.keyReleased(e);
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paddle.draw(g);
        ball.draw(g);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!bricks[i][j].isDestroyed()) {
                    bricks[i][j].draw(g);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paddle.move();
        ball.move();
        ball.checkCollision(paddle, bricks);
        repaint();
    }
}