Weather App

This Weather App allows users to check the weather conditions in any city. It fetches real-time weather data using the OpenWeatherMap API and displays the current temperature, weather conditions, humidity, and wind speed.

Features
Real-time Weather Data: Fetches and displays the latest weather information for any city.
Search Functionality: Users can search for the weather in different cities using a search bar.
Dynamic Background: The app changes the background image based on the city searched, using images from Unsplash.
Responsive Design: Optimized for both desktop and mobile views.
How to Use
Clone the repository to your local machine.
Open the index.html file in your browser.
Enter the name of a city in the search bar and click the search button or press "Enter" to see the weather details for that city.
Technologies Used
HTML: For the structure of the app.
CSS: For styling and layout.
JavaScript: For fetching data from the API and updating the DOM.
OpenWeatherMap API: For retrieving weather data.
Unsplash API: For dynamic background images.


To run the Weather App locally, you need to replace the apiKey in app.js with your own API key from OpenWeatherMap.

let weather = {
    apiKey: "YOUR_API_KEY", // Replace with your OpenWeatherMap API key
    // rest of the code
};

Use this api key if you don't have = '67b92f0af5416edbfe58458f502b0a31'