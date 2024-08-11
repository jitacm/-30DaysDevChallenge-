document.getElementById("getWeather").addEventListener("click", function () {
  const city = document.getElementById("city").value;
  const apiKey = "YOUR_API_KEY"; // Replace with your OpenWeatherMap API key
  const url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;

  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      const weatherResult = document.getElementById("weatherResult");
      if (data.cod === 200) {
        const sunrise = new Date(data.sys.sunrise * 1000).toLocaleTimeString();
        const sunset = new Date(data.sys.sunset * 1000).toLocaleTimeString();
        weatherResult.innerHTML = `
              <h2>${data.name}, ${data.sys.country}</h2>
              <p>Temperature: ${data.main.temp}°C</p>
              <p>Feels like: ${data.main.feels_like}°C</p>
              <p>Min temp: ${data.main.temp_min}°C</p>
              <p>Max temp: ${data.main.temp_max}°C</p>
              <p>Humidity: ${data.main.humidity}%</p>
              <p>Pressure: ${data.main.pressure} hPa</p>
              <p>Weather: ${data.weather[0].main} - ${data.weather[0].description}</p>
              <p>Wind: ${data.wind.speed} m/s, Direction: ${data.wind.deg}°</p>
              <p>Cloudiness: ${data.clouds.all}%</p>
              <p>Sunrise: ${sunrise}</p>
              <p>Sunset: ${sunset}</p>
            `;
      } else {
        weatherResult.innerHTML = `<p>${data.message}</p>`;
      }
    })
    .catch((error) => {
      console.error("Error fetching weather data:", error);
      const weatherResult = document.getElementById("weatherResult");
      weatherResult.innerHTML = `<p>Error fetching weather data. Please try again.</p>`;
    });
});
