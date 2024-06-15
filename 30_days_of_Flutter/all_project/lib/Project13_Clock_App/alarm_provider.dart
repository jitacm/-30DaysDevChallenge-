import 'package:all_project/Project13_Clock_App/notification_service.dart';
import 'package:flutter/material.dart';

class AlarmProvider with ChangeNotifier {
  List<Alarm> _alarms = [];

  List<Alarm> get alarms => _alarms;

  void addAlarm(DateTime alarmTime, String alarmTitle) {
    final alarm = Alarm(alarmTime: alarmTime, alarmTitle: alarmTitle);
    _alarms.add(alarm);
    NotificationService.scheduleNotification(alarmTime, alarmTitle);
    notifyListeners();
  }

  void removeAlarm(Alarm alarm) {
    _alarms.remove(alarm);
    notifyListeners();
  }
}

class Alarm {
  final DateTime alarmTime;
  final String alarmTitle;

  Alarm({required this.alarmTime, required this.alarmTitle});
}
