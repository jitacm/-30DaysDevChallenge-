import 'package:all_project/Project12_Teanslator_App/translation_service.dart';
import 'package:flutter/material.dart';

class TranslationProvider with ChangeNotifier {
  String _translatedText = '';

  String get translatedText => _translatedText;

  Future<void> translateText(String text, String targetLanguage) async {
    try {
      _translatedText = await TranslationService.translate(text, targetLanguage);
      notifyListeners();
    } catch (e) {
      _translatedText = 'Error: ${e.toString()}';
      notifyListeners();
    }
  }
}
