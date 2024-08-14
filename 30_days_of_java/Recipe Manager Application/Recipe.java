import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private String name;
    private List<String> ingredients;
    private String instructions;
    private String category;

    public Recipe(String name, List<String> ingredients, String instructions, String category) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name;
    }
}