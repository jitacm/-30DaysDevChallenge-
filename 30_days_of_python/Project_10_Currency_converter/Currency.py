import requests

def convert_currency(amount, from_currency, to_currency):
Feat/190-detailed-age-cal

 Feature/add-error-handling-http-requests
    api_key = "your_api_key_here"  # Use API key directly in the URL string
    url = f"https://v6.exchangerate-api.com/v6/{api_key}/latest/{from_currency}"
    
    try:
        response = requests.get(url)  # Making the single HTTP request
        response.raise_for_status()   # Check if the request was successful
        

        data = response.json()        # Parsing the response to JSON
        
        # Check status and parse JSON separately to handle errors clearly.
        # The (response.status_code == 200 block was) removed as response.raise_for_status() handles it.
        
        if to_currency in data["conversion_rates"]:  # Check if to_currency is in the conversion rates
            rate = data["conversion_rates"][to_currency]
            converted_amount = amount * rate
            print(f"{amount} {from_currency} = {converted_amount:.2f} {to_currency}")
        else:
            print(f"Conversion rate for {to_currency} not found.")
       
 main
    api_key = "23d7f3f047f426e2f46cda57"
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
            
 main
    except requests.RequestException as e:
        print(f"Error: {e}")  # Print the error message if the request fails

amount = float(input("Enter amount: "))
from_currency = input("From currency (e.g., USD): ").upper()
to_currency = input("To currency (e.g., EUR): ").upper()
convert_currency(amount, from_currency, to_currency)