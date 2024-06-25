import numpy as np
import tensorflow as tf
from sklearn.datasets import make_classification
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from tensorflow.keras.utils import to_categorical
import skfuzzy as fuzz
from skfuzzy import control as ctrl

# Set a random seed for reproducibility
np.random.seed(42)

# Generate a synthetic dataset
n_features = 100  # 10x10 'image'
n_samples = 1000
n_classes = 10

X, y = make_classification(n_samples=n_samples, n_features=n_features,
                           n_informative=int(n_features*0.6), n_redundant=int(n_features*0.1),
                           n_classes=n_classes, random_state=42)

# Reshape into 2D 'image' format for CNN
X_reshaped = X.reshape(-1, 10, 10, 1)

# Normalize the data
scaler = StandardScaler()
X_flat = X_reshaped.reshape(-1, n_features)
X_scaled_flat = scaler.fit_transform(X_flat)
X_scaled = X_scaled_flat.reshape(-1, 10, 10, 1)

# Convert class vectors to binary class matrices (one-hot encoding)
y_categorical = to_categorical(y, n_classes)

# Split the dataset into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X_scaled, y_categorical, test_size=0.2, random_state=42)

# Define an improved CNN model
def build_cnn_model(input_shape, num_classes):
    model = tf.keras.Sequential([
        tf.keras.layers.Conv2D(filters=64, kernel_size=(3, 3), activation='relu', input_shape=input_shape),
        tf.keras.layers.BatchNormalization(),
        tf.keras.layers.MaxPooling2D(pool_size=(2, 2)),
        tf.keras.layers.Conv2D(filters=128, kernel_size=(3, 3), activation='relu'),
        tf.keras.layers.BatchNormalization(),
        tf.keras.layers.MaxPooling2D(pool_size=(2, 2)),
        tf.keras.layers.Flatten(),
        tf.keras.layers.Dense(256, activation='relu'),
        tf.keras.layers.Dropout(0.5),
        tf.keras.layers.Dense(num_classes, activation='softmax')
    ])

    # Compile the model
    model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.001),
                  loss='categorical_crossentropy',
                  metrics=['accuracy'])

    return model

# Build the model
cnn_model = build_cnn_model(X_train.shape[1:], n_classes)

# Define data augmentation
datagen = tf.keras.preprocessing.image.ImageDataGenerator(
    width_shift_range=0.1,
    height_shift_range=0.1,
    horizontal_flip=True)

# Train the model with data augmentation
# Train the model with data augmentation
# Calculate validation steps
validation_steps = int(0.1 * X_train.shape[0] / 32)  # 10% of training data, batch size 32

cnn_model.fit(datagen.flow(X_train, y_train, batch_size=32),
              epochs=30,
              validation_data=(X_test, y_test),  # Use the test set as validation data
              validation_steps=validation_steps,  # Specify validation steps
              callbacks=[
                  tf.keras.callbacks.EarlyStopping(monitor='val_loss', patience=5, restore_best_weights=True),
                  tf.keras.callbacks.ReduceLROnPlateau(monitor='val_loss', factor=0.5, patience=3)
              ])

# Evaluate the model on the test data
scores = cnn_model.evaluate(X_test, y_test, verbose=0)

print('Test loss:', scores[0])
print('Test accuracy:', scores[1])

# Define the Mamdani Fuzzy Inference System
def integrate_mamdani_with_cnn(cnn_output):
    # Assuming cnn_output is a single prediction (output of softmax layer)
    # You may need to adapt this based on your fuzzy logic requirements

    # Get the confidence as the maximum probability from the CNN output
    confidence_value = np.max(cnn_output)

    # Define input and output variables for the fuzzy system
    confidence = ctrl.Antecedent(np.arange(0, 1.1, 0.1), 'confidence')
    action = ctrl.Consequent(np.arange(0, 1.1, 0.1), 'action')

    # Define fuzzy membership functions for input and output variables
    confidence['low'] = fuzz.trimf(confidence.universe, [0, 0, 0.5])
    confidence['medium'] = fuzz.trimf(confidence.universe, [0, 0.5, 1])
    confidence['high'] = fuzz.trimf(confidence.universe, [0.5, 1, 1])

    action['stay'] = fuzz.trimf(action.universe, [0, 0, 0.5])
    action['move'] = fuzz.trimf(action.universe, [0, 0.5, 1])

    # Define fuzzy rules
    rule1 = ctrl.Rule(confidence['low'], action['stay'])
    rule2 = ctrl.Rule(confidence['medium'], action['move'])
    rule3 = ctrl.Rule(confidence['high'], action['move'])

    # Create a fuzzy control system
    fuzzy_ctrl = ctrl.ControlSystem([rule1, rule2, rule3])
    fuzzy_system = ctrl.ControlSystemSimulation(fuzzy_ctrl)

    # Set the input value based on the CNN output
    fuzzy_system.input['confidence'] = confidence_value

    # Perform fuzzy inference
    fuzzy_system.compute()

    # Get the defuzzified output
    defuzzified_output = fuzzy_system.output['action']

    return defuzzified_output

# Example usage with a single CNN output
cnn_output_example = cnn_model.predict(np.expand_dims(X_test[0], axis=0))[0]
fuzzy_output = integrate_mamdani_with_cnn(cnn_output_example)

print('CNN Output:', cnn_output_example)
print('Fuzzy System Output:', fuzzy_output)
