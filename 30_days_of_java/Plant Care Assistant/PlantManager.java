import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    private List<Plant> plants;
    private static final String FILE_NAME = "plants.ser";

    public PlantManager() {
        plants = new ArrayList<>();
        loadPlants();
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
        savePlants();
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void updatePlant(Plant plant) {
        savePlants();
    }

    private void savePlants() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(plants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPlants() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                plants = (List<Plant>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}