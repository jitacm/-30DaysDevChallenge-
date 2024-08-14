import java.util.List;

public class ReportGenerator {
    public static String generateSimpleReport(List<Transaction> transactions) {
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction t : transactions) {
            if (t.getCategory().equals("Income")) {
                totalIncome += t.getAmount();
            } else {
                totalExpense += t.getAmount();
            }
        }

        return String.format("Total Income: %.2f\nTotal Expense: %.2f\nNet: %.2f",
                totalIncome, totalExpense, totalIncome - totalExpense);
    }
}