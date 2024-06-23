import 'dart:async';
import 'package:flutter/material.dart';

class StopwatchProvider extends ChangeNotifier {
  Stopwatch _stopwatch = Stopwatch();
  Timer? _timer;

  String get elapsedTime {
    final duration = _stopwatch.elapsed;
    return duration.toString().split('.').first.padLeft(8, "0");
  }

  void start() {
    if (!_stopwatch.isRunning) {
      _stopwatch.start();
      _timer = Timer.periodic(Duration(milliseconds: 30), (timer) {
        notifyListeners();
      });
    }
  }

  void stop() {
    _stopwatch.stop();
    _timer?.cancel();
  }

  void reset() {
    _stopwatch.reset();
    notifyListeners();
  }
}
