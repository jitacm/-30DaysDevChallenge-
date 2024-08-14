import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static EntryManager entryManager;

    public static void main(String[] args) {
        // Load entries from file
        try {
            entryManager = SerializationManager.loadEntries("diaryEntries.ser");
        } catch (Exception e) {
            entryManager = new EntryManager();
        }

        // Create the main application window
        JFrame frame = new JFrame("Personal Diary/Journal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Buttons for creating entries and searching
        JButton newEntryButton = new JButton("New Entry");
        JButton searchButton = new JButton("Search Entries");
        JButton saveButton = new JButton("Save & Exit");

        newEntryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EntryWindow.showEntryWindow(entryManager);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchWindow.showSearchWindow(entryManager);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Save entries to file
                try {
                    SerializationManager.saveEntries(entryManager, "diaryEntries.ser");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving entries: " + ex.getMessage());
                }
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.add(newEntryButton);
        panel.add(searchButton);
        panel.add(saveButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
