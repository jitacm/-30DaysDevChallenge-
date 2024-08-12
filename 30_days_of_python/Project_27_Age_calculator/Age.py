from datetime import datetime

def calculate_age(birthdate):
    today = datetime.today()
    birthdate = datetime.strptime(birthdate, "%Y-%m-%d")
    
    years = today.year - birthdate.year
    months = today.month - birthdate.month
    days = today.day - birthdate.day
    
    # Adjust for cases where the current day or month is earlier than the birth day or month
    if days < 0:
        months -= 1
        days += (birthdate.replace(year=today.year, month=today.month) - birthdate.replace(year=today.year, month=today.month-1)).days
    if months < 0:
        years -= 1
        months += 12

    return years, months, days

birthdate = input("Enter your birthdate (YYYY-MM-DD): ")
years, months, days = calculate_age(birthdate)
print(f"You are {years} years, {months} months, and {days} days old.")