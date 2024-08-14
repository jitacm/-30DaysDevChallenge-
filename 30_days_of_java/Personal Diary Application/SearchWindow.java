import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchWindow {
    public static void showSearchWindow(EntryManager entryManager) {
        JFrame frame = new JFrame("Search Entries");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        JTextField tagField = new JTextField();
        JTextField moodField = new JTextField();
        JTextArea resultArea = new JTextArea();
        JButton searchButton = new JButton("Search");

        frame.add(new JLabel("Search by Tag:"));
        frame.add(tagField);
        frame.add(new JLabel("Search by Mood:"));
        frame.add(moodField);
        frame.add(searchButton);
        frame.add(new JScrollPane(resultArea));

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tag = tagField.getText();
                String mood = moodField.getText();
                List<Entry> results;

                if (!tag.isEmpty()) {
                    results = entryManager.searchByTag(tag);
                } else if (!mood.isEmpty()) {
                    results = entryManager.searchByMood(mood);
                } else {
                    results = entryManager.getEntries();
                }

                resultArea.setText("");
                for (Entry entry : results) {
                    resultArea.append(entry.toString() + "\n");
                }
            }
        });

        frame.setVisible(true);
    }
}
