import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarMaintenanceTracker extends JFrame {
    private static final long serialVersionUID = 1L;
    private Vehicle vehicle;
    private List<MaintenanceTask> tasks;
    private JTextField makeField, modelField, yearField, fuelField, expenseField;
    private JTextArea taskArea;
    private JButton saveButton, loadButton;

    public CarMaintenanceTracker() {
        setTitle("Car Maintenance Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the tasks list
        tasks = new ArrayList<>();

        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // Vehicle Info Panel
        JPanel vehiclePanel = new JPanel(new GridLayout(6, 2));
        tabbedPane.addTab("Vehicle Info", vehiclePanel);

        vehiclePanel.add(new JLabel("Make:"));
        makeField = new JTextField();
        vehiclePanel.add(makeField);

        vehiclePanel.add(new JLabel("Model:"));
        modelField = new JTextField();
        vehiclePanel.add(modelField);

        vehiclePanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        vehiclePanel.add(yearField);

        vehiclePanel.add(new JLabel("Fuel Efficiency (km/l):"));
        fuelField = new JTextField();
        vehiclePanel.add(fuelField);

        vehiclePanel.add(new JLabel("Expenses ($):"));
        expenseField = new JTextField();
        vehiclePanel.add(expenseField);

        // Maintenance Tasks Panel
        JPanel tasksPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Maintenance Tasks", tasksPanel);

        taskArea = new JTextArea();
        tasksPanel.add(new JScrollPane(taskArea), BorderLayout.CENTER);

        JPanel taskButtonPanel = new JPanel();
        tasksPanel.add(taskButtonPanel, BorderLayout.SOUTH);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(e -> addTask());
        taskButtonPanel.add(addTaskButton);

        JButton clearTasksButton = new JButton("Clear Tasks");
        clearTasksButton.addActionListener(e -> clearTasks());
        taskButtonPanel.add(clearTasksButton);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveData());
        buttonPanel.add(saveButton);

        loadButton = new JButton("Load");
        loadButton.addActionListener(e -> loadData());
        buttonPanel.add(loadButton);

        // Initialize vehicle and tasks
        vehicle = new Vehicle("", "", 0, 0, 0);
        tasks = new ArrayList<>();
    }

    private void addTask() {
        String description = JOptionPane.showInputDialog("Enter task description:");
        if (description != null && !description.trim().isEmpty()) {
            Date date = new Date(); // You can use a date picker for a real application
            tasks.add(new MaintenanceTask(description, date));
            updateTaskArea();
        }
    }

    private void clearTasks() {
        tasks.clear();
        updateTaskArea();
    }

    private void updateTaskArea() {
        taskArea.setText("");
        for (MaintenanceTask task : tasks) {
            taskArea.append(task.getDescription() + " - " + new SimpleDateFormat("yyyy-MM-dd").format(task.getDate()) + "\n");
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("carData.ser"))) {
            oos.writeObject(vehicle);
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("carData.ser"))) {
            vehicle = (Vehicle) ois.readObject();
            tasks = (List<MaintenanceTask>) ois.readObject();

            if (vehicle == null) {
                vehicle = new Vehicle("", "", 0, 0, 0);
            }
            if (tasks == null) {
                tasks = new ArrayList<>();
            }

            // Update UI fields
            makeField.setText(vehicle.getMake());
            modelField.setText(vehicle.getModel());
            yearField.setText(String.valueOf(vehicle.getYear()));
            fuelField.setText(String.valueOf(vehicle.getFuelEfficiency()));
            expenseField.setText(String.valueOf(vehicle.getExpenses()));

            updateTaskArea();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarMaintenanceTracker app = new CarMaintenanceTracker();
            app.setVisible(true);
        });
    }
}
