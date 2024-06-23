import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'stopwatch_provider.dart';

class StopwatchPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Stopwatch'),
      ),
      body: Center(
        child: Consumer<StopwatchProvider>(
          builder: (context, stopwatchProvider, child) {
            return Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  stopwatchProvider.elapsedTime,
                  style: TextStyle(fontSize: 48),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                      onPressed: stopwatchProvider.start,
                      child: Text('Start'),
                    ),
                    SizedBox(width: 10),
                    ElevatedButton(
                      onPressed: stopwatchProvider.stop,
                      child: Text('Stop'),
                    ),
                    SizedBox(width: 10),
                    ElevatedButton(
                      onPressed: stopwatchProvider.reset,
                      child: Text('Reset'),
                    ),
                  ],
                ),
              ],
            );
          },
        ),
      ),
    );
  }
}
