import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<Task> tasks;
    private static final String FILENAME = "todolist.ser";

    public ToDoList() {
        tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(String description) {
        tasks.add(new Task(description));
        saveTasks();
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks();
        }
    }

    public void toggleTaskCompletion(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(!task.isCompleted());
            saveTasks();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadTasks() {
        File file = new File(FILENAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                tasks = (List<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
