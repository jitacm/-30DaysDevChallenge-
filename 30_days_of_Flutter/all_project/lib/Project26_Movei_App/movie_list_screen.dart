import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'movie_model.dart';
import 'movie_provider.dart';

class MovieListScreen extends StatefulWidget {
  @override
  _MovieListScreenState createState() => _MovieListScreenState();
}

class _MovieListScreenState extends State<MovieListScreen> {
  final TextEditingController _searchController = TextEditingController();

  @override
  void initState() {
    super.initState();
    Provider.of<MovieProvider>(context, listen: false).fetchMovies();
  }

  @override
  Widget build(BuildContext context) {
    final movieProvider = Provider.of<MovieProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: Text('Movie Database'),
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              controller: _searchController,
              decoration: InputDecoration(
                hintText: 'Search movies...',
                prefixIcon: Icon(Icons.search),
              ),
              onChanged: (value) {
                // Implement search functionality
              },
            ),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: movieProvider.movies.length,
              itemBuilder: (context, index) {
                Movie movie = movieProvider.movies[index];
                return ListTile(
                  leading: Image.network(movie.posterUrl),
                  title: Text(movie.title),
                  subtitle: Text(movie.overview),
                  trailing: Text('${movie.rating}'),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
