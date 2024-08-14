# Import necessary libraries
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.preprocessing import LabelEncoder, MinMaxScaler
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.naive_bayes import MultinomialNB
from sklearn.neighbors import KNeighborsClassifier
import sklearn.metrics as m
import pickle

# Load Dataset
data = pd.read_csv('dataset.csv')

# Drop the 'Loan_ID' column as it is not needed for prediction
data.drop(['Loan_ID'], axis=1, inplace=True)

# Exploratory Data Analysis (EDA)
print(data.info())
print(data.describe())
print(data.isnull().sum())

# Visualizations
sns.countplot(x='Loan_Status', hue='Gender', data=data)
plt.title('Loan Status by Gender')
plt.show()

sns.countplot(x='Loan_Status', hue='Married', data=data)
plt.title('Loan Status by Marital Status')
plt.show()

sns.countplot(x='Loan_Status', hue='Dependents', data=data)
plt.title('Loan Status by Number of Dependents')
plt.show()

sns.countplot(x='Loan_Status', hue='Education', data=data)
plt.title('Loan Status by Education')
plt.show()

sns.countplot(x='Loan_Status', hue='Self_Employed', data=data)
plt.title('Loan Status by Self Employment')
plt.show()

sns.boxplot(x='Loan_Status', y='ApplicantIncome', data=data)
plt.title('Applicant Income by Loan Status')
plt.show()

sns.jointplot(x='ApplicantIncome', y='LoanAmount', data=data, kind='scatter')
plt.title('Applicant Income vs Loan Amount')
plt.show()

# Correlation Heatmap
data_encoded = pd.get_dummies(data, drop_first=True)
corr = data_encoded.corr()
sns.heatmap(corr, annot=True, cmap='coolwarm')
plt.title('Correlation Heatmap')
plt.show()

# Handle Missing Values
loan_credit_1Y = data['Loan_Status'] == 'Y'
loan_credit_0N = data['Loan_Status'] == 'N'

data.loc[loan_credit_1Y, 'Credit_History'] = data.loc[loan_credit_1Y, 'Credit_History'].fillna(1.0)
data.loc[loan_credit_0N, 'Credit_History'] = data.loc[loan_credit_0N, 'Credit_History'].fillna(0.0)
data.dropna(inplace=True, axis=0)

print(data.isnull().sum())  # Ensure no missing values remain

# Data Encoding
le = LabelEncoder()

categorical_columns = ['Gender', 'Married', 'Dependents', 'Education', 'Self_Employed', 'Property_Area', 'Loan_Status', 'Credit_History', 'Loan_Amount_Term']
for column in categorical_columns:
    data[column] = le.fit_transform(data[column])

print(data.head())

# MinMax Scaling
mms = MinMaxScaler()
data['ApplicantIncome'] = mms.fit_transform(np.array(data['ApplicantIncome']).reshape(-1, 1))
data['CoapplicantIncome'] = mms.fit_transform(np.array(data['CoapplicantIncome']).reshape(-1, 1))

# Splitting The Data
X = data.drop(['Loan_Status'], axis=1)
y = data['Loan_Status']
x_train, x_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Model Preparation
models = {
    'Logistic Regression': LogisticRegression(),
    'Support Vector Machine': SVC(),
    'Decision Tree': DecisionTreeClassifier(),
    'Random Forest Classifier': RandomForestClassifier(),
    'Multinomial NB': MultinomialNB(),
    'K-Nearest Neighbors': KNeighborsClassifier()
}

y_predictions_all = pd.DataFrame()
y_predict = []

for model_name, model in models.items():
    model.fit(x_train, y_train)
    y_pred = model.predict(x_test)
    y_predictions_all[model_name] = y_pred

# Metrics Measurement
metrics = []
for model_name in models.keys():
    y_pred = y_predictions_all[model_name]
    metrics.append([
        m.accuracy_score(y_test, y_pred),
        m.f1_score(y_test, y_pred),
        m.log_loss(y_test, y_pred),
        m.precision_score(y_test, y_pred),
        m.recall_score(y_test, y_pred)
    ])

metric_names = ['accuracy', 'f1 score', 'logloss', 'precision', 'recall']
metric_models = pd.DataFrame(metrics, columns=metric_names, index=models.keys())
print(metric_models)

# Final Evaluation and Saving Model
print("Final Evaluation shows that SVM Performs the best.")

# Save the best performing model (SVM) to a file
best_model = models['Support Vector Machine']
pickle.dump(best_model, open("model_svm.pkl", "wb+"), protocol=pickle.HIGHEST_PROTOCOL)

