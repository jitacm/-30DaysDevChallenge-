# Tic-Tac-Toe Game

This is a simple Tic-Tac-Toe game implemented in Java. It features both a command-line and a graphical user interface (GUI) version of the game.

## Features

### Command-Line Version
- **Interactive Gameplay:** Players take turns entering their moves via console input.
- **Winning Condition Detection:** The game checks after each move if a player has won by getting three marks in a row, column, or diagonal.
- **Input Validation:** Ensures that player moves are within valid board coordinates and on an empty space.
- **Clear Console Output:** Displays the current state of the board after each move, providing a clear view of the game progression.

### GUI Version
- **Graphical User Interface:** The game is displayed in a window using Java Swing, providing a visual interface for players.
- **Interactive Gameplay:** Players click on the buttons representing the board cells to make their moves.
- **Visual Feedback:** The game displays 'X' or 'O' on the buttons based on the current player’s move.
- **Winning Notification:** A message dialog will pop up to notify when a player wins or if the game ends in a draw.
- **Easy Reset:** The board automatically resets after a win or a draw, allowing for continuous play.

## How to Run

To run the GUI version of the game:
1. Clone the repository or download the source.
2. Compile the `Main.java` file using a Java compiler (e.g., `javac Main.java`).
3. Execute the compiled `.class` file using `java Main`.

To run the command-line version:
1. Navigate to the command-line interface and follow the same steps as above but ensure to run the command-line version of the code.

## Instructions

### GUI Version
- Launch the application, and a window will appear with a 3x3 grid.
- Click on the buttons in the grid to place your mark ('X' or 'O').
- The game alternates turns between 'X' and 'O'.
- A message dialog will appear if a player wins or if the game ends in a tie.
- The board will automatically reset after the game ends.

### Command-Line Version
- Players alternate turns, starting with 'X'.
- When prompted, enter the row and column (0-2) where you want to place your mark.
- The game will notify if a move is invalid (e.g., out of bounds or on an occupied space).
- The game ends when a player wins or the board is full.

Enjoy playing Tic-Tac-Toe!
