import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'task_timer_provider.dart';
import 'task_timer_page.dart';



class Tasktrack extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => TaskTimerProvider(),
      child: MaterialApp(
        title: 'Task Timer',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: TaskTimerPage(),
      ),
    );
  }
}
