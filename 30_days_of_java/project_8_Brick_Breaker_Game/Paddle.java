import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Paddle {
    private int x, y;
    private final int WIDTH = 100, HEIGHT = 10;
    private int dx;

    public Paddle() {
        x = 350;
        y = 550;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void move() {
        x += dx;
        if (x < 0) x = 0;
        if (x > 700) x = 700;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dx = -5;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = 5;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return WIDTH;
    }
}