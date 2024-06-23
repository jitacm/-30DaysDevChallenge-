import 'package:flutter/material.dart';
import 'package:pedometer/pedometer.dart';

class PedometerApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Pedometer App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: PedometerHomePage(),
    );
  }
}

class PedometerHomePage extends StatefulWidget {
  @override
  _PedometerHomePageState createState() => _PedometerHomePageState();
}

class _PedometerHomePageState extends State<PedometerHomePage> {
  late Stream<StepCount> _stepCountStream;
  int _steps = 0;

  @override
  void initState() {
    super.initState();
    _startListening();
  }

  void _startListening() {
    _stepCountStream = Pedometer.stepCountStream;
    _stepCountStream.listen(_onStepCount).onError(_onError);
  }

  void _onStepCount(StepCount event) {
    setState(() {
      _steps = event.steps;
    });
  }

  void _onError(error) {
    print('Pedometer Error: $error');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Pedometer App'),
      ),
      body: Center(
        child: Text(
          'Steps taken: $_steps',
          style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
        ),
      ),
    );
  }
}
