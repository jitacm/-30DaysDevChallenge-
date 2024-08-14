import yfinance as yf
import os

# Download stock data for AAPL
stock_data = yf.download("AAPL", start="2023-01-01", end="2024-8-13")

# Ensure the directory exists
if not os.path.exists('csv'):
    os.makedirs('csv')

# Save the data to a CSV file
stock_data.to_csv("csv/stock_data.csv")
