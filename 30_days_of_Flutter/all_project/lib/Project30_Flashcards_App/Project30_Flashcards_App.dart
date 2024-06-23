import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'flashcard_provider.dart';
import 'flashcard_list_page.dart';



class Flashcards extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => FlashcardProvider(),
      child: MaterialApp(
        title: 'Flashcards App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: FlashcardListPage(),
      ),
    );
  }
}
