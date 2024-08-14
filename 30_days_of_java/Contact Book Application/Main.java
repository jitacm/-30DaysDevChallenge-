import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContactBookGUI gui = new ContactBookGUI();
            gui.setVisible(true);
        });
    }
}