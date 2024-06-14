import 'dart:convert';
import 'package:http/http.dart' as http;

class TranslationService {
  static const String _baseUrl = 'https://api-free.deepl.com/v2/translate';
  static const String _apiKey = 'YOUR_DEEPL_API_KEY';

  static Future<String> translate(String text, String targetLanguage) async {
    final response = await http.post(
      Uri.parse(_baseUrl),
      headers: {
        'Authorization': 'DeepL-Auth-Key $_apiKey',
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: {
        'text': text,
        'target_lang': targetLanguage,
      },
    );

    if (response.statusCode == 200) {
      final jsonResponse = jsonDecode(response.body);
      final translatedText = jsonResponse['translations'][0]['text'];
      return translatedText;
    } else {
      throw Exception('Failed to load translation');
    }
  }
}
