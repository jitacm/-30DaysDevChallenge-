# Number Guessing Game

Welcome to the Number Guessing Game! This is a simple Java-based game where the player tries to guess a randomly generated number within a specified range.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Code Explanation](#code-explanation)
- [Contribution](#contribution)
- [License](#license)
- [Contact](#contact)

## Introduction

The Number Guessing Game is a fun and interactive way to practice and demonstrate basic Java programming skills. The game generates a random number between 1 and 100, and the player has to guess the number with feedback provided after each guess.

## Features

- Generates a random number between 1 and 100.
- Provides feedback on whether the guess is too high, too low, or correct.
- Tracks the number of attempts taken by the player.
- Option to play again after completing a game.

## Installation

To run the Number Guessing Game on your local machine, follow these steps:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/nikharepriyant/30DaysDevChallenge.git
    ```

2. **Navigate to the project directory**:
    ```bash
    cd 30DaysDevChallenge/30_days_of_java/NumberGuessingGame
    ```

3. **Compile the Java program**:
    ```bash
    javac NumberGuessingGame.java
    ```

4. **Run the program**:
    ```bash
    java NumberGuessingGame
    ```

## Usage

1. **Start the game**: Run the `NumberGuessingGame` class.
2. **Follow the prompts**: Enter your guesses as prompted by the game.
3. **Receive feedback**: The game will tell you if your guess is too high, too low, or correct.
4. **Play again**: After guessing the correct number, you will be asked if you want to play again. Type "yes" to start a new game or "no" to exit.

### Main Components

1. **Random Number Generation**:
    - `Random random = new Random();`
    - `int numberToGuess = random.nextInt(100) + 1;` - Generates a random number between 1 and 100.

2. **User Input**:
    - `Scanner scanner = new Scanner(System.in);`
    - `int playerGuess = scanner.nextInt();` - Captures the user's guess.

3. **Game Loop**:
    - `while (!hasGuessedCorrectly) {...}` - Continuously prompts the player to guess until the correct number is found.

4. **Feedback**:
    - Provides feedback on whether the guess is too high, too low, or correct.
    - Displays the number of attempts once the correct number is guessed.

5. **Play Again**:
    - `System.out.print("Would you like to play again? (yes/no): ");`
    - `playAgain = scanner.next();` - Asks the player if they want to play again.

## Contribution

Contributions are welcome! If you have any ideas for improvements or new features, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact

If you have any questions or suggestions, feel free to reach out:

- GitHub: [nikharepriyant](https://github.com/nikharepriyant)
- Email: nikharepriyant@gmail.com

Thank you for checking out the Number Guessing Game! Have fun coding!
