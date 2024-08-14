import java.io.*;

public class PersistenceManager {
    private static final String FILE_NAME = "appointments.dat";

    public DataModel loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (DataModel) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new DataModel();  // Return an empty data model if loading fails
        }
    }

    public void saveData(DataModel dataModel) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(dataModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
