import 'package:flutter/material.dart';

class Flashcard {
  String question;
  String answer;

  Flashcard(this.question, this.answer);
}

class FlashcardProvider extends ChangeNotifier {
  List<Flashcard> _flashcards = [];

  List<Flashcard> get flashcards => _flashcards;

  void addFlashcard(String question, String answer) {
    _flashcards.add(Flashcard(question, answer));
    notifyListeners();
  }

  void removeFlashcard(Flashcard flashcard) {
    _flashcards.remove(flashcard);
    notifyListeners();
  }
}
