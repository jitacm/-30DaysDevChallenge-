import 'dart:async';
import 'package:flutter/material.dart';

class TimerProvider extends ChangeNotifier {
  int _seconds = 0;
  Timer? _timer;

  int get seconds => _seconds;

  void start() {
    _timer?.cancel();
    _timer = Timer.periodic(Duration(seconds: 1), (timer) {
      _seconds++;
      notifyListeners();
    });
  }

  void stop() {
    _timer?.cancel();
  }

  void reset() {
    _timer?.cancel();
    _seconds = 0;
    notifyListeners();
  }
}
