import random

number_to_guess = random.randint(1, 100)
attempts = 0

print("Guess the number between 1 and 100")

while True:
    guess = int(input("Enter your guess: "))
    attempts += 1
    
    if guess < number_to_guess:
        print("Too low!")
    elif guess > number_to_guess:
        print("Too high!")
    else:
        print(f"Congratulations! You've guessed the number in {attempts} attempts.")
        break
