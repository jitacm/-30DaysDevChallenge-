import 'package:all_project/Project11_Quiz_App/quiz_provider.dart';
import 'package:all_project/Project11_Quiz_App/quiz_screen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';




class Quiz extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => QuizProvider(),
      child: MaterialApp(
        title: 'Quiz App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: QuizScreen(),
      ),
    );
  }
}
