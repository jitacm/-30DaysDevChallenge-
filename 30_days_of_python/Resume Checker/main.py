from flask import Flask, request, render_template, jsonify
from werkzeug.utils import secure_filename
import os
import re
import nltk
from spellchecker import SpellChecker
from nltk.tokenize import word_tokenize
from PyPDF2 import PdfReader
from docx import Document

nltk.download('punkt')

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = 'uploads/'

if not os.path.exists(app.config['UPLOAD_FOLDER']):
    os.makedirs(app.config['UPLOAD_FOLDER'])

spell = SpellChecker()

# A set of common names and technical terms
common_names_and_terms = set([
    "Bharat", "Sahil", "Tanishq", "Python", "Java", "SQL", "HTML", "CSS", "JavaScript", "AWS", "Azure", "GCP",
    "TensorFlow", "PyTorch", "NumPy", "Pandas", "scikit-learn", "GitHub", "Linux", "Docker", "Kubernetes", "Vivek"
    "Figma", "CMS", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", 
    "November", "December", "Nagpur", "Wordpress","Internpe", "Rupesh", "Ramesh", "Suresh", "Anshun", "Rutuj", "Taklikar"
    "Rajkumar", "Rao", "Bahadure", "Bhardwaj", "Bansod","Meshram", "Dudhani", "Mohammad", "Ansari", "Kale", "Nasre", 
    "Kharapkar", "Patankar"])

def extract_text_from_pdf(file_path):
    text = ""
    with open(file_path, 'rb') as file:
        reader = PdfReader(file)
        for page in reader.pages:
            text += page.extract_text()
    return text

def extract_text_from_docx(file_path):
    doc = Document(file_path)
    text = '\n'.join([para.text for para in doc.paragraphs])
    return text

def analyze_resume(file_path, file_extension):
    if file_extension == '.pdf':
        content = extract_text_from_pdf(file_path)
    elif file_extension == '.docx':
        content = extract_text_from_docx(file_path)
    else:
        with open(file_path, 'r') as file:
            content = file.read()
    
    mistakes = []

    # Check for missing contact information
    if not re.search(r'\b\d{10}\b', content) or not re.search(r'\b[\w.-]+?@\w+?\.\w+?\b', content):
        mistakes.append("Missing contact information (phone number or email).")

    # Check for spelling errors
    tokens = word_tokenize(content)
    misspelled_words = [
        word for word in tokens 
        if word.isalpha() 
        and word not in common_names_and_terms 
        and spell.correction(word) != word
    ]
    if misspelled_words:
        mistakes.append("Spelling errors found: " + ", ".join(misspelled_words[:5]))  # Display up to 5 spelling mistakes

    # Check for lack of section headings
    required_headings = ["Education", "Experience", "Skills"]
    missing_headings = [heading for heading in required_headings if heading.lower() not in content.lower()]
    if missing_headings:
        mistakes.append("Missing section headings: " + ", ".join(missing_headings))

    # Limit to top 3 mistakes
    return mistakes[:3]

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/upload', methods=['POST'])
def upload_file():
    if 'file' not in request.files:
        return jsonify({'error': 'No file part'})

    file = request.files['file']
    if file.filename == '':
        return jsonify({'error': 'No selected file'})

    if file:
        filename = secure_filename(file.filename)
        file_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(file_path)
        file_extension = os.path.splitext(filename)[1].lower()
        mistakes = analyze_resume(file_path, file_extension)
        return jsonify({'mistakes': mistakes})

if __name__ == '__main__':
    app.run(debug=True)
