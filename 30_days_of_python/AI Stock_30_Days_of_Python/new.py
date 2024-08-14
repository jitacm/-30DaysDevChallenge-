from tensorflow.keras.models import load_model
import numpy as np

# Load the LSTM model
model = load_model('models/lstm_stock_predictor.h5')

# Print model summary
model.summary()

# Example input data: 3 samples, 10 timesteps, 1 feature
input_data = np.random.rand(1, 10, 1)

# Make predictions
predictions = model.predict(input_data)

# Print the predictions
print("Predictions:")
print(predictions)
