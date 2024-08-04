def calculate_bmi(weight, height):
    return weight / (height ** 2)

def get_positive_number(prompt):
    while True:
        try:
            value = float(input(prompt))  # Attempt to convert input to a float
            if value > 0:
                return value  # Return the value if it is positive
            else:
                print("Please enter a positive number.")  # Prompt for positive number
        except ValueError:
            print("Invalid input. Please enter a numeric value.")  # Handle non-numeric input

# Call get_positive_number within calculate_bmi to avoid initial inputs
bmi = calculate_bmi(
    get_positive_number("Enter your weight in kg: "),
    get_positive_number("Enter your height in meters: ")
)

print(f"Your BMI is {bmi:.2f}")

if bmi < 18.5:
    print("You are underweight.")
elif 18.5 <= bmi < 24.9:
    print("You have a normal weight.")
elif 25 <= bmi < 29.9:
    print("You are overweight.")
else:
    print("You are obese.")
