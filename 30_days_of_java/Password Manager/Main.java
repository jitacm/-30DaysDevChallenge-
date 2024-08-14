import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String masterPassword = JOptionPane.showInputDialog("Enter master password:");
            if (masterPassword != null && !masterPassword.isEmpty()) {
                PasswordManager manager = new PasswordManager(masterPassword);
                new PasswordManagerGUI(manager).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Master password is required. Exiting.");
                System.exit(0);
            }
        });
    }
}