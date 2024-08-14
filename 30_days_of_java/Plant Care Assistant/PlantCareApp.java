import javax.swing.SwingUtilities;

public class PlantCareApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlantCareGUI gui = new PlantCareGUI();
            gui.setVisible(true);
        });
    }
}