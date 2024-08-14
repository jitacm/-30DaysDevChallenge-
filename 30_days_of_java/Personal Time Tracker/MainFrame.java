import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class MainFrame extends JFrame {
    private static final String FILE_NAME = "time_tracker.dat";
    private TimeTracker timeTracker = new TimeTracker();
    private Activity currentActivity = null;
    private JTextArea reportArea = new JTextArea(20, 50);
    private JTextField categoryField = new JTextField(20);

    public MainFrame() {
        setTitle("Personal Time Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);

        panel.add(new JLabel("Category:"));
        panel.add(categoryField);

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton reportButton = new JButton("Generate Report");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        panel.add(startButton);
        panel.add(stopButton);
        panel.add(reportButton);
        panel.add(saveButton);
        panel.add(loadButton);

        reportArea.setEditable(false);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = categoryField.getText();
                if (!category.isEmpty()) {
                    currentActivity = new Activity(category);
                    currentActivity.start();
                    timeTracker.addActivity(currentActivity);
                    categoryField.setText(""); // Clear input field
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a category.");
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentActivity != null) {
                    currentActivity.stop();
                    currentActivity = null;
                } else {
                    JOptionPane.showMessageDialog(null, "No activity is currently running.");
                }
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timeTracker.saveToFile(FILE_NAME);
                    JOptionPane.showMessageDialog(null, "Data saved successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage());
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timeTracker = TimeTracker.loadFromFile(FILE_NAME);
                    JOptionPane.showMessageDialog(null, "Data loaded successfully.");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error loading data: " + ex.getMessage());
                }
            }
        });
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder();
        List<Activity> activities = timeTracker.getActivities();
        for (Activity activity : activities) {
            report.append(String.format("%s: %s\n", activity.getName(), activity.getFormattedDuration()));
        }
        reportArea.setText(report.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
