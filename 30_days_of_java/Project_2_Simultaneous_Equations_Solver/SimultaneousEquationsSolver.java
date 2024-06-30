import java.util.Scanner;

public class SimultaneousEquationsSolver {

    public static void main(String[] args) {
        
        System.out.println("=========================================");
        System.out.println("Welcome to the Simultaneous Equations Solver!");
        System.out.println("This program solves a system of two linear equations in the form:");
        System.out.println("  a1*x + b1*y = c1");
        System.out.println("  a2*x + b2*y = c2");
        System.out.println("You will be prompted to enter the coefficients a1, b1, c1, a2, b2, and c2.");
        System.out.println("=========================================");

        Scanner scanner = new Scanner(System.in);

        // Prompt user for coefficients
        System.out.println("Enter coefficients for the first equation (a1*x + b1*y = c1):");
        double a1 = getDoubleInput(scanner, "a1");
        double b1 = getDoubleInput(scanner, "b1");
        double c1 = getDoubleInput(scanner, "c1");

        System.out.println("Enter coefficients for the second equation (a2*x + b2*y = c2):");
        double a2 = getDoubleInput(scanner, "a2");
        double b2 = getDoubleInput(scanner, "b2");
        double c2 = getDoubleInput(scanner, "c2");

        double[] result = solveEquations(a1, b1, c1, a2, b2, c2);

        if (result != null) {
            System.out.println("=========================================");
            System.out.println("The solution is:");
            System.out.println("x = " + result[0]);
            System.out.println("y = " + result[1]);
            System.out.println("=========================================");
        } else {
            System.out.println("=========================================");
            System.out.println("The system has no unique solution.");
            System.out.println("=========================================");
        }

        scanner.close();
    }

    /**
     * Prompts the user to enter a valid double value for the specified coefficient.
     *
     * @param scanner The Scanner object for user input.
     * @param coefficientName The name of the coefficient to prompt for.
     * @return The double value entered by the user.
     */
    public static double getDoubleInput(Scanner scanner, String coefficientName) {
        while (true) {
            System.out.print(coefficientName + ": ");
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /**
     * Solves the system of equations a1*x + b1*y = c1 and a2*x + b2*y = c2 using Cramer's rule.
     *
     * @param a1 Coefficient of x in the first equation.
     * @param b1 Coefficient of y in the first equation.
     * @param c1 Constant term in the first equation.
     * @param a2 Coefficient of x in the second equation.
     * @param b2 Coefficient of y in the second equation.
     * @param c2 Constant term in the second equation.
     * @return An array containing the solutions for x and y, or null if there is no unique solution.
     */
    public static double[] solveEquations(double a1, double b1, double c1, double a2, double b2, double c2) {
        // Calculate the determinant
        double determinant = a1 * b2 - a2 * b1;

        // If determinant is zero, there is no unique solution
        if (determinant == 0) {
            return null;
        }

        // Calculate x and y using Cramer's rule
        double x = (c1 * b2 - c2 * b1) / determinant;
        double y = (a1 * c2 - a2 * c1) / determinant;

        return new double[]{x, y};
    }
}
