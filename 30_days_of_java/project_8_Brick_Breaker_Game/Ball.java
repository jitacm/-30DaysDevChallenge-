import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball {
    private int x, y, dx, dy;
    private final int DIAMETER = 20;

    public Ball() {
        x = 390;
        y = 530;
        dx = 1;
        dy = -1;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0 || x > 780) dx = -dx;
        if (y < 0) dy = -dy;
        if (y > 580) dy = -dy;  // Reset ball position or end game if necessary
    }

    public void checkCollision(Paddle paddle, Brick[][] bricks) {
        Rectangle ballBounds = new Rectangle(x, y, DIAMETER, DIAMETER);
        Rectangle paddleBounds = new Rectangle(paddle.getX(), 550, paddle.getWidth(), 10);

        if (ballBounds.intersects(paddleBounds)) {
            dy = -dy;
        }

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (!bricks[i][j].isDestroyed()) {
                    Rectangle brickBounds = bricks[i][j].getBounds();
                    if (ballBounds.intersects(brickBounds)) {
                        dy = -dy;
                        bricks[i][j].setDestroyed(true);
                    }
                }
            }
        }
    }
}