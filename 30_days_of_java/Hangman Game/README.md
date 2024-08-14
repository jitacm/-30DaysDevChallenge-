# Hangman Game

## Description

Hangman is a classic word-guessing game implemented in Java with a Swing GUI. This version includes additional features such as increased attempts, a "Refresh" button to change the word and reset the game, and a "Restart" button to restart the current game with the same word.

![Screenshot 2024-08-14 022854](https://github.com/user-attachments/assets/e9687c72-fdaa-4be7-b550-a02c97b6f31b)

## Features

- **Classic Gameplay**: Guess the hidden word one letter at a time.
- **Responsive GUI**: Built with Java Swing for a clean and user-friendly interface.
- **Refresh Button**: Change the word and reset attempts without restarting the application.
- **Restart Button**: Restart the current game with the same word.
- **Increased Attempts**: Players are given 10 attempts to guess the word.

## Installation

1. **Clone the Repository**:
    ```bash
    git clone <repository-url>
    ```
2. **Compile the Source Code**:
    Navigate to the project directory and compile the source code using a Java compiler:
    ```bash
    javac HangmanGame.java
    ```
3. **Run the Game**:
    Run the compiled `HangmanGame` class:
    ```bash
    java HangmanGame
    ```

## Usage

- **Guess the Word**: Click on the letters to guess the hidden word. The word and the number of attempts remaining will be displayed at the top and bottom of the window, respectively.
- **Refresh**: Click the "Refresh" button to start a new game with a different word and reset your attempts to the maximum.
- **Restart**: Click the "Restart" button to start over with the same word and restore your attempts to the maximum.

## Example Gameplay

1. Start the game and see the word displayed as underscores (e.g., "_ _ _ _ _").
2. Click on letters to make guesses.
3. Correct guesses reveal the letter in the word, while incorrect guesses decrease the number of attempts left.
4. Use the "Refresh" button to change the word and reset the game.
5. Use the "Restart" button to retry the current word with a full set of attempts.
6. The game ends when you either guess the word correctly or run out of attempts.

## Customization

### You can easily customize the game by modifying the following:

- **Word List**: Edit the `WORDS` array in the `HangmanGame` class to include different or more words.
- **Number of Attempts**: Change the `MAX_ATTEMPTS` constant to increase or decrease the number of guesses allowed.

## Development

### Prerequisites

- **Java Development Kit (JDK)**: Ensure that you have Java installed on your system.
- **IDE**: Optional, but recommended to use an Integrated Development Environment like IntelliJ IDEA or Eclipse.

### Running the Project

1. **Open the Project in an IDE**:
   - If you prefer using an IDE, open the project in IntelliJ IDEA, Eclipse, or any other Java IDE.
2. **Build the Project**:
   - Compile the project files within the IDE.
3. **Run the Application**:
   - Execute the `HangmanGame` class to start the game.
