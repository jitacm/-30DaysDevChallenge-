import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';
import 'package:intl/intl.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  runApp(CountdownApp());
}

class CountdownApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Countdown App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: CountdownHomePage(),
    );
  }
}

class CountdownHomePage extends StatefulWidget {
  @override
  _CountdownHomePageState createState() => _CountdownHomePageState();
}

class _CountdownHomePageState extends State<CountdownHomePage> {
  final FlutterLocalNotificationsPlugin flutterLocalNotificationsPlugin =
  FlutterLocalNotificationsPlugin();
  List<Event> events = [];
  final TextEditingController _eventController = TextEditingController();
  DateTime? _selectedDate;

  @override
  void initState() {
    super.initState();
    _initializeNotifications();
    _loadEvents();
  }

  void _initializeNotifications() async {
    final InitializationSettings initializationSettings =
    const InitializationSettings(
        android: AndroidInitializationSettings('@mipmap/ic_launcher'),
        iOS: IOSInitializationSettings());
    await flutterLocalNotificationsPlugin.initialize(initializationSettings);
  }

  void _scheduleNotification(Event event) async {
    final scheduledDate = event.dateTime.subtract(const Duration(minutes: 1));
    final androidPlatformChannelSpecifics = const AndroidNotificationDetails(
      'countdown_channel',
      'Countdown Notifications',
      // 'Notifications for countdown events',
      importance: Importance.max,
      priority: Priority.high,
    );
    final iOSPlatformChannelSpecifics = const IOSNotificationDetails();
    final platformChannelSpecifics = NotificationDetails(
      android: androidPlatformChannelSpecifics,
      iOS: iOSPlatformChannelSpecifics,
    );
    await flutterLocalNotificationsPlugin.schedule(
      event.id,
      'Event Reminder',
      '${event.name} is starting soon!',
      scheduledDate,
      platformChannelSpecifics,
    );
  }

  void _loadEvents() async {
    final prefs = await SharedPreferences.getInstance();
    final eventList = prefs.getStringList('events') ?? [];
    setState(() {
      events = eventList
          .map((e) => Event.fromJson(e))
          .toList();
    });
  }

  void _saveEvents() async {
    final prefs = await SharedPreferences.getInstance();
    final eventList = events.map((e) => e.toJson()).toList();
    prefs.setStringList('events', eventList);
  }

  void _addEvent(String name, DateTime dateTime) {
    final event = Event(
      id: DateTime.now().millisecondsSinceEpoch,
      name: name,
      dateTime: dateTime,
    );
    setState(() {
      events.add(event);
      events.sort((a, b) => a.dateTime.compareTo(b.dateTime));
    });
    _saveEvents();
    _scheduleNotification(event);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Countdown App'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _eventController,
              decoration: const InputDecoration(labelText: 'Event Name'),
            ),
            Row(
              children: [
                Expanded(
                  child: Text(_selectedDate == null
                      ? 'No date selected!'
                      : DateFormat.yMMMd().format(_selectedDate!)),
                ),
                TextButton(
                  onPressed: () async {
                    DateTime? pickedDate = await showDatePicker(
                      context: context,
                      initialDate: DateTime.now(),
                      firstDate: DateTime.now(),
                      lastDate: DateTime(2100),
                    );
                    if (pickedDate != null) {
                      setState(() {
                        _selectedDate = pickedDate;
                      });
                    }
                  },
                  child: const Text('Select Date'),
                ),
              ],
            ),
            ElevatedButton(
              onPressed: () {
                if (_eventController.text.isNotEmpty && _selectedDate != null) {
                  _addEvent(_eventController.text, _selectedDate!);
                  _eventController.clear();
                  _selectedDate = null;
                }
              },
              child: const Text('Add Event'),
            ),
            Expanded(
              child: ListView.builder(
                itemCount: events.length,
                itemBuilder: (context, index) {
                  final event = events[index];
                  return ListTile(
                    title: Text(event.name),
                    subtitle: Text(DateFormat.yMMMd().format(event.dateTime)),
                    trailing: CountdownTimer(endTime: event.dateTime),
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class Event {
  final int id;
  final String name;
  final DateTime dateTime;

  Event({required this.id, required this.name, required this.dateTime});

  factory Event.fromJson(String json) {
    final data = json.split('|');
    return Event(
      id: int.parse(data[0]),
      name: data[1],
      dateTime: DateTime.parse(data[2]),
    );
  }

  String toJson() {
    return '$id|$name|$dateTime';
  }
}

class CountdownTimer extends StatefulWidget {
  final DateTime endTime;

  CountdownTimer({required this.endTime});

  @override
  _CountdownTimerState createState() => _CountdownTimerState();
}

class _CountdownTimerState extends State<CountdownTimer> {
  late Duration _remainingTime;
  late Timer _timer;

  @override
  void initState() {
    super.initState();
    _updateRemainingTime();
    _timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      _updateRemainingTime();
    });
  }

  void _updateRemainingTime() {
    setState(() {
      _remainingTime = widget.endTime.difference(DateTime.now());
    });
  }

  @override
  void dispose() {
    _timer.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    if (_remainingTime.isNegative) {
      return const Text('Event started!');
    } else {
      return Text(
        '${_remainingTime.inDays}d ${_remainingTime.inHours % 24}h ${_remainingTime.inMinutes % 60}m ${_remainingTime.inSeconds % 60}s',
      );
    }
  }
}
