import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'timer_provider.dart';

class TimerPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Timer'),
      ),
      body: Center(
        child: Consumer<TimerProvider>(
          builder: (context, timerProvider, child) {
            return Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  '${timerProvider.seconds}s',
                  style: TextStyle(fontSize: 48),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                      onPressed: timerProvider.start,
                      child: Text('Start'),
                    ),
                    SizedBox(width: 10),
                    ElevatedButton(
                      onPressed: timerProvider.stop,
                      child: Text('Stop'),
                    ),
                    SizedBox(width: 10),
                    ElevatedButton(
                      onPressed: timerProvider.reset,
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
