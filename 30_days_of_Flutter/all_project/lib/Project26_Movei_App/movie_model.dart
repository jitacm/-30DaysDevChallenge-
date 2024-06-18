class Movie {
  final String title;
  final String posterUrl;
  final String overview;
  final double rating;

  Movie({
    required this.title,
    required this.posterUrl,
    required this.overview,
    required this.rating,
  });

  factory Movie.fromJson(Map<String, dynamic> json) {
    return Movie(
      title: json['title'],
      posterUrl: 'https://image.tmdb.org/t/p/w500${json['poster_path']}',
      overview: json['overview'],
      rating: (json['vote_average'] as num).toDouble(),
    );
  }
}
