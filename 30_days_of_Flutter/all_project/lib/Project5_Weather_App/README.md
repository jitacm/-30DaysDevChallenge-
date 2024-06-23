# Weather App

A simple weather application built using Flutter that displays current weather information for a specified location.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This weather app fetches and displays the current weather information for a specified location. It uses the OpenWeatherMap API to get the latest weather data.

## Features

- Display current temperature, humidity, and weather conditions
- Fetch weather data for a specified city
- User-friendly interface

## Prerequisites

- [Flutter](https://flutter.dev/docs/get-started/install) installed on your machine
- OpenWeatherMap API key (you can get one for free by signing up on [OpenWeatherMap](https://openweathermap.org/api))

## Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/yourusername/weather_app.git
    cd weather_app
    ```

2. Install the dependencies:

    ```sh
    flutter pub get
    ```

3. Create a `.env` file in the root directory and add your OpenWeatherMap API key:

    ```sh
    API_KEY=your_openweather_api_key
    ```

## Usage

1. Run the app:

    ```sh
    flutter run
    ```

2. Enter a city name to fetch and display the current weather information.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements, bug fixes, or features.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
