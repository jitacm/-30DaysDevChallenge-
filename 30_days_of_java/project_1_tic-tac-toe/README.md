# Tic-Tac-Toe Game

This is a simple command-line implementation of the classic Tic-Tac-Toe game in Java. The game allows two players to take turns marking spaces on a 3x3 board until one player wins by getting three marks in a row, column, or diagonal, or the board is full with no winner (resulting in a draw).

git## Features

- **Interactive Gameplay:** Players take turns entering their moves via console input.
- **Winning Condition Detection:** The game checks after each move if a player has won by getting three marks in a row, column, or diagonal.
- **Input Validation:** Ensures that player moves are within valid board coordinates and on an empty space.
- **Clear Console Output:** Displays the current state of the board after each move, providing a clear view of the game progression.

## How to Run

To run the game:
1. Clone the repository or download the source 
2. Compile the `Main.java` file.
3. Execute the compiled `.class` file using `java Main`.

## Instructions

- Players alternate turns, starting with 'X'.
- When prompted, enter the row and column (0-2) where you want to place your mark.
- The game will notify if a move is invalid (e.g., out of bounds or on an occupied space).
- The game ends when a player wins or the board is full.

Enjoy playing Tic-Tac-Toe!
