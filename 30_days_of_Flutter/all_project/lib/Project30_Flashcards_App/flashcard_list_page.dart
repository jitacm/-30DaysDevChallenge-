import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'flashcard_provider.dart';
import 'flashcard_view_page.dart';

class FlashcardListPage extends StatelessWidget {
  final TextEditingController _questionController = TextEditingController();
  final TextEditingController _answerController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flashcards'),
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              controller: _questionController,
              decoration: InputDecoration(
                labelText: 'Question',
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: TextField(
              controller: _answerController,
              decoration: InputDecoration(
                labelText: 'Answer',
              ),
            ),
          ),
          ElevatedButton(
            onPressed: () {
              if (_questionController.text.isNotEmpty &&
                  _answerController.text.isNotEmpty) {
                Provider.of<FlashcardProvider>(context, listen: false).addFlashcard(
                  _questionController.text,
                  _answerController.text,
                );
                _questionController.clear();
                _answerController.clear();
              }
            },
            child: Text('Add Flashcard'),
          ),
          Expanded(
            child: Consumer<FlashcardProvider>(
              builder: (context, flashcardProvider, child) {
                return ListView.builder(
                  itemCount: flashcardProvider.flashcards.length,
                  itemBuilder: (context, index) {
                    final flashcard = flashcardProvider.flashcards[index];
                    return ListTile(
                      title: Text(flashcard.question),
                      trailing: IconButton(
                        icon: Icon(Icons.delete),
                        onPressed: () {
                          flashcardProvider.removeFlashcard(flashcard);
                        },
                      ),
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => FlashcardViewPage(flashcard: flashcard),
                          ),
                        );
                      },
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
