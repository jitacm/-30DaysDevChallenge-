import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'note_provider.dart';
import 'note_list_screen.dart';


class NoteTakingApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => NoteProvider(),
      child: MaterialApp(
        title: 'Note Taking App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: NoteListScreen(),
      ),
    );
  }
}
