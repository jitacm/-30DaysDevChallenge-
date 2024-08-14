import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler

# Load data with dates as index
data = pd.read_csv('csv/stock_data.csv', index_col='Date', parse_dates=True)

# Feature Engineering
data['50_MA'] = data['Close'].rolling(window=50).mean()
data['200_MA'] = data['Close'].rolling(window=200).mean()
data = data.dropna()  # Drop NaNs

# Scale only numerical columns
scaler = MinMaxScaler(feature_range=(0, 1))
scaled_data = scaler.fit_transform(data[['Close', '50_MA', '200_MA']])

# Save preprocessed data to CSV
pd.DataFrame(scaled_data, columns=['Close', '50_MA', '200_MA'], index=data.index).to_csv('csv/preprocessed_data.csv')

