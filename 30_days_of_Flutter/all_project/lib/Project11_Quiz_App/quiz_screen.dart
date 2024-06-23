import 'package:all_project/Project11_Quiz_App/quiz_provider.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class QuizScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final quizProvider = Provider.of<QuizProvider>(context);

    if (quizProvider.currentQuestionIndex >= quizProvider.questions.length) {
      return Scaffold(
        appBar: AppBar(
          title: Text('Quiz Completed'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                'Your Score: ${quizProvider.score}',
                style: TextStyle(fontSize: 24),
              ),
              SizedBox(height: 20),
              ElevatedButton(
                child: Text('Restart Quiz'),
                onPressed: () {
                  quizProvider.resetQuiz();
                },
              ),
            ],
          ),
        ),
      );
    }

    final question = quizProvider.currentQuestion;

    return Scaffold(
      appBar: AppBar(
        title: Text('Quiz App'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Text(
              question.questionText,
              style: TextStyle(fontSize: 24),
            ),
            SizedBox(height: 20),
            ...question.answers.asMap().entries.map((entry) {
              final answerIndex = entry.key;
              final answerText = entry.value;
              return ElevatedButton(
                child: Text(answerText),
                onPressed: () {
                  quizProvider.answerQuestion(answerIndex);
                },
              );
            }).toList(),
          ],
        ),
      ),
    );
  }
}
