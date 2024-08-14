# load_lstm_model.py
import numpy as np
from tensorflow.keras.models import load_model


# Function to load the LSTM model
def load_lstm_model(model_path):
    try:
        model = load_model(model_path)
        print("Model loaded successfully.")
        return model
    except Exception as e:
        print(f"Error loading model: {e}")
        return None


# Function to prepare the data
def prepare_data(sequence, timesteps, features):
    # Ensure the input data is in the correct shape
    try:
        data = np.array(sequence).reshape((1, timesteps, features))
        return data
    except Exception as e:
        print(f"Error preparing data: {e}")
        return None


# Function to make predictions
def make_predictions(model, data):
    if model and data is not None:
        predictions = model.predict(data)
        print("Predictions:", predictions)
    else:
        print("Model or data is not available.")


# Main execution
if __name__ == "__main__":
    # Path to your LSTM model file
    model_path = 'models/lstm_stock_predictor.h5'

    # Example sequence (replace this with your actual input data)
    # Example: a sequence of 10 timesteps with 3 features each
    sequence = [
        [0.1, 0.2, 0.3],
        [0.2, 0.3, 0.4],
        [0.3, 0.4, 0.5],
        [0.4, 0.5, 0.6],
        [0.5, 0.6, 0.7],
        [0.6, 0.7, 0.8],
        [0.7, 0.8, 0.9],
        [0.8, 0.9, 1.0],
        [0.9, 1.0, 1.1],
        [1.0, 1.1, 1.2]
    ]

    # Define the number of timesteps and features
    timesteps = len(sequence)
    features = len(sequence[0])

    # Load the model
    model = load_lstm_model(model_path)

    # Prepare the data
    data = prepare_data(sequence, timesteps, features)

    # Make predictions
    make_predictions(model, data)
