**Import:**

- `from datetime import datetime`: This line imports the `datetime` class from the `datetime` module. This class provides functionalities for working with dates and times.

**Function Definition:**

- `def calculate_age(birthdate):`: This line defines a function named `calculate_age` that takes a string argument `birthdate` in YYYY-MM-DD format (e.g., "1990-12-31") and returns your age in years as an integer.

**Calculating Age:**

- `today = datetime.today()`: This line gets the current date and time using `datetime.today()`. It creates a `datetime` object representing the date and time at the moment the code is executed.
- `birthdate = datetime.strptime(birthdate, "%Y-%m-%d")`: This line converts the user-provided birthdate string (`birthdate`) into a `datetime` object. It uses the `datetime.strptime` function to perform the conversion. The format specifier `"%Y-%m-%d"` tells the function how to interpret the different parts of the birthdate string (year, month, day).

  - **Important:** Make sure the user enters the birthdate in the correct YYYY-MM-DD format for this conversion to work properly.

- `age = today.year - birthdate.year - ((today.month, today.day) < (birthdate.month, birthdate.day))`: This line calculates your age. Here's a breakdown of the expression:
    - `today.year - birthdate.year`: This calculates the difference in years between the current year (`today.year`) and your birth year (`birthdate.year`). This gives you the basic age considering only years.
    - `(today.month, today.day) < (birthdate.month, birthdate.day)`: This part checks if your birthday has already occurred in the current year. It compares your birth month and day (`birthdate.month`, `birthdate.day`) with the current month and day (`today.month`, `today.day`). If your birthday has not passed yet this year (i.e., the current date is earlier than your birthday), the comparison will be `True`.
    - The entire comparison `(today.month, today.day) < (birthdate.month, birthdate.day)` is converted to a numeric value using the truth value of the comparison. `True` is generally converted to 1 and `False` to 0.
    - By subtracting this value (`((today.month, today.day) < (birthdate.month, birthdate.day))`) from the initial age calculation (`today.year - birthdate.year`), you effectively subtract 1 from your age if your birthday hasn't happened in the current year yet. This ensures your age considers the fact that you haven't aged a full year since your last birthday.

- `return age`: This line returns the calculated age (`age`) in years as an integer.

**Main Execution:**

- `birthdate = input("Enter your birthdate (YYYY-MM-DD): ")`: This line prompts the user to enter their birthdate in the YYYY-MM-DD format using `input`. The entered birthdate is stored in the `birthdate` variable.
- `age = calculate_age(birthdate)`: This line calls the `calculate_age` function with the user-provided `birthdate` to calculate their age.
- `print(f"You are {age} years old.")`: This line prints a message to the console displaying the calculated age (`age`) retrieved from the function call.

