import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDataManager {
    private static final String FILE_NAME = "recipes.dat";

    public static void saveRecipes(List<Recipe> recipes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Recipe> loadRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            recipes = (List<Recipe>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File might not exist yet, return empty list
        }
        return recipes;
    }
}