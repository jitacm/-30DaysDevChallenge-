import requests
from urllib3.exceptions import InsecureRequestWarning
from pprint import PrettyPrinter

# Suppress only the single InsecureRequestWarning from urllib3
requests.packages.urllib3.disable_warnings(InsecureRequestWarning)

BASE_URL = "https://cdn.nba.com"
ALL_JSON = "/static/json/liveData/odds/odds_todaysGames.json"

printer = PrettyPrinter()

def get_links():
    url = BASE_URL + ALL_JSON
    print(f"Fetching URL: {url}")
    try:
        response = requests.get(url, verify=False)
        print(f"Response Status Code: {response.status_code}")
        print(f"Response Content: {response.text}")
        response.raise_for_status()
        data = response.json()
        return data.get('links', {})
    except requests.exceptions.HTTPError as e:
        print(f"HTTP Error occurred: {e.response.status_code} - {e.response.text}")
        return None
    except requests.exceptions.RequestException as e:
        print(f"An error occurred: {e}")
        return None

def get_scoreboard():
    links = get_links()
    if not links:
        return

    scoreboard = links.get('currentScoreboard')
    if not scoreboard:
        print("Scoreboard data not found")
        return

    url = BASE_URL + scoreboard
    print(f"Fetching URL: {url}")
    try:
        response = requests.get(url, verify=False)
        print(f"Response Status Code: {response.status_code}")
        print(f"Response Content: {response.text}")
        games = response.json().get('games', [])

        for game in games:
            home_team = game['hTeam']
            away_team = game['vTeam']
            clock = game['clock']
            period = game['period']

            print("------------------------------------------")
            print(f"{home_team['triCode']} vs {away_team['triCode']}")
            print(f"{home_team['score']} - {away_team['score']}")
            print(f"{clock} - {period['current']}")
    except requests.exceptions.RequestException as e:
        print(f"An error occurred while fetching scoreboard data: {e}")

def get_stats():
    links = get_links()
    if not links:
        return

    stats = links.get('leagueTeamStatsLeaders')
    if not stats:
        print("Stats data not found")
        return

    url = BASE_URL + stats
    print(f"Fetching URL: {url}")
    try:
        response = requests.get(url, verify=False)
        print(f"Response Status Code: {response.status_code}")
        print(f"Response Content: {response.text}")
        teams = response.json().get('league', {}).get('standard', {}).get('regularSeason', {}).get('teams', [])

        teams = list(filter(lambda x: x['name'] != "Team", teams))
        teams.sort(key=lambda x: int(x['ppg']['rank']))

        for i, team in enumerate(teams):
            name = team['name']
            nickname = team['nickname']
            ppg = team['ppg']['avg']
            print(f"{i + 1}. {name} - {nickname} - {ppg}")
    except requests.exceptions.RequestException as e:
        print(f"An error occurred while fetching stats data: {e}")

# Example usage
get_scoreboard()
get_stats()
