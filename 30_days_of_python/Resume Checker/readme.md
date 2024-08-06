# Resume Checker

Resume Checker is a web application built with Python and Flask that helps users identify common mistakes in their resumes/CVs. Users can upload their resume in PDF, DOCX, or text formats, and the application will analyze the document for issues such as missing contact information, spelling errors, and missing section headings.

## Features

- **Upload and Analyze:** Upload resumes/CVs in PDF, DOCX, or text formats and analyze them for common mistakes.
- **Text Extraction:** Extract text content from uploaded PDF and DOCX files.
- **Mistake Identification:** Identify common issues such as missing contact information, spelling errors, and missing section headings.
- **Top 3 Mistakes:** Display the top 3 mistakes found in the document to help users improve their resumes.

## Getting Started

### Prerequisites

- Python 3.x
- Flask
- nltk
- spellchecker
- PyPDF2
- python-docx

### Installation

1. **Clone the repository:**

    ```bash
    git clone 
    ```

2. **Navigate to the project directory:**

    ```bash
    cd resume-checker
    ```

3. **Install the required dependencies:**

    ```bash
    pip install -r requirements.txt
    ```

4. **Download NLTK data:**

    ```python
    import nltk
    nltk.download('punkt')
    ```

### Running the Application

1. **Start the Flask server:**

    ```bash
    python app.py
    ```

2. **Open your web browser and go to:**

    ```
    http://127.0.0.1:5000/
    ```

3. **Upload your resume/CV and analyze it for common mistakes.**

## Usage

1. **Upload Resume/CV:**
   - Click the "Upload" button and select your resume/CV file (PDF, DOCX, or text format).

2. **Analyze Resume/CV:**
   - The application will extract text from the uploaded file and analyze it for common mistakes.

3. **View Results:**
   - The top 3 mistakes found in the document will be displayed on the page.

## Development

### Contributing

1. **Fork the repository.**
2. **Create a new branch:**

    ```bash
    git checkout -b feature/your-feature
    ```

3. **Commit your changes:**

    ```bash
    git commit -am 'Add new feature'
    ```

4. **Push to the branch:**

    ```bash
    git push origin feature/your-feature
    ```

5. **Create a new Pull Request.**

### Running Locally

To make changes to the project:

1. **Edit the `app.py`, `templates/index.html`, and `static` files as needed.**
2. **Refresh your browser to see changes in real-time.**


## Acknowledgments

- Flask for creating the web application framework.
- NLTK and SpellChecker for text processing and spell checking.
- PyPDF2 and python-docx for extracting text from PDF and DOCX files.
- Bootstrap for styling and layout.
