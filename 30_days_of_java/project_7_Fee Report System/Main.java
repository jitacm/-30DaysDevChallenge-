import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FeeReportSystem system = new FeeReportSystem();

        while (true) {
            System.out.println("Fee Report System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Add Fee Payment");
            System.out.println("5. Generate Fee Report");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    system.addStudent();
                    break;
                case 2:
                    system.updateStudent();
                    break;
                case 3:
                    system.deleteStudent();
                    break;
                case 4:
                    system.addFeePayment();
                    break;
                case 5:
                    system.generateFeeReport();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}