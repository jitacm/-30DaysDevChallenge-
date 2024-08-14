import javax.swing.*;

public class CaesarCipherApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CaesarCipherGUI gui = new CaesarCipherGUI();
            gui.createAndShowGUI();
        });
    }
}
