import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;


class WeatherApp extends StatefulWidget {
  @override
  _WeatherAppState createState() => _WeatherAppState();
}

class _WeatherAppState extends State<WeatherApp> {
  final String apiKey = 'YOUR_API_KEY'; // Add your OpenWeatherMap API key here
  String city = 'New York'; // Default city
  String description = '';
  double temperature = 0;
  String iconCode = '';

  Future<void> fetchWeather() async {
    final response = await http.get(Uri.parse(
        'https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey&units=metric'));
    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      setState(() {
        description = data['weather'][0]['description'];
        temperature = data['main']['temp'];
        iconCode = data['weather'][0]['icon'];
      });
    } else {
      throw Exception('Failed to load weather data');
    }
  }

  @override
  void initState() {
    super.initState();
    fetchWeather();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Weather App'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              if (iconCode.isNotEmpty)
                Image.network('https://openweathermap.org/img/wn/$iconCode.png'),
              Text(
                'City: $city',
                style: TextStyle(fontSize: 20),
              ),
              Text(
                'Temperature: ${temperature.toStringAsFixed(1)}°C',
                style: TextStyle(fontSize: 20),
              ),
              Text(
                'Description: $description',
                style: TextStyle(fontSize: 20),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
