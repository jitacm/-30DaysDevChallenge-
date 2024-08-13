# Stock Price Prediction Project

This project implements a stock price prediction model using LSTM (Long Short-Term Memory) networks. The goal is to predict future stock prices based on historical data.

## Requirements

To run this project, you need to install the following Python libraries:

- NumPy
- Pandas
- Matplotlib
- Keras

You can install these libraries using the following commands:

## Code Overview
## Data Preparation:

The dataset is read from a CSV file named google_dataset.csv.
The closing prices are extracted and normalized using MinMaxScaler.
Data is transformed into time series with a window size of 100 days.
## Model Definition:

A Bidirectional LSTM model is defined using Keras.
The model architecture includes two Bidirectional LSTM layers followed by Dense layers.
## Training and Evaluation:

The model is trained on 70% of the data and evaluated on the remaining 30%.
The model is saved as model.h5.
## Prediction:

Predictions are made on the test data, and results are plotted to compare predicted vs. actual values.
## How to Run
Ensure you have all the required libraries installed.
Place the google_dataset.csv file in the same directory as the Jupyter Notebook.
Run the Jupyter Notebook cells sequentially to execute the code.