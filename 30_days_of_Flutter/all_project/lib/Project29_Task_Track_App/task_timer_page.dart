import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'task_timer_provider.dart';

class TaskTimerPage extends StatelessWidget {
  final TextEditingController _taskController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Task Timer'),
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              controller: _taskController,
              decoration: InputDecoration(
                labelText: 'Task Name',
                suffixIcon: IconButton(
                  icon: Icon(Icons.add),
                  onPressed: () {
                    if (_taskController.text.isNotEmpty) {
                      Provider.of<TaskTimerProvider>(context, listen: false)
                          .addTask(_taskController.text);
                      _taskController.clear();
                    }
                  },
                ),
              ),
            ),
          ),
          Expanded(
            child: Consumer<TaskTimerProvider>(
              builder: (context, taskTimerProvider, child) {
                return ListView.builder(
                  itemCount: taskTimerProvider.tasks.length,
                  itemBuilder: (context, index) {
                    final task = taskTimerProvider.tasks[index];
                    return ListTile(
                      title: Text(task.name),
                      subtitle: Text(task.formattedTime),
                      trailing: Row(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          IconButton(
                            icon: Icon(Icons.play_arrow),
                            onPressed: () {
                              taskTimerProvider.startTask(task);
                            },
                          ),
                          IconButton(
                            icon: Icon(Icons.stop),
                            onPressed: () {
                              taskTimerProvider.stopTask(task);
                            },
                          ),
                          IconButton(
                            icon: Icon(Icons.refresh),
                            onPressed: () {
                              taskTimerProvider.resetTask(task);
                            },
                          ),
                        ],
                      ),
                    );
                  },
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
