import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String playAgain;

        do {
            int numberToGuess = random.nextInt(100) + 1;
            int numberOfTries = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have selected a number between 1 and 100. Try to guess it!");

            while (!hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int playerGuess = scanner.nextInt();
                numberOfTries++;

                if (playerGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (playerGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    System.out.println("It took you " + numberOfTries + " tries.");
                    hasGuessedCorrectly = true;
                }
            }

            System.out.print("Would you like to play again? (yes/no): ");
            playAgain = scanner.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thank you for playing the Number Guessing Game!");
        scanner.close();
    }
}