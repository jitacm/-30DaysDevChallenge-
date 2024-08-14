import numpy as np
import pandas as pd
import pandas_datareader as pdr
import matplotlib.pyplot as plt
import seaborn as sb
import datetime as dt
sb.set()

import yfinance as yf
import datetime as dt

start_date = dt.datetime(2020, 1, 1)
end_date = dt.datetime(2023, 12, 31)

amzn = yf.download('AMZN', start=start_date, end=end_date)

amzn.head()


# Prepare the features (X) and target (y)
X = amzn[['Open', 'High', 'Low', 'Volume']]  # Features
y = amzn['Close']  # Target

# Split the data into training and testing sets
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)


from sklearn.linear_model import LinearRegression

# Create a linear regression model
model = LinearRegression()

# Fit the model to the training data
model.fit(X_train, y_train)

# Make predictions on the testing data
y_pred = model.predict(X_test)


from sklearn.metrics import mean_squared_error, r2_score

# Calculate the Mean Squared Error
mse = mean_squared_error(y_test, y_pred)

# Calculate the R-squared value
r2 = r2_score(y_test, y_pred)

print(f'Mean Squared Error: {mse}')
print(f'R-squared: {r2}')
