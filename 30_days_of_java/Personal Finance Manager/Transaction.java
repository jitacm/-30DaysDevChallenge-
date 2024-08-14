import java.time.LocalDateTime;
import java.io.Serializable;

public class Transaction implements Serializable {
    private double amount;
    private String description;
    private String category;
    private LocalDateTime date;

    public Transaction(double amount, String description, String category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %.2f (%s)", date, category, amount, description);
    }
}