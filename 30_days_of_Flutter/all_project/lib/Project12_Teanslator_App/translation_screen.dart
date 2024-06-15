import 'package:all_project/Project12_Teanslator_App/translation_provider.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class TranslationScreen extends StatefulWidget {
  @override
  _TranslationScreenState createState() => _TranslationScreenState();
}

class _TranslationScreenState extends State<TranslationScreen> {
  final _textController = TextEditingController();
  String _targetLanguage = 'ES'; // Default target language is Spanish

  @override
  Widget build(BuildContext context) {
    final translationProvider = Provider.of<TranslationProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: Text('Language Translator'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            TextField(
              controller: _textController,
              decoration: InputDecoration(
                labelText: 'Enter text to translate',
              ),
            ),
            DropdownButton<String>(
              value: _targetLanguage,
              onChanged: (String? newValue) {
                setState(() {
                  _targetLanguage = newValue!;
                });
              },
              items: <String>['EN', 'DE', 'FR', 'ES', 'IT', 'NL', 'PL', 'RU']
                  .map<DropdownMenuItem<String>>((String value) {
                return DropdownMenuItem<String>(
                  value: value,
                  child: Text(value),
                );
              }).toList(),
            ),
            ElevatedButton(
              onPressed: () {
                translationProvider.translateText(
                  _textController.text,
                  _targetLanguage,
                );
              },
              child: Text('Translate'),
            ),
            SizedBox(height: 20),
            Text(
              'Translated Text:',
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 10),
            Text(
              translationProvider.translatedText,
              style: TextStyle(fontSize: 18),
            ),
          ],
        ),
      ),
    );
  }
}
