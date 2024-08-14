# Importing Necessary Libraries
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from keras.models import Sequential
from keras.layers import Dense, LSTM, Bidirectional, Dropout
from sklearn.preprocessing import MinMaxScaler

# Reading the Dataset
data = pd.read_csv('google_dataset.csv')
print(data.head())

# Extracting the Closing Price
close_price = data['close'].values
close_price = close_price.reshape(-1, 1)

# Plotting the Closing Price
plt.plot(close_price)
plt.title('Closing Price')
plt.xlabel('Days')
plt.ylabel('Price')
plt.show()

# Applying MinMax Scaler
minmax = MinMaxScaler()
close_price = minmax.fit_transform(close_price)

print("Data Min:", minmax.data_min_)
print("Data Max:", minmax.data_max_)

# Converting the data into time stamps of 100 days
data_stamps = []
price = []

for i in range(len(close_price) - 100):
    data_stamps.append(close_price[i:i+100])
    price.append(close_price[i+100])

data_stamps = np.array(data_stamps)
price = np.array(price)

print("Data Stamps Shape:", data_stamps.shape)
print("Price Shape:", price.shape)

# Splitting the data stamps into training and testing samples
split_idx = int(0.7 * len(data_stamps))

x_train = data_stamps[:split_idx]
y_train = price[:split_idx]

x_test = data_stamps[split_idx:]
y_test = price[split_idx:]

# Defining a Neural Network
model = Sequential()
model.add(Bidirectional(LSTM(100, return_sequences=True), input_shape=(100, 1)))
model.add(Bidirectional(LSTM(100)))
model.add(Dense(64, activation='relu'))
model.add(Dense(1, activation='linear'))

model.summary()

model.compile(optimizer='Adam', loss='mse')

# Training the Model
model.fit(x_train, y_train, epochs=50)

# Evaluating the Model
model.evaluate(x_test, y_test)

# Predicting the Test Data
y_pred = model.predict(x_test)

# Plotting Closing Prices: Testing vs Predicted Data
plt.plot(y_pred, label='Predicted')
plt.plot(y_test, label='Actual')
plt.title('Testing vs Predicted Data')
plt.xlabel('Days')
plt.ylabel('Price')
plt.legend()
plt.show()

# Saving the Model
model.save('model.h5')
