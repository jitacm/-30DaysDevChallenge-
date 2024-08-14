import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import MinMaxScaler
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense, Dropout

# Load and preprocess data
data = pd.read_csv('csv/stock_data.csv', index_col='Date', parse_dates=True)
data['50_MA'] = data['Close'].rolling(window=50).mean()
data['200_MA'] = data['Close'].rolling(window=200).mean()
data = data.dropna()

scaler = MinMaxScaler(feature_range=(0, 1))
scaled_data = scaler.fit_transform(data[['Close', '50_MA', '200_MA']])

# Create dataset
def create_dataset(dataset, time_step=1):
    X, y = [], []
    for i in range(len(dataset) - time_step - 1):
        X.append(dataset[i:(i + time_step), :])
        y.append(dataset[i + time_step, 0])
    return np.array(X), np.array(y)

time_step = 60
X, y = create_dataset(scaled_data, time_step)
X = X.reshape(X.shape[0], X.shape[1], X.shape[2])

# Build LSTM model
model = Sequential()
model.add(LSTM(units=50, return_sequences=True, input_shape=(X.shape[1], X.shape[2])))
model.add(Dropout(0.2))
model.add(LSTM(units=50, return_sequences=False))
model.add(Dropout(0.2))
model.add(Dense(units=25))
model.add(Dense(units=1))

model.compile(optimizer='adam', loss='mean_squared_error')

# Train model
model.fit(X, y, batch_size=64, epochs=50)
model.save('models/lstm_stock_predictor.h5')

# Evaluate model
model = tf.keras.models.load_model('models/lstm_stock_predictor.h5')

test_data = scaled_data[-len(X) - time_step:]
X_test, y_test = create_dataset(test_data, time_step)
X_test = X_test.reshape(X_test.shape[0], X_test.shape[1], X_test.shape[2])

predictions = model.predict(X_test)
predictions = scaler.inverse_transform(predictions)

# Plot results
plt.figure(figsize=(14,7))
plt.plot(data.index[-len(predictions):], data['Close'].iloc[-len(predictions):], label='Actual Price')
plt.plot(data.index[-len(predictions):], predictions, label='Predicted Price')
plt.legend()
plt.show()
