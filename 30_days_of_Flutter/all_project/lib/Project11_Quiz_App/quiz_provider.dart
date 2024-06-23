import 'package:all_project/Project11_Quiz_App/question.dart';
import 'package:flutter/material.dart';

class QuizProvider with ChangeNotifier {
  List<Question> _questions = [
    Question(
      questionText: 'What is the capital of France?',
      answers: ['Paris', 'London', 'Berlin', 'Madrid'],
      correctAnswerIndex: 0,
    ),
    Question(
      questionText: 'Who wrote "To Kill a Mockingbird"?',
      answers: ['Harper Lee', 'Mark Twain', 'Ernest Hemingway', 'F. Scott Fitzgerald'],
      correctAnswerIndex: 0,
    ),
    // Add more questions here
  ];

  int _currentQuestionIndex = 0;
  int _score = 0;

  List<Question> get questions => _questions;
  int get currentQuestionIndex => _currentQuestionIndex;
  int get score => _score;

  Question get currentQuestion => _questions[_currentQuestionIndex];

  void answerQuestion(int answerIndex) {
    if (answerIndex == currentQuestion.correctAnswerIndex) {
      _score++;
    }
    _currentQuestionIndex++;
    notifyListeners();
  }

  void resetQuiz() {
    _currentQuestionIndex = 0;
    _score = 0;
    notifyListeners();
  }
}
