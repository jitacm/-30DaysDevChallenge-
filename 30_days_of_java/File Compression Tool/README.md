# File Compression Tool

## Overview

The File Compression Tool is a desktop application developed in Java that allows users to compress and decompress various types of files. It features a user-friendly GUI and supports text files, image files, audio files, and document files. The application utilizes different compression techniques depending on the file type.

![Screenshot 2024-08-13 210415](https://github.com/user-attachments/assets/378678bf-e36c-4fcd-a9d3-c7f1763037e9)

## Features

- **Text File Compression**: Compress `.txt` files using Huffman coding.
- **Image File Compression**: Compress `.png`, `.jpg`, and `.bmp` files using ZIP compression.
- **Audio File Compression**: Compress `.wav` and `.mp3` files using ZIP compression.
- **Document File Compression**: Compress `.pdf` and `.docx` files using ZIP compression.
- **Decompression**: Decompress files that were compressed by this tool.

## File Formats Supported

- **Text Files**: `.txt`
- **Image Files**: `.png`, `.jpg`, `.bmp`
- **Audio Files**: `.wav`, `.mp3`
- **Document Files**: `.pdf`, `.docx`

## Installation and Setup

### Prerequisites

- **Java Development Kit (JDK)**: Make sure you have JDK 8 or later installed.
- **Apache Maven**: (Optional) For managing dependencies.

### Installation Steps

1. **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd file-compression-tool
    ```

2. **Compile the project**:
    ```bash
    javac FileCompressionTool.java CompressionHandler.java HuffmanCompression.java ImageCompression.java AudioCompression.java DocumentCompression.java ZipCompression.java
    ```

3. **Run the application**:
    ```bash
    java FileCompressionTool
    ```

## Usage

1. **Launch the application**: Double-click the `.jar` file or run the application from the terminal.
2. **Select a file**: Click the "Select File" button to choose a file you want to compress or decompress.
3. **Compress**: After selecting a file, click "Compress" to compress the file.
4. **Decompress**: Select a compressed file and click "Decompress" to restore the original file.

## Project Structure

- **`FileCompressionTool.java`**: Main class, manages the GUI and application flow.
- **`CompressionHandler.java`**: Routes compression tasks based on the file type.
- **`HuffmanCompression.java`**: Handles compression for text files.
- **`ImageCompression.java`**: Handles image file compression.
- **`AudioCompression.java`**: Handles audio file compression.
- **`DocumentCompression.java`**: Handles document file compression.
- **`ZipCompression.java`**: Utility class for ZIP compression.

## Acknowledgments

- **Apache PDFBox**: For handling PDF files.
- **Apache POI**: For handling Microsoft Office documents.
- **JLayer**: For managing MP3 files.
