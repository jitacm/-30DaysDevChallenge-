import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'note_model.dart';
import 'note_provider.dart';
import 'package:uuid/uuid.dart';

class NoteEditScreen extends StatefulWidget {
  final Note? note;

  NoteEditScreen({this.note});

  @override
  _NoteEditScreenState createState() => _NoteEditScreenState();
}

class _NoteEditScreenState extends State<NoteEditScreen> {
  final _titleController = TextEditingController();
  final _contentController = TextEditingController();
  final _uuid = Uuid();

  @override
  void initState() {
    super.initState();
    if (widget.note != null) {
      _titleController.text = widget.note!.title;
      _contentController.text = widget.note!.content;
    }
  }

  void _saveNote() {
    if (_titleController.text.isEmpty || _contentController.text.isEmpty) {
      return;
    }

    Note note = Note(
      id: widget.note?.id ?? _uuid.v4(),
      title: _titleController.text,
      content: _contentController.text,
    );

    if (widget.note == null) {
      Provider.of<NoteProvider>(context, listen: false).addNote(note);
    } else {
      Provider.of<NoteProvider>(context, listen: false).updateNote(note);
    }

    Navigator.pop(context);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.note == null ? 'New Note' : 'Edit Note'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _titleController,
              decoration: InputDecoration(labelText: 'Title'),
            ),
            TextField(
              controller: _contentController,
              decoration: InputDecoration(labelText: 'Content'),
              maxLines: null,
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _saveNote,
              child: Text('Save Note'),
            ),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    _titleController.dispose();
    _contentController.dispose();
    super.dispose();
  }
}
