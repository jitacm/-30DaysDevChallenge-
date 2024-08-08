import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private String course;
    private List<Double> feePayments = new ArrayList<>();

    public Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void addFeePayment(double amount) {
        feePayments.add(amount);
    }

    public double getTotalFeePaid() {
        double total = 0;
        for (double payment : feePayments) {
            total += payment;
        }
        return total;
    }
}