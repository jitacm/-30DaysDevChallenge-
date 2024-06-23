import 'dart:async';
import 'package:flutter/material.dart';

class Task {
  String name;
  int seconds;
  Timer? timer;

  Task(this.name) : seconds = 0;

  String get formattedTime {
    final minutes = (seconds ~/ 60).toString().padLeft(2, '0');
    final secs = (seconds % 60).toString().padLeft(2, '0');
    return '$minutes:$secs';
  }
}

class TaskTimerProvider extends ChangeNotifier {
  List<Task> _tasks = [];

  List<Task> get tasks => _tasks;

  void addTask(String name) {
    _tasks.add(Task(name));
    notifyListeners();
  }

  void startTask(Task task) {
    task.timer?.cancel();
    task.timer = Timer.periodic(Duration(seconds: 1), (timer) {
      task.seconds++;
      notifyListeners();
    });
  }

  void stopTask(Task task) {
    task.timer?.cancel();
  }

  void resetTask(Task task) {
    task.timer?.cancel();
    task.seconds = 0;
    notifyListeners();
  }
}
