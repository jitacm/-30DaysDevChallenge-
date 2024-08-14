import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String FILE_NAME = "transactions.dat";

    public static void saveTransactions(List<Transaction> transactions) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            transactions = (List<Transaction>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File might not exist yet, which is fine for a new user
        }
        return transactions;
    }
}