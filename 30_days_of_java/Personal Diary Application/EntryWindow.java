import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class EntryWindow {
    public static void showEntryWindow(EntryManager entryManager) {
        JFrame frame = new JFrame("New Entry");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 1));

        JTextField dateField = new JTextField();
        JTextArea contentArea = new JTextArea();
        JTextField tagsField = new JTextField();
        String[] moods = {"Happy", "Sad", "Neutral", "Angry"};
        JComboBox<String> moodBox = new JComboBox<>(moods);
        JButton saveButton = new JButton("Save Entry");

        frame.add(new JLabel("Date:"));
        frame.add(dateField);
        frame.add(new JLabel("Content:"));
        frame.add(new JScrollPane(contentArea));
        frame.add(new JLabel("Tags (comma-separated):"));
        frame.add(tagsField);
        frame.add(new JLabel("Mood:"));
        frame.add(moodBox);
        frame.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String content = contentArea.getText();
                String tags = tagsField.getText();
                String mood = (String) moodBox.getSelectedItem();

                Entry entry = new Entry(date, content, Arrays.asList(tags.split(",")), mood);
                entryManager.addEntry(entry);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
