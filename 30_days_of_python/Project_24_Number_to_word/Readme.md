**Data Structures:**

- `ones`: A list containing words for single digits (0 to 9).
- `teens`: A list containing words for numbers between 11 and 19.
- `tens`: A list containing words for tens digits (10 to 90).
- `thousands`: A list containing words for magnitude multipliers (Thousand, Million, Billion).

**Helper Function (`word`):**

- `word(num, index)`: This recursive helper function takes two arguments:
    - `num`: The integer value to be converted to words for the current group of digits (e.g., hundreds place, tens place, ones place).
    - `index`: An index representing the magnitude of the current group (0 for ones place, 1 for thousands place, and so on).

  - **Base Cases:**
    - If `num` is 0, it returns an empty string (no words needed).
    - If `num` is less than 10, it returns the corresponding word from the `ones` list for the single digit.
    - If `num` is less than 20 (between 11 and 19), it returns the corresponding word from the `teens` list.

  - **Tens and Ones Place:**
    - If `num` is less than 100, it retrieves the word for the tens digit (`tens[num // 10]`) using integer division and then retrieves the word for the ones digit (`ones[num % 10]`) using the modulo operator. It combines these words with a space.

  - **Hundreds Place and Higher:**
    - Otherwise, it retrieves the word for the hundreds digit (`ones[num // 100]`) and adds "Hundred ". Then, it recursively calls itself (`word(num % 100, 0)`) to handle the remaining digits (tens and ones place) with the index reset to 0. This ensures proper handling of numbers beyond hundreds.

**Main Function (`number_to_words`):**

- It first handles the special case of zero, returning "Zero" if the input `num` is 0.

  - **Initialization:**
    - An empty string (`result`) is initialized to store the final word representation.
    - An index (`index`) is set to 0, which tracks the magnitude level (ones, thousands, millions, etc.).

  - **Iterative Processing:**
    - A `while` loop continues as long as `num` is greater than 0.
    - Inside the loop:
      - If the current group of digits (represented by `num % 1000`) is not zero (meaning there are digits in this group):
        - It calls the helper function `word` to convert the current group (`num % 1000`) to words, considering its magnitude (`index`). The result is stored in a temporary variable.
        - It appends the magnitude word (e.g., "Thousand", "Million") from the `thousands` list using the current `index` to the temporary variable with a space.
        - It combines this temporary variable (containing words for the current group and its magnitude) with the existing `result` string, effectively building the word representation from right to left (ones place to higher magnitudes).
      - It updates `num` by integer division (`num //= 1000`) to remove the processed group of digits and prepare for the next group (e.g., moving from thousands to hundreds place).
      - It increments the `index` by 1 to track the increasing magnitude level.

  - **Final Result:**
    - After the loop finishes, the `result` string contains the complete word representation for the entire number.
    - It uses the `strip()` method to remove any leading or trailing spaces from the `result` string and returns the final word representation.

**User Input and Output:**

- The code prompts the user to enter a number using `input`.
- It converts the user input to an integer using `int`.
- It calls the `number_to_words` function with the converted integer to get its word representation.
- Finally, it prints the word representation of the number to the console.

This code effectively demonstrates a recursive approach to convert integers to their corresponding English word representations, handling numbers up to billions.