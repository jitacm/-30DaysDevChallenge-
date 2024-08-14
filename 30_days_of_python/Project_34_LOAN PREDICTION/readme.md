# Loan Prediction System

## Overview
This project is a Loan Prediction System that uses various machine learning models to predict whether a loan will be approved or not based on certain features such as gender, marital status, income, credit history, and more. The project involves data preprocessing, exploratory data analysis (EDA), feature scaling, model training, and evaluation.

## Table of Contents
- [Project Overview](#overview)
- [Dependencies](#dependencies)
- [Data](#data)
- [Exploratory Data Analysis](#exploratory-data-analysis)
- [Data Preprocessing](#data-preprocessing)
- [Model Training](#model-training)
- [Model Evaluation](#model-evaluation)
- [Final Model Selection](#final-model-selection)
- [How to Use](#how-to-use)
- [Results](#results)

## Dependencies
The following Python libraries are required to run this project:
- numpy
- pandas
- matplotlib
- seaborn
- scikit-learn

## Install the required dependencies using the following command:

pip install numpy pandas matplotlib seaborn scikit-learn


## Data 

The dataset used in this project contains information about loan applicants, including features like Gender, Married, Dependents, Education, Self_Employed, ApplicantIncome, LoanAmount, and more. The target variable is Loan_Status, indicating whether the loan was approved (Y) or not (N).

## Exploratory Data Analysis
## The dataset is analyzed using various visualizations:

Count Plots: For categorical variables like Gender, Married, Education, etc., to see their distribution concerning Loan_Status.
Box Plots: To understand the relationship between continuous variables like ApplicantIncome and LoanAmount with Loan_Status.
Heatmap: To visualize correlations between the features.
Data Preprocessing
Handling Missing Values: Missing values in Credit_History are filled based on Loan_Status.
Encoding Categorical Variables: Categorical features are encoded using Label Encoding.
Feature Scaling: ApplicantIncome and CoapplicantIncome are scaled using MinMaxScaler.

## Model Training
The following machine learning models were trained on the preprocessed data:

Logistic Regression
Support Vector Machine (SVM)
Decision Tree
Random Forest Classifier
Naive Bayes (Multinomial NB)
K-Nearest Neighbors (KNN)

## Model Evaluation
The models were evaluated using the following metrics:

Accuracy
F1 Score
Log Loss
Precision
Recall

## Final Model Selection
Based on the evaluation metrics, the Support Vector Machine (SVM) model was selected as the final model, as it outperformed the other models.

## How to Use
Clone the repository.
Ensure all dependencies are installed.
Run the notebook or script to preprocess the data, train the models, and evaluate the performance.
The final trained model (SVM) is saved as model_svm.pkl and can be loaded for predictions.

## Results
The Support Vector Machine (SVM) model performed the best among all the models tested, with the highest accuracy and F1 score. This model is suitable for predicting loan approvals based on the given features.