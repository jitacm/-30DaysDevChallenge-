import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeManagerGUI extends JFrame {
    private JList<Recipe> recipeList;
    private DefaultListModel<Recipe> listModel;
    private JTextField searchField;
    private JButton addButton, editButton, deleteButton;
    private JTextArea recipeDetails;
    private List<Recipe> recipes;

    public RecipeManagerGUI() {
        setTitle("Recipe Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        recipes = RecipeDataManager.loadRecipes();
        listModel = new DefaultListModel<>();
        for (Recipe recipe : recipes) {
            listModel.addElement(recipe);
        }

        recipeList = new JList<>(listModel);
        searchField = new JTextField(20);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        recipeDetails = new JTextArea();
        recipeDetails.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);

        JScrollPane listScrollPane = new JScrollPane(recipeList);
        JScrollPane detailsScrollPane = new JScrollPane(recipeDetails);

        setLayout(new BorderLayout());
        add(searchPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.WEST);
        add(detailsScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addRecipe());
        editButton.addActionListener(e -> editRecipe());
        deleteButton.addActionListener(e -> deleteRecipe());
        searchField.addActionListener(e -> searchRecipes());
        recipeList.addListSelectionListener(e -> displayRecipeDetails());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                RecipeDataManager.saveRecipes(recipes);
            }
        });
    }

    private void addRecipe() {
        String name = JOptionPane.showInputDialog(this, "Enter recipe name:");
        if (name != null && !name.isEmpty()) {
            String ingredientsStr = JOptionPane.showInputDialog(this, "Enter ingredients (comma-separated):");
            String instructions = JOptionPane.showInputDialog(this, "Enter instructions:");
            String category = JOptionPane.showInputDialog(this, "Enter category:");

            List<String> ingredients = new ArrayList<>();
            for (String ingredient : ingredientsStr.split(",")) {
                ingredients.add(ingredient.trim());
            }

            Recipe newRecipe = new Recipe(name, ingredients, instructions, category);
            recipes.add(newRecipe);
            listModel.addElement(newRecipe);
        }
    }

    private void editRecipe() {
        Recipe selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            // Implement edit functionality
            JOptionPane.showMessageDialog(this, "Edit functionality not implemented yet.");
        }
    }

    private void deleteRecipe() {
        Recipe selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            recipes.remove(selectedRecipe);
            listModel.removeElement(selectedRecipe);
        }
    }

    private void searchRecipes() {
        String searchTerm = searchField.getText().toLowerCase();
        listModel.clear();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(searchTerm) ||
                recipe.getIngredients().toString().toLowerCase().contains(searchTerm)) {
                listModel.addElement(recipe);
            }
        }
    }

    private void displayRecipeDetails() {
        Recipe selectedRecipe = recipeList.getSelectedValue();
        if (selectedRecipe != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name: ").append(selectedRecipe.getName()).append("\n\n");
            sb.append("Category: ").append(selectedRecipe.getCategory()).append("\n\n");
            sb.append("Ingredients:\n");
            for (String ingredient : selectedRecipe.getIngredients()) {
                sb.append("- ").append(ingredient).append("\n");
            }
            sb.append("\nInstructions:\n").append(selectedRecipe.getInstructions());
            recipeDetails.setText(sb.toString());
        } else {
            recipeDetails.setText("");
        }
    }
}