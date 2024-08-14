import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PlantCareGUI extends JFrame {
    private PlantManager plantManager;
    private JList<Plant> plantList;
    private DefaultListModel<Plant> listModel;
    private JTextField nameField, speciesField;
    private JButton addButton, waterButton, fertilizeButton, updateHealthButton;
    private JTextArea infoArea;

    public PlantCareGUI() {
        plantManager = new PlantManager();
        setTitle("Plant Care Assistant");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        loadPlants();
    }

    private void initComponents() {
        // Left panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        plantList = new JList<>(listModel);
        plantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        plantList.addListSelectionListener(e -> displayPlantInfo());
        leftPanel.add(new JScrollPane(plantList), BorderLayout.CENTER);

        // Right panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        nameField = new JTextField(15);
        speciesField = new JTextField(15);
        addButton = new JButton("Add Plant");
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Species:"));
        inputPanel.add(speciesField);
        inputPanel.add(addButton);
        rightPanel.add(inputPanel, BorderLayout.NORTH);

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        rightPanel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        waterButton = new JButton("Water");
        fertilizeButton = new JButton("Fertilize");
        updateHealthButton = new JButton("Update Health");
        buttonPanel.add(waterButton);
        buttonPanel.add(fertilizeButton);
        buttonPanel.add(updateHealthButton);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> addPlant());
        waterButton.addActionListener(e -> waterPlant());
        fertilizeButton.addActionListener(e -> fertilizePlant());
        updateHealthButton.addActionListener(e -> updateHealth());
    }

    private void loadPlants() {
        listModel.clear();
        for (Plant plant : plantManager.getPlants()) {
            listModel.addElement(plant);
        }
    }

    private void addPlant() {
        String name = nameField.getText();
        String species = speciesField.getText();
        if (!name.isEmpty() && !species.isEmpty()) {
            Plant plant = new Plant(name, species);
            plantManager.addPlant(plant);
            listModel.addElement(plant);
            nameField.setText("");
            speciesField.setText("");
        }
    }

    private void displayPlantInfo() {
        Plant selectedPlant = plantList.getSelectedValue();
        if (selectedPlant != null) {
            StringBuilder info = new StringBuilder();
            info.append("Name: ").append(selectedPlant.getName()).append("\n");
            info.append("Species: ").append(selectedPlant.getSpecies()).append("\n");
            info.append("Last Watered: ").append(selectedPlant.getLastWatered()).append("\n");
            info.append("Last Fertilized: ").append(selectedPlant.getLastFertilized()).append("\n");
            info.append("Health Status: ").append(selectedPlant.getHealthStatus()).append("\n");
            info.append("\nCare Tips:\n");
            info.append(getCareTips(selectedPlant.getSpecies()));
            infoArea.setText(info.toString());
        }
    }

    private void waterPlant() {
        Plant selectedPlant = plantList.getSelectedValue();
        if (selectedPlant != null) {
            selectedPlant.setLastWatered(LocalDate.now());
            plantManager.updatePlant(selectedPlant);
            displayPlantInfo();
        }
    }

    private void fertilizePlant() {
        Plant selectedPlant = plantList.getSelectedValue();
        if (selectedPlant != null) {
            selectedPlant.setLastFertilized(LocalDate.now());
            plantManager.updatePlant(selectedPlant);
            displayPlantInfo();
        }
    }

    private void updateHealth() {
        Plant selectedPlant = plantList.getSelectedValue();
        if (selectedPlant != null) {
            String newHealth = JOptionPane.showInputDialog(this, "Enter new health status:");
            if (newHealth != null && !newHealth.isEmpty()) {
                selectedPlant.setHealthStatus(newHealth);
                plantManager.updatePlant(selectedPlant);
                displayPlantInfo();
            }
        }
    }

    private String getCareTips(String species) {
        // This method would ideally contain a database or map of species-specific care tips
        // For simplicity, we'll return a generic tip
        return "Water regularly and ensure proper sunlight exposure.";
    }
}