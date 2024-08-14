import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;

public class HabitTrackerApp extends JFrame {
    private DefaultListModel<Habit> habitListModel;
    private JList<Habit> habitList;
    private JButton addHabitButton, removeHabitButton, completeHabitButton, viewStatsButton, setReminderButton;

    public HabitTrackerApp() {
        setTitle("Habit Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        habitListModel = new DefaultListModel<>();
        loadHabits(); // Load habits from serialized file

        habitList = new JList<>(habitListModel);
        JScrollPane scrollPane = new JScrollPane(habitList);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5));

        addHabitButton = new JButton("Add Habit");
        removeHabitButton = new JButton("Remove Habit");
        completeHabitButton = new JButton("Complete Habit");
        viewStatsButton = new JButton("View Stats");
        setReminderButton = new JButton("Set Reminder");

        buttonPanel.add(addHabitButton);
        buttonPanel.add(removeHabitButton);
        buttonPanel.add(completeHabitButton);
        buttonPanel.add(viewStatsButton);
        buttonPanel.add(setReminderButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addHabitButton.addActionListener(e -> addHabit());
        removeHabitButton.addActionListener(e -> removeHabit());
        completeHabitButton.addActionListener(e -> completeHabit());
        viewStatsButton.addActionListener(e -> viewStats());
        setReminderButton.addActionListener(e -> setReminder());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveHabits(); // Save habits to serialized file on exit
            }
        });

        // Check reminders every minute
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new ReminderTask(), 0, 60000);
    }

    private void addHabit() {
        String name = JOptionPane.showInputDialog(this, "Enter Habit Name:");
        if (name != null && !name.trim().isEmpty()) {
            Habit habit = new Habit(name);
            habitListModel.addElement(habit);
        }
    }

    private void removeHabit() {
        int selected = habitList.getSelectedIndex();
        if (selected != -1) {
            habitListModel.remove(selected);
        }
    }

    private void completeHabit() {
        int selected = habitList.getSelectedIndex();
        if (selected != -1) {
            Habit habit = habitListModel.get(selected);
            habit.complete();
            habitList.repaint();
        }
    }

    private void viewStats() {
        int selected = habitList.getSelectedIndex();
        if (selected != -1) {
            Habit habit = habitListModel.get(selected);
            JOptionPane.showMessageDialog(this, habit.getStats(), "Habit Stats", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void setReminder() {
        int selected = habitList.getSelectedIndex();
        if (selected != -1) {
            String time = JOptionPane.showInputDialog(this, "Set Reminder Time (HH:MM):");
            if (time != null && time.matches("\\d{2}:\\d{2}")) {
                Habit habit = habitListModel.get(selected);
                habit.setReminderTime(time);
                habitList.repaint();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void loadHabits() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("habits.dat"))) {
            habitListModel = (DefaultListModel<Habit>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            habitListModel = new DefaultListModel<>();
        }
    }

    private void saveHabits() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("habits.dat"))) {
            oos.writeObject(habitListModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playAlarm() {
        try {
            File soundFile = new File("resources/alarm.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ReminderTask extends TimerTask {
        public void run() {
            LocalTime now = LocalTime.now();
            for (int i = 0; i < habitListModel.size(); i++) {
                Habit habit = habitListModel.get(i);
                if (habit.getReminderTime() != null && LocalTime.parse(habit.getReminderTime()).equals(now.truncatedTo(java.time.temporal.ChronoUnit.MINUTES))) {
                    playAlarm();
                    JOptionPane.showMessageDialog(HabitTrackerApp.this, "Reminder: " + habit.getName(), "Reminder", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HabitTrackerApp app = new HabitTrackerApp();
            app.setVisible(true);
        });
    }
}
