# Minesweeper

This is a simple Minesweeper game implemented in Java using Swing for the GUI. The game board is an 8x8 grid with 10 randomly placed mines.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Visual Studio Code (VS Code)
- VS Code Java Extension Pack

## Running the Game

1. Open the `Minesweeper.java` file in the `src` folder.
2. Click the `Run` button (green triangle) at the top right corner of the editor, or right-click inside the editor and select `Run Java`.
3. The Minesweeper game window should appear, and you can start playing.

## How to Play

- **Left-click** on a tile to reveal it.
- **Right-click** on a tile to place or remove a flag.
- The goal is to reveal all tiles that do not contain mines.
- If you reveal a tile with a mine, you lose the game.
- If you flag all mines correctly, you win the game.

## Code Overview

### `Minesweeper.java`

This is the main class of the Minesweeper game. It sets up the game board, handles user interactions, and contains the game logic.

- **Inner Class: `MineTile`**: A custom JButton representing each tile on the game board.
- **Methods**:
  - `Minesweeper()`: Constructor that sets up the game window and board.
  - `setMines()`: Places mines randomly on the board.
  - `revealMines()`: Reveals all mines on the board when the game is over.
  - `checkMine(int r, int c)`: Checks a tile for mines and reveals surrounding tiles if no mines are found.
  - `countMine(int r, int c)`: Counts the number of mines around a given tile.
