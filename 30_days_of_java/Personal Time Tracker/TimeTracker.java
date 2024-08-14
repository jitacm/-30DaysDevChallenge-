import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TimeTracker implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Activity> activities = new ArrayList<>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    public static TimeTracker loadFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            return new TimeTracker(); // Return a new instance if the file does not exist
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (TimeTracker) ois.readObject();
        }
    }
}
