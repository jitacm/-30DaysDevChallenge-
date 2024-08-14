from flask import Flask, request, jsonify
from tensorflow.keras.models import load_model
import numpy as np

app = Flask(__name__)

# Load the trained model
model = load_model('models/lstm_stock_predictor.h5')

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json(force=True)
    # Example preprocessing - modify based on your needs
    preprocessed_data = np.array(data).reshape(1, -1)  # Adjust as needed
    prediction = model.predict(preprocessed_data)
    return jsonify({'prediction': prediction.tolist()})

if __name__ == '__main__':
    app.run(debug=True)
