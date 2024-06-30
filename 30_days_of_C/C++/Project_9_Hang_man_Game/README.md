# Hangman Game

This is a simple Hangman game implemented in C. The program allows one player to enter a word, and another player (or the same player) to guess the word by entering one letter at a time. The game continues until the word is guessed or the player runs out of attempts.

## Features

- Enter a word for the Hangman game.
- Guess the word by entering one letter at a time.
- Display the word with guessed letters and underscores for missing letters.
- Track remaining attempts and notify the player of their win or loss.

## Requirements

- A C compiler (e.g., GCC).

## How to Compile and Run

1. **Clone the repository** (if applicable) or download the source code file.
2. **Open a terminal** and navigate to the directory where the source code file is located.
3. **Compile the program** using a C compiler. For example, if you are using GCC, run the following command:
    ```bash
    gcc hangman_game.c -o hangman_game
    ```
4. **Run the compiled program** by executing the following command:
    ```bash
    ./hangman_game
    ```

## Usage

1. When you run the program, you will be prompted to enter a word for the Hangman game:
    ```
    Enter a word for Hangman: example
    ```

2. The screen will clear, and the game will display the word as underscores, representing the letters to be guessed:
    ```
    _ _ _ _ _ _ _ 
    Attempts remaining: 6
    Enter a letter:
    ```

3. Enter one letter at a time to guess the word. The program will display the updated word and remaining attempts after each guess:
    ```
    _ _ a _ _ _ _ 
    Attempts remaining: 6
    Enter a letter:
    ```

4. If a guessed letter is correct, it will be revealed in the word. If it is incorrect, the number of remaining attempts will decrease.

5. The game continues until either the word is completely guessed, displaying a congratulatory message:
    ```
    Congratulations! You guessed the word: example
    ```

   Or until the player runs out of attempts, displaying the correct word:
    ```
    Sorry, you've run out of attempts. The word was: example
    ```

## Example

### Starting the Game

