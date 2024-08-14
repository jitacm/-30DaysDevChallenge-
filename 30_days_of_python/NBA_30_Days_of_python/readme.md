"National Basketball Association" Leaderboard Application
This Python application interacts with the "National Basketball Association" (NBA) API to fetch and display real-time game scores and team statistics. It uses the requests library to make HTTP requests and processes JSON data from the NBA's online resources.

**Features**
Scoreboard Fetching: Retrieves the current scoreboard, showing live NBA game scores, periods, and clock status.
Team Stats Leaderboard: Displays the league's top teams based on points per game (PPG), along with rankings and additional stats.

**Requirements**
Python 3.x
Requests library: You can install it using pip install requests
PrettyPrinter (for clean output formatting)

**Customizing API Requests**
To fetch specific data, you can modify the API request URL to include parameters such as year, language, and season. This allows you to retrieve information based on different seasons, languages, and specific years. Adjust these parameters in the URL according to your needs:

Base URL: https://cdn.nba.com
Scoreboard Endpoint: /static/json/liveData/odds/odds_todaysGames.json
Team Stats Endpoint: /static/json/liveData/odds/odds_todaysGames.json