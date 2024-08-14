import javax.swing.JFrame;

public class PongGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Arcade Game");
        PongPanel panel = new PongPanel();
        frame.add(panel);
        frame.setSize(800, 600); // Window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
