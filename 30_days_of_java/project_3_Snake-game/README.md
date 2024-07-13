# Snake Game

This is a simple implementation of the classic Snake game using Java and the Swing library for the graphical user interface (GUI). The game allows you to control a snake that moves around the board, consuming food to grow longer, while avoiding collisions with the walls and itself.

## Features

- **Grid-based Movement**: The snake and food are aligned to a grid, making the movement and positioning clear and consistent.
- **Random Food Placement**: Food is placed randomly on the board.
- **Collision Detection**: The game ends if the snake collides with the wall or its own body.
- **Score Display**: The current score, representing the length of the snake, is displayed on the screen.
- **Game Over Message**: A "Game Over" message is displayed when the game ends.

## How to Run

1. Ensure you have Java installed on your machine.
2. Clone this repository or copy the code into a file named `SnakeGame.java`.
3. Open a terminal or command prompt and navigate to the directory containing `SnakeGame.java`.
4. Compile the code using the following command:
    ```sh
    javac SnakeGame.java
    ```
5. Run the compiled class using:
    ```sh
    java SnakeGame
    ```

## Controls

- **Arrow Keys**: Control the direction of the snake. (Code for key controls is provided but not implemented in the initial version)

## Code Overview

- **Tile Class**: Represents a single tile on the game board.
- **SnakeGame Class**: The main class that extends `JPanel` and implements `ActionListener`.
  - **Constructor**: Initializes the game board, snake, food, and game loop.
  - **paintComponent(Graphics g)**: Draws the game elements, including the grid, snake, food, and score.
  - **draw(Graphics g)**: Handles the actual drawing of the snake, food, and grid.
  - **placeFood()**: Randomly places the food on the board.
  - **move()**: Handles the movement logic of the snake and checks for collisions.
  - **actionPerformed(ActionEvent e)**: Called on each timer tick to move the snake