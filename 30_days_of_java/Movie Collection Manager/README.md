# Movie Collection Manager

## Description

Movie Collection Manager is a Java-based desktop application that allows users to catalog, rate, review, and track their personal movie collections. It provides an intuitive interface for managing movie libraries and includes features for lending physical media.

![Screenshot 2024-08-14 004429](https://github.com/user-attachments/assets/1e7af722-2ff4-4a7e-a118-e92851855132)

## Features

- Add movies to your collection with title, rating, and review
- View all movies in an easy-to-read list format
- Remove movies from your collection
- Rate and review your movies
- Track lending of physical media (record who borrowed which movie)
- Data persistence using Java Serialization (your collection is saved automatically)

## Installation

1. Ensure you have Java Development Kit (JDK) 8 or higher installed on your system.

2. Clone this repository:
`git clone <repository-url>`

3. Navigate to the project directory:
`cd movie-collection-manager`

4. Compile the Java files:
`javac MovieCollectionManager.java`

5. Run the application:
`java MovieCollectionManager`

## Usage

1. **Adding a movie**: Fill in the title, rating, and review fields, then click "Add Movie".
2. **Removing a movie**: Select a movie from the list and click "Remove Movie".
3. **Lending a movie**: Select a movie, enter the borrower's name in the "Lent To" field, and click "Lend Movie".
4. **Returning a movie**: Select a lent movie and click "Return Movie".
5. **Saving your collection**: Click "Save Collection" or simply close the application (it saves automatically on exit).
