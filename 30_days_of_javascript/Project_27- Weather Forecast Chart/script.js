// script.js
const apiKey = 'YOUR_API_KEY'; // Replace with your weather API key
const apiUrl = `https://api.weatherapi.com/v1/forecast.json?key=${apiKey}&q=New York&days=1&aqi=no&alerts=no`;

async function fetchWeatherData() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error('Failed to fetch weather data');
        }
        const data = await response.json();
        return data.forecast.forecastday[0].hour; // Assuming API returns hourly data for the first day
    } catch (error) {
        console.error('Error fetching weather data:', error);
    }
}

async function renderWeatherChart() {
    const weatherData = await fetchWeatherData();
    const hours = weatherData.map(entry => entry.time); // Assuming time is returned by API
    const temperatures = weatherData.map(entry => entry.temp_c); // Assuming temperature in Celsius

    const ctx = document.getElementById('weatherChart').getContext('2d');
    const chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: hours,
            datasets: [{
                label: 'Temperature (°C)',
                data: temperatures,
                fill: false,
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1
            }]
        },
        options: {
            scales: {
                x: {
                    title: {
                        display: true,
                        text: 'Time'
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Temperature (°C)'
                    }
                }
            }
        }
    });
}

renderWeatherChart();
