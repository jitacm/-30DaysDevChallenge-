import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'movie_model.dart';

class MovieProvider with ChangeNotifier {
  static const String apiKey = 'YOUR_API_KEY_HERE';
  static const String baseUrl = 'https://api.themoviedb.org/3';

  List<Movie> _movies = [];

  List<Movie> get movies => _movies;

  Future<void> fetchMovies() async {
    final response = await http.get(Uri.parse('$baseUrl/movie/popular?api_key=$apiKey'));

    if (response.statusCode == 200) {
      final jsonData = json.decode(response.body);
      final List<Movie> loadedMovies = [];

      for (var item in jsonData['results']) {
        loadedMovies.add(Movie.fromJson(item));
      }

      _movies = loadedMovies;
      notifyListeners();
    } else {
      throw Exception('Failed to load movies');
    }
  }
}
