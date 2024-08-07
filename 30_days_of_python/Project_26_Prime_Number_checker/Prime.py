def is_prime(n):
    if n <= 1:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

try:
    number = int(input("Enter a number: "))
    if number < 0:
        print("Please enter a non-negative integer.")
    elif is_prime(number):
        print(f"{number} is a prime number.")
    else:
        print(f"{number} is not a prime number.")
except ValueError:
    print("Invalid input. Please enter a valid integer.")
