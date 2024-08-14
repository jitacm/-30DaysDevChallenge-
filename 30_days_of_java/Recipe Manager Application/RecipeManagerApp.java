import javax.swing.*;

public class RecipeManagerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecipeManagerGUI gui = new RecipeManagerGUI();
            gui.setVisible(true);
        });
    }
}