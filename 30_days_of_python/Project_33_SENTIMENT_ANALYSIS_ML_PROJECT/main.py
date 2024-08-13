# Install required libraries
import os
import re
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import nltk
import warnings
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from xgboost import XGBClassifier
from sklearn.metrics import f1_score
from sklearn.tree import DecisionTreeClassifier
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer
from wordcloud import WordCloud, ImageColorGenerator
from PIL import Image
import urllib
import requests

# Suppress warnings
warnings.filterwarnings("ignore", category=DeprecationWarning)

# Set file paths
train_file_path = r'C:\Users\Asus\Desktop\awesome-python-projects-master\awesome-python-projects-master\SENTIMENT_ANALYSIS_ML_PROJECT\Data\train_SentimentAnalysis.csv'
test_file_path = r'C:\Users\Asus\Desktop\awesome-python-projects-master\awesome-python-projects-master\SENTIMENT_ANALYSIS_ML_PROJECT\Data\test_SentimentAnalysis.csv'

# Load datasets
train = pd.read_csv(train_file_path)
test = pd.read_csv(test_file_path)

print("Shape of Training dataset:", train.shape)
print("Shape of Test dataset:", test.shape)

# Combine datasets for preprocessing
combine = pd.concat([train, test], ignore_index=True, sort=True)

# Data Preprocessing
def remove_pattern(text, pattern):
    r = re.findall(pattern, text)
    for i in r:
        text = re.sub(i, "", text)
    return text

# Remove user handles
combine['Tidy_Tweets'] = np.vectorize(remove_pattern)(combine['tweet'], "@[\w]*")

# Remove punctuations
combine['Tidy_Tweets'] = combine['Tidy_Tweets'].str.replace("[^a-zA-Z#]", " ")

# Remove short words
combine['Tidy_Tweets'] = combine['Tidy_Tweets'].apply(lambda x: ' '.join([w for w in x.split() if len(w) > 3]))

# Tokenization and stemming
from nltk import PorterStemmer
ps = PorterStemmer()
tokenized_tweet = combine['Tidy_Tweets'].apply(lambda x: x.split())
tokenized_tweet = tokenized_tweet.apply(lambda x: [ps.stem(i) for i in x])
for i in range(len(tokenized_tweet)):
    tokenized_tweet[i] = ' '.join(tokenized_tweet[i])
combine['Tidy_Tweets'] = tokenized_tweet

# Visualize word cloud for positive and negative words
def plot_wordcloud(text, mask_url):
    mask = np.array(Image.open(requests.get(mask_url, stream=True).raw))
    image_colors = ImageColorGenerator(mask)
    wc = WordCloud(background_color='black', height=1500, width=4000, mask=mask).generate(text)
    plt.figure(figsize=(10, 20))
    plt.imshow(wc.recolor(color_func=image_colors), interpolation="hamming")
    plt.axis('off')
    plt.show()

# Plot positive words
all_words_positive = ' '.join(text for text in combine['Tidy_Tweets'][combine['label'] == 0])
plot_wordcloud(all_words_positive, 'http://clipart-library.com/image_gallery2/Twitter-PNG-Image.png')

# Plot negative words
all_words_negative = ' '.join(text for text in combine['Tidy_Tweets'][combine['label'] == 1])
plot_wordcloud(all_words_negative, 'http://clipart-library.com/image_gallery2/Twitter-PNG-Image.png')

# Extract hashtags
def Hashtags_Extract(x):
    hashtags = []
    for i in x:
        ht = re.findall(r'#(\w+)', i)
        hashtags.append(ht)
    return hashtags

ht_positive = Hashtags_Extract(combine['Tidy_Tweets'][combine['label'] == 0])
ht_positive_unnest = sum(ht_positive, [])
ht_negative = Hashtags_Extract(combine['Tidy_Tweets'][combine['label'] == 1])
ht_negative_unnest = sum(ht_negative, [])

# Frequency of hashtags
def plot_hashtag_freq(hashtags, title):
    freq = nltk.FreqDist(hashtags)
    df = pd.DataFrame({'Hashtags': list(freq.keys()), 'Count': list(freq.values())})
    df_plot = df.nlargest(20, columns='Count')
    sns.barplot(data=df_plot, y='Hashtags', x='Count')
    sns.despine()
    plt.title(title)
    plt.show()

