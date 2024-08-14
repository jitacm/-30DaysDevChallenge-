## Exam Prep Master

Exam Prep Master is an online platform designed to help students prepare for exams by providing a variety of practice quizzes and tracking their performance over time. This repository contains the  for the application, which is built using Node.js, Express, and MongoDB.

## Features
User Authentication:

Secure user registration and login with password encryption using bcrypt.

## Score Tracking:

Track user scores for different subjects (e.g., Math, Science) and difficulty levels (easy, medium, hard).
Store and update the best scores for each user.

## User Data Management:

Retrieve individual user details by username.
Fetch all users' data for administrative purposes.

## Password Management:

Update user passwords with secure hashing.

## Getting Started
## Prerequisites
Node.js installed on your machine.
MongoDB database setup.
A .env file with the following environment variables:
 
MONGO_URL=<your-mongodb-url>
PORT=<your-preferred-port>
## Installation
Clone this repository:
 
git clone 

## Navigate to the project directory:
 
cd exam-prep-master
## Install the required dependencies:
 
npm install 

## Running the Application
Start the server with the following command:

 
npm run dev
The server will run on http://localhost:<your-port>.

## API Endpoints
POST /signup: Register a new user.
POST /login: Authenticate a user and retrieve their username.
PUT /update-password: Update a user's password.
PUT /update-score: Update a user's best score for a subject and difficulty level.
GET /users/:username: Get details of a user by username.
GET /users: Retrieve details of all users.