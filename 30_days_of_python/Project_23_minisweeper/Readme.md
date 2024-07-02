**Functions:**

- `create_board(size, num_mines)`: This function takes two arguments:
    - `size`: An integer representing the size of the minesweeper board (number of rows and columns).
    - `num_mines`: An integer representing the number of mines to place on the board.

  - **Board Creation:**
    - It creates a 2D list (`board`) using list comprehension. The outer list represents rows, and the inner list represents columns. Initially, all cells are filled with spaces (' ').

  - **Mine Placement:**
    - `random.sample(range(size * size), num_mines)`: This line generates `num_mines` random, unique indices within the range of the entire board (0 to `size * size` - 1). These indices represent the locations where mines will be placed.
    - It iterates through the list of randomly generated mine indices (`mines`). For each mine index:
      - It converts the linear index (`mine`) into row (`mine // size`) and column (`mine % size`) coordinates using integer division and modulo operations.
      - It places a 'M' (representing a mine) at the corresponding row and column position in the `board` list.

  - **Return Board:**
    - The function returns the `board` list containing the mines placed on the empty board.

- `display_board(board)`: This function takes a 2D list (`board`) representing the minesweeper board as input.

  - **Printing Board:**
    - It iterates through each row (`row`) in the `board` list.
    - Inside the loop, it uses `' '.join(row)` to join all the characters in the current row (which are spaces or 'M' for mines) into a single string separated by spaces. This creates a string representation of the row.
    - It prints the string representation of each row, followed by a newline character (`\n`) to move to the next line after each row is printed.
    - Finally, it prints an additional newline character (`\n`) to add a blank line after the entire board is displayed.

**Main Execution:**

- `size = 5`: This line sets the size of the minesweeper board to 5x5 (5 rows and 5 columns).
- `num_mines = 5`: This line sets the number of mines to place on the board to 5.
- `board = create_board(size, num_mines)`: This line calls the `create_board` function to generate a random minesweeper board with the specified size and number of mines. The generated board is stored in the `board` variable.
- `display_board(board)`: This line calls the `display_board` function to print the generated minesweeper board to the console