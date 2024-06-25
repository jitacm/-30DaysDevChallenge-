### **Description**
This project will demonstrates the creation and enhancement of a Convolutional Neural Network (CNN) for classifying synthetic data, formatted as 10x10 pixel 'images' into 10 distinct classes. The CNN model's output is further processed using a Mamdani Fuzzy Inference System to perform decision-making based on the model's confidence. 

### **The project will be divided into several key stages:**
**Data Generation and Preprocessing:** Creating synthetic data, reshaping, normalizing, and splitting into training and test sets.
**Model Architecture:** Designing a more complex CNN model to improve classification accuracy.
**Training with Data Augmentation:** Enhancing the model's generalization through data augmentation and advanced training techniques.
**Evaluation:** Assessing the model's performance on test data.
**Integration with Fuzzy Logic:** Implementing a Mamdani Fuzzy Inference System to make decisions based on the CNN's output.

**1. Data Generation and Preprocessing**
We generate a synthetic dataset using make_classification from sklearn.datasets, simulating 10x10 pixel images (100 features) and 10 classes. The data is then reshaped and normalized:

- Features and Samples: 100 features (10x10) and 1000 samples.
- Class Balance: Balanced across 10 classes.
- Normalization: Using StandardScaler to normalize the data.
- Reshape: Transforming the feature vectors into 10x10 'images'.
**2. Model Architecture**
The improved CNN model comprises multiple layers to enhance its capacity and performance:

- Input Layer: Accepts 10x10x1 input images.
- Convolutional Layers:
- - First Conv Layer: 64 filters, 3x3 kernel, ReLU activation.
- - Second Conv Layer: 128 filters, 3x3 kernel, ReLU activation.
- Batch Normalization: Applied after each convolutional layer to stabilize learning.
- Pooling Layers: MaxPooling with 2x2 pool size to reduce spatial dimensions.
- Flatten Layer: Converts the 2D feature maps to 1D feature vectors.
- Dense Layers:
- First Dense Layer: 256 neurons, ReLU activation.
- Dropout Layer: 50% dropout rate to prevent overfitting.
- Output Layer: 10 neurons (one for each class), softmax activation.
**3. Training with Data Augmentation**
To enhance the model's generalization ability, data augmentation is applied:

Augmentation Techniques: Horizontal flips, width, and height shifts.
Training Configuration:
Optimizer: Adam with a learning rate of 0.001.
Loss Function: Categorical cross-entropy.
Metrics: Accuracy.
Epochs: 30.
Validation Split: 10% of training data for validation.
Callbacks:
EarlyStopping to prevent overfitting by stopping training when validation loss stops improving.
ReduceLROnPlateau to reduce the learning rate when the validation loss plateaus.
**4. Evaluation**
The model's performance is evaluated on the test set:

Metrics: Test loss and test accuracy.
Results: Provides an understanding of the model's capability to generalize to unseen data.
**5. Integration with Fuzzy Logic**
A Mamdani Fuzzy Inference System is used to make decisions based on the CNN's output confidence:

Confidence Extraction: The maximum value from the CNN's softmax output is used as the confidence level.
Fuzzy Variables:
Input: Confidence, with membership functions for low, medium, and high.
Output: Action, with membership functions for stay and move.
Fuzzy Rules:
If confidence is low, then action is stay.
If confidence is medium, then action is move.
If confidence is high, then action is move.

*Inference:* The system computes the action based on the given confidence level.