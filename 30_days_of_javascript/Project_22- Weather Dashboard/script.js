// script.js
const apiKey = 'YOUR_API_KEY'; // Replace with your OpenWeatherMap API key

async function getWeather() {
    const city = document.getElementById('city-input').value;
    if (city === '') return;

    const currentWeatherUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;
    const forecastUrl = `https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${apiKey}&units=metric`;

    const currentWeatherResponse = await fetch(currentWeatherUrl);
    const currentWeatherData = await currentWeatherResponse.json();
    displayCurrentWeather(currentWeatherData);

    const forecastResponse = await fetch(forecastUrl);
    const forecastData = await forecastResponse.json();
    displayForecast(forecastData);
}

function displayCurrentWeather(data) {
    const currentWeatherDiv = document.getElementById('current-weather');
    currentWeatherDiv.innerHTML = `
        <h2>Current Weather in ${data.name}</h2>
        <div class="weather-card">
            <img src="http://openweathermap.org/img/wn/${data.weather[0].icon}.png" alt="${data.weather[0].description}">
            <div>
                <h3>${data.weather[0].description}</h3>
                <p>Temperature: ${data.main.temp}°C</p>
                <p>Humidity: ${data.main.humidity}%</p>
                <p>Wind: ${data.wind.speed} m/s</p>
            </div>
        </div>
    `;
}

function displayForecast(data) {
    const forecastDiv = document.getElementById('forecast');
    forecastDiv.innerHTML = '<h2>5-Day Forecast</h2>';

    data.list.forEach((item, index) => {
        if (index % 8 === 0) { // Display one forecast per day (8 * 3-hour intervals = 24 hours)
            forecastDiv.innerHTML += `
                <div class="weather-card">
                    <img src="http://openweathermap.org/img/wn/${item.weather[0].icon}.png" alt="${item.weather[0].description}">
                    <div>
                        <h3>${new Date(item.dt_txt).toLocaleDateString()}</h3>
                        <p>${item.weather[0].description}</p>
                        <p>Temperature: ${item.main.temp}°C</p>
                        <p>Humidity: ${item.main.humidity}%</p>
                    </div>
                </div>
            `;
        }
    });
}
