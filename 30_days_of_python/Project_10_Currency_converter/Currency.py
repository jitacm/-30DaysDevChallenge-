import requests

def convert_currency(amount, from_currency, to_currency):
    api_key = "your_api_key_here"
    url = f"https://v6.exchangerate-api.com/v6/{api_key}/latest/{from_currency}"
    
    try:
        response = requests.get(url)  # Making the HTTP request
        response.raise_for_status()   # Check if the request was successful
        
        data = response.json()        # Parsing the response to JSON
        
        if response.status_code == 200:
            rate = data["conversion_rates"].get(to_currency)
            if rate:  # Check if the rate is available
                converted_amount = amount * rate
                print(f"{amount} {from_currency} = {converted_amount:.2f} {to_currency}")
            else:
                print(f"Conversion rate for {to_currency} not found.")
        else:
            print("Failed to retrieve exchange rates")
            
    except requests.RequestException as e:
        print(f"Error: {e}")  # Print the error message if the request fails

amount = float(input("Enter amount: "))
from_currency = input("From currency (e.g., USD): ").upper()
to_currency = input("To currency (e.g., EUR): ").upper()
convert_currency(amount, from_currency, to_currency)
