import pandas as pd
import matplotlib.pyplot as plt
import yfinance as yf

def get_stock_data(ticker):
    stock = yf.Ticker(ticker)
    return stock.history(period="1y")

def plot_stock_data(stock_data):
    stock_data['Close'].plot(figsize=(10, 5))
    plt.title('Stock Price Over Time')
    plt.xlabel('Date')
    plt.ylabel('Close Price')
    plt.show()

ticker = input("Enter stock ticker: ")
stock_data = get_stock_data(ticker)
plot_stock_data(stock_data)
