let weather = {
  apiKey: "",
  fetchWeather: function (city) {
      fetch(
          "https://api.openweathermap.org/data/2.5/weather?q=" +
          city +
          "&units=metric&appid=" +
          this.apiKey
      )
          .then((response) => {
              if (!response.ok) {
                  alert("No weather found.");
                  throw new Error("No weather found.");
              }
              return response.json();
          })
          .then((data) => this.displayWeather(data));
  },
  displayWeather: function (data) {
      const { name } = data;
      const { icon, description } = data.weather[0];
      const { temp, humidity } = data.main;
      const { speed } = data.wind;
      document.querySelector(".city").innerText = "Weather in " + name;
      document.querySelector(".icon").src =
          "https://openweathermap.org/img/wn/" + icon + ".png";
      document.querySelector(".description").innerText = description;
      document.querySelector(".temp").innerText = temp + "°C";
      document.querySelector(".humidity").innerText =
          "Humidity: " + humidity + "%";
      document.querySelector(".wind").innerText =
          "Wind speed: " + speed + " km/h";
      document.querySelector(".weather").classList.remove("loading");

      // Set background image based on weather description
      let backgroundImage = '';
      switch (description.toLowerCase()) {
          case 'clear sky':
              backgroundImage = "url('https://plus.unsplash.com/premium_photo-1670527200668-ad4b53a1aefb?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8Y2xlYXIlMjBza3l8ZW58MHx8MHx8fDA%3D')";
              break;
          case 'few clouds':
              backgroundImage = "url('https://plus.unsplash.com/premium_photo-1661897016268-b77ad5186d02?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8ZmV3JTIwY2xvdWRzfGVufDB8fDB8fHww')";
              break;
          case 'scattered clouds':
              backgroundImage = "url('https://images.unsplash.com/photo-1646069816777-7e8a69b0596f?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fHNjYXR0ZXJlZCUyMGNsb3Vkc3xlbnwwfHwwfHx8MA%3D%3D')";
              break;
          case 'rain':
              backgroundImage = "url('https://images.unsplash.com/photo-1501691223387-dd0500403074?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cmFpbnxlbnwwfHwwfHx8MA%3D%3D')";
              break;
          case 'snow':
              backgroundImage = "url('https://images.unsplash.com/photo-1486944670663-e0a2ea5018eb?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fHNub3d8ZW58MHx8MHx8fDA%3D')";
              break;
          default:
              backgroundImage = "url('https://plus.unsplash.com/premium_photo-1670527200668-ad4b53a1aefb?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8Y2xlYXIlMjBza3l8ZW58MHx8MHx8fDA%3D')";
      
      }

      document.body.style.backgroundImage = backgroundImage;
  },
  search: function () {
      this.fetchWeather(document.querySelector(".search-bar").value);
  },
};

document.querySelector(".search button").addEventListener("click", function () {
  weather.search();
});

document
  .querySelector(".search-bar")
  .addEventListener("keyup", function (event) {
      if (event.key == "Enter") {
          weather.search();
      }
  });

weather.fetchWeather("Nagpur");
