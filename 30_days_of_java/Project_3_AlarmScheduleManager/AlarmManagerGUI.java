import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlarmManagerGUI extends JFrame {
    private JTextField taskNameField;
    private JTextField alarmDateField;
    private JTextField alarmTimeField;
    private JTextArea taskArea;
    private AlarmManager alarmManager;
    private DateTimeFormatter dateFormatter;
    private DateTimeFormatter timeFormatter;
    private JPanel taskInputPanel;
    private List<JPanel> taskPanels;
    private int taskCount = 1;

    public AlarmManagerGUI() {
        alarmManager = new AlarmManager();
        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        taskPanels = new ArrayList<>();

        // Create UI components
        taskArea = new JTextArea(10, 30);
        taskArea.setEditable(false);
        taskArea.setFont(new Font("Arial", Font.PLAIN, 14));
        taskArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Set up main layout
        setLayout(new BorderLayout());

        // Create a panel for input fields
        taskInputPanel = new JPanel();
        taskInputPanel.setLayout(new BoxLayout(taskInputPanel, BoxLayout.Y_AXIS));

        // Add the first task panel
        addTaskPanel();

        add(taskInputPanel, BorderLayout.CENTER);
        add(new JScrollPane(taskArea), BorderLayout.SOUTH);

        // Set up frame
        setTitle("Alarm Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void addTaskPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(getTaskPanelColor(taskCount)); // Set color based on task count
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        taskNameField = new JTextField(20);
        alarmDateField = new JTextField(10);
        alarmTimeField = new JTextField(5);
        JButton addButton = new JButton("Add Task");

        // Add button action listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Task Name:"), gbc);

        gbc.gridx = 1;
        panel.add(taskNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Alarm Date (yyyy-MM-dd):"), gbc);

        gbc.gridx = 1;
        panel.add(alarmDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Alarm Time (HH:mm):"), gbc);

        gbc.gridx = 1;
        panel.add(alarmTimeField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(addButton, gbc);

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Hover effect
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBackground(panel.getBackground().darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel.setBackground(getTaskPanelColor(taskCount));
            }
        });

        taskInputPanel.add(panel);
        taskPanels.add(panel);
        taskCount++;
    }

    private void addTask() {
        String taskName = taskNameField.getText();
        String alarmDate = alarmDateField.getText();
        String alarmTime = alarmTimeField.getText();

        try {
            LocalDate localDate = LocalDate.parse(alarmDate, dateFormatter);
            LocalTime localTime = LocalTime.parse(alarmTime, timeFormatter);
            LocalDateTime alarmDateTime = LocalDateTime.of(localDate, localTime);
            AlarmTask task = new AlarmTask(taskName, alarmDateTime);
            alarmManager.addTask(task);
            taskArea.append("Task: " + taskName + " scheduled for " + alarmDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n");
            taskNameField.setText("");
            alarmDateField.setText("");
            alarmTimeField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date or time format. Please use yyyy-MM-dd for date and HH:mm for time.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Color getTaskPanelColor(int taskCount) {
        Color[] colors = {
                new Color(220, 220, 220), // Light Gray
                new Color(173, 216, 230), // Light Blue
                new Color(240, 128, 128), // Light Coral
                new Color(144, 238, 144), // Light Green
                new Color(255, 182, 193)  // Light Pink
        };
        return colors[taskCount % colors.length];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Set a modern look and feel
                try {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new AlarmManagerGUI();
            }
        });
    }
}
