# Recipe Manager

A Java-based desktop application for managing and organizing recipes.

![Screenshot 2024-08-13 005848](https://github.com/user-attachments/assets/9d8a461f-2647-4f00-94f6-96d83fdf594f)

## Features

- Store and organize recipes
- Add, edit, and delete recipes
- Search for recipes by name or ingredients
- Categorize recipes by dish type
- Persistent data storage (recipes are saved even when the app is closed)

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Java IDE (e.g., IntelliJ IDEA, Eclipse, or NetBeans)

## Installation

1. **Clone the repository:**

   `git clone <repository-url>`

2. **Open the project in your preferred Java IDE.**

3. **Build the project to resolve dependencies.**

## Usage

1. Run the `RecipeManagerApp` class to start the application.
2. Use the GUI to add, edit, delete, and search for recipes.
3. Recipes are automatically saved when you close the application.

## Project Structure

- `Recipe.java`: Data model for recipes
- `RecipeDataManager.java`: Handles data persistence
- `RecipeManagerGUI.java`: Main application window and user interface
- `RecipeManagerApp.java`: Entry point for the application
