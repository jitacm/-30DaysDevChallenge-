import java.io.*;

public class SerializationManager {
    public static void saveEntries(EntryManager manager, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(manager);
        }
    }

    public static EntryManager loadEntries(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (EntryManager) ois.readObject();
        }
    }
}
