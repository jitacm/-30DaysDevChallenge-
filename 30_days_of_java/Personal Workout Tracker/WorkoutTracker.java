import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class WorkoutTracker extends JFrame {
    private ArrayList<Exercise> exercises;
    private HashMap<String, String> goals;
    private JTextArea logArea;
    private JTextField exerciseField, setsField, repsField, weightField;

    public WorkoutTracker() {
        exercises = new ArrayList<>();
        goals = new HashMap<>();
        
        setTitle("Personal Workout Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Exercise:"));
        exerciseField = new JTextField();
        inputPanel.add(exerciseField);
        inputPanel.add(new JLabel("Sets:"));
        setsField = new JTextField();
        inputPanel.add(setsField);
        inputPanel.add(new JLabel("Reps:"));
        repsField = new JTextField();
        inputPanel.add(repsField);
        inputPanel.add(new JLabel("Weight (e.g. 180 KG):"));
        weightField = new JTextField();
        inputPanel.add(weightField);
        JButton logButton = new JButton("Log Exercise");
        logButton.addActionListener(e -> logExercise());
        inputPanel.add(logButton);
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Log Area
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton viewProgressButton = new JButton("View Progress");
        viewProgressButton.addActionListener(e -> viewProgress());
        buttonPanel.add(viewProgressButton);
        JButton setGoalButton = new JButton("Set Goal");
        setGoalButton.addActionListener(e -> setGoal());
        buttonPanel.add(setGoalButton);
        JButton viewGoalsButton = new JButton("View Goals");
        viewGoalsButton.addActionListener(e -> viewGoals());
        buttonPanel.add(viewGoalsButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        loadData();
    }
    
    private void logExercise() {
        try {
            String name = exerciseField.getText();
            int sets = Integer.parseInt(setsField.getText());
            int reps = Integer.parseInt(repsField.getText());
            String weight = weightField.getText();
            
            Exercise exercise = new Exercise(name, sets, reps, weight);
            exercises.add(exercise);
            
            logArea.append(exercise.toString() + "\n");
            
            exerciseField.setText("");
            setsField.setText("");
            repsField.setText("");
            weightField.setText("");
            
            saveData();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for sets and reps.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void viewProgress() {
        StringBuilder progress = new StringBuilder("Exercise Progress:\n");
        for (Exercise exercise : exercises) {
            progress.append(exercise.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, progress.toString());
    }
    
    private void setGoal() {
        String exercise = JOptionPane.showInputDialog("Enter exercise name:");
        String goal = JOptionPane.showInputDialog("Enter goal (e.g., '100 kg' or '20 reps'):");
        goals.put(exercise, goal);
        saveData();
    }
    
    private void viewGoals() {
        StringBuilder goalsStr = new StringBuilder("Current Goals:\n");
        for (Map.Entry<String, String> entry : goals.entrySet()) {
            goalsStr.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(this, goalsStr.toString());
    }
    
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("workout_data.ser"))) {
            oos.writeObject(exercises);
            oos.writeObject(goals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("workout_data.ser"))) {
            exercises = (ArrayList<Exercise>) ois.readObject();
            goals = (HashMap<String, String>) ois.readObject();
            for (Exercise exercise : exercises) {
                logArea.append(exercise.toString() + "\n");
            }
        } catch (IOException | ClassNotFoundException e) {
            // File might not exist yet, which is fine for first run
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WorkoutTracker().setVisible(true));
    }
}

class Exercise implements Serializable {
    private String name;
    private int sets;
    private int reps;
    private String weight;
    private Date date;
    
    public Exercise(String name, int sets, int reps, String weight) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.date = new Date();
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("%s - %s: %d sets, %d reps, %s", sdf.format(date), name, sets, reps, weight);
    }
}