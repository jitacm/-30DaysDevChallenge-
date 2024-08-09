import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FeeReportSystem {
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student course: ");
        String course = scanner.nextLine();

        Student student = new Student(id, name, course);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        Student student = findStudentById(id);

        if (student != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new course: ");
            String course = scanner.nextLine();
            student.setName(name);
            student.setCourse(course);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);

        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void addFeePayment() {
        System.out.print("Enter student ID for payment: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);

        if (student != null) {
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            student.addFeePayment(amount);
            System.out.println("Payment added successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void generateFeeReport() {
        System.out.print("Enter student ID for report: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);

        if (student != null) {
            System.out.println("Fee Report for " + student.getName());
            System.out.println("Course: " + student.getCourse());
            System.out.println("Total Fee Paid: " + student.getTotalFeePaid());
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}