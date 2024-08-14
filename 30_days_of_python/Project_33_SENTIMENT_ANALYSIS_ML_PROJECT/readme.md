# Sentiment Analysis of Twitter Data

## Overview

This project performs sentiment analysis on Twitter data using various machine learning models and techniques. The goal is to classify tweets as either positive or negative based on their sentiment. The analysis involves preprocessing tweets, extracting features using Bag-of-Words (BoW) and TF-IDF, and evaluating models such as Logistic Regression, XGBoost, and Decision Trees.

## Features

- **Data Loading**:
  - Loads and combines training and test datasets from CSV files.

- **Data Preprocessing**:
  - Cleans tweets by removing user handles, punctuations, and short words.
  - Tokenizes and stems the text data.
  - Extracts hashtags and visualizes frequent positive and negative hashtags.

- **Text Vectorization**:
  - Converts text data into numerical features using Bag-of-Words and TF-IDF representations.

- **Machine Learning Models**:
  - **Logistic Regression**: Trained using both BoW and TF-IDF features.
  - **XGBoost**: Trained using both BoW and TF-IDF features.
  - **Decision Tree**: Trained using both BoW and TF-IDF features.

- **Model Evaluation**:
  - Computes F1 scores to assess model performance.
  - Compares different models based on their F1 scores and visualizes the results.

- **Visualization**:
  - Generates word clouds for positive and negative words.
  - Creates bar plots for the most frequent hashtags.
  - Plots F1 scores for model comparison.

- **Prediction**:
  - Applies the best-performing model to predict sentiments on the test dataset.
  - Saves predictions in a DataFrame for submission.

## Installation

To install the required libraries, run the following command:

```bash
pip install pandas numpy matplotlib seaborn nltk scikit-learn xgboost wordcloud


Usage
Load Data:

Place your train_SentimentAnalysis.csv and test_SentimentAnalysis.csv files in the Data directory.
Run the Analysis:

Execute the Jupyter Notebook or Python script to perform data preprocessing, feature extraction, model training, evaluation, and prediction.
View Results:

Check the visualizations and F1 scores for model performance.
Review the final predictions for the test dataset.