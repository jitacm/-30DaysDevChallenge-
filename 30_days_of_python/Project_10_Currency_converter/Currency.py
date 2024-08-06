import requests

def convert_currency(amount, from_currency, to_currency):
    api_key = "23d7f3f047f426e2f46cda57"  # Use API key directly in the URL string (This assignment is now done only once)
    url = f"https://v6.exchangerate-api.com/v6/{api_key}/latest/{from_currency}"
    
    try:
        response = requests.get(url)  # Making the single HTTP request
        response.raise_for_status()   # This line makes the single API request

        
        data = response.json()        # Parsing the response to JSON
        
        if to_currency in data["conversion_rates"]:  # Check if to_currency is in rate to avoid Keyerror
            rate = data["conversion_rates"][to_currency]
            converted_amount = amount * rate
            print(f"{amount} {from_currency} = {converted_amount:.2f} {to_currency}")
        else:
            print(f"Conversion rate for {to_currency} not found.")
        
    except requests.RequestException as e:
        print(f"Error: {e}")  # Print the error message if the request fails

amount = float(input("Enter amount: "))
from_currency = input("From currency (e.g., USD): ").upper()
to_currency = input("To currency (e.g., EUR): ").upper()
convert_currency(amount, from_currency, to_currency)

