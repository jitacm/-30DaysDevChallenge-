**Functions:**

- `print_board(board)`: This function takes a 2D list representing the game board as input.

  - **Printing Board:**
    - It iterates through each row (`row`) in the board list.
    - Inside the loop, it uses `' | '.join(row)` to join all the characters in the current row (which are spaces or "X" and "O" for players) into a single string separated by vertical pipes (" | ").
    - This creates a string representation of the row with separators.
    - It prints the string representation of each row, followed by a newline character (`\n`) to move to the next line after each row is printed.
    - After all rows are printed, it prints a line of dashes ("-" * 5) to create a visual separation between the rows.

- `check_winner(board, player)`: This function takes two arguments:
    - `board`: The 2D list representing the game board.
    - `player`: The current player's symbol ("X" or "O").

  - **Checking Rows and Columns:**
    - It iterates through each row (`row`) in the `board` list.
      - Inside the loop, it uses `all(s == player for s in row)` to check if all elements (`s`) in the current row are equal to the `player` symbol. The `all` function returns `True` if all elements in the iterable are true.
      - If all elements in a row match the current player's symbol, it means the player has won in that row, and the function returns `True`.
    - It then iterates through each column (`col`) using a `range(3)` loop (since there are 3 columns in a Tic-Tac-Toe board).
      - Inside the loop, it uses a similar logic with `all(row[col] == player for row in board)` to check if all elements in the specified column (`col`) across all rows (`row`) match the current player's symbol.
      - If all elements in a column match the player's symbol, it indicates a win, and the function returns `True`.

  - **Checking Diagonals:**
    - It checks for wins in both diagonals using a single line with conditional expressions:
      - `all(board[i][i] == player for i in range(3))`: This checks the main diagonal (top-left to bottom-right) by iterating through indices (`i`) from 0 to 2. It ensures all elements at positions `board[i][i]` (where the row and column indices are the same) match the player's symbol.
      - `all(board[i][2 - i] == player for i in range(3))`: This checks the secondary diagonal (top-right to bottom-left) using a similar logic, but with `board[i][2 - i]` to access elements along the diagonal.
    - If either diagonal condition is met (all elements match the player's symbol), it returns `True`.

  - **No Winner:**
    - If none of the conditions above are met (no winning row, column, or diagonal), the function returns `False`, indicating the game continues.

- `tic_tac_toe()`: This function serves as the main game loop.

  - **Game Setup:**
    - It initializes an empty board using a list comprehension to create a 3x3 grid filled with spaces (" ").
    - It sets the starting player to "X".

  - **Game Loop:**
    - The loop runs for a maximum of 9 turns (since there are 9 squares on the board).
    - Inside the loop:
      - It calls the `print_board` function to display the current state of the board.
      - It prompts the current player (indicated by `current_player`) to enter their move using `input`. The `input` function returns a string, so it uses `map(int, ...split())` to convert the user input (row and column separated by space) into a tuple of integers representing the chosen position.
      - It checks if the selected position on the board (`board[row][col]`) is empty (" ").
        - If the position is empty:
          - It updates the board at the chosen position with the current player's symbol.
          - It calls the `check_winner` function to see if the current player has achieved a win after this move.
            - If `check_winner` returns `True`,