import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private ToDoList todoList;
    private JList<Task> taskList;
    private DefaultListModel<Task> listModel;
    private JTextField taskInput;

    public GUI() {
        todoList = new ToDoList();
        setTitle("To-Do List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        updateListModel();

        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        taskInput = new JTextField(20);
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        inputPanel.add(taskInput);
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton removeButton = new JButton("Remove Task");
        JButton toggleButton = new JButton("Toggle Completion");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleTaskCompletion();
            }
        });

        buttonPanel.add(removeButton);
        buttonPanel.add(toggleButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addTask() {
        String description = taskInput.getText().trim();
        if (!description.isEmpty()) {
            todoList.addTask(description);
            taskInput.setText("");
            updateListModel();
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            todoList.removeTask(selectedIndex);
            updateListModel();
        }
    }

    private void toggleTaskCompletion() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            todoList.toggleTaskCompletion(selectedIndex);
            updateListModel();
        }
    }

    private void updateListModel() {
        listModel.clear();
        for (Task task : todoList.getTasks()) {
            listModel.addElement(task);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
