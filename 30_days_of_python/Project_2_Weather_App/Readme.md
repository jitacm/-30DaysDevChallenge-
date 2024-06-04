
# Weather App
A simple Python application that fetches and displays weather information for a given city using the OpenWeatherMap API.

## Features
Fetches current weather data for a specified city
Displays temperature, pressure, humidity, and weather description

## Prerequisites
Python 3.x
requests library


## Installation

1. Clone the repository (if applicable):
```bash
git clone https://github.com/yourusername/weather-app.git
cd weather-app
```
2. Install the requests library:
```bash
pip install requests
```

3. Get your OpenWeatherMap API key:
  - Sign up at OpenWeatherMap
  - Navigate to the API keys section and copy your API key


### Usage

1. Replace YOUR_API_KEY with your actual API key:
Open `weather_app.py` in a text editor and replace `YOUR_API_KEY` with your OpenWeatherMap API key.

```bash
api_key = "YOUR_API_KEY"
```

2. Run the application:

```bash
python weather_app.py
```

3. Enter a city name:

  - The application will prompt you to enter a city name.
  - After entering the city name, the current weather information will be displayed.

## Example

```bash
Enter city name: London
City: London
Temperature: 15°C
Pressure: 1012 hPa
Humidity: 82%
Weather Description: Clear sky

```
