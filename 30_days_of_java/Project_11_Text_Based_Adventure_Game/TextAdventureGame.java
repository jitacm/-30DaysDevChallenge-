import java.util.Scanner;

public class TextAdventureGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Text-Based Adventure Game!");
        System.out.println("Your journey begins now...\n");

        startAdventure(scanner);

        scanner.close();
    }

    public static void startAdventure(Scanner scanner) {
        System.out.println("You find yourself at a crossroads in a dark forest.");
        System.out.println("Do you want to go left into the unknown, or right towards the distant lights?");
        System.out.print("Type 'left' or 'right': ");
        String choice1 = scanner.nextLine().toLowerCase();

        if (choice1.equals("left")) {
            goLeftPath(scanner);
        } else if (choice1.equals("right")) {
            goRightPath(scanner);
        } else {
            System.out.println("Invalid choice. Please choose 'left' or 'right'.");
            startAdventure(scanner);
        }
    }

    public static void goLeftPath(Scanner scanner) {
        System.out.println("\nYou walk down a narrow path, surrounded by tall trees. The air grows colder...");
        System.out.println("Suddenly, you hear a rustling in the bushes. Do you want to investigate or run?");
        System.out.print("Type 'investigate' or 'run': ");
        String choice2 = scanner.nextLine().toLowerCase();

        if (choice2.equals("investigate")) {
            investigateBushes(scanner);
        } else if (choice2.equals("run")) {
            runAway(scanner);
        } else {
            System.out.println("Invalid choice. Please choose 'investigate' or 'run'.");
            goLeftPath(scanner);
        }
    }

    public static void goRightPath(Scanner scanner) {
        System.out.println("\nYou walk towards the lights and discover a small village.");
        System.out.println("The villagers seem friendly and offer you food and shelter.");
        System.out.println("Do you want to stay the night or continue your journey?");
        System.out.print("Type 'stay' or 'continue': ");
        String choice3 = scanner.nextLine().toLowerCase();

        if (choice3.equals("stay")) {
            stayInVillage();
        } else if (choice3.equals("continue")) {
            continueJourney(scanner);
        } else {
            System.out.println("Invalid choice. Please choose 'stay' or 'continue'.");
            goRightPath(scanner);
        }
    }

    public static void investigateBushes(Scanner scanner) {
        System.out.println("\nYou cautiously approach the bushes. Suddenly, a wild animal jumps out!");
        System.out.println("Do you want to fight it or run?");
        System.out.print("Type 'fight' or 'run': ");
        String choice4 = scanner.nextLine().toLowerCase();

        if (choice4.equals("fight")) {
            System.out.println("\nYou bravely fight the animal and manage to scare it away. You survive and continue your journey.");
        } else if (choice4.equals("run")) {
            System.out.println("\nYou run as fast as you can and manage to escape the animal. You survive and continue your journey.");
        } else {
            System.out.println("Invalid choice. Please choose 'fight' or 'run'.");
            investigateBushes(scanner);
        }

        System.out.println("Congratulations! You survived this adventure.");
    }

    public static void runAway(Scanner scanner) {
        System.out.println("\nYou turn around and run as fast as you can, escaping the unknown danger.");
        System.out.println("However, you find yourself lost in the forest. Do you want to climb a tree to get a better view or keep walking?");
        System.out.print("Type 'climb' or 'walk': ");
        String choice5 = scanner.nextLine().toLowerCase();

        if (choice5.equals("climb")) {
            System.out.println("\nYou climb a tree and spot a distant village. You make your way there and find safety.");
        } else if (choice5.equals("walk")) {
            System.out.println("\nYou keep walking and eventually find your way out of the forest.");
        } else {
            System.out.println("Invalid choice. Please choose 'climb' or 'walk'.");
            runAway(scanner);
        }

        System.out.println("Congratulations! You survived this adventure.");
    }

    public static void stayInVillage() {
        System.out.println("\nYou decide to stay the night in the village. The villagers welcome you with open arms.");
        System.out.println("The next morning, you continue your journey, well-rested and safe.");
        System.out.println("Congratulations! You survived this adventure.");
    }

    public static void continueJourney(Scanner scanner) {
        System.out.println("\nYou decide to continue your journey through the night. The road is dark and full of dangers.");
        System.out.println("Do you want to take a shortcut through the woods or stay on the main road?");
        System.out.print("Type 'shortcut' or 'road': ");
        String choice6 = scanner.nextLine().toLowerCase();

        if (choice6.equals("shortcut")) {
            System.out.println("\nYou take the shortcut, but the woods are treacherous. You stumble upon a hidden trap and narrowly escape.");
        } else if (choice6.equals("road")) {
            System.out.println("\nYou stay on the main road and safely reach the next town by morning.");
        } else {
            System.out.println("Invalid choice. Please choose 'shortcut' or 'road'.");
            continueJourney(scanner);
        }

        System.out.println("Congratulations! You survived this adventure.");
    }
}