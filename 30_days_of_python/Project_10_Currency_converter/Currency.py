import requests

def convert_currency(amount, from_currency, to_currency):
    api_key = "your_api_key_here"
    url = f"https://v6.exchangerate-api.com/v6/{api_key}/latest/{from_currency}"
    response = requests.get(url)
    data = response.json()

    if response.status_code == 200:
        rate = data["conversion_rates"][to_currency]
        converted_amount = amount * rate
        print(f"{amount} {from_currency} = {converted_amount:.2f} {to_currency}")
    else:
        print("Failed to retrieve exchange rates")

amount = float(input("Enter amount: "))
from_currency = input("From currency (e.g., USD): ").upper()
to_currency = input("To currency (e.g., EUR): ").upper()
convert_currency(amount, from_currency, to_currency)
