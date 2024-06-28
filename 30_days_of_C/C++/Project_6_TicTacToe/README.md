# Tic-Tac-Toe Game

This is a simple Tic-Tac-Toe game implemented in C. Two players take turns to place their markers (`X` and `O`) on a 3x3 board. The player who successfully places three of their markers in a row (horizontally, vertically, or diagonally) wins the game. If all nine squares are filled and no player has three in a row, the game is a draw.

## Getting Started

### Prerequisites

- A C compiler (e.g., GCC)

### Running the Game

1. **Clone the Repository:**
    ```sh
    git clone <repository-url>
    cd <repository-directory>
    ```

2. **Compile the Code:**
    ```sh
    gcc -o tic_tac_toe tic_tac_toe.c
    ```

3. **Run the Executable:**
    ```sh
    ./tic_tac_toe
    ```

### How to Play

- The game starts with Player 1 (`X`).
- Players take turns to enter the row and column number (0, 1, or 2) to place their marker.
- The game will display the board after each move.
- The game will announce the winner or a draw when the game ends.