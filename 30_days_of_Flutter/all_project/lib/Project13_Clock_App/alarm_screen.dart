// import 'package:flutter/material.dart';
// import 'package:provider/provider.dart';
// import 'package:alarm_clock/providers/alarm_provider.dart';
//
// class AlarmScreen extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     final alarmProvider = Provider.of<AlarmProvider>(context);
//
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('Alarm Clock'),
//       ),
//       body: Column(
//         children: [
//           Expanded(
//             child: ListView.builder(
//               itemCount: alarmProvider.alarms.length,
//               itemBuilder: (context, index) {
//                 final alarm = alarmProvider.alarms[index];
//                 return ListTile(
//                   title: Text('${alarm.alarmTitle}'),
//                   subtitle: Text('${alarm.alarmTime}'),
//                   trailing: IconButton(
//                     icon: Icon(Icons.delete),
//                     onPressed: () {
//                       alarmProvider.removeAlarm(alarm);
//                     },
//                   ),
//                 );
//               },
//             ),
//           ),
//           Padding(
//             padding: const EdgeInsets.all(8.0),
//             child: ElevatedButton(
//               onPressed: () async {
//                 final TimeOfDay? pickedTime = await showTimePicker(
//                   context: context,
//                   initialTime: TimeOfDay.now(),
//                 );
//                 if (pickedTime != null) {
//                   final now = DateTime.now();
//                   final alarmTime = DateTime(
//                     now.year,
//                     now.month,
//                     now.day,
//                     pickedTime.hour,
//                     pickedTime.minute,
//                   );
//                   showDialog(
//                     context: context,
//                     builder: (context) {
//                       final titleController = TextEditingController();
//                       return AlertDialog(
//                         title: Text('Set Alarm Title'),
//                         content: TextField(
//                           controller: titleController,
//                           decoration: InputDecoration(
//                             labelText: 'Alarm Title',
//                           ),
//                         ),
//                         actions: [
//                           TextButton(
//                             onPressed: () {
//                               alarmProvider.addAlarm(
//                                 alarmTime,
//                                 titleController.text,
//                               );
//                               Navigator.of(context).pop();
//                             },
//                             child: Text('Save'),
//                           ),
//                         ],
//                       );
//                     },
//                   );
//                 }
//               },
//               child: Text('Add Alarm'),
//             ),
//           ),
//         ],
//       ),
//     );
//   }
// }