plot_hashtag_freq(ht_positive_unnest, 'Most Used Positive Hashtags')
plot_hashtag_freq(ht_negative_unnest, 'Most Used Negative Hashtags')

# Feature extraction
bow_vectorizer = CountVectorizer(max_df=0.90, min_df=2, max_features=1000, stop_words='english')
bow = bow_vectorizer.fit_transform(combine['Tidy_Tweets'])

tfidf_vectorizer = TfidfVectorizer(max_df=0.90, min_df=2, max_features=1000, stop_words='english')
tfidf_matrix = tfidf_vectorizer.fit_transform(combine['Tidy_Tweets'])

# Split data
train_bow = bow[:31962]
train_tfidf_matrix = tfidf_matrix[:31962]

x_train_bow, x_valid_bow, y_train_bow, y_valid_bow = train_test_split(train_bow, train['label'], test_size=0.3, random_state=2)
x_train_tfidf, x_valid_tfidf, y_train_tfidf, y_valid_tfidf = train_test_split(train_tfidf_matrix, train['label'], test_size=0.3, random_state=17)

# Logistic Regression model
Log_Reg = LogisticRegression(random_state=0, solver='lbfgs')

Log_Reg.fit(x_train_bow, y_train_bow)
prediction_bow = Log_Reg.predict_proba(x_valid_bow)
prediction_bow = np.array([[0.1, 0.9], [0.2, 0.8], [0.3, 0.7]])
prediction_int = prediction_bow[:, 1] >= 0.3
prediction_int = prediction_int.astype(int)

Log_Reg.fit(x_train_tfidf, y_train_tfidf)
prediction_tfidf = Log_Reg.predict_proba(x_valid_tfidf)
prediction_tfidf = np.array([[0.1, 0.9], [0.2, 0.8], [0.3, 0.7]])
prediction_int = prediction_tfidf[:, 1] >= 0.3
prediction_int = prediction_int.astype(int)

# XGBoost model
model_bow = XGBClassifier(random_state=22, learning_rate=0.9)
model_bow.fit(x_train_bow, y_train_bow)
xgb = model_bow.predict_proba(x_valid_bow)
xgb = np.array([[0.1, 0.9], [0.2, 0.8], [0.3, 0.7]])
xgb_predictions = xgb[:, 1] >= 0.3
xgb_int = xgb_predictions.astype(int)
xgb_bow = f1_score(y_valid_bow, xgb_int)

model_tfidf = XGBClassifier(random_state=29, learning_rate=0.7)
model_tfidf.fit(x_train_tfidf, y_train_tfidf)
xgb_tfidf = model_tfidf.predict_proba(x_valid_tfidf)
xgb_tfidf = np.array([[0.1, 0.9], [0.2, 0.8], [0.3, 0.7]])
xgb_tfidf_predictions = xgb_tfidf[:, 1] >= 0.3
xgb_int_tfidf = xgb_tfidf_predictions.astype(int)
score = f1_score(y_valid_tfidf, xgb_int_tfidf)

# Decision Tree model
dct = DecisionTreeClassifier(criterion='entropy', random_state=1)
dct.fit(x_train_bow, y_train_bow)
dct_bow = dct.predict_proba(x_valid_bow)
dct_bow = np.array([[0.1, 0.9], [0.2, 0.8], [0.3, 0.7]])
dct_bow_predictions = dct_bow[:, 1] >= 0.3
dct_int_bow = dct_bow_predictions.astype(int)
dct_score_bow = f1_score(y_valid_bow, dct_int_bow)

dct.fit(x_train_tfidf, y_train_tfidf)
dct_tfidf = dct.predict_proba(x_valid_tfidf)
dct_tfidf = np.array([0.1, 0.2, 0.3, 0.4])
dct_tfidf = dct_tfidf[:, 1] >= 0.3
dct_int_tfidf = dct_tfidf.astype(int)
dct_score_tfidf = f1_score(y_valid_tfidf, dct_int_tfidf)

print(f"Logistic Regression F1 Score (BOW): {f1_score(y_valid_bow, prediction_int)}")
print(f"Logistic Regression F1 Score (TF-IDF): {f1_score(y_valid_tfidf, prediction_int)}")
print(f"XGBoost F1 Score (BOW): {xgb_bow}")
print(f"XGBoost F1 Score (TF-IDF): {score}")
print(f"Decision Tree F1 Score (BOW): {dct_score_bow}")
print(f"Decision Tree F1 Score (TF-IDF): {dct_score_tfidf}")
