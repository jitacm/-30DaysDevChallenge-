import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'note_model.dart';
import 'note_provider.dart';
import 'note_edit_screen.dart';

class NoteListScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Notes'),
      ),
      body: Consumer<NoteProvider>(
        builder: (context, noteProvider, child) {
          return ListView.builder(
            itemCount: noteProvider.notes.length,
            itemBuilder: (context, index) {
              Note note = noteProvider.notes[index];
              return ListTile(
                title: Text(note.title),
                subtitle: Text(note.content),
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => NoteEditScreen(note: note),
                    ),
                  );
                },
                trailing: IconButton(
                  icon: Icon(Icons.delete),
                  onPressed: () {
                    noteProvider.deleteNote(note.id);
                  },
                ),
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => NoteEditScreen(),
            ),
          );
        },
        child: Icon(Icons.add),
      ),
    );
  }
}
