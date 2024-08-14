import pandas as pd
import matplotlib.pyplot as plt
from alpha_vantage.timeseries import TimeSeries


api_key = 'YOUR_API_KEY'
ts = TimeSeries(key=api_key, output_format='pandas')

# Fetch intraday data with a 1-minute interval
data, meta_data = ts.get_intraday(symbol='AMZN', interval='1min', outputsize='full')

# Display the first few rows of the data
print(data.head())


# Plot the closing prices
plt.figure(figsize=(14, 7))
plt.plot(data.index, data['4. close'], label='Close Price')
plt.title('Amazon (AMZN) Intraday Closing Prices')
plt.xlabel('Date')
plt.ylabel('Close Price')
plt.legend()
plt.grid(True)
plt.show()
