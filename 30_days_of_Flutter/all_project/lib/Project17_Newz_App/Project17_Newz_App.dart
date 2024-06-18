import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:provider/provider.dart';


class NewsApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => NewsProvider(),
      child: MaterialApp(
        title: 'News App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: NewsHomePage(),
      ),
    );
  }
}

class NewsHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final newsProvider = Provider.of<NewsProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: Text('News App'),
      ),
      body: Center(
        child: newsProvider.isLoading
            ? CircularProgressIndicator()
            : newsProvider.articles.isEmpty
            ? Text('No news available.')
            : ListView.builder(
          itemCount: newsProvider.articles.length,
          itemBuilder: (context, index) {
            final article = newsProvider.articles[index];
            return ListTile(
              title: Text(article.title),
              subtitle: Text(article.description ?? 'No description'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => NewsDetailPage(article: article),
                  ),
                );
              },
            );
          },
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: newsProvider.fetchNews,
        child: Icon(Icons.refresh),
      ),
    );
  }
}

class NewsDetailPage extends StatelessWidget {
  final Article article;

  NewsDetailPage({required this.article});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(article.title),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(article.title, style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold)),
            SizedBox(height: 8),
            Text(article.publishedAt, style: TextStyle(color: Colors.grey)),
            SizedBox(height: 16),
            Text(article.content ?? 'No content available'),
          ],
        ),
      ),
    );
  }
}

class NewsProvider extends ChangeNotifier {
  final String apiKey = 'YOUR_NEWSAPI_KEY_HERE';
  List<Article> articles = [];
  bool isLoading = false;

  NewsProvider() {
    fetchNews();
  }

  Future<void> fetchNews() async {
    isLoading = true;
    notifyListeners();

    final url = 'https://newsapi.org/v2/top-headlines?country=us&apiKey=$apiKey';
    final response = await http.get(Uri.parse(url));

    if (response.statusCode == 200) {
      final jsonData = json.decode(response.body);
      final List<dynamic> jsonArticles = jsonData['articles'];
      articles = jsonArticles.map((json) => Article.fromJson(json)).toList();
    } else {
      articles = [];
    }

    isLoading = false;
    notifyListeners();
  }
}

class Article {
  final String title;
  final String description;
  final String url;
  final String content;
  final String publishedAt;

  Article({
    required this.title,
    required this.description,
    required this.url,
    required this.content,
    required this.publishedAt,
  });

  factory Article.fromJson(Map<String, dynamic> json) {
    return Article(
      title: json['title'],
      description: json['description'],
      url: json['url'],
      content: json['content'],
      publishedAt: json['publishedAt'],
    );
  }
}
